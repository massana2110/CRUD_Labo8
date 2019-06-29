/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * 
 * @author David Massana
 */
public class MainWindow extends JFrame{
    
    private JButton create;
    private JButton update;
    private JButton delete;
    private JButton read;
    
    private JLabel lblTitle;
    private Container contenedor;
    
    public MainWindow(){
        super("CRUD V. 1.0");
        initComponents();
    }
    
    public void initComponents(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        
        lblTitle = new JLabel(); //Se inicializa el texto que se quiere mostrar
        lblTitle.setText("ADMINISTRACION DE PRODUCTOS"); //Se da valor al texto
        lblTitle.setFont(new Font("arial",Font.PLAIN,25)); //Se establece el tipo de fuente, el modelo de fuenta (negrita, cursiva, plana) y el tamaño.
        lblTitle.setBounds(150, 150, 500, 75); //Posicion del texto
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.DARK_GRAY); //para cambiar color de letra.
        
        create = new JButton("CREATE");
        create.setForeground(Color.DARK_GRAY);
        create.setBounds(125, 300, 100, 50);
        
        read = new JButton("READ");
        read.setForeground(Color.DARK_GRAY);
        read.setBounds(275, 300, 100, 50);
        
        update = new JButton("UPDATE");
        update.setForeground(Color.DARK_GRAY);
        update.setBounds(425, 300, 100, 50);
        
        delete = new JButton("DELETE");
        delete.setForeground(Color.DARK_GRAY);
        delete.setBounds(575, 300, 100, 50);
        
        contenedor = getContentPane();
        contenedor.add(lblTitle);
        contenedor.add(create);
        contenedor.add(read);
        contenedor.add(update);
        contenedor.add(delete);
        setSize(800,600);
        
        create.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        CreateWindow cw = new CreateWindow();
                    }
                }
        );
        
        read.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        ReadWindow rw = new ReadWindow();
                    }
                }
        );
        
        update.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        UpdateWindow uw = new UpdateWindow();
                    }
                }
        );
    }
    
}
