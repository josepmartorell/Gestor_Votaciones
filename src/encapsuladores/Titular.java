
package encapsuladores;

public class Titular {
    private String codigo;
    private String nombre;
    private double cuotaParticipacion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCuotaParticipacion() {
        return cuotaParticipacion;
    }

    public void setCuotaParticipacion(double cuotaParticipacion) {
        this.cuotaParticipacion = cuotaParticipacion;
    }
}
