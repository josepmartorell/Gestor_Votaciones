/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import encapsuladores.Sociedad;
import excepciones.GenericaExcepcion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jtech
 */
public class SociedadesDatos {
    
    public List<Sociedad> consultarTodas(Connection connection) throws Exception
    {
        List<Sociedad> listaSociedades = new ArrayList();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
                String sql = "SELECT * FROM sociedades ORDER BY razon_social, cif";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql); 
                while (resultSet.next()) { 
                   Sociedad sociedad = new Sociedad();             
                   sociedad.setCif(resultSet.getString(1));
                   sociedad.setRazonSocial(resultSet.getString(2));
                   listaSociedades.add(sociedad);
                } 
            } catch (SQLException excepcion) {
                throw new GenericaExcepcion(51);
            } finally
            {
                if (resultSet != null) resultSet.close(); 
                if (statement != null) statement.close();
            }

        return listaSociedades;
    }
    
}
