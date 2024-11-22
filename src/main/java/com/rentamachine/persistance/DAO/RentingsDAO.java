package com.rentamachine.persistance.DAO;

import com.rentamachine.Utils;
import com.rentamachine.models.Renting;
import com.rentamachine.persistance.DB.DatabaseConnection;
import jdk.jshell.execution.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RentingsDAO {
    public RentingsDAO() {}
    public void addRenting(Renting objRenting) {
        String sql = "INSERT INTO rentings (start_date, end_date, clients_id, machinery_id) VALUES (?,?,?,?);";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setDate(1, objRenting.getStart_date());  /// prerapa la declararion de sql
            statement.setDate(2, objRenting.getEnd_date());
            statement.setInt(3, objRenting.getClientsId());
            statement.setInt(4, objRenting.getMachineryId());

            statement.executeUpdate(); /// ejecuta la declaracion SQL
            System.out.println("Se creo correctamente el alquiler");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Renting> getAllRentings() {
        ArrayList<Renting> rentingsList = new ArrayList<>();
        String sql = "SELECT * FROM rentings;";
        try(
                Connection conn = DatabaseConnection.getConnection();
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);
        ) {
            while (result.next()) {

                Renting renting = new Renting(
                        result.getInt("id"),
                        result.getDate("start_date"),
                        result.getDate("end_date"),
                        result.getInt("machinery_id"),
                        result.getInt("clients_id")
                );
                rentingsList.add(renting);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rentingsList;
    }

    public int countRentings() {
        String sql = Utils.countsql("rentings");
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

    public ArrayList<Renting> getRentingsByPage(int pageNumber) {
        ArrayList<Renting> rentings = new ArrayList<>();
        String sql = "SELECT * FROM rentings LIMIT 5 OFFSET ?;";

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
                Renting renting = new Renting(
                        result.getInt("id"),
                        result.getDate("start_date"),
                        result.getDate("end_date"),
                        result.getInt("machinery_id"),
                        result.getInt("clients_id")
                );
                rentings.add(renting);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rentings;
    }
}


