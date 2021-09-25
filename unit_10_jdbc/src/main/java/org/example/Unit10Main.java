package org.example;

import org.example.dao.impl.DbDAOImpl;
import org.example.entity.LocationEntity;
import org.example.entity.ProblemEntity;
import org.example.entity.RouteEntity;
import org.example.entity.SolutionEntity;
import org.example.service.impl.DbServiceImpl;
import org.example.service.impl.PrintServiceImpl;

import java.sql.SQLException;
import java.util.List;


public class Unit10Main {

    public static void main(String[] args) {
        PrintServiceImpl printService = new PrintServiceImpl();
        DbServiceImpl dbService = new DbServiceImpl(new DbDAOImpl());
        try {
            List<LocationEntity> locations = dbService.readAllLocations();

            List<RouteEntity> routes = dbService.readAllRouts();

            List<ProblemEntity> problems = dbService.getUnsolvedProblems();

            List<SolutionEntity> solutionsOfProblems = printService.findSolutionForUnsolvedProblem(problems);

            System.out.println("Locations:");
            locations.forEach(location -> System.out.println(location.getId() + ". " + location.getName()));

            System.out.println("\nRoutes:");
            routes.forEach(route -> System.out.println(route.getId() + ". Cost from " + route.getFrom_id() + " to " + route.getTo_id() + " is " + route.getCost()));

            System.out.println("\nUnsolved problems:");
            problems.forEach(problem -> System.out.println(problem.getId() + ". " + problem.getFrom_id() + " to " + problem.getTo_id()));

            System.out.println("\nSolutions:");
            solutionsOfProblems.forEach(solution -> System.out.println("For " + solution.getProblem_id() + " problem: " + solution.getCost()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
