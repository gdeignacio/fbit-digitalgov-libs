
package org.fundaciobit.administraciodigital.helium.ws.tramitacio.v1.client;

import org.fundaciobit.administraciodigital.utils.util.NVL;



/**
 *
 * @author gdeignacio
 */
public class DadesConnexioCMAIBTramitacio {
    
    public static final String _QNAME = "http://conselldemallorca.net/helium/ws/tramitacio/v1"; 
                                       
    public static final String _CODAPP = "es.caib.cmaib";
    public static final String _CODCLIENT = "helium.client";
    
    public static final String _USERNAME = NVL.nvl(System.getProperty(_CODAPP + "." + _CODCLIENT + ".username"), "").trim();
    public static final String _PASSWORD = NVL.nvl(System.getProperty(_CODAPP + "." + _CODCLIENT + ".password"), "").trim();
    public static final String _BASE_URL = NVL.nvl(System.getProperty(_CODAPP + "." + _CODCLIENT + ".baseURL"), "").trim();
    
    public static final String _ENTORNO = NVL.nvl(System.getProperty(_CODAPP + "." + _CODCLIENT + ".entorno"), "").trim();

    public static final String _SERVICE_NAME = "TramitacioServiceImplService";
    
    public static final String _SERVICE_CONTEXT = "/ws/v1/Tramitacio";

    public static final String _WSDL_ENDING = "?wsdl";

    public static final String _WSDL_LOCATION = _BASE_URL + _SERVICE_CONTEXT + _WSDL_ENDING;
            
    static{
        if (_USERNAME == null) System.out.println("La propiedad " + _CODAPP + "." + _CODCLIENT + ".username no está bien definida.");
        if (_PASSWORD == null) System.out.println("La propiedad " + _CODAPP + "." + _CODCLIENT + ".password no está bien definida.");
        if (_BASE_URL == null) System.out.println("La propiedad  " + _CODAPP + "." + _CODCLIENT + ".baseURL no está bien definida.");
        if (_USERNAME == null || _PASSWORD == null || _BASE_URL == null) throw new RuntimeException("Configuración de WS para " + _CODAPP + "." + _CODCLIENT + " no completada");
    }
    
}
