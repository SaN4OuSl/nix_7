package org.example.dao.impl;

import org.example.Unit10Main;
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

    private static final String GET_LOCATION = "SELECT * FROM locations WHERE id LIKE ?";
    private static final String GET_ALL_LOCATIONS = "SELECT * FROM locations";
    private static final String GET_ALL_ROUTES = "SELECT * FROM routes";
    private static final String GET_UNSOLVED_PROBLEMS = "SELECT * FROM problems a LEFT JOIN solutions b ON a.id = b.problem_id WHERE b.problem_id IS NULL";
    private static final String CREATE_SOLUTIONS = "INSERT INTO solutions (problem_id,cost) values(?,?)";
    private Connection connection;

    public DbDAOImpl() {
        Properties properties = loadProperties();
        String url = properties.getProperty("url");
        String user = properties.getProperty("username");
        String pass = properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Properties loadProperties() {

        Properties properties = new Properties();

        try (InputStream input = Unit10Main.class.getResourceAsStream("/application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return properties;
    }

    public List<ProblemEntity> getUnsolvedProblems() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(GET_UNSOLVED_PROBLEMS)) {
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
            throw new SQLException();
        }
    }

    public LocationEntity getLocationById(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(GET_LOCATION)) {
            LocationEntity location = new LocationEntity();
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                location.setId(resultSet.getInt("id"));
                location.setName(resultSet.getString("name"));
            }
            return location;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public List<LocationEntity> readAllLocations() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            List<LocationEntity> locations = new ArrayList<>();
            LocationEntity location;
            ResultSet resultSet = statement.executeQuery(GET_ALL_LOCATIONS);
            while (resultSet.next()) {
                location = new LocationEntity();
                location.setId(resultSet.getInt("id"));
                location.setName(resultSet.getString("name"));

                locations.add(location);
            }
            return locations;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public List<RouteEntity> readAllRouts() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            List<RouteEntity> routes = new ArrayList<>();
            RouteEntity route;
            ResultSet resultSet = statement.executeQuery(GET_ALL_ROUTES);
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
            throw new SQLException();
        }
    }

    public Integer createSolutions(SolutionEntity solution) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_SOLUTIONS)) {
            statement.setInt(1, solution.getProblem_id());
            statement.setInt(2, solution.getCost());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
}
