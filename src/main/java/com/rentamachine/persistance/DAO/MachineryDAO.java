package com.rentamachine.persistance.DAO;

import com.rentamachine.Utils;
import com.rentamachine.models.Machine;
import com.rentamachine.persistance.DB.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MachineryDAO {
    public MachineryDAO() {}

    public void addMachine(Machine objMechine) {
        String sql = "INSERT INTO machinery (model, serieNumber, status) VALUES (?,?,?)";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ) {
                statement.setString(1, objMechine.getModel());
                statement.setString(2, objMechine.getSerieNumber());
                statement.setString(3, objMechine.getStatus().toString());
                statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Machine> getAllMachines() {
        ArrayList<Machine> machineryList = new ArrayList<>();
        String sql = "SELECT * FROM machinery;";
        try(
                Connection conn = DatabaseConnection.getConnection();
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);
        ) {
            while (result.next()) {

                Machine machine = new Machine(
                        result.getInt("id"),
                        result.getString("model"),
                        result.getString("serieNumber"),
                        (result.getString("status").equals("DISPONIBLE"))?
                                Machine.Status.DISPONIBLE : Machine.Status.ALQUILADA
                );
                machineryList.add(machine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return machineryList;
    }

    public int countMachinery() {
        String sql = Utils.countsql("machinery");
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

    public Machine.Status findMachineById(int number) {
        String sql = " select * from machinery where id = ?;";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, number);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()) {
                return (result.getString("status").equals("DISPONIBLE")? Machine.Status.DISPONIBLE : Machine.Status.ALQUILADA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateMachine(int id, Machine.Status status) {
        String sql = "UPDATE machinery SET status = ? WHERE AND id = ?;";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(2, status.toString());
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean findMachineBySerie(String serieNumber) {
        String sql = " select * from machinery where serieNumber = ?;";
        try(
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ) {
            preparedStatement.setString(1, serieNumber);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<Machine> getMachineryByPage(int pageNumber) {
        ArrayList<Machine> machinery = new ArrayList<>();
        String sql = "SELECT * FROM machinery LIMIT 5 OFFSET ?;";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

        ) {
            int offset = (pageNumber - 1) * 5;
            preparedStatement.setInt(1, offset);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Machine machine = new Machine(
                        result.getInt("id"),
                        result.getString("model"),
                        result.getString("serieNumber"),
                        (result.getString("status").equals("DISPONIBLE")? Machine.Status.DISPONIBLE: Machine.Status.ALQUILADA)
                );
                machinery.add(machine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return machinery;
    }
}

