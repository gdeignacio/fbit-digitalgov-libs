package org.fundaciobit.administraciodigital.helium.ws.tramitacio.v1.client;


import static org.fundaciobit.administraciodigital.helium.ws.tramitacio.v1.client.DadesConnexioCMAIBTramitacio._CODAPP;
import static org.fundaciobit.administraciodigital.helium.ws.tramitacio.v1.client.DadesConnexioCMAIBTramitacio._CODCLIENT;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import net.conselldemallorca.helium.ws.tramitacio.v1.ArxiuProces;


import net.conselldemallorca.helium.ws.tramitacio.v1.CampProces;
import net.conselldemallorca.helium.ws.tramitacio.v1.CampTasca;
import net.conselldemallorca.helium.ws.tramitacio.v1.DocumentProces;
import net.conselldemallorca.helium.ws.tramitacio.v1.ExpedientInfo;
import net.conselldemallorca.helium.ws.tramitacio.v1.TascaTramitacio;
import net.conselldemallorca.helium.ws.tramitacio.v1.TramitacioException_Exception;

import net.conselldemallorca.helium.ws.tramitacio.v1.TramitacioService;
import net.conselldemallorca.helium.ws.tramitacio.v1.TramitacioServiceImplService;




/**
 *
 * @author gdeignacio
 */
public class CMAIBTramitacioClient {

    protected static final Logger LOG = Logger.getLogger(CMAIBTramitacioClient.class.getName());

    /**
     * Objecte que emmagatzema la instancia de la classe segons el patro
     * singleton
     *
     */
    private static final CMAIBTramitacioClient client = new CMAIBTramitacioClient();

    /**
     * Construeix un objecte de la classe. Aquest metode es privat per forcar el
     * patro singleton.
     */
    private CMAIBTramitacioClient() {
        super();
    }

    /**
     * Recupera l objecte singleton.
     *
     * @return objete singleton de la clase CMAIBDocumentOrganismeWsClient.
     */
    public static CMAIBTramitacioClient getClient() {
        return client;
    }

    private static final QName SERVICE_NAME = new QName(DadesConnexioCMAIBTramitacio._QNAME,
            DadesConnexioCMAIBTramitacio._SERVICE_NAME);

