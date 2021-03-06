package org.fundaciobit.administraciodigital.helium.ws.formulari.client;

//import org.fundaciobit.administraciodigital.helium.commons.IDominiHeliumItem;
//import static org.fundaciobit.administraciodigital.helium.ws.formulari.client.DadesConnexioFormulariExtern._CODAPP;
//import static org.fundaciobit.administraciodigital.helium.ws.formulari.client.DadesConnexioFormulariExtern._CODCLIENT;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import net.conselldemallorca.helium.integracio.forms.GuardarFormulari;
import net.conselldemallorca.helium.integracio.forms.GuardarFormulariImplService;
import net.conselldemallorca.helium.integracio.forms.ParellaCodiValor;


import org.fundaciobit.administraciodigital.utils.ws.connexio.DadesConnexioSOAP;

/**
 *
 * @author gdeignacio
 */
public class FormulariExternClient {
    
    private String propertyBase = "es.caib.cmaib.";

    protected static final Logger LOG = Logger.getLogger(FormulariExternClient.class.getName());

    /**
     * Objecte que emmagatzema la instancia de la classe segons el patro
     * singleton
     *
     */
    private static final FormulariExternClient client = new FormulariExternClient();

    /**
     * Construeix un objecte de la classe. Aquest metode es privat per forcar el
     * patro singleton.
     */
    private FormulariExternClient() {
        super();
    }

    /**
     * Recupera l objecte singleton.
     *
     * @return objete singleton de la clase CMAIBDocumentOrganismeWsClient.
     */
    public static FormulariExternClient getClient() {
        return client;
    }

    public void setPropertyBase(String propertyBase) {
        this.propertyBase = propertyBase;
    }
    
    

    private static final QName SERVICE_NAME = new QName(DadesConnexioFormulariExtern._QNAME,
            DadesConnexioFormulariExtern._SERVICE_NAME);

    private GuardarFormulari getServicePort() {
        
        URL wsdlURL = null;
        
        final DadesConnexioSOAP dadesConnexio = new DadesConnexioFormulariExtern(propertyBase);

        try {
            wsdlURL = new URL(dadesConnexio.getWsdlLocation());
        } catch (MalformedURLException ex) {
            Logger.getLogger(FormulariExternClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        try {
            wsdlURL = new URL(DadesConnexioFormulariExtern._WSDL_LOCATION);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FormulariExternClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        dadesConnexio.getUserName(),
                        dadesConnexio.getPassword().toCharArray()
                 );
            }
        });
        
        
      
        GuardarFormulariImplService ss = new GuardarFormulariImplService(wsdlURL, SERVICE_NAME);
        GuardarFormulari port = ss.getGuardarFormulariImplPort();

        
        Map<String, Object> req = ((BindingProvider) port).getRequestContext();
       
        req.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, dadesConnexio.getEndPoint());

        req.put(BindingProvider.USERNAME_PROPERTY, dadesConnexio.getUserName());
        req.put(BindingProvider.PASSWORD_PROPERTY, dadesConnexio.getPassword());
        
        
        
        //req.put(BindingProvider.USERNAME_PROPERTY, "idameto");
        //req.put(BindingProvider.PASSWORD_PROPERTY, "idameto");
        
        
        //Map<String, List<String>> headers = new HashMap<String, List<String>>();
        //headers.put("Username", Collections.singletonList(DadesConnexioFormulariExtern._USERNAME));
        //headers.put("Password", Collections.singletonList(DadesConnexioFormulariExtern._PASSWORD));
        //req.put(MessageContext.HTTP_REQUEST_HEADERS, headers);  
        
        //Map<String, List<String>> headers = new HashMap<String, List<String>>();
        //headers.put("Username", Collections.singletonList("idameto"));
        //headers.put("Password", Collections.singletonList("idameto"));
        //req.put(MessageContext.HTTP_REQUEST_HEADERS, headers);  

        return port;

    }
   /*
    public void guardar(String idFormulari, List<IDominiHeliumItem> variables){
        
        GuardarFormulari port = getServicePort();
        
        
    }*/
    
    private static void guardar(GuardarFormulari port, String idFormulari, List<ParellaCodiValor> lVariables){
        port.guardar(idFormulari, lVariables);
    }
    
 
    
    

    /*
    private static String putDocumentOrganisme(CMAIBDocumentOrganismeWs port, DocumentWs _putDocumentOrganisme_document) throws WsValidationException, WsI18NException {

        LOG.log(Level.INFO, "Invoking putDocumentOrganisme...");
        String _putDocumentOrganisme__return = _putDocumentOrganisme__return = port.putDocumentOrganisme(_putDocumentOrganisme_document);
        return _putDocumentOrganisme__return;

    }

    public String putDocumentOrganisme(CMAIBDocument document) {

        CMAIBDocumentOrganismeWs port = getServicePort();

        String response = null;

        DocumentWs documentWs = CMAIBDocumentBean.toDocumentWs(document);

        try {
            response = putDocumentOrganisme(port, documentWs);
        } catch (WsValidationException ex) {
            LOG.log(Level.SEVERE, "Expected exception: WsValidationException has occurred.", ex);
        } catch (WsI18NException ex) {
            LOG.log(Level.SEVERE, "Expected exception: WsI18NException has occurred.", ex);
        }

        return response;
    }
*/
    
    public static void main(String args[]) throws Exception {

        String app = "es.caib.cmaib.";
        
        DadesConnexioFormulariExtern dadesConnexio = new DadesConnexioFormulariExtern(app);
        
        System.setProperty(app + dadesConnexio.getCodClient() + ".username", "");
        System.setProperty(app + dadesConnexio.getCodClient()  + ".password", "");
        System.setProperty(app + dadesConnexio.getCodClient() + ".baseURL", "http://helium.fundaciobit.org/helium");

        System.setProperty(app + dadesConnexio.getCodClient() + ".entorno", "EntornCMAIB");
        System.setProperty(app + dadesConnexio.getCodClient() + ".grupo", "CMI_ADMIN"); 
        
        
        //System.setProperty("es.caib.subdepen.helium.client.entorno", "CONAFESOC");
        //System.setProperty("es.caib.subdepen.helium.client.usuario", "u82545");
        //System.setProperty("es.caib.subdepen.helium.client.grupo", "SDE_TRAMIT_JEFSEC");
        //System.setProperty("es.caib.subdepen.helium.client.baseURL", "http://sdesapplin1.caib.es:28080/helium/ws");

        FormulariExternClient client = FormulariExternClient.getClient();
        
        GuardarFormulari port = client.getServicePort();
        
        LOG.log(Level.INFO, "Resultados0LOK");
        System.out.println("-------------------------Resultados0LOK ");
        
        //List lista = consultaFormulariTasca(port, "EntornCMAIB", "5990");
        //System.out.println(lista);
        
       
        
        
        
//        System.out.println(lista);
        
        
    
    }
    
    
   

    

}
