/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author sebas
 */
public class DBConexion {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/eurekabank?useSSL=false&serverTimezone=UTC",
                "root",
                "rootroot"
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();  // Esto es lo más importante
            return null;
        }
    }
}
