
package negocio;

import datos.RepositorioXML;

public class RepositorioNegocio {
    public Object[] cargarRepositorio() throws Exception
    {
        return new RepositorioXML().cargar();
    }    
}

