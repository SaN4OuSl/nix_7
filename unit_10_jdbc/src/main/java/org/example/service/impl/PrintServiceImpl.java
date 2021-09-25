package org.example.service.impl;

import org.example.dao.DbDAO;
import org.example.dao.impl.DbDAOImpl;
import org.example.entity.LocationEntity;
import org.example.entity.ProblemEntity;
import org.example.entity.RouteEntity;
import org.example.entity.SolutionEntity;
import org.example.exception.InvalidIdException;
import org.example.exception.NotFoundException;
import org.example.service.PrintService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrintServiceImpl implements PrintService {

    public  List<SolutionEntity> findSolutionForUnsolvedProblem(List<ProblemEntity> problems) {

        try (DbDAO dao = new DbDAOImpl()) {
            DbServiceImpl dbService = new DbServiceImpl(dao);

            GraphServiceImpl graphService = new GraphServiceImpl();
            List<LocationEntity> locationsFrom = new ArrayList<>();
            List<LocationEntity> locationsTo = new ArrayList<>();

            for (ProblemEntity problem : problems) {
                locationsFrom.add(dbService.getLocationById(problem.getFrom_id()));
                locationsTo.add(dbService.getLocationById(problem.getTo_id()));
            }

            List<LocationEntity> locations = dbService.readAllLocations();
            List<RouteEntity> routes = dbService.readAllRouts();
            graphService.initializeGraph(locations, routes);

            List<SolutionEntity> solutions = new ArrayList<>();

            for (int i = 0; i < problems.size(); i++) {
                solutions.add(graphService.shortestWay(locationsFrom.get(i), locationsTo.get(i)));
            }

            SolutionEntity solution;
            for (int i = 0; i < problems.size(); i++) {
                solution = solutions.get(i);
                solution.setProblem_id(problems.get(i).getId());
            }

            dbService.createSolution(solutions);
            return solutions;
        } catch (SQLException | InvalidIdException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
