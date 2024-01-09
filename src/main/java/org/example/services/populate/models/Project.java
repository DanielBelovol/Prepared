package org.example.services.populate.models;

public class Project {
    private int id;
    private int clientId;
    private String startDate;
    private String finishData;

    public Project(int id, int clientId, String startDate, String finishData) {
        this.id = id;
        this.clientId = clientId;
        this.startDate = startDate;
        this.finishData = finishData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishData() {
        return finishData;
    }

    public void setFinishData(String finishData) {
        this.finishData = finishData;
    }
}
