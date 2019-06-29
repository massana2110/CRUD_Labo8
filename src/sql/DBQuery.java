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
    
    public boolean addProduct(String np,int id_cat, float pu, int ce){
        try{
            con = conexion.abrirConexion();
            PreparedStatement pstm = con.prepareStatement("INSERT INTO producto (nombre_producto, id_categoria, precio_unitario, cantidad_existencia) values (?,?,?,?)");
            pstm.setString(1, np);
            pstm.setInt(2, id_cat);
            pstm.setFloat(3, pu);
            pstm.setInt(4, ce);
            ResultSet rs = pstm.executeQuery();
            if (pstm.executeUpdate() > 0) {
                return true;
            }
        } catch(SQLException error){
            System.out.println("ERROR " + error.getMessage());
        } finally {
            conexion.cerrarConexion(con);
        }
        return false;
    }
    
    public String[] getCategoriaProd(){
        String[] tipos = new String[7];
        int cont = 0;
        try{
            con = conexion.abrirConexion();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM categoria_producto");
            
            while(rs.next()){
                tipos[cont] = rs.getString("nombre_categoria");
                cont++;
            }
            stm.close();
            conexion.cerrarConexion(con);
        } catch(SQLException se){
            System.out.println("ERROR: " + se.getMessage());
        }
        return tipos;
    }
}
