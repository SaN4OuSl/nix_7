package org.example.service.impl;

import org.example.entity.LocationEntity;
import org.example.entity.RouteEntity;
import org.example.entity.SolutionEntity;
import org.example.exception.NotFoundException;
import org.example.service.GraphService;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GraphServiceImpl implements GraphService {

    private static final Logger LOGGER = LoggerFactory.getLogger("info");
    private SimpleWeightedGraph<String, DefaultWeightedEdge> graph;

    public void initializeGraph(List<LocationEntity> locations, List<RouteEntity> routes) throws NotFoundException {
        LOGGER.info("Start initialize graph.");
        graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        for (LocationEntity location : locations) {
            graph.addVertex(location.getName());
        }

        DefaultWeightedEdge edge;
        for (RouteEntity route : routes) {
            LocationEntity from = findLocationById(locations, route.getFrom_id());
            LocationEntity to = findLocationById(locations, route.getTo_id());

            edge = graph.addEdge(from.getName(), to.getName());
            graph.setEdgeWeight(edge, route.getCost());
        }
    }

    public SolutionEntity shortestWay(LocationEntity from, LocationEntity to) {
        LOGGER.info("Start find shortest way.");
        int maxCost = 200_000;
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath
                = new DijkstraShortestPath<>(graph);

        double cost = dijkstraShortestPath.getPath(from.getName(), to.getName()).getWeight();
        SolutionEntity solution = new SolutionEntity();
        if (cost <= maxCost)
            solution.setCost((int) cost);
        else {
            LOGGER.info("The cost of the way is more than 200 000");
            solution.setCost(maxCost);
        }
        return solution;
    }

    private LocationEntity findLocationById(List<LocationEntity> locations, Integer id) throws NotFoundException {
        LOGGER.info("Start find location by id.");
        for (LocationEntity location : locations) {
            if (location.getId().equals(id)) return location;
        }
        LOGGER.info("Some error with location. " + new NotFoundException("location"));
        throw new NotFoundException("location");
    }
}
