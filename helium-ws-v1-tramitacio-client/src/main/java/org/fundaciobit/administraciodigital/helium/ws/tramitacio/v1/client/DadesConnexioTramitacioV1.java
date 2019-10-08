
package org.fundaciobit.administraciodigital.helium.ws.tramitacio.v1.client;

import org.fundaciobit.administraciodigital.utils.util.NVL;
import org.fundaciobit.administraciodigital.utils.ws.connexio.BadConfiguredConnectionException;
import org.fundaciobit.administraciodigital.utils.ws.connexio.DadesConnexioSOAP;



/**
 *
 * @author gdeignacio
 */
public class DadesConnexioTramitacioV1 extends DadesConnexioSOAP {
    
    public static final String _QNAME = "http://conselldemallorca.net/helium/ws/tramitacio/v1";
    //public static final String _QNAME = "http://tramitacio.integracio.helium.conselldemallorca.net/";
                                       
    //public static final String _CODAPP = "es.caib.cmaib";
    private static final String _CODCLIENT = "helium.client";
    
    public static final String _SERVICE_NAME = "TramitacioServiceImplService";
    //public static final String _SERVICE_NAME = "TramitacioService";
    
    private static final String _SERVICE_CONTEXT = "/ws/v1/Tramitacio";
    //private static final String _SERVICE_CONTEXT = "/ws/TramitacioService";
    private static final String _WSDL_ENDING = "?wsdl";

    public DadesConnexioTramitacioV1(String codapp) {
        super(codapp);
        check();
    }
    
    @Override
    protected String getCodClient() {
        return _CODCLIENT;
    }

    @Override
    public String getServiceName() {
        return _SERVICE_NAME;
    }

    @Override
    protected String getServiceContext() {
        return _SERVICE_CONTEXT;
    }

    @Override
    protected String getWsdlEnding() {
        return _WSDL_ENDING;
    }

    @Override
    public String getQNAME() {
        return _QNAME;
    }

    public String getEntorn() {
        return NVL.nvl(System.getProperty(getCodapp() + getCodClient() + ".entorn"), "").trim();
    }
    
    public String getAplicacio() {
        return NVL.nvl(System.getProperty(getCodapp() + getCodClient() + ".aplicacio"), "").trim();
    }
    
     private void check(){
        
        NVL.nvl(getEntorn(), new BadConfiguredConnectionException("La propiedad " + getCodapp()  + getCodClient() + ".entorn no está bien definida."));
        NVL.nvl(getAplicacio(), new BadConfiguredConnectionException("La propiedad " + getCodapp()  + getCodClient() + ".aplicacio no está bien definida."));
        
      
    }
    
}
