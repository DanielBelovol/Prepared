package org.example.services;

import org.example.dao.*;
import org.example.models.*;

import java.util.List;

public class DatabaseQueryService {
    private final HighestSalaryWorkerD highestSalaryWorkerD;
    private final MaxDurationProjectD maxDurationProjectD;
    private final OldestYoungestWorkerD oldestYoungestWorkerD;
    private final ProjectCostD projectCostD;
    private final TopClientProjectsD topClientProjectsD;

    public DatabaseQueryService() {
        this.highestSalaryWorkerD = new HighestSalaryWorkerD();
        this.maxDurationProjectD = new MaxDurationProjectD();
        this.oldestYoungestWorkerD = new OldestYoungestWorkerD();
        this.projectCostD = new ProjectCostD();
        this.topClientProjectsD = new TopClientProjectsD();
    }

    public List<HighestSalaryWorker> findHighestSalaryWorker() {
        return highestSalaryWorkerD.findHighestSalaryWorker();
    }

    public List<MaxDurationProject> findMaxDurationProject() {
        return maxDurationProjectD.findMaxDurationProject();
    }

    public List<OldestYoungestWorker> findOldestYoungestWorker() {
        return oldestYoungestWorkerD.findOldestYoungestWorker();
    }

    public List<ProjectCost> findProjectCost() {
        return projectCostD.findProjectCost();
    }

    public List<TopClientProjects> findTopClientProjects() {
        return topClientProjectsD.findTopClientProjects();
    }
}
