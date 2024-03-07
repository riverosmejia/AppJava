/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author riv
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private Connection conexion=null;
    
    private String url="";
    
    private String usuario="";
    
    private String contraseña="";
    
    public Conexion(String url1, String user,String PS){
    
         this.url = url1;
        
        this.usuario = user;
        
         this.contraseña = PS;

        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        
    }
    
}
