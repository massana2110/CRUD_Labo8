/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sql.DBQuery;

/**
 * 
 * @author David Massana
 */
public class CreateWindow extends JFrame{

    private Container contenedor;
    private JTextField nombreProd;
    private JTextField precioProd;
    private JTextField cantExist;
    private JComboBox tipoCat;
    private JButton aceptar;
    private JButton regresar;
    
    private DBQuery query;
    
    public CreateWindow(){
        super("CREATE");
        query = new DBQuery();
        initComponents();
        setVisible(true);
    }
    
    public void initComponents(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panelForm = new JPanel();
        GridLayout gl = new GridLayout(6, 2, 10, 5);
        panelForm.setLayout(gl);
        panelForm.add(new JLabel("Agregar producto:"));
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel("NOMBRE:"));
        nombreProd = new JTextField(25);
        panelForm.add(nombreProd);
        panelForm.add(new JLabel("PRECIO UNITARIO:"));
        precioProd = new JTextField(4);
        panelForm.add(precioProd);
        panelForm.add(new JLabel("CANTIDAD:"));
        cantExist = new JTextField(4);
        panelForm.add(cantExist);
        panelForm.add(new JLabel("CATEGORIA:"));
        tipoCat = new JComboBox();
        String[ ] tipos;
        tipos = query.getCategoriaProd();
        tipoCat.addItem("Seleccione...");
        for(String tipo:tipos){
            tipoCat.addItem(tipo);
        }
        panelForm.add(tipoCat);
        aceptar = new JButton("ACEPTAR");
        panelForm.add(aceptar);
        regresar = new JButton("REGRESAR");
        panelForm.add(regresar);
        
        contenedor = getContentPane();
        contenedor.add(panelForm);
        setSize(400,350);
        setResizable(false);
        
        aceptar.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent a){
                    if (validar()) {
                        String txtNombreProd = nombreProd.getText();
                        String txtPrecioProd = precioProd.getText();
                        float precio = Float.parseFloat(txtPrecioProd);
                        String txtCantExist = cantExist.getText();
                        int cant = Integer.parseInt(txtCantExist);
                        int idCategoria = tipoCat.getSelectedIndex();
                        try{
                            if (query.addProduct(txtNombreProd, idCategoria, precio, cant)) {
                                JOptionPane.showMessageDialog(rootPane, "PRODUCTO AGREGADO CON EXITO.");
                                limpiarCampos();
                            }
                            else{
                                JOptionPane.showMessageDialog(rootPane, "ERROR: NO SE PUDO COMPLETAR LA ACCION.");
                            }
                        }catch(SQLException e){
                            e.printStackTrace();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(rootPane, "ERROR: NECESITA LLENAR TODOS LOS CAMPOS");
                    }
                }
            }
        );
        
        regresar.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    setVisible(false);
                    dispose(); 
                }
            }
        );  
    }
    
    public boolean validar(){
        boolean res = true;
        if( nombreProd.getText().isEmpty()
            || precioProd.getText().isEmpty()
            || cantExist.getText().isEmpty()
            || tipoCat.getSelectedIndex() == 0){
                res = false;
        }
        return res;
    }
    
    public void limpiarCampos(){
        nombreProd.setText("");
        precioProd.setText("");
        cantExist.setText("");
        tipoCat.setSelectedIndex(0);
    }
}

