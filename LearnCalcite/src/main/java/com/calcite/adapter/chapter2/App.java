package com.calcite.adapter.chapter2;

import java.sql.*;


public class App {
    public static void main(String[] args) {

        String modelPath = App.class.getClassLoader().getResource("model-chapter2.json").getPath();

        /*
        try (Connection connection = DriverManager.getConnection("jdbc:calcite:model=" + modelPath)) {
            ResultSet result = connection.getMetaData().getTables(null, null, null, null);
            while (result.next()) {
                System.out.println("Catalog : " + result.getString(1) + ",Database : " + result.getString(2) + ",Table : " + result.getString(3));
            }
            result.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
         */


        try (Connection connection = DriverManager.getConnection("jdbc:calcite:model=" + modelPath);
             Statement stmt = connection.createStatement()) {

            String sql = "SELECT * FROM book";
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                System.out.println(result.getString(1) + "\t" + result.getString(2) + "\t" + result.getString(3));
            }
            result.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        String sql = "SELECT p.name FROM author AS p";
        try (Connection connection = DriverManager.getConnection("jdbc:calcite:model=" + modelPath);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                System.out.println(result.getString(1));
            }
            result.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
