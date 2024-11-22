package com.rentamachine.persistance.DAO;

import com.rentamachine.Utils;
import com.rentamachine.models.Client;
import com.rentamachine.persistance.DB.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientsDAO {
    public ClientsDAO() {}
    public void addClient(Client objClient) {
        String sql = "INSERT INTO clients (name, email, phone, address) VALUES (?,?,?,?);";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, objClient.getName());  /// prerapa la declararion de sql
            statement.setString(2, objClient.getEmail());
            statement.setString(3, objClient.getPhone());
            statement.setString(4, objClient.getAddress());



            statement.executeUpdate(); /// ejecuta la declaracion SQL
            System.out.println("Se creo correctamente el cliente");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Client> getAllClients() {
        ArrayList<Client> clientsList = new ArrayList<>();
        String sql = "SELECT * FROM clients;";
        try(
                Connection conn = DatabaseConnection.getConnection();
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);
        ) {
            while (result.next()) {

                Client client = new Client(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("email"),
                        result.getString("phone"),
                        result.getString("address")
                );
                clientsList.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientsList;
    }

    public int countClients() {
        String sql = Utils.countsql("clients");
        try(
                Connection conn = DatabaseConnection.getConnection();
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);
        ) {
            if (result.next()) {
                return result.getInt("total_records");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Client> getClientsByPage(int pageNumber) {
        ArrayList<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients LIMIT 5 OFFSET ?;";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

        ) {
            // Calcula el OFFSET basado en el número de página
            int offset = (pageNumber - 1) * 5;
            preparedStatement.setInt(1, offset);
            ResultSet result = preparedStatement.executeQuery();

            // Procesa los resultados
            while (result.next()) {
                Client client = new Client(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("email"),
                        result.getString("phone"),
                        result.getString("address")
                );
                clients.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clients;
    }
}
