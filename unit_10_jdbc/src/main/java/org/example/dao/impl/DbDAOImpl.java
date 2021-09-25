package org.example.dao.impl;

import org.example.dao.DbDAO;
import org.example.entity.LocationEntity;
import org.example.entity.ProblemEntity;
import org.example.entity.RouteEntity;
import org.example.entity.SolutionEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DbDAOImpl implements DbDAO {

    private final Connection connection;

    public DbDAOImpl() {
        Properties properties = loadProperties();
        String url = properties.getProperty("url");
        String user = properties.getProperty("username");
        String pass = properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties loadProperties() {

        Properties properties = new Properties();

        try (InputStream input = DbDAOImpl.class.getResourceAsStream("/application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return properties;
    }

    public List<ProblemEntity> getUnsolvedProblems() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM problems a LEFT JOIN solutions b ON a.id = b.problem_id WHERE b.problem_id IS NULL")) {
            List<ProblemEntity> problems = new ArrayList<>();
            ProblemEntity problem;
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                problem = new ProblemEntity();
                problem.setId(resultSet.getInt("id"));
                problem.setFrom_id(resultSet.getInt("from_id"));
                problem.setTo_id(resultSet.getInt("to_id"));
                problems.add(problem);
            }
            return problems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LocationEntity getLocationById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM locations WHERE id LIKE ?")) {
            LocationEntity location = new LocationEntity();
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                location.setId(resultSet.getInt("id"));
                location.setName(resultSet.getString("name"));
            }
            return location;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LocationEntity> readAllLocations() {
        try (Statement statement = connection.createStatement()) {
            List<LocationEntity> locations = new ArrayList<>();
            LocationEntity location;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM locations");
            while (resultSet.next()) {
                location = new LocationEntity();
                location.setId(resultSet.getInt("id"));
                location.setName(resultSet.getString("name"));

                locations.add(location);
            }
            return locations;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RouteEntity> readAllRouts() {
        try (Statement statement = connection.createStatement()) {
            List<RouteEntity> routes = new ArrayList<>();
            RouteEntity route;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM routes");
            while (resultSet.next()) {
                route = new RouteEntity();
                route.setId(resultSet.getInt("id"));
                route.setFrom_id(resultSet.getInt("from_id"));
                route.setTo_id(resultSet.getInt("to_id"));
                route.setCost(resultSet.getInt("cost"));

                routes.add(route);
            }
            return routes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createSolutions(List<SolutionEntity> solutions) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO solutions (problem_id,cost) values(?,?)")) {
            connection.setAutoCommit(false);
            for (int i=0; i<solutions.size(); i++) {
                statement.setInt(1, solutions.get(i).getProblem_id());
                statement.setInt(2, solutions.get(i).getCost());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
