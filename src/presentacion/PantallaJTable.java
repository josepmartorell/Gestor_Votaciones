
package presentacion;

import excepciones.GestorExcepciones;
import encapsuladores.BaseDatos;
import encapsuladores.Contexto;
import encapsuladores.Votacion;
import encapsuladores.Sociedad;
import java.awt.Color;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import negocio.RepositorioNegocio;
import negocio.TitularesNegocio;
import negocio.VotacionesNegocio;
import negocio.SociedadesNegocio;
import javax.swing.SwingUtilities;


public class PantallaJTable extends JFrame {
    
      private Contexto contexto = null;
      
    public PantallaJTable() {
        
        Object[] repositorio = null;
                
        try {  
              contexto = new Contexto("usuario1", obtenerIP());
              repositorio = new RepositorioNegocio().cargarRepositorio();
              setSize(1200,900);
              setLocationRelativeTo(null);
              setTitle("Votación propuesta");
              Componentes componentes = new Componentes();
              componentes.setPantallaJTable(this);
              componentes.setRepositorio(repositorio);
              componentes.setContexto(contexto);
              ubicarComponentes(componentes);
              componentes.getModeloDatos().cargarTitulares(new TitularesNegocio().consultarTodos((BaseDatos)componentes.getRepositorio()[0])); 
              setVisible(true);              
        } catch (Exception exception)
           {  new GestorExcepciones().gestionarExcepcion(exception, contexto); } 
    }

    
     private String obtenerIP() throws UnknownHostException {
        String[] cadenasIP = InetAddress.getLocalHost().getHostAddress().split("\\.");
        StringBuffer procesoIP = new StringBuffer(16);
        for (int i=0; i<cadenasIP.length; i++)
        {
           if (i>0)
               procesoIP.append(".");
           procesoIP.append(String.format("%03d", Integer.parseInt(cadenasIP[i])));
        }
    
        return new String(procesoIP);    
    }    
     
     
    private Componentes ubicarComponentes(Componentes componentes) throws Exception {

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        DecimalFormat decimalFormat = new DecimalFormat("##0.000", decimalFormatSymbols);
    
        setLayout(null);
       
        GestorEventos gestorEventos = new GestorEventos(componentes);
        
        addWindowListener(gestorEventos);        // REGISTRO DE ESCUCHA DE EVENTOS DE VENTANA

        componentes.setModeloDatos(new ModeloDatos(componentes, new TitularesNegocio().consultarNumeroFilas((BaseDatos)componentes.getRepositorio()[0])));
        componentes.setjTable(new JTable(componentes.getModeloDatos()));
        componentes.setJScrollpaneTabla(new JScrollPane(componentes.getjTable()));
        componentes.getJScrollpaneTabla().setBounds(20,20,1140,500);
        add(componentes.getJScrollpaneTabla());
        
        TableColumn columna[] = new TableColumn[7];
        columna[0] = componentes.getjTable().getColumnModel().getColumn(0);
        columna[0].setPreferredWidth(30);

        columna[1] = componentes.getjTable().getColumnModel().getColumn(1);
        columna[1].setPreferredWidth(350);

        columna[2] = componentes.getjTable().getColumnModel().getColumn(2);
        columna[2].setPreferredWidth(50);

        columna[3] = componentes.getjTable().getColumnModel().getColumn(3);
        columna[3].setPreferredWidth(50);

        columna[4] = componentes.getjTable().getColumnModel().getColumn(4);
        columna[4].setPreferredWidth(50);

        columna[5] = componentes.getjTable().getColumnModel().getColumn(5);
        columna[5].setPreferredWidth(50);
        
        columna[6] = componentes.getjTable().getColumnModel().getColumn(6);
        columna[6].setPreferredWidth(50);
        
        for (int i=0; i<4; i++)
        {
           componentes.setVisualizaNumVotos( new JLabel(decimalFormat.format(componentes.getContadorVotos(i))) ,i);       
           componentes.getVisualizaNumVotos(i).setBounds(730+(i*120), 530, 100, 50);
           add(componentes.getVisualizaNumVotos(i)); 
        }
        
        componentes.setBotonCargaSociedad(new JButton("Carga sociedad"));
        componentes.getBotonCargaSociedad().setBounds(100, 600, 150, 35);
        componentes.getBotonCargaSociedad().addActionListener(gestorEventos);     // REGISTRO DE ESCUCHA DE EVENTO DE BOTON
        add(componentes.getBotonCargaSociedad());
        
        componentes.setSeleccionIdSociedad(new JComboBox());
        componentes.getSeleccionIdSociedad().setBounds(400, 610, 600, 20);
        componentes.getSeleccionIdSociedad().setBackground(Color.white);
        add(componentes.getSeleccionIdSociedad());
        cargarComboSociedades(componentes);
        
        componentes.setBotonCargaVotacion(new JButton("Carga votación"));
        componentes.getBotonCargaVotacion().setBounds(100, 650, 150, 35);
        componentes.getBotonCargaVotacion().addActionListener(gestorEventos);     // REGISTRO DE ESCUCHA DE EVENTO DE BOTON
        add(componentes.getBotonCargaVotacion());
              
        componentes.setSeleccionIdVotacion(new JComboBox());
        componentes.getSeleccionIdVotacion().setBounds(400, 660, 600, 20);
        componentes.getSeleccionIdVotacion().setBackground(Color.white);
        add(componentes.getSeleccionIdVotacion());
        cargarComboVotaciones(componentes);
        
        componentes.setBotonNuevaVotacion(new JButton("Nueva votación"));
        componentes.getBotonNuevaVotacion().setBounds(100, 700, 150, 35);
        componentes.getBotonNuevaVotacion().addActionListener(gestorEventos);     // REGISTRO DE ESCUCHA DE EVENTO DE BOTON
        add(componentes.getBotonNuevaVotacion());
        
        componentes.setEtiquetaTemaVotado(new JLabel("Tema votado"));
        componentes.getEtiquetaTemaVotado().setBounds(300, 692, 150, 50);
        add(componentes.getEtiquetaTemaVotado());
        
        componentes.setjTextFieldTemaVotado(new JTextField());
        componentes.getjTextFieldTemaVotado().setBounds(400, 710, 600, 20);
        add(componentes.getjTextFieldTemaVotado());       
        
        componentes.setBotonGuardaVotacion(new JButton("Guarda votación"));
        componentes.getBotonGuardaVotacion().setBounds(100, 750, 150, 35);
        componentes.getBotonGuardaVotacion().addActionListener(gestorEventos);     // REGISTRO DE ESCUCHA DE EVENTO DE BOTON
        add(componentes.getBotonGuardaVotacion());        
        
        return componentes;
    }
        
    public void cargarComboSociedades(Componentes componentes) throws Exception {
        componentes.getSeleccionIdSociedad().removeAllItems();
        List<Sociedad> listaSociedades = new SociedadesNegocio().consultarTodasSociedades((BaseDatos)componentes.getRepositorio()[0]);
        for (int i=0; i<listaSociedades.size(); i++)
        {
            Sociedad sociedad = listaSociedades.get(i);
            componentes.getSeleccionIdSociedad().addItem(sociedad.getCif()+"  /  "+sociedad.getRazonSocial());
        }
    }
    
    public void cargarComboVotaciones(Componentes componentes) throws Exception {
        componentes.getSeleccionIdVotacion().removeAllItems();
        List<Votacion> listaVotaciones = new VotacionesNegocio().consultarTodasVotaciones((BaseDatos)componentes.getRepositorio()[0]);
        for (int i=0; i<listaVotaciones.size(); i++)
        {
            Votacion votacion = listaVotaciones.get(i);
            componentes.getSeleccionIdVotacion().addItem(votacion.getIdVotacion()+"     "+votacion.getTemaVotado());
        }
    }


    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(PantallaJTable::new);          
    }   
}
