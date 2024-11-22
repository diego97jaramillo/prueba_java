package com.rentamachine.views;

import com.rentamachine.controllers.ClientController;
import com.rentamachine.controllers.MachineController;
import com.rentamachine.controllers.RentingController;

import java.util.Scanner;

public class Menu {
    RentingController rentingController = new RentingController();
    ClientController clientController = new ClientController();
    MachineController machineController = new MachineController();

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            // Mostrar las opciones del menú
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar un nuevo alquiler");
            System.out.println("2. Registrar nuevos clientes");
            System.out.println("3. Consultar la lista de clientes registrados");
            System.out.println("4. Registrar máquinas multifuncionales");
            System.out.println("5. Consultar la lista de máquinas registradas");
            System.out.println("6. Salir");

            // Leer la opción seleccionada
            int option = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer del scanner

            switch (option) {
                case 1:
                    rentingController.addRenting();
                    break;
                case 2:
                    clientController.addClient();
                    break;
                case 3:
                    System.out.println("Escribe la pagina que quieres visualizar: ");
                    int page = scanner.nextInt();
                    clientController.listClients(page);
                    scanner.nextLine();
                    break;
                case 4:
                    machineController.addMachine();
                    break;
                case 5:
                    System.out.println("Escribe la pagina que quieres visualizar: ");
                    int pageMachine = scanner.nextInt();
                    scanner.nextLine();
                    machineController.listMachines(pageMachine);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
        scanner.close();
    }
}
