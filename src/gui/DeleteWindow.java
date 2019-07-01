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
public class DeleteWindow extends JFrame{

    private Container contenedor;
    private JTextField idProd;

    private JButton eliminar;
    private JButton view;
    private JButton cancelar;
    
    private DBQuery query;
    
    public DeleteWindow(){
        super("DELETE");
        query = new DBQuery();
        initComponents();
        setVisible(true);
    }
    
    public void initComponents(){
        JPanel panelForm = new JPanel();
        GridLayout gl = new GridLayout(4,2,6,3);
        panelForm.setLayout(gl);
        panelForm.add(new JLabel("Eliminar Producto:"));
        view = new JButton("Ver Productos");
        panelForm.add(view);
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel("ID PRODUCTO: "));
        idProd = new JTextField(5);
        panelForm.add(idProd);
        
        
        eliminar = new JButton("ACTUALIZAR");
        panelForm.add(eliminar);
        cancelar = new JButton("REGRESAR");
        panelForm.add(cancelar);
        
        //AÑADE TODOS LOS BOTONES Y LABELS A LA VENTANA
        contenedor = getContentPane();
        contenedor.add(panelForm);
        setSize(300,300);
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
        eliminar.addActionListener(
            new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idProd.getText().isEmpty()){
                    JOptionPane.showMessageDialog(rootPane, "CAMPOS VACIOS. FAVOR LLENARLOS", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                } else {
                    String txtId = idProd.getText();
                    int id = Integer.parseInt(txtId);

                    try{
                        if (query.deleteProduct(id)) {
                            JOptionPane.showMessageDialog(rootPane, "PRODUCTO ELIMINADO CON EXITO", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
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
}
