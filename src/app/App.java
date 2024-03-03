package app;

import java.sql.Connection;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        // Llamamos al método para establecer la conexión
        try (Connection conexion = ConexionBD.establecerConexion()) {
            if (conexion != null) {
                
                System.out.println("Conexión establecida. Puedes realizar operaciones con la base de datos aquí.");
                System.out.println("\nconexión: " + conexion+"\n");
                
                // Realiza tus operaciones con la base de datos dentro de este bloque
                // Por ejemplo, podrías ejecutar consultas SQL
            }
        } catch (SQLException e) {
            System.err.println("Error en la operación con la base de datos: " + e.getMessage());
        }
    }
}
