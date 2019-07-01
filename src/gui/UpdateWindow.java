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
public class UpdateWindow extends JFrame{
    
    private Container contenedor;
    private JTextField idProd;
    private JTextField nombreProd;
    private JTextField precioProd;
    private JTextField cantExist;
    private JComboBox tipoCat;
    private JButton guardar;
    private JButton view;
    private JButton cancelar;
    
    private DBQuery query;
    
    public UpdateWindow(){
        super("UPDATE");
        query = new DBQuery();
        initComponents();
        setVisible(true);
    }
    
    public void initComponents(){
        JPanel panelForm = new JPanel();
        GridLayout gl = new GridLayout(8,2,10,5);
        panelForm.setLayout(gl);
        panelForm.add(new JLabel("Editar Producto:"));
        view = new JButton("Ver Productos");
        panelForm.add(view);
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel("ID PRODUCTO: "));
        idProd = new JTextField(5);
        panelForm.add(idProd);
        
        panelForm.add(new JLabel("Nombre: "));
        nombreProd = new JTextField(50);
        panelForm.add(nombreProd);
        panelForm.add(new JLabel("Precio: "));
        precioProd = new JTextField(5);
        panelForm.add(precioProd);
        panelForm.add(new JLabel("Tipo Categoria: "));
        tipoCat = new JComboBox();
                String[ ] tipos;
        tipos = query.getCategoriaProd();
        tipoCat.addItem("Seleccione...");
        for(String tipo:tipos){
            tipoCat.addItem(tipo);
        }
        //tipoCat.setEnabled(false);
        panelForm.add(tipoCat);
        panelForm.add(new JLabel("Cantidad Existencia: "));
        cantExist = new JTextField(5);
        panelForm.add(cantExist);
        guardar = new JButton("ACTUALIZAR");
        panelForm.add(guardar);
        cancelar = new JButton("REGRESAR");
        panelForm.add(cancelar);
        
        //AÑADE TODOS LOS BOTONES Y LABELS A LA VENTANA
        contenedor = getContentPane();
        contenedor.add(panelForm);
        setSize(400,350);
        setResizable(false);
        
        view.addActionListener(
            new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadWindow rw = new ReadWindow();
            }
        });
        cancelar.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    setVisible(false);
                    dispose(); 
                }
            }
        );
        guardar.addActionListener(
            new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idProd.getText().isEmpty() || nombreProd.getText().isEmpty() || precioProd.getText().isEmpty() || 
                        cantExist.getText().isEmpty() || tipoCat.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(rootPane, "CAMPOS VACIOS. FAVOR LLENARLOS", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                } else {
                    String txtId = idProd.getText();
                    int id = Integer.parseInt(txtId);
                    String txtNombre = nombreProd.getText();
                    String txtPrecio = precioProd.getText();
                    float precio = Float.parseFloat(txtPrecio);
                    int idCat = tipoCat.getSelectedIndex();
                    String txtCant = cantExist.getText();
                    int cant = Integer.parseInt(txtCant);
                    try{
                        if (query.updateProduct(id, txtNombre, idCat , precio, cant)) {
                            limpiarCampos();
                            JOptionPane.showMessageDialog(rootPane, "PRODUCTO EDITADO CON EXITO", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "ERROR: PRODUCTO NO EXISTE EN LA BD", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch(SQLException err){
                        err.printStackTrace();
                    }
                }
            }
        });
    }
    
    public void limpiarCampos(){
        idProd.setText("");
        nombreProd.setText("");
        precioProd.setText("");
        cantExist.setText("");
        tipoCat.setSelectedIndex(0);
    }
}
