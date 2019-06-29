/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

/**
 * 
 * @author David Massana
 */
public class Producto {
    private int id_Producto;
    private String nombreProd;
    private String categProd;
    private float precioProd;
    private int cantExist;
    
    public Producto(){
    }

    public Producto(int id_Producto, String nombreProd, String categProd, float precioProd, int cantExist) {
        this.id_Producto = id_Producto;
        this.nombreProd = nombreProd;
        this.categProd = categProd;
        this.precioProd = precioProd;
        this.cantExist = cantExist;
    }
    
    
    
}
