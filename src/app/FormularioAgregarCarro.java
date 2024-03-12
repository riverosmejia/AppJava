/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package app;

import java.awt.Image;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author riv
 */
public class FormularioAgregarCarro extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    
    private Connection con;
    
    private String cedulaSeleccionada;
    
    private String placa;
    
    private String color;
    
    private String Ano;
    
    private int KilometrajeInicial;
    
    private File archivoSeleccionado=null;
    
    private DefaultComboBoxModel<String> conductoresModel = new DefaultComboBoxModel<>();

    
    public FormularioAgregarCarro(Connection con1) {

        this.con=con1;
        
        initComponents();
        agregarEventos();
        
        cargarConductoresDisponibles();
        
        
    }
    
    private void cargarConductoresDisponibles() {
        
    jComboBoxCedula.setModel(conductoresModel); // E
    
    // Borra los elementos existentes en el JComboBox
    jComboBoxCedula.removeAllItems();

    // Consulta SQL para obtener las cédulas de los conductores desde la base de datos
    String consulta = "SELECT Cédula, nombre, Apellidos FROM Conductores";

    try (PreparedStatement statement = con.prepareStatement(consulta);
         ResultSet resultSet = statement.executeQuery()) {

        // Itera sobre los resultados y agrega las cédulas al JComboBox
        while (resultSet.next()) {
            String cedula = resultSet.getString("Cédula");
            String nombre = resultSet.getString("nombre");
            String apellidos = resultSet.getString("Apellidos");
            String item = nombre + " " + apellidos+"/"+cedula;
            jComboBoxCedula.addItem(item);
        }

    } catch (SQLException e) {
        // Manejar cualquier excepción de base de datos aquí
        e.printStackTrace();
    }
}
    
    private void agregarEventos() {
        
        jButton1.addActionListener(new java.awt.event.ActionListener() {
          
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
        
            }
        
        );
        
        jButtonFoto.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
               jButtonFotoActionPerformed(evt);
            }
        }
        );
        
        jComboBoxCedula.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
               jComboBoxCedulaActionPerformed(evt);
            }
        }
        );
    }
    
    private void jButtonFotoActionPerformed(java.awt.event.ActionEvent evt) {
        // Crea un JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        // Establece el filtro para mostrar solo archivos de imagen (puedes ajustar los formatos según tus necesidades)
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        // Muestra el cuadro de diálogo de selección de archivos
        int resultado = fileChooser.showOpenDialog(this);

        // Verifica si el usuario seleccionó un archivo
        if (resultado == JFileChooser.APPROVE_OPTION) {
            // Obtiene el archivo seleccionado
             this.archivoSeleccionado = fileChooser.getSelectedFile();

            // Verifica si el archivo es una imagen
            if (esImagen(this.archivoSeleccionado)) {
                // Muestra la imagen en un JLabel (ajustando su tamaño)
                mostrarImagenProporcionada(this.archivoSeleccionado);

                // Aquí puedes agregar la lógica para almacenar la ruta o la imagen en la base de datos
                // ...
            } else {
                // Muestra un mensaje de error si el archivo no es una imagen
                mostrarVentanaError("ERROR: El archivo seleccionado no es una imagen válida.");
            }
        }
    }

    // Método para verificar siint el archivo es una imagen
    private boolean esImagen(File archivo) {
        String nombreArchivo = archivo.getName().toLowerCase();
        return nombreArchivo.endsWith(".jpg") || nombreArchivo.endsWith(".jpeg") || nombreArchivo.endsWith(".png") || nombreArchivo.endsWith(".gif");
    }

    // Método para mostrar la imagen de forma proporcionada en el JLabel
    private void mostrarImagenProporcionada(File archivo) {
        ImageIcon imagenIcono = new ImageIcon(archivo.getAbsolutePath());

        // Obtén las dimensiones del JLabel
        int anchoLabel = jLabelFoto.getWidth();
        int altoLabel = jLabelFoto.getHeight();

        // Escala la imagen al tamaño del JLabel (proporcionadamente)
        Image imagenEscalada = imagenIcono.getImage().getScaledInstance(anchoLabel, altoLabel, Image.SCALE_SMOOTH);

        // Crea un nuevo ImageIcon con la imagen escalada
        ImageIcon imagenProporcionada = new ImageIcon(imagenEscalada);

        // Asigna el ImageIcon al JLabel
        jLabelFoto.setIcon(imagenProporcionada);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Llama a los métodos getter para obtener los valores de los campos de texto
        this.placa = getTextFieldPlaca();
        this.color = getTextFieldMarca();
        this.Ano = getTextFieldAno();
        this.KilometrajeInicial =getKilometraje();

        if(this.validarPlaca(placa)){

            // Realiza la acción deseada con los valores (en este caso, imprime en la consola)

            if(this.validarKilometraje(KilometrajeInicial)){

                System.out.println("Placa: " + this.placa);
                System.out.println("Marca: " + this.color);
                System.out.println("  Año: " + this.Ano);
                System.out.println("Kil. Inical: "+ this.KilometrajeInicial);
                System.out.println("Cédula: "+this.cedulaSeleccionada);

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
        jLabelFoto = new javax.swing.JLabel();
        jButtonFoto = new javax.swing.JButton();
        jComboBoxCedula = new javax.swing.JComboBox<>();
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

        jLabelFoto.setBackground(new java.awt.Color(153, 153, 153));
        jLabelFoto.setForeground(new java.awt.Color(102, 102, 102));
        jLabelFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonFoto.setText("Agregar Foto");

        jComboBoxCedula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCedulaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldAno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(jTextFieldPlaca)
                    .addComponent(jTextFieldMarca)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldKilometrajeInicial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonFoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelFoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxCedula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jComboBoxCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonFoto)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(19, 19, 19)
                        .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldKilometrajeInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(113, Short.MAX_VALUE))
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

    private void jComboBoxCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCedulaActionPerformed
            // Obtener el item seleccionado del JComboBox
        String itemSeleccionado = (String) jComboBoxCedula.getSelectedItem();

        // Verificar si el item seleccionado no es nulo
        if (itemSeleccionado != null) {
            // Dividir el item para obtener el nombre, apellidos y cédula
            String[] partes = itemSeleccionado.split("/");

            // Verificar si se obtuvieron las partes esperadas
            if (partes.length >= 1) {
                // Obtener nombre, apellidos y cédula
                this.cedulaSeleccionada = partes[1];

                // Realizar las acciones necesarias con los valores obtenidos
                //System.out.println("Nombre: " + nombreSeleccionado);
                //System.out.println("Apellidos: " + apellidosSeleccionados);
                //System.out.println("Cédula: " + cedulaSeleccionada);

                // Aquí puedes realizar otras acciones según tus necesidades
            } else {
                // Manejar el caso en el que el item no tiene el formato esperado
                System.err.println("Formato de item no válido: " + itemSeleccionado);
            }
        } else {
            // Manejar el caso en el que el item seleccionado es nulo
            System.err.println("Item seleccionado es nulo");
        }
    }//GEN-LAST:event_jComboBoxCedulaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonFoto;
    private javax.swing.JComboBox<String> jComboBoxCedula;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelFoto;
    private javax.swing.JTextField jTextFieldAno;
    private javax.swing.JTextField jTextFieldKilometrajeInicial;
    private javax.swing.JTextField jTextFieldMarca;
    private javax.swing.JTextField jTextFieldPlaca;
    // End of variables declaration//GEN-END:variables
    
}

