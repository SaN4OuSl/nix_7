package org.example.service;

import org.example.entity.ProblemEntity;
import org.example.entity.SolutionEntity;

import java.util.List;

public interface PrintService {

    List<SolutionEntity> findSolutionForUnsolvedProblem(java.util.List<ProblemEntity> problems);
}
