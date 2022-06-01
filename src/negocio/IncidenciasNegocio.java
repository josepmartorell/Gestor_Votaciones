
package negocio;

import datos.IncidenciasDatos;
import encapsuladores.Contexto;
import java.io.IOException;

public class IncidenciasNegocio {
    
    public void escribirFichero(int codigoError, String mensajeError, Contexto contexto) throws IOException {
       new IncidenciasDatos().escribirEnFicheroIncidencias(codigoError, mensajeError, contexto);
    }        
}
