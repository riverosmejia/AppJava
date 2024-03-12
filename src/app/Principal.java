package app;

import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Principal {

    private Connection con = null;

    public Principal() {

        // Cargar el controlador JDBC de MariaDB
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Conexion Con = new Conexion("jdbc:mariadb://localhost:3306/qwerty", "root", "123");
            this.con = Con.getConexion();
            if (this.con != null) {
                //System.out.println("Conexión establecida: " + this.con);
            } else {
                System.err.println("Error: La conexión es nula.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }

        // Establecer la conexión en la clase Principal
        Conexion Con = new Conexion("jdbc:mariadb://localhost:3306/qwerty", "root", "123");
        this.con = Con.getConexion();
        
    }

    public void Programa() {
        
        FormularioAgregarCarro panelCarroA = new FormularioAgregarCarro(this.con);
        
        Panel1 panel = new Panel1(this.con);

        //panel.MostrarTable("Conductores");

        // Obtén el componente JTable directamente de Panel1
        JTable visor = panel.getVisor();
        JFrame frame1 = panel.getFrame();

        // Crea un JFrame y agrega el Panel1
        JFrame frame = new JFrame("Vista de Conductores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agrega el Panel1 directamente al contenido del JFrame
        frame.getContentPane().add(panelCarroA, java.awt.BorderLayout.PAGE_START);

        // Configura el frame para ajustar el tamaño según el contenido
        frame.pack();

        // Configura el frame y hazlo visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void mostrarPanel1() {
        Panel1 panel = new Panel1(this.con);
        panel.MostrarTable("Conductores");

        // Obtén el componente JTable directamente de Panel1
        JTable visor = panel.getVisor();
        JFrame frame1 = panel.getFrame();

        // Crea un JFrame y agrega el Panel1
        JFrame frame = new JFrame("Vista de Conductores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(visor.getTableHeader(), java.awt.BorderLayout.PAGE_START);
        frame.getContentPane().add(visor, java.awt.BorderLayout.CENTER);

        // Agrega el Panel1 directamente al contenido del JFrame
        frame.getContentPane().add(panel, java.awt.BorderLayout.PAGE_END);

        // Configura el frame para ajustar el tamaño según el contenido
        frame.pack();

        // Configura el frame y hazlo visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.Programa();
    }
}
