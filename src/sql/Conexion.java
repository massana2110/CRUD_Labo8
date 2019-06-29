/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author David Massana
 */
public class Conexion {

    private final String DRIVER = "org.postgresql.Driver";
    private final String URL = "jdbc:postgresql://localhost:5432/Ventas";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "root";

    public Connection abrirConexion(){
        Connection con = null;
        try{
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        finally{
            if(con != null)
                return con;
            else
                return null;
        }
    }
    
    public void cerrarConexion(Connection c){
        try{
            c.close();
        }
        catch(SQLException e){
            System.out.println("ERROR: "+e.getMessage());
        }
    }

}
