package org.example.dao;

import org.example.entity.LocationEntity;
import org.example.entity.ProblemEntity;
import org.example.entity.RouteEntity;
import org.example.entity.SolutionEntity;

import java.sql.SQLException;
import java.util.List;

public interface DbDAO extends AutoCloseable {

    List<ProblemEntity> getUnsolvedProblems() throws SQLException;

    LocationEntity getLocationById(Integer id) throws SQLException;

    List<LocationEntity> readAllLocations() throws SQLException;

    List<RouteEntity> readAllRouts() throws SQLException;

    void createSolutions(List<SolutionEntity> solutions) throws SQLException;

    @Override
    void close() throws SQLException;
}
