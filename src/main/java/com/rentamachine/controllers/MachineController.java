package com.rentamachine.controllers;

import com.rentamachine.models.Machine;
import com.rentamachine.persistance.DAO.MachineryDAO;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class MachineController {
    private MachineryDAO machineryDAO;

    public MachineController() {
        this.machineryDAO = new MachineryDAO();
    }

    public void addMachine() {
        Machine newMachine = getMachineFromConsole();
        String numToValidate = newMachine.getSerieNumber();
        if(verifyUnique(numToValidate)) {
            machineryDAO.addMachine(newMachine);
            System.out.println("se ha creado correctamente la maquina.");
            return;
        };
        System.out.println("No cumple con el numero de serie unico, no se guardo el registro de la maquina");
    }

    public void listMachines(int page) {
        ArrayList<Machine> list = machineryDAO.getMachineryByPage(page);
        list.forEach(machine -> System.out.println(machine.toString()));
    }

    public void update(int id, Machine.Status status) {
        machineryDAO.updateMachine(id,status);
    }

    public Machine.Status findMachineById(int number) {
        return machineryDAO.findMachineById(number);
    }

    public boolean verifyUnique(String validateNum) {
        return this.machineryDAO.findMachineBySerie(validateNum);
    }

    private Machine getMachineFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el modelo de la máquina: ");
        String model = scanner.nextLine();

        System.out.print("Ingrese el número de serie: ");
        String serieNumber = scanner.nextLine();

        System.out.print("Ingrese el estado de la máquina (DISPONIBLE/ALQUILADA): ");
        String statusInput = scanner.nextLine();
        Machine.Status status = (statusInput.equalsIgnoreCase("ALQUILADA")) ? Machine.Status.ALQUILADA: Machine.Status.DISPONIBLE;


        return new Machine(0, model, serieNumber, status);
    }
}
