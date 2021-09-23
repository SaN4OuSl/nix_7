package org.example.dao;

import org.example.entity.LocationEntity;
import org.example.entity.ProblemEntity;
import org.example.entity.RouteEntity;
import org.example.entity.SolutionEntity;

import java.sql.SQLException;
import java.util.List;

public interface DbDAO {

    List<ProblemEntity> getUnsolvedProblems() throws SQLException;

    LocationEntity getLocationById(Integer id) throws SQLException;

    List<LocationEntity> readAllLocations() throws SQLException;

    List<RouteEntity> readAllRouts() throws SQLException;

    Integer createSolutions(SolutionEntity solution) throws SQLException;
}
