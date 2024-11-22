package com.rentamachine.models;

public class Machine {
    public enum Status {
        DISPONIBLE,
        ALQUILADA
    }

    private int id;
    private String model;
    private String serieNumber;
    private Status status;

    public Machine(int id, String model, String serieNumber, Status status) {
        this.id = id;
        this.model = model;
        this.serieNumber = serieNumber;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerieNumber() {
        return serieNumber;
    }

    public void setSerieNumber(String serieNumber) {
        this.serieNumber = serieNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", serieNumber='" + serieNumber + '\'' +
                ", status=" + status +
                '}';
    }
}
