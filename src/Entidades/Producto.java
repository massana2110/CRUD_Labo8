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

    public int getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public String getCategProd() {
        return categProd;
    }

    public void setCategProd(String categProd) {
        this.categProd = categProd;
    }

    public float getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(float precioProd) {
        this.precioProd = precioProd;
    }

    public int getCantExist() {
        return cantExist;
    }

    public void setCantExist(int cantExist) {
        this.cantExist = cantExist;
    }
    
    
    
}
