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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sql.DBQuery;

/**
 * 
 * @author David Massana
 */
public class UpdateWindow extends JFrame{
    
    private Container contenedor;
    private JTextField nombreProd;
    private JTextField precioProd;
    private JTextField cantExist;
    private JComboBox tipoCat;
    private JButton guardar;
    private JButton buscar;
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
        GridLayout gl = new GridLayout(7,2,10,5);
        panelForm.setLayout(gl);
        panelForm.add(new JLabel("Editar Producto:"));
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel("NOMBRE: "));
        nombreProd = new JTextField(50);
        panelForm.add(nombreProd);
        panelForm.add(new JLabel(""));
        buscar = new JButton("Buscar");
        panelForm.add(buscar);
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
        guardar = new JButton("ACTUALIZAR");
        guardar.setEnabled(false);
        panelForm.add(guardar);
        cancelar = new JButton("REGRESAR");
        panelForm.add(cancelar);
        
        //AÑADE TODOS LOS BOTONES Y LABELS A LA VENTANA
        contenedor = getContentPane();
        contenedor.add(panelForm);
        setSize(350,230);
        setResizable(false);
        
        cancelar.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    setVisible(false);
                    dispose(); 
                }
            }
        ); 
    }
}
