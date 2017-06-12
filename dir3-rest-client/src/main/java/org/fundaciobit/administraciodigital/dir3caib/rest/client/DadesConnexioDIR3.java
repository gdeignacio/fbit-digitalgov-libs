
package org.fundaciobit.administraciodigital.dir3caib.rest.client;

import org.fundaciobit.administraciodigital.utils.ws.connexio.DadesConnexioREST;


/**
 *
 * @author gdeignacio
 */
public class DadesConnexioDIR3 extends DadesConnexioREST {
    
    //public static final String _QNAME = "http://conselldemallorca.net/helium/ws/tramitacio/v1"; 
                                       
    //public static final String _CODAPP = "es.caib.cmaib";
    private static final String _CODCLIENT = "dir3caib.client";
    
    public static final String _SERVICE_NAME = "TramitacioServiceImplService";
    
    private static final String _SERVICE_CONTEXT = "/rest";

    //private static final String _WSDL_ENDING = "?wsdl";

    public DadesConnexioDIR3(String codapp) {
        super(codapp);
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

    //@Override
    //protected String getWsdlEnding() {
    //    return _WSDL_ENDING;
    //}

    //@Override
    //public String getQNAME() {
    //    return _QNAME;
    //}

    //public String getEntorno() {
    //    return NVL.nvl(System.getProperty(codapp + "." + _CODCLIENT + ".entorno"), "").trim();
    //}
    
}
