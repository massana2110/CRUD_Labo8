/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



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
        return true;
    }
}
