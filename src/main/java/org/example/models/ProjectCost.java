package org.example.models;

public class ProjectCost {
    private int projectID;
    private String clientName;
    private int totalCost;

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "ProjectCost{" +
                "projectID=" + projectID +
                ", clientName='" + clientName + '\'' +
                ", totalCost=" + totalCost +
                '}';
    }
}
