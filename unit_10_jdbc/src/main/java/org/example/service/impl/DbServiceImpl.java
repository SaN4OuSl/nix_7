package org.example.service.impl;

import org.example.dao.DbDAO;
import org.example.entity.LocationEntity;
import org.example.entity.ProblemEntity;
import org.example.entity.RouteEntity;
import org.example.entity.SolutionEntity;
import org.example.exception.InvalidIdException;
import org.example.service.DbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class DbServiceImpl implements DbService {

    private static final Logger LOGGER = LoggerFactory.getLogger("info");
    private final DbDAO DAO;

    public DbServiceImpl(DbDAO dao) {
        DAO = dao;
    }

    public List<ProblemEntity> getUnsolvedProblems() throws SQLException {
        LOGGER.info("Start getting unsolved problems.");
        return DAO.getUnsolvedProblems();
    }

    public LocationEntity getLocationById(Integer id) throws SQLException, InvalidIdException {
        LOGGER.info("Start getting location by id.");
        if (id <= 0) throw new InvalidIdException();
        return DAO.getLocationById(id);
    }

    public List<RouteEntity> readAllRouts() throws SQLException {
        LOGGER.info("Start reading all routes.");
        return DAO.readAllRouts();
    }

    public List<LocationEntity> readAllLocations() throws SQLException {
        LOGGER.info("Start reading locations.");
        return DAO.readAllLocations();
    }

    public void createSolution(List<SolutionEntity> solutions) throws SQLException {
        LOGGER.info("Start creating solution.");
        DAO.createSolutions(solutions);
    }

}
