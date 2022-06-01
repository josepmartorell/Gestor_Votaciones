
package datos;

import encapsuladores.Titular;
import excepciones.GenericaExcepcion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TitularesDatos {
 
public List<Titular> consultarTodos(Connection connection) throws Exception
{
    List<Titular> listaCopropietarios = new ArrayList();
    ResultSet resultSet = null;
    Statement statement = null;
    try {
            String sql = "SELECT * FROM titulares ORDER BY codigo";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); 
            while (resultSet.next()) { 
               Titular copropietario = new Titular();
               copropietario.setCodigo(resultSet.getString(1));
               copropietario.setNombre(resultSet.getString(2));
               copropietario.setCuotaParticipacion(resultSet.getDouble(3)); 
               listaCopropietarios.add(copropietario);
            } 
        } catch (SQLException excepcion) {
            throw new GenericaExcepcion(40);
        } finally
        {
            if (resultSet != null) resultSet.close(); 
            if (statement != null) statement.close();
        }

    return listaCopropietarios;
}  


public Integer consultarNumeroFilas(Connection connection) throws Exception
{
    Integer numFilas = null;
    ResultSet resultSet = null;
    Statement statement = null; 
    String sql = "SELECT COUNT(*) FROM titulares";

    try {
            statement = connection.createStatement(); 
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
               numFilas = resultSet.getInt(1);          
            }   
        } catch (SQLException excepcion) {
            throw new GenericaExcepcion(41);
        } finally
        {
            if (resultSet != null) resultSet.close(); 
            if (statement != null) statement.close();
        }

    return numFilas;
}    
}
