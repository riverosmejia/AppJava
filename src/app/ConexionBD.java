package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    
    
    
    public static Connection establecerConexion() {
        Connection conexion = null;
        try {
            // Cargar el controlador JDBC
            Class.forName("org.mariadb.jdbc.Driver");

            // Establecer la conexión
            String url = "jdbc:mariadb://localhost:3306/qwerty";
            String usuario = "root";
            String contraseña = "123";
            conexion = DriverManager.getConnection(url, usuario, contraseña);

            //System.out.println("Conexión establecida correctamente :P");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al establecer la conexión: " + e.getMessage());
        }
        return conexion;
    }
}
