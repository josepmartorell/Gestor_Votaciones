
package presentacion;

import encapsuladores.Contexto;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Componentes {
    
    private Object[] repositorio;
    private Contexto contexto;
    private PantallaJTable pantallaJTable;
    private JTable jTable;
    private ModeloDatos modeloDatos;
    private JScrollPane JScrollpaneTabla;
    private JLabel[] visualizaNumVotos = new JLabel[4];
    private double[] contadorVotos = {0,0,0,0};
    private JButton botonCargaSociedad; 
    private JButton botonCargaVotacion;     
    private JButton botonNuevaVotacion;    
    private JButton botonGuardaVotacion;
    private JComboBox seleccionIdVotacion;
    private JComboBox seleccionIdSociedad;    
    private JLabel etiquetaTemaVotado;
    private JTextField jTextFieldTemaVotado;
    private int filaActualizada;
    private int columnaActualizada;

    public JLabel getVisualizaNumVotos(int componente) {
        return visualizaNumVotos[componente];
    }

    public void setVisualizaNumVotos(JLabel visualizaNumVotos, int componente) {
        this.visualizaNumVotos[componente] = visualizaNumVotos;
    }    
    
    public double getContadorVotos(int componente) {
        return contadorVotos[componente];
    }

    public void setContadorVotos(double contadorVotos, int componente) {
        this.contadorVotos[componente] = contadorVotos;
    }

    public Object[] getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(Object[] repositorio) {
        this.repositorio = repositorio;
    }

    public Contexto getContexto() {
        return contexto;
    }

    public void setContexto(Contexto contexto) {
        this.contexto = contexto;
    }
    
    public PantallaJTable getPantallaJTable() {
        return pantallaJTable;
    }

    public void setPantallaJTable(PantallaJTable pantallaJTable) {
        this.pantallaJTable = pantallaJTable;
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

    public ModeloDatos getModeloDatos() {
        return modeloDatos;
    }

    public void setModeloDatos(ModeloDatos modeloDatos) {
        this.modeloDatos = modeloDatos;
    }

    public JScrollPane getJScrollpaneTabla() {
        return JScrollpaneTabla;
    }

    public void setJScrollpaneTabla(JScrollPane JScrollpaneTabla) {
        this.JScrollpaneTabla = JScrollpaneTabla;
    }

    public JLabel[] getVisualizaNumVotos() {
        return visualizaNumVotos;
    }

    public void setVisualizaNumVotos(JLabel[] visualizaNumVotos) {
        this.visualizaNumVotos = visualizaNumVotos;
    }

    public double[] getContadorVotos() {
        return contadorVotos;
    }

    public void setContadorVotos(double[] contadorVotos) {
        this.contadorVotos = contadorVotos;
    }

    public JButton getBotonCargaSociedad() {
        return botonCargaSociedad;
    }

    public void setBotonCargaSociedad(JButton botonCargaSociedad) {
        this.botonCargaSociedad = botonCargaSociedad;
    }
    

    public JButton getBotonCargaVotacion() {
        return botonCargaVotacion;
    }

    public void setBotonCargaVotacion(JButton botonCargaVotacion) {
        this.botonCargaVotacion = botonCargaVotacion;
    }

    public JButton getBotonNuevaVotacion() {
        return botonNuevaVotacion;
    }

    public void setBotonNuevaVotacion(JButton botonNuevaVotacion) {
        this.botonNuevaVotacion = botonNuevaVotacion;
    }

    public JButton getBotonGuardaVotacion() {
        return botonGuardaVotacion;
    }

    public void setBotonGuardaVotacion(JButton botonGuardaVotacion) {
        this.botonGuardaVotacion = botonGuardaVotacion;
    }

    public JComboBox getSeleccionIdSociedad() {
        return seleccionIdSociedad;
    }

    public void setSeleccionIdSociedad(JComboBox seleccionIdSociedad) {
        this.seleccionIdSociedad = seleccionIdSociedad;
    }
    
    public JComboBox getSeleccionIdVotacion() {
        return seleccionIdVotacion;
    }

    public void setSeleccionIdVotacion(JComboBox seleccionIdVotacion) {
        this.seleccionIdVotacion = seleccionIdVotacion;
    }

    public JLabel getEtiquetaTemaVotado() {
        return etiquetaTemaVotado;
    }

    public void setEtiquetaTemaVotado(JLabel etiquetaTemaVotado) {
        this.etiquetaTemaVotado = etiquetaTemaVotado;
    }

    public JTextField getjTextFieldTemaVotado() {
        return jTextFieldTemaVotado;
    }

    public void setjTextFieldTemaVotado(JTextField jTextFieldTemaVotado) {
        this.jTextFieldTemaVotado = jTextFieldTemaVotado;
    }

    public int getFilaActualizada() {
        return filaActualizada;
    }

    public void setFilaActualizada(int filaActualizada) {
        this.filaActualizada = filaActualizada;
    }

    public int getColumnaActualizada() {
        return columnaActualizada;
    }

    public void setColumnaActualizada(int columnaActualizada) {
        this.columnaActualizada = columnaActualizada;
    }
}
