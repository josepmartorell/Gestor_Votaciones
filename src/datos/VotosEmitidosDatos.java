
package datos;

import encapsuladores.Votacion;
import encapsuladores.VotoEmitido;
import excepciones.GenericaExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VotosEmitidosDatos {

    public void insertar(Connection connection, Votacion votacion, Object datos[][]) throws Exception
    {   PreparedStatement preparedStatement = null;
        try {    
                String sql = "INSERT INTO votos_emitidos VALUES(?,?,?,?,?,?)";          
                preparedStatement = connection.prepareStatement(sql); 

                for (Object[] dato : datos) {
                    preparedStatement.setString(1, (String) dato[0]);
                    preparedStatement.setString(2, votacion.getIdVotacion());
                    for (int k = 3; k<=6; k++) {
                        byte voto = 0;
                        if ((Boolean) dato[k]) {
                            voto = 1;
                        }
                        preparedStatement.setByte(k, voto);
                    }
                    preparedStatement.executeUpdate();
                }  

            } catch (SQLException excepcion) {           
                throw new GenericaExcepcion(60);
            } finally
            {
                if (preparedStatement != null) preparedStatement.close();
            }    
    }         


    public List<VotoEmitido> consultarResultadoVotacion(Connection connection, Votacion votacion) throws Exception
    {
        List<VotoEmitido> listaVotosEmitidos = new ArrayList();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
                String sql = "SELECT opcion_ausente, opcion_abstencion, opcion_afirmativo,opcion_negativo FROM votos_emitidos WHERE id_votacion=? ORDER BY codigo_titular";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, votacion.getIdVotacion()); 
                resultSet = preparedStatement.executeQuery(); 
                while (resultSet.next()) { 
                   VotoEmitido votoEmitido = new VotoEmitido();             
                   for (int i=1; i<=4; i++)
                   {  
                       byte opcionVotacionBD = resultSet.getByte(i);
                       Boolean opcionVotacion;
                       if (opcionVotacionBD == 1)
                           votoEmitido.setComponenteOpcionesVotacion(true, i-1);
                       else
                           votoEmitido.setComponenteOpcionesVotacion(false, i-1);                       
                   }
                   listaVotosEmitidos.add(votoEmitido);
                } 
            } catch (SQLException excepcion) {
                throw new GenericaExcepcion(61);
            } finally
            {
                if (resultSet != null) resultSet.close(); 
                if (preparedStatement != null) preparedStatement.close();
            }

        return listaVotosEmitidos;
    }  
}