    private TramitacioService getServicePort() {

        URL wsdlURL = null;

        try {
            wsdlURL = new URL(DadesConnexioCMAIBTramitacio._WSDL_LOCATION);

        } catch (MalformedURLException ex) {
            Logger.getLogger(CMAIBTramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        DadesConnexioCMAIBTramitacio._USERNAME,
                        DadesConnexioCMAIBTramitacio._PASSWORD.toCharArray());
            }
        });

        TramitacioServiceImplService ss = new TramitacioServiceImplService(wsdlURL, SERVICE_NAME);
        TramitacioService port = ss.getTramitacioServiceImplPort();

        Map<String, Object> req = ((BindingProvider) port).getRequestContext();

        req.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                DadesConnexioCMAIBTramitacio._BASE_URL + DadesConnexioCMAIBTramitacio._SERVICE_CONTEXT);

        req.put(BindingProvider.USERNAME_PROPERTY, DadesConnexioCMAIBTramitacio._USERNAME);
        req.put(BindingProvider.PASSWORD_PROPERTY, DadesConnexioCMAIBTramitacio._PASSWORD);

        //req.put(BindingProvider.USERNAME_PROPERTY, "idameto");
        //req.put(BindingProvider.PASSWORD_PROPERTY, "idameto");
        //Map<String, List<String>> headers = new HashMap<String, List<String>>();
        //headers.put("Username", Collections.singletonList(DadesConnexioCMAIBTramitacio._USERNAME));
        //headers.put("Password", Collections.singletonList(DadesConnexioCMAIBTramitacio._PASSWORD));
        //req.put(MessageContext.HTTP_REQUEST_HEADERS, headers);  
        //Map<String, List<String>> headers = new HashMap<String, List<String>>();
        //headers.put("Username", Collections.singletonList("idameto"));
        //headers.put("Password", Collections.singletonList("idameto"));
        //req.put(MessageContext.HTTP_REQUEST_HEADERS, headers);  
        return port;

    }

    private static void dummy(TramitacioService port) {

        LOG.log(Level.INFO, "Invoking dummy...");
        // port.consultaFormulariTasca(_CODAPP, _CODAPP)

    }

    
    private static List<ExpedientInfo> consultaExpedients(TramitacioService port, String entorn, String numero) throws TramitacioException_Exception   {
        
        String _entorn = entorn;
        String _usuari = null;
        String _numero = numero;
        XMLGregorianCalendar _dataInici1 = null;
        XMLGregorianCalendar _dataInici2 = null;
        String _expedientTipusCodi = null;
        String _estatCodi = null;
        boolean _iniciat = false;
        boolean _finalitzat = false;
        Double _geoPosX = null;
        Double _geoPosY = null;
        String _geoReferencia = null;
        
        
        
        List<ExpedientInfo> _consultaExpedients__return = port.consultaExpedients(_entorn,_usuari, _numero, 
                _dataInici1, _dataInici2, _expedientTipusCodi, _estatCodi,
                _iniciat, _finalitzat, _geoPosX, _geoPosY, _geoReferencia);
        
        return _consultaExpedients__return;
        
    }
    
    
    public ExpedientInfo consultaExpedient(String numero) {

        TramitacioService port = getServicePort();
        String entorn = DadesConnexioCMAIBTramitacio._ENTORNO;
        ExpedientInfo response = null;

        try {
            
            List<ExpedientInfo> expedients = consultaExpedients(port, entorn, numero);
            for (ExpedientInfo expedient:expedients){
                response=expedient;
                break;
            }
            
        } catch (TramitacioException_Exception ex) {
            Logger.getLogger(CMAIBTramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }
    
    
    public ArxiuProces getArxiuProces(Long idArxiuProces){
    
        TramitacioService port = getServicePort();
        String idEntorn = DadesConnexioCMAIBTramitacio._ENTORNO;
        ArxiuProces response = null;
        
        try {
            response = getArxiuProces(port, idArxiuProces);
        } catch (TramitacioException_Exception ex) {
            Logger.getLogger(CMAIBTramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
        
    }
    
    
    
    private static ArxiuProces getArxiuProces(TramitacioService port, Long idArxiuProces) throws TramitacioException_Exception {

        ArxiuProces _getArxiuProces__return = port.getArxiuProces(idArxiuProces);
        return _getArxiuProces__return;

    }
    
    
    private static List<DocumentProces> consultarDocumentsProces(TramitacioService port, String idEntorn, String idProces) throws TramitacioException_Exception {

        List<DocumentProces> _consultarDocumentsProces__return = port.consultarDocumentsProces(idEntorn, idProces);
        return _consultarDocumentsProces__return;

    }
    
    public List<DocumentProces> consultarDocumentsProces(String idProces){
    
        TramitacioService port = getServicePort();
        String idEntorn = DadesConnexioCMAIBTramitacio._ENTORNO;
        List<DocumentProces> response = null;
        
        try {
            response = consultarDocumentsProces(port, idEntorn, idProces);
        } catch (TramitacioException_Exception ex) {
            Logger.getLogger(CMAIBTramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
        
    }
    
    
    private static List<CampProces> consultarVariablesProces(TramitacioService port, String idEntorn, String idProces) throws TramitacioException_Exception {

        List<CampProces> _consultarVariablesProces__return = port.consultarVariablesProces(idEntorn, idProces);
        return _consultarVariablesProces__return;
        
    }
    
    public List<CampProces> consultarVariablesProces(String idProces) {

        TramitacioService port = getServicePort();
        String idEntorn = DadesConnexioCMAIBTramitacio._ENTORNO;
        List<CampProces> response = null;

        try {
            response = consultarVariablesProces(port, idEntorn, idProces);
        } catch (TramitacioException_Exception ex) {
            Logger.getLogger(CMAIBTramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }
    
    
    private static List<TascaTramitacio> consultaTasquesGrup(TramitacioService port, String idEntorn) throws TramitacioException_Exception {

        List<TascaTramitacio> _consultaTasquesGrup__return = port.consultaTasquesGrup(idEntorn);
        return _consultaTasquesGrup__return;

    }

    public String consultaTasquesGrup(String idEntorn) {

        TramitacioService port = getServicePort();

        String response = null;

        try {
            response = consultaTasquesGrup(port, idEntorn).toString();
        } catch (TramitacioException_Exception ex) {
            Logger.getLogger(CMAIBTramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }

    private static List<CampTasca> consultaFormulariTasca(TramitacioService port, String idEntorn, String idTasca) throws TramitacioException_Exception {

        List<CampTasca> _consultaFormulariTasca__return = port.consultaFormulariTasca(idEntorn, idTasca);
        return _consultaFormulariTasca__return;

    }

    public String consultaFormulariTasca(String idEntorn, String idTasca) {

        TramitacioService port = getServicePort();

        String response = null;

        try {
            response = consultaFormulariTasca(port, idEntorn, idTasca).toString();
        } catch (TramitacioException_Exception ex) {
            Logger.getLogger(CMAIBTramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
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

        System.setProperty(_CODAPP + "." + _CODCLIENT + ".username", "admin");
        System.setProperty(_CODAPP + "." + _CODCLIENT + ".password", "admincmaib");
        System.setProperty(_CODAPP + "." + _CODCLIENT + ".baseURL", "http://helium.fundaciobit.org/helium");

        System.setProperty(_CODAPP + "." + _CODCLIENT + ".entorno", "EntornCMAIB");
        System.setProperty(_CODAPP + "." + _CODCLIENT + ".grupo", "CMI_ADMIN");

        //System.setProperty("es.caib.subdepen.helium.client.entorno", "CONAFESOC");
        //System.setProperty("es.caib.subdepen.helium.client.usuario", "u82545");
        //System.setProperty("es.caib.subdepen.helium.client.grupo", "SDE_TRAMIT_JEFSEC");
        //System.setProperty("es.caib.subdepen.helium.client.baseURL", "http://sdesapplin1.caib.es:28080/helium/ws");
        CMAIBTramitacioClient client = CMAIBTramitacioClient.getClient();

        TramitacioService port = client.getServicePort();

        

        //List lista = consultaFormulariTasca(port, "EntornCMAIB", "5990");
        //System.out.println(lista);
        
        //port.consultaExpedients(_CODAPP, _CODAPP, _CODAPP, dataInici1, dataInici2, _CODCLIENT, _CODAPP, true, true, Double.NaN, Double.NaN, _CODCLIENT)
        
        String entorn = "EntornCMAIB";
        String usuari = null;
        //String titol = null;
        String numero = "AIA/123-2017";
        XMLGregorianCalendar dataInici1 = null;//GregorianCalendarUtils.timestampToXMLGregorianCalendar(new Timestamp(System.currentTimeMillis()-System.currentTimeMillis()));
        XMLGregorianCalendar dataInici2 = null;// GregorianCalendarUtils.timestampToXMLGregorianCalendar(new Timestamp(System.currentTimeMillis()));
        String expedientTipusCodi = null; //"CMAIB_AIA_SIMPL";
        String estatCodi = null;
        boolean iniciat = false;
        boolean finalitzat = false;
        Double geoPosX = null;
        Double geoPosY = null;
        String geoReferencia = null;
        
        //port.consultaExpedients(usuari, usuari, usuari, dataInici2, dataInici2, usuari, usuari, iniciat, iniciat, geoPosY, geoPosY, numero)
        
        List<ExpedientInfo> expedients = port.consultaExpedients(entorn,usuari, numero , 
                                dataInici1, dataInici2, expedientTipusCodi, estatCodi,
                iniciat, finalitzat, geoPosX, geoPosY,
                geoReferencia);
        
        //Gson gson = new Gson();
        
        for (ExpedientInfo expedient:expedients){
            
            String line = Long.toString(expedient.getProcessInstanceId()) + " " +  expedient.getNumero()  + " " +
                    expedient.getExpedientTipusCodi()  + " " +
                    expedient.getExpedientTipusNom() + " " + expedient.getIdentificador() + " " + 
                    expedient.getEntornCodi() + " " + expedient.getIniciadorCodi() + " " + 
                    expedient.getInteressatNif() + " " + expedient.getInteressatNom();
            
            System.out.println(line);
           
          //  System.out.println(gson.toJson(expedient));
            
        }
        
       
        
        
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("                                     Variables");
        System.out.println("-----------------------------------------------------------------------------------------");
        
        
        List<CampProces> variables = port.consultarVariablesProces("EntornCMAIB", "37185" );
        
        for (CampProces variable:variables){
            
            String linea = variable.getCodi() + " " +  variable.getDominiCampText()  + " " +
                    variable.getDominiCampValor() + " " + variable.getDominiId() + " " + 
                    variable.getObservacions() + " " + variable.getTipus() + " " + 
                    variable.getValor() + " " +//" " + variable.getJbpmAction() + " " + 
                    variable.getDominiParams() + " " + variable.getEtiqueta();
            
            System.out.println(linea);
            
            String line = "public static final DVariablesAiaSimplificada " + variable.getCodi().toUpperCase() +
                      " = new DVariablesAiaSimplificada(\"" +  variable.getCodi() + "\",\"" + variable.getEtiqueta() + "\");"; 
            
            //System.out.println("------------------------------Fin valores");
            
            //System.out.println(line);
        }
        
        
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("                                     Fi Variables");
        System.out.println("-----------------------------------------------------------------------------------------");
        
        
        for (CampProces variable:variables){
            /*
            String line = variable.getCodi() + " " +  variable.getDominiCampText()  + " " +
                    variable.getDominiCampValor() + " " + variable.getDominiId() + " " + 
                    variable.getObservacions() + " " + variable.getTipus() + " " + 
                    variable.getValor() + " " + variable.getJbpmAction() + " " + 
                    variable.getDominiParams() + " " + variable.getEtiqueta();
            */
            
            String line = "DVariablesAiaSimplificada." + variable.getCodi().toUpperCase() + ","; 
                            
            
            System.out.println(line);
        }
        
        
                
        List lista = consultaTasquesGrup(port, "EntornCMAIB");
        System.out.println(lista);

        lista = port.consultaTasquesPersonals("EntornCMAIB");
        
        for (Object tasca : lista) {
            TascaTramitacio task = (TascaTramitacio) tasca;
            System.out.println(task.getCodi() + " " + task.getExpedient() + " " + task.getId() + " " + task.getProcessInstanceId());
            //System.out.println(tasca.toString());
        }
        
        

//        System.out.println(lista);
    }

}
