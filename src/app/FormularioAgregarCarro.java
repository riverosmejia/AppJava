/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package app;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;


/**
 *
 * @author riv
 */
public class FormularioAgregarCarro extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    
    private Connection con=null;
    
    public FormularioAgregarCarro(Connection con1) {
        
        initComponents();
        agregarEventos();
        
        
        
        this.con=con1;
        
    }
    
    private void agregarEventos() {
        
        jButton1.addActionListener(new java.awt.event.ActionListener() {
          
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
        
            }
        
        );
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Llama a los métodos getter para obtener los valores de los campos de texto
        String placa = getTextFieldPlaca();
        String color = getTextFieldMarca();
        String Ano = getTextFieldAno();
        int KilometrajeInicial =getKilometraje();
        
        System.out.println(KilometrajeInicial);
        
        
        if(this.validarPlaca(placa)){
            
            // Realiza la acción deseada con los valores (en este caso, imprime en la consola)
    
            if(this.validarKilometraje(KilometrajeInicial)){
            
                System.out.println("Placa: " + placa);
                System.out.println("Marca: " + color);
                System.out.println("  Año: " + Ano);
                System.out.println("Kil. Inical: "+ KilometrajeInicial);

            }else{
            
                mostrarVentanaError("ERROR: Kilometraje ingresado incorrectamente.");
                
            }
            
        }else{
            
            mostrarVentanaError("ERROR: placa ingresada incorrectamente.");
            
        }
        
    }
    
    private boolean validarKilometraje(int kilometraje) {
        return kilometraje >= 0;  // Por ejemplo, verifica que sea un número positivo
    }


    private boolean validarPlaca(String placa) {
        // El patrón regex para verificar que la placa tenga 3 letras seguidas de 3 números
        String patron = "^[A-Za-z]{3}\\d{3}$";
        return placa.matches(patron);
    }
    
    public String getTextFieldPlaca() {
        return jTextFieldPlaca.getText();
    }

    public String getTextFieldMarca() {
        return jTextFieldMarca.getText();
    }

    public String getTextFieldAno() {
        return jTextFieldAno.getText();
    }
    
    public int getKilometraje() {
        try {
            // Obtiene el texto del JTextField
            String texto = jTextFieldKilometrajeInicial.getText();

            // Convierte el texto a un entero
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            // Manejo de excepciones en caso de que no sea un valor válido
            return -1; // Puedes devolver un valor predeterminado o lanzar una excepción si prefieres
        }
    }


    
    private void mostrarVentanaError(String mensaje) {
    JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
}

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldPlaca = new javax.swing.JTextField();
        jTextFieldMarca = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldAno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldKilometrajeInicial = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jTextFieldPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPlacaActionPerformed(evt);
            }
        });

        jTextFieldMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMarcaActionPerformed(evt);
            }
        });

        jLabel1.setText("Placa");

        jLabel2.setText("Marca");

        jButton1.setText("Guardar");

        jLabel3.setText("Año de Fabricación");

        jTextFieldAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAnoActionPerformed(evt);
            }
        });

        jLabel4.setText("Kilometraje Inicial");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldKilometrajeInicial, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAno)
                            .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(jTextFieldMarca, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(65, 65, 65))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldKilometrajeInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPlacaActionPerformed

    private void jTextFieldMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMarcaActionPerformed

    private void jTextFieldAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAnoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextFieldAno;
    private javax.swing.JTextField jTextFieldKilometrajeInicial;
    private javax.swing.JTextField jTextFieldMarca;
    private javax.swing.JTextField jTextFieldPlaca;
    // End of variables declaration//GEN-END:variables
    
}

