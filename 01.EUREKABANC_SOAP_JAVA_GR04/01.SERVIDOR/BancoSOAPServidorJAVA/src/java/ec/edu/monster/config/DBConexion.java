package ec.edu.monster.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConexion {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/eurekabank?useSSL=false&serverTimezone=UTC",
<<<<<<< HEAD
                "root",
                "root"
=======
                "root", 
                "rootroot"
>>>>>>> f314e0f09a32b7739f700b8e03fe9c5690fb3ec1
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a la BD: " + e.getMessage());
        }
    }
}