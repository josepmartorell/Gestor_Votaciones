
package datos;

import encapsuladores.BaseDatos;
import excepciones.GenericaExcepcion;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBaseDatos {

    public Connection abrirConexion(BaseDatos baseDatos) throws Exception 
    {
        Connection connection = null;  
        try {        
             Class.forName(baseDatos.getClassDriver());
             connection = java.sql.DriverManager.getConnection(baseDatos.getUrlConexion(), baseDatos.getUsuario(), baseDatos.getPassword());  
        } catch (SQLException excepcion) {          
            throw new GenericaExcepcion(20);
        }          
        return connection;         
    }
    
    public  void cerrarConexion(Connection connection) throws GenericaExcepcion
    {
        try {
             if (connection!= null) 
                connection.close();    
        } catch (SQLException excepcion) {          
            throw new GenericaExcepcion(20);
        }    
    }                 
}

