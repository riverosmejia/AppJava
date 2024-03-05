package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTable;

public class Principal {

    private Connection con;

    public Principal() {
        
        // Cargar el controlador JDBC de MariaDB
        try {
        
            Class.forName("org.mariadb.jdbc.Driver");
        
        } catch (ClassNotFoundException e) {
        
            System.err.println("Error al cargar el controlador JDBC: " + e.getMessage());
        
        }

        // Establecer la conexión en la clase Principal
        this.con = establecerConexion();
    }

    private Connection establecerConexion() {
        Connection conexion = null;
        // Establece la conexión a la base de datos (reemplaza con tus detalles)
        String url = "jdbc:mariadb://localhost:3306/qwerty";
        String usuario = "root";
        String contraseña = "123";

        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }

        return conexion;
    }

    public void Programa() {
        Panel1 panel = new Panel1(this.con);
        panel.MostrarTable("Conductores");

        // Obtén el componente JTable directamente de Panel1
        JTable visor = panel.getVisor();

        // Crea un JFrame y agrega el JTable
        JFrame frame = new JFrame("Vista de Conductores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(visor.getTableHeader(), java.awt.BorderLayout.PAGE_START);
        frame.getContentPane().add(visor, java.awt.BorderLayout.CENTER);

        // Configura el frame y hazlo visible
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Crea una instancia de Principal y ejecuta el programa
        Principal principal = new Principal();
        principal.Programa();
    }
}
