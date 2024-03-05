/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

//import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JFrame;
//import java.sql.SQLException;
//import javax.swing.table.DefaultTableModel;

/**
 *
 * @author riv
 */
public class Principal {
    
    private Connection con;
    
    public Principal(Connection conecion){
        
        this.con=conecion;
        
    }
    
    public void Programa() {
        Panel1 panel = new Panel1(this.con);
        panel.Mostrar("Conductores");

        // Obtén el componente JTable del Panel1
        javax.swing.JTable visor = panel.getVisor();

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
    
    
}
