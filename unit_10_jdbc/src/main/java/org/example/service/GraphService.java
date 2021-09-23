package org.example.service;

import org.example.entity.LocationEntity;
import org.example.entity.RouteEntity;
import org.example.entity.SolutionEntity;
import org.example.exception.NotFoundException;

import java.util.List;

public interface GraphService {
    void initializeGraph(List<LocationEntity> locations, List<RouteEntity> routes) throws NotFoundException;

    SolutionEntity shortestWay(LocationEntity from, LocationEntity to);
}
