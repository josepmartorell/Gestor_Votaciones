
package datos;

import encapsuladores.Votacion;
import excepciones.GenericaExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VotacionesDatos {

    public void insertar(Connection connection, Votacion votacion) throws Exception
    {   PreparedStatement preparedStatement = null;
        try {    
                String sql = "INSERT INTO votaciones VALUES(?,?,?)";          
                preparedStatement = connection.prepareStatement(sql); 
                preparedStatement.setString(1, votacion.getIdVotacion());
                preparedStatement.setString(2, votacion.getTemaVotado()); 
                preparedStatement.setString(3, votacion.getCifSociedad());
                preparedStatement.executeUpdate();
            } catch (SQLException excepcion) {           
                throw new GenericaExcepcion(50);
            } finally
            {
                if (preparedStatement != null) preparedStatement.close();
            }    
    }     


    public List<Votacion> consultarTodas(Connection connection) throws Exception
    {
        List<Votacion> listaVotaciones = new ArrayList();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
                String sql = "SELECT * FROM votaciones ORDER BY tema_votado, id_votacion";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql); 
                while (resultSet.next()) { 
                   Votacion votacion = new Votacion();             
                   votacion.setIdVotacion(resultSet.getString(1));
                   votacion.setTemaVotado(resultSet.getString(2));
                   listaVotaciones.add(votacion);
                } 
            } catch (SQLException excepcion) {
                throw new GenericaExcepcion(51);
            } finally
            {
                if (resultSet != null) resultSet.close(); 
                if (statement != null) statement.close();
            }

        return listaVotaciones;
    }  
}
