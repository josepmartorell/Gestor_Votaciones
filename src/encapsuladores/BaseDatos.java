
package encapsuladores;

public class BaseDatos {
    private String nombre;
    private String classDriver;
    private String urlConexion;
    private String usuario;
    private String password;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClassDriver() {
        return classDriver;
    }

    public void setClassDriver(String classDriver) {
        this.classDriver = classDriver;
    }

    public String getUrlConexion() {
        return urlConexion;
    }

    public void setUrlConexion(String urlConexion) {
        this.urlConexion = urlConexion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }            
}

