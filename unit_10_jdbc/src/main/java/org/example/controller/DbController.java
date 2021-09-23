package org.example.controller;

import org.example.entity.LocationEntity;
import org.example.entity.ProblemEntity;
import org.example.entity.RouteEntity;
import org.example.entity.SolutionEntity;
import org.example.exception.InvalidIdException;
import org.example.exception.NotFoundException;
import org.example.service.impl.DbServiceImpl;
import org.example.service.impl.GraphServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbController {

    private static final DbServiceImpl dbService = new DbServiceImpl();
    private static final GraphServiceImpl graphService = new GraphServiceImpl();

    public static void run() {
        try {
            List<LocationEntity> locations = dbService.readAllLocations();

            List<RouteEntity> routes = dbService.readAllRouts();

            List<ProblemEntity> problems = dbService.getUnsolvedProblems();

            List<SolutionEntity> solutionsOfProblems = findSolutionForUnsolvedProblem(problems);

            System.out.println("Locations:");
            locations.forEach(location -> System.out.println(location.getId() + ". " + location.getName()));

            System.out.println("\nRoutes:");
            routes.forEach(route -> System.out.println(route.getId() + ". Cost from " + route.getFrom_id() + " to " + route.getTo_id() + " is " + route.getCost()));

            System.out.println("\nUnsolved problems:");
            problems.forEach(problem -> System.out.println(problem.getId() + ". " + problem.getFrom_id() + " to " + problem.getTo_id()));

            System.out.println("\nSolutions:");
            solutionsOfProblems.forEach(solution -> System.out.println("For " + solution.getProblem_id() + " problem: " + solution.getCost()));

        } catch (SQLException | InvalidIdException | NotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<SolutionEntity> findSolutionForUnsolvedProblem(List<ProblemEntity> problems) throws SQLException, InvalidIdException, NotFoundException {
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

        for (SolutionEntity newSolution : solutions) {
            dbService.createSolution(newSolution);
        }

        return solutions;
    }
}
