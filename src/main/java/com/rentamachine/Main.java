package com.rentamachine;

import com.rentamachine.controllers.ClientController;
import com.rentamachine.controllers.MachineController;
import com.rentamachine.controllers.RentingController;
import com.rentamachine.models.Client;
import com.rentamachine.models.Machine;
import com.rentamachine.models.Renting;
import com.rentamachine.persistance.DAO.ClientsDAO;
import com.rentamachine.persistance.DAO.MachineryDAO;
import com.rentamachine.persistance.DAO.RentingsDAO;
import com.rentamachine.views.Menu;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        Menu app = new Menu();
        app.mostrarMenu();
    }
}