/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import BLL.CuentaService;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Entity.Cuenta;
import java.io.IOException;

/**
 *
 * @author AISM
 */
public class Principal extends javax.swing.JFrame {

    ArrayList<Cuenta> listaCuentas;
    public Principal() {
        initComponents();
        Div.setEditable(false);
        Div.setBackground(Color.red);
        txtPropietario1.setEditable(false);
        txtSaldo1.setEditable(false);
        listaCuentas = new ArrayList<>();
        cargar();
        pintarTabla();
    }
    
    private void cargar(){
        try{
            listaCuentas=CuentaService.leerCuentas();
        }catch(Exception whyNot){
            JOptionPane.showMessageDialog(null, "Tabla no cargada","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void pintarTabla(){
        DefaultTableModel modelo=new DefaultTableModel();
        String[] columnas={"NumCuenta","Propietario","Saldo"};
        modelo.setColumnIdentifiers(columnas);
        
        for(Cuenta f:listaCuentas){
            String propietario=f.getPropietario();
            float saldo=f.getSaldo();
            String[] fila={"*******************", propietario, String.valueOf(saldo)};
            modelo.addRow(fila);
        }
        tblCuentas.setModel(modelo); 
    }
    
    private void agregar(){
        try{       
            if(validarTabla(txtNumCuenta.getText().trim())){
                JOptionPane.showMessageDialog(rootPane, "Ya hay una cuenta registrada con ese código");
            }else{               

                    String numCuenta = txtNumCuenta.getText().trim();
                    String propietario = txtPropietario.getText().trim();
                    Cuenta f = new Cuenta();
                    f.setNumCuenta(numCuenta);
                    f.setPropietario(propietario);
                    f.setSaldo(0);
                    listaCuentas.add(f);
                    CuentaService.guardarCuenta(listaCuentas);
                    pintarTabla();
                    //limpiar();
                       
                
            }
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, "Cuenta no agregada, asegúrese de llenar correctamente los campos","ERROR",JOptionPane.ERROR_MESSAGE);
        }        
    }
    
    private boolean validarTabla(String codigo){
        for(Cuenta p: listaCuentas){
            if(p.getNumCuenta().compareTo(codigo)==0){
                return true;
            }
        }
        return false;
    }
    
    private void eliminar(){
        int filaSeleccionada = tblCuentas.getSelectedRow();
        listaCuentas.remove(filaSeleccionada);
        CuentaService.guardarCuenta(listaCuentas);
        pintarTabla();
        //codigoTexto.setEditable(true);
    }
    
    private void buscar(){
        String numCuenta = txtNumCuenta1.getText().trim();
        String numeroCuenta = "";
        String propietario = "";
        String saldo = "";
        int contador = 0;
        for (Cuenta f: listaCuentas){
            if(f.getNumCuenta().equals(numCuenta)){
                numeroCuenta = f.getNumCuenta();
                propietario = f.getPropietario();
                saldo = String.valueOf(f.getSaldo());
                contador++;
            }
        }
        if(contador == 0){
            JOptionPane.showMessageDialog(null, "No se encontró la cuenta");
        }else {
            txtNumCuenta1.setText(numeroCuenta);
            txtPropietario1.setText(propietario);
            txtSaldo1.setText(saldo);
        }
    }
    
    private void consignar(){
        try{
        if (txtNumCuenta1.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Ingrese un número de cuenta");
        }else{
            
                String numCuenta = txtNumCuenta1.getText().trim();
                int contador = 0;
                int posicCuenta = 0;
                for (Cuenta f: listaCuentas){
                    if(f.getNumCuenta().equals(numCuenta)){
                        float valor = Float.parseFloat(JOptionPane.showInputDialog("Escriba la cantidad a consignar"));
                        if(valor <= 0){
                            JOptionPane.showMessageDialog(null,"Debe ser mayor que cero");
                        }else{
 
                            f.setSaldo(f.consignar(valor));
                            CuentaService.guardarCuenta(listaCuentas);
                            
                            contador++;
                            txtSaldo1.setText(String.valueOf(f.getSaldo()));
                            break;
                        }
                    }
                    posicCuenta++;
                }
              if(contador == 0){
                    JOptionPane.showMessageDialog(null, "No se encontró la cuenta");
                }else {
                    JOptionPane.showMessageDialog(null, "Consignación exitosa");
                    pintarTabla();
                }
        
        
    }
        }catch(Exception error){
            
        }
    }
    
    private void retirar(){
        try{
        if (txtNumCuenta1.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Ingrese un número de cuenta");
        }else{
            
                String numCuenta = txtNumCuenta1.getText().trim();
                int contador = 0;
                int posicCuenta = 0;
                for (Cuenta f: listaCuentas){
                    if(f.getNumCuenta().equals(numCuenta)){
                        float valor = Float.parseFloat(JOptionPane.showInputDialog("Escriba la cantidad a retirar"));
                        if(valor <= 0 || valor > f.getSaldo()){
                            JOptionPane.showMessageDialog(null,"Escriba un valor válido. Puede retirar"
                                    + " máximo "+f.getSaldo());
                        }else{

                            f.setSaldo(f.retirar(valor));
                            CuentaService.guardarCuenta(listaCuentas);
                            contador++;
                            txtSaldo1.setText(String.valueOf(f.getSaldo()));
                            break;
                        }
                    }
                    posicCuenta++;
                }
              if(contador == 0){
                    JOptionPane.showMessageDialog(null, "No se encontró la cuenta");
                }else {
                    JOptionPane.showMessageDialog(null, "Retiro exitoso");
                    pintarTabla();
                }
        
        
    }
        }catch(Exception error){
            
        }
    }
    
    
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPropietario = new javax.swing.JTextField();
        txtNumCuenta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCuentas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Div = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPropietario1 = new javax.swing.JTextField();
        txtNumCuenta1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSaldo1 = new javax.swing.JTextField();
        btnConsignar = new javax.swing.JButton();
        btnRetirar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Número de cuenta:");

        jLabel2.setText("Propietario:");

        txtPropietario.setName("txtPropietario"); // NOI18N
        txtPropietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPropietarioActionPerformed(evt);
            }
        });

        txtNumCuenta.setName("txtNumCuenta"); // NOI18N
        txtNumCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumCuentaActionPerformed(evt);
            }
        });

        tblCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Número Cuenta", "Propietario", "Saldo"
            }
        ));
        tblCuentas.setName("tblCuentas"); // NOI18N
        jScrollPane1.setViewportView(tblCuentas);

        jButton1.setText("Registrar");
        jButton1.setName("btnRegistrar"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar");
        jButton2.setName("btnEliminar"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Div.setBackground(new java.awt.Color(255, 0, 0));
        jScrollPane2.setViewportView(Div);

        jLabel3.setText("REGISTRAR CUENTA");

        jLabel4.setText("TRANSACCIÓN");

        jLabel5.setText("Número de cuenta:");

        jLabel6.setText("Propietario:");

        txtPropietario1.setName("txtPropietario"); // NOI18N
        txtPropietario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPropietario1ActionPerformed(evt);
            }
        });

        txtNumCuenta1.setName("txtNumCuenta"); // NOI18N
        txtNumCuenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumCuenta1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Saldo:");

        txtSaldo1.setName("txtPropietario"); // NOI18N
        txtSaldo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaldo1ActionPerformed(evt);
            }
        });

        btnConsignar.setText("Consignar");
        btnConsignar.setName("btnRegistrar"); // NOI18N
        btnConsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsignarActionPerformed(evt);
            }
        });

        btnRetirar.setText("Retirar");
        btnRetirar.setName("btnEliminar"); // NOI18N
        btnRetirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.setName("btnRegistrar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jButton1)
                        .addGap(47, 47, 47)
                        .addComponent(jButton2)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(130, 130, 130))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(26, 26, 26)
                                .addComponent(txtSaldo1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnBuscar)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5))
                                    .addGap(26, 26, 26)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPropietario1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNumCuenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnConsignar)
                                .addGap(41, 41, 41)
                                .addComponent(btnRetirar)))
                        .addContainerGap(34, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtNumCuenta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPropietario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtSaldo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnConsignar)
                            .addComponent(btnRetirar)
                            .addComponent(btnBuscar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNumCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(241, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPropietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPropietarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPropietarioActionPerformed

    private void txtNumCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumCuentaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        agregar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        eliminar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtPropietario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPropietario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPropietario1ActionPerformed

    private void txtNumCuenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumCuenta1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumCuenta1ActionPerformed

    private void txtSaldo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaldo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldo1ActionPerformed

    private void btnConsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsignarActionPerformed
        // TODO add your handling code here:
        consignar();
    }//GEN-LAST:event_btnConsignarActionPerformed

    private void btnRetirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarActionPerformed
        // TODO add your handling code here:
        retirar();
    }//GEN-LAST:event_btnRetirarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane Div;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnConsignar;
    private javax.swing.JButton btnRetirar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCuentas;
    private javax.swing.JTextField txtNumCuenta;
    private javax.swing.JTextField txtNumCuenta1;
    private javax.swing.JTextField txtPropietario;
    private javax.swing.JTextField txtPropietario1;
    private javax.swing.JTextField txtSaldo1;
    // End of variables declaration//GEN-END:variables
}
