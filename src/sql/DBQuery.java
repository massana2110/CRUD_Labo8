/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sql;

import Entidades.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



/**
 * 
 * @author David Massana
 */
public class DBQuery {

    private Connection con;
    private Conexion conexion;
    
    public DBQuery(){
        conexion = new Conexion();
    }
    
    public boolean addProduct(String np,int id_cat, float pu, int ce) throws SQLException{
        try{
            con = conexion.getConnection();
            PreparedStatement pstm = con.prepareStatement("INSERT INTO producto (nombre_producto, id_categoria, precio_unitario, cantidad_existencia) values (?,?,?,?)");
            pstm.setString(1, np);
            pstm.setInt(2, id_cat);
            pstm.setFloat(3, pu);
            pstm.setInt(4, ce);
            
            if (pstm.executeUpdate() > 0) {
                return true;
            }
            
        } catch(SQLException error){
            System.out.println("ERROR " + error.getMessage());
            return false;
        } 
        return true;
    }
    
    public String[] getCategoriaProd(){
        String[] tipos = new String[7];
        int cont = 0;
        try{
            con = conexion.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM categoria_producto");
            
            while(rs.next()){
                tipos[cont] = rs.getString("nombre_categoria");
                cont++;
            }
            stm.close();
        } catch(SQLException se){
            System.out.println("ERROR: " + se.getMessage());
        }
        return tipos;
    }
    
    public ArrayList<Producto> getAllProd(){
        
        ArrayList<Producto> products = new ArrayList();
        String query = "SELECT id_producto, nombre_producto, nombre_categoria, precio_unitario, cantidad_existencia FROM producto" +
                        " INNER JOIN categoria_producto ON producto.id_categoria = categoria_producto.id_categoria" +
                        " ORDER BY id_producto ASC";
        try{
            con = conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Producto prod = new Producto();
                prod.setId_Producto(rs.getInt("id_producto"));
                prod.setNombreProd(rs.getString("nombre_producto"));
                prod.setCategProd(rs.getString("nombre_categoria"));
                prod.setPrecioProd(rs.getFloat("precio_unitario"));
                prod.setCantExist(rs.getInt("cantidad_existencia"));
                
                products.add(prod);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }
    
    public boolean updateProduct(int id, String nombre, int categ, float precioProd, int cantExist) throws SQLException{
        String query = "UPDATE producto SET nombre_producto = ?, id_categoria = ?, precio_unitario = ?, cantidad_existencia = ? WHERE id_producto = ?";
        
        try{
            con = conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setInt(2, categ);
            ps.setFloat(3, precioProd);
            ps.setInt(4, cantExist);
            ps.setInt(5, id);
            
            if(ps.executeUpdate()!=0){
                return true;
            }
            ps.close();

        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    public boolean deleteProduct(int idProd) throws SQLException{
        String query = "DELETE FROM producto WHERE id_producto = ?";
        
        try{
            con = conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idProd);
            int rows = ps.executeUpdate();
            if (rows != 0) {
                return true;
            }
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
