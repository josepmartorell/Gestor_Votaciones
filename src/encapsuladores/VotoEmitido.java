
package encapsuladores;

public class VotoEmitido {
    private Boolean[] opcionesVotacion = new Boolean[4];
    
    public Boolean[] getOpcionesVotacion() {
        return opcionesVotacion;
    }

    public void setOpcionesVotacion(Boolean[] opcionesVotacion) {
        this.opcionesVotacion = opcionesVotacion;
    }
    
    public Boolean getComponenteOpcionesVotacion(int componente) {
        return opcionesVotacion[componente];
    }

    public void setComponenteOpcionesVotacion(Boolean opcionVotacion, int componente) {
        this.opcionesVotacion[componente] = opcionVotacion;
    }        
}
