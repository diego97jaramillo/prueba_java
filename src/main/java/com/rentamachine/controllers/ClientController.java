package com.rentamachine.controllers;

import com.rentamachine.models.Client;
import com.rentamachine.persistance.DAO.ClientsDAO;
import com.rentamachine.persistance.DAO.MachineryDAO;

import java.util.ArrayList;
import java.util.Scanner;

public class ClientController {
    private ClientsDAO clientsDAO;

    public ClientController() {
        this.clientsDAO = new ClientsDAO();
    }


    public void addClient() {
        Client newClient = getClientFromConsole();
        clientsDAO.addClient(newClient);
    }

    public void listClients(int page) {
        ArrayList<Client> list = clientsDAO.getClientsByPage(page);
        list.forEach(client1 -> System.out.println(client1.toString()));
    }

    private Client getClientFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre: ");
        String name = scanner.nextLine();

        System.out.print("Ingrese el correo electrónico: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese el número de teléfono: ");
        String phone = scanner.nextLine();

        System.out.print("Ingrese la dirección: ");
        String address = scanner.nextLine();
        return new Client(0,name, email, phone, address);
    }
}
