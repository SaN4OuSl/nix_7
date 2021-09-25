package org.example.service;

import org.example.entity.LocationEntity;
import org.example.entity.ProblemEntity;
import org.example.entity.RouteEntity;
import org.example.entity.SolutionEntity;
import org.example.exception.InvalidIdException;

import java.sql.SQLException;
import java.util.List;

public interface DbService {
    List<ProblemEntity> getUnsolvedProblems() throws SQLException;

    LocationEntity getLocationById(Integer id) throws SQLException, InvalidIdException;

    List<RouteEntity> readAllRouts() throws SQLException;

    List<LocationEntity> readAllLocations() throws SQLException;

    void createSolution(List<SolutionEntity> solutions) throws SQLException;
}
