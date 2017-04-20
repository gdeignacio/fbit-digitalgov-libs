
package org.fundaciobit.administraciodigital.helium.ws.formulari.client;

import org.fundaciobit.administraciodigital.helium.ws.connexio.DadesConnexio;


/**
 *
 * @author gdeignacio
 */
public class DadesConnexioFormulariExtern extends DadesConnexio {
    
    public static final String _QNAME = "http://forms.integracio.helium.conselldemallorca.net/"; 
                                       
    private static final String _CODCLIENT = "helium.formulari";

    public static final String _SERVICE_NAME = "GuardarFormulariImplService";
    
    private static final String _SERVICE_CONTEXT = "/ws/FormulariExtern";

    private static final String _WSDL_ENDING = "?wsdl";

    public DadesConnexioFormulariExtern(String codapp) {
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

    @Override
    protected String getWsdlEnding() {
        return _WSDL_ENDING;
    }

    @Override
    public String getQNAME() {
        return _QNAME;
    }

    
    
}
