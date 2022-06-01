
package datos;

import encapsuladores.Contexto;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

public class IncidenciasDatos {

    public void escribirEnFicheroIncidencias(int codigoError, String mensajeError, Contexto contexto) throws IOException {
                  
             File file = new File("log/log.txt");
             if (!file.exists())
                 file.createNewFile();
             FileWriter fileWriter = new FileWriter(file, true);
             PrintWriter printWriter = new PrintWriter(fileWriter);
             
             printWriter.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(contexto.getFechaHora()) +"  "+ String.format("%-20s",contexto.getIdentificadorUsuario()) + String.format("%-18s",contexto.getIpCliente()) + String.format("%5s",codigoError)+ "  "+String.format("%-110s",mensajeError));
             
             if (printWriter != null)
                  printWriter.close();             
             if (fileWriter != null)
                  fileWriter.close();   
    }    
}
