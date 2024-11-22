package com.rentamachine.models;

import java.sql.Date;

public class Renting {
    private Date start_date;
    private Date end_date;
    private int machineryId;
    private int clientsId;
    private int id;

    public Renting(int id, Date start_date, Date end_date, int machineryId, int clientsId) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.machineryId = machineryId;
        this.clientsId = clientsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getMachineryId() {
        return machineryId;
    }

    public void setMachineryId(int machineryId) {
        this.machineryId = machineryId;
    }

    public int getClientsId() {
        return clientsId;
    }

    public void setClientsId(int clientsId) {
        this.clientsId = clientsId;
    }

    @Override
    public String toString() {
        return "Renting{" +
                "start_date=" + start_date +
                ", end_date=" + end_date +
                ", machineryId=" + machineryId +
                ", clientsId=" + clientsId +
                ", id=" + id +
                '}';
    }
}
