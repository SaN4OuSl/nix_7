package org.example.tasks.level3;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TrirdTask {

    private static final String input = "module_2/src/main/resources/files/input.txt";
    private static final String output = "module_2/src/main/resources/files/output.txt";

    public static void run() {
        System.out.println("Third task");
        Path path = Paths.get(input);
        try {
            System.out.println("Input:");
            ArrayList<String> graphStringFromFile = (ArrayList<String>) Files.readAllLines(path);
            graphStringFromFile.forEach(System.out::println);
            System.out.println("-------------------------------");
            System.out.println("Output:");
            findWaysInGraph(graphStringFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findWaysInGraph(ArrayList<String> stringGraph) {
        SimpleWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        ArrayList<String> vertexes = new ArrayList<>();
        ArrayList<String[]> allEdges = new ArrayList<>();

        String[] edgesOfCity;
        int countVertexes = Integer.parseInt(stringGraph.get(0));
        ArrayList<Integer> countEdgesOfEveryCity = new ArrayList<>();

        int countEdgesOfCity;
        int endIndex = 0;
        for (int i = 1; i < stringGraph.size(); i++) {
            vertexes.add(stringGraph.get(i));
            countEdgesOfCity = (Integer.parseInt((stringGraph.get(i + 1))));
            countEdgesOfEveryCity.add(countEdgesOfCity);
            i += 2;
            edgesOfCity = new String[countEdgesOfCity];
            for (int j = 0; j < edgesOfCity.length; i++) {
                edgesOfCity[j] = stringGraph.get(i);
                j++;
            }
            allEdges.add(edgesOfCity);
            countVertexes--;
            i--;
            if (countVertexes == 0) {
                endIndex = i + 1;
                break;
            }
        }

        countVertexes = Integer.parseInt(stringGraph.get(0));
        for (int i = 0; i < countVertexes; i++) {
            graph.addVertex(vertexes.get(i));
        }

        String vertexTarget;
        double weight;
        DefaultWeightedEdge edge;

        for (int i = 0; i < countVertexes; i++) {
            for (int j = 0; j < countEdgesOfEveryCity.get(i); j++) {
                String[] edgeString = allEdges.get(i)[j].split(" ");
                vertexTarget = vertexes.get(Integer.parseInt(edgeString[0]) - 1);
                weight = Double.parseDouble(edgeString[1]);
                if (!graph.containsEdge(vertexes.get(i), vertexTarget)) {
                    edge = graph.addEdge(vertexes.get(i), vertexTarget);
                    graph.setEdgeWeight(edge, weight);
                }
            }
        }
        findWay(graph, stringGraph.subList(endIndex, stringGraph.size()));
    }

    private static void findWay(Graph<String, DefaultWeightedEdge> graph, List<String> findWay) {
        int maxCost = 200_000;
        int countFoundWays = Integer.parseInt(findWay.get(0));

        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath
                = new DijkstraShortestPath<>(graph);

        double weight = 0;
        String[] sourceAndTarget;
        StringBuilder output = new StringBuilder();
        for (int i = 1; i < countFoundWays + 1; i++) {
            sourceAndTarget = findWay.get(i).split(" ");
            if (weight <= maxCost) {
                weight = dijkstraShortestPath.getPath(sourceAndTarget[0], sourceAndTarget[1])
                        .getWeight();
            } else {
                weight = maxCost;
            }
            System.out.println(weight);
            output.append(weight).append("\n");
        }

        try {
            Path path = Paths.get(TrirdTask.output);
            Files.writeString(path, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
