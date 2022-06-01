
package datos;

import encapsuladores.SistemaArchivos;
import encapsuladores.Votacion;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class VotacionesPDF {

    public void generarPDF(SistemaArchivos sistemaArchivos, Votacion votacion, double[] contadorVotos) throws IOException{  
       
            String nombreFichero = sistemaArchivos.getRutaCreacion()+"Votacion"+votacion.getIdVotacion().substring(0, 4)+votacion.getIdVotacion().substring(5, 7)+votacion.getIdVotacion().substring(8, 10)+votacion.getIdVotacion().substring(11, 13)+votacion.getIdVotacion().substring(14, 16)+votacion.getIdVotacion().substring(17)+".pdf"; 
                
            PDDocument pDDocument = new PDDocument();            
           
            PDPage pDPage = new PDPage();
            pDDocument.addPage(pDPage);
            PDPageContentStream pDPageContentStream = new PDPageContentStream(pDDocument, pDPage);
            escribirCuerpo(pDDocument, pDPageContentStream, votacion, contadorVotos);                                 
            pDPageContentStream.close();       
            pDDocument.save(nombreFichero);
            pDDocument.close();    
    }
    
    
    private void escribirCuerpo(PDDocument pDDocument, PDPageContentStream pDPageContentStream, Votacion votacion, double[] contadorVotos) throws IOException{

            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
            DecimalFormat decimalFormat = new DecimalFormat("##0.000", decimalFormatSymbols);
            
            escribirTexto(pDPageContentStream, PDType1Font.TIMES_BOLD_ITALIC, 12, 50, 690, "Comunidad de Propietarios Urbanización \"MONTES DE IBERIA\"");
            escribirTexto(pDPageContentStream, PDType1Font.TIMES_ITALIC, 12, 50, 670, "MADRID");
            
            escribirLinea(pDPageContentStream, 30, 570, 580, 570);
            escribirTexto(pDPageContentStream, PDType1Font.TIMES_BOLD, 10, 180, 550, "VOTACIÓN POR CUOTA DE PARTICIPACIÓN");
            escribirLinea(pDPageContentStream, 30, 540, 580, 540);             
            escribirTexto(pDPageContentStream, PDType1Font.TIMES_BOLD, 8, 50, 500, "Tema votado: ");
            escribirTexto(pDPageContentStream, PDType1Font.TIMES_ROMAN, 8, 130, 500, votacion.getTemaVotado());           
            escribirTexto(pDPageContentStream, PDType1Font.TIMES_BOLD, 8, 50, 480, "Fecha registro :");
            escribirTexto(pDPageContentStream, PDType1Font.TIMES_ROMAN, 8, 130, 480, votacion.getIdVotacion());
            escribirTexto(pDPageContentStream, PDType1Font.TIMES_BOLD, 8, 50, 460, "Total cuotas de votos ausentes: ");
            escribirTexto(pDPageContentStream, PDType1Font.COURIER, 8, 170, 460, String.format("%10s", decimalFormat.format(contadorVotos[0])));  
            escribirTexto(pDPageContentStream, PDType1Font.TIMES_BOLD, 8, 50, 440, "Total cuotas de abstenciones:");
            escribirTexto(pDPageContentStream, PDType1Font.COURIER, 8, 170, 440, String.format("%10s", decimalFormat.format(contadorVotos[1]))); 
            escribirTexto(pDPageContentStream, PDType1Font.TIMES_BOLD, 8, 50, 420, "Total cuotas de votos a favor:");
            escribirTexto(pDPageContentStream, PDType1Font.COURIER, 8, 170, 420, String.format("%10s", decimalFormat.format(contadorVotos[2])));
            escribirTexto(pDPageContentStream, PDType1Font.TIMES_BOLD, 8, 50, 400, "Total cuotas de votos en contra:");
            escribirTexto(pDPageContentStream, PDType1Font.COURIER, 8, 170, 400, String.format("%10s", decimalFormat.format(contadorVotos[3])));
    }
    
    
    private void escribirTexto(PDPageContentStream pDPageContentStream, PDFont pdFont, float sizeFuente, float inicioH, float inicioV,  String texto) throws IOException {
               
        pDPageContentStream.beginText();   
        pDPageContentStream.setFont(pdFont, sizeFuente);
        pDPageContentStream.newLineAtOffset(inicioH, inicioV);
        pDPageContentStream.showText(texto);
        pDPageContentStream.endText();
    }
      
    
    private void escribirLinea(PDPageContentStream pDPageContentStream, float inicioH, float inicioV, float finH, float finV) throws IOException {
        pDPageContentStream.moveTo(inicioH, inicioV);
        pDPageContentStream.lineTo(finH, finV);
        pDPageContentStream.stroke();
    }            
}
