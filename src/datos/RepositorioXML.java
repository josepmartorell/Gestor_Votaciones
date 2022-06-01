
package datos;

import encapsuladores.BaseDatos;
import encapsuladores.SistemaArchivos;
import excepciones.GenericaExcepcion;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


public class RepositorioXML {

    public Object[] cargar() throws Exception {
        
        Object[] repositorio = new Object[2];
        BaseDatos baseDatos = null;
        try {
              Document document = new SAXBuilder().build("xml/repositorio.xml");      // xml es un directorio en el raiz del Proyecto
              Element raiz = document.getRootElement();   
                 
              //      LEER BASE DE DATOS SELECCIONADA                  
              Element basesDeDatos = raiz.getChild("BASES_DE_DATOS");
              Element baseDatosSelecionada = basesDeDatos.getChild("BASE_DATOS_SELECCIONADA");  
              List listaXMLBD = basesDeDatos.getChildren("BASE_DATOS");
              boolean encontradaBD = false;
              Iterator iteratorBD = listaXMLBD.iterator();
              while (iteratorBD.hasNext() && !encontradaBD)
              {                         
                  Element elementBD = (Element)iteratorBD.next();              
                  if (elementBD.getAttributeValue("nombre").compareTo(baseDatosSelecionada.getText()) == 0)
                  {             //   ENCONTRADA BD SELECCIONADA Y CARGA XML EN OBJETO ENCAPSULADOR
                      encontradaBD = true;
                      baseDatos = new BaseDatos();
                      baseDatos.setNombre(elementBD.getAttributeValue("nombre"));
                      baseDatos.setClassDriver(elementBD.getChild("CLASS_DRIVER").getText());
                      baseDatos.setUrlConexion(elementBD.getChild("URL_CONEXION").getText());
                      baseDatos.setUsuario(elementBD.getChild("USUARIO").getText());
                      baseDatos.setPassword(elementBD.getChild("PASSWORD").getText()); 
                      repositorio[0] = baseDatos;
                  }
              }     
              

             //      LEER SISTEMA ARCHIVOS
             Element elementSistemaArchivos = raiz.getChild("SISTEMA_ARCHIVOS");  
             SistemaArchivos sistemaArchivos = new SistemaArchivos();
             sistemaArchivos.setRutaCreacion(elementSistemaArchivos.getChild("RUTA_CREACION").getText());
        
             repositorio[1] = sistemaArchivos;              
                            
        } catch(JDOMException excepcion)
          { throw new GenericaExcepcion(30); }
          catch(IOException excepcion)
          { throw new GenericaExcepcion(31); }
                    
       return repositorio;
    }              
}
