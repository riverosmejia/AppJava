package app;

import java.sql.Connection;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        // Llamamos al método para establecer la conexión
        Connection conexion1 = null;

        try (Connection conexion = ConexionBD.establecerConexion()) {
            if (conexion != null) {
                // Imprimimos un mensaje si la conexión es exitosa (opcional)
                System.out.println("Conexión establecida. Puedes realizar operaciones con la base de datos aquí.");
                System.out.println("\nconexión: " + conexion + "\n");

                // Asignamos la conexión a una variable
                conexion1 = conexion;

                // Realiza tus operaciones con la base de datos dentro de este bloque
                // Por ejemplo, podrías ejecutar consultas SQL
            }
        } catch (SQLException e) {
            System.err.println("Error en la operación con la base de datos: " + e.getMessage());
        }

        // Creamos una instancia de Principal y le pasamos la conexión
        Principal arranque = new Principal(conexion1);

        // Ejecutamos el método Programa de la instancia de Principal
        arranque.Programa();
    }
}
