package com.rentamachine.controllers;
import com.rentamachine.models.Machine;
import com.rentamachine.models.Renting;
import com.rentamachine.persistance.DAO.RentingsDAO;
import java.util.ArrayList;
import java.util.Scanner;

public class RentingController {
    private RentingsDAO rentingsDAO ;
    private MachineController machineController;
    public RentingController() {
        this.rentingsDAO = new RentingsDAO();
        this.machineController = new MachineController();
    }

    public void addRenting() {
        Machine.Status newStatus = Machine.Status.DISPONIBLE;
        Renting newRenting = getRentingFromConsole();
        if(machineController.findMachineById(newRenting.getMachineryId()) == null) {
            System.out.println("No se pudo agregar la maquina, la maquina con ese id, no existe");
            return;
        }
        rentingsDAO.addRenting(newRenting);
        if (machineController.findMachineById(newRenting.getMachineryId()) == Machine.Status.DISPONIBLE) newStatus = Machine.Status.ALQUILADA;
        machineController.update(newRenting.getMachineryId(), newStatus);
    }



    public void listRentings(int page) {
        ArrayList<Renting> list = rentingsDAO.getRentingsByPage(page);
        list.forEach(renting -> System.out.println(renting.toString()));
    }

    private Renting getRentingFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la fecha de inicio (yyyy-mm-dd): ");
        String startDateInput = scanner.nextLine();

        System.out.print("Ingrese la fecha de fin (yyyy-mm-dd): ");
        String endDateInput = scanner.nextLine();

        System.out.print("Ingrese el ID del cliente: ");
        int clientId = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el ID de la maquinaria: ");
        int machineryId = Integer.parseInt(scanner.nextLine());

        // Asegúrate de que las fechas sean convertidas adecuadamente en objetos Date.
        java.sql.Date startDate = java.sql.Date.valueOf(startDateInput);
        java.sql.Date endDate = java.sql.Date.valueOf(endDateInput);

        return new Renting(0, startDate, endDate, machineryId, clientId);
    }
}
