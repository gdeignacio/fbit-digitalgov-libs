package org.fundaciobit.administraciodigital.helium.ws.tramitacio.v1.client;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.jaxb.JAXBToStringBuilder;
import org.apache.cxf.jaxb.JAXBToStringStyle;
import org.fundaciobit.administraciodigital.utils.cxf.authentication.AuthenticatorReplacer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.fundaciobit.administraciodigital.utils.ws.connexio.DadesConnexioSOAP;

/**
 *
 * @author gdeignacio
 */
public class TramitacioV1Client {
    
    private DadesConnexioSOAP dadesConnexio;

    public DadesConnexioSOAP getDadesConnexio() {
        return dadesConnexio;
    }

    public void setDadesConnexio(DadesConnexioSOAP dadesConnexio) {
        this.dadesConnexio = dadesConnexio;
    }

    protected static final Logger LOG = Logger.getLogger(TramitacioV1Client.class.getName());

    /**
     * Objecte que emmagatzema la instancia de la classe segons el patro
     * singleton
     *
     */
    private static final TramitacioV1Client client = new TramitacioV1Client();

    /**
     * Construeix un objecte de la classe. Aquest metode es privat per forcar el
     * patro singleton.
     */
    private TramitacioV1Client() {
        super();
    }

     /**
     * Recupera l objecte singleton.
     *
     * @return objete singleton de la clase.
     */
    private static TramitacioV1Client _getClient(DadesConnexioTramitacioV1 dadesConnexio) {
        client.setDadesConnexio(dadesConnexio);
        return client;
    }
    
    /**
     * Recupera el singleton amb dadesConnexio prèviament inicialitzat
     * new DadesConnexioTramitacio("foo.bar")
     * properties foo.bar.helium.client.xxx
     * @param dadesConnexio
     * @return 
     * @see DadesConnexioTramitacio
     * @see TramitacioClient
     */
    public static TramitacioV1Client getClient(DadesConnexioTramitacioV1 dadesConnexio) {
        DadesConnexioTramitacioV1 dct = (dadesConnexio!=null)?dadesConnexio:new DadesConnexioTramitacioV1("");
        return _getClient(dct);
    }
    
    /**
     * Recupera el singleton i inicialitza DadesConnexio 
     * new DadesConnexio("")
     * properties helium.client.xxx
     * @return 
     * @see DadesConnexioTramitacio
     * @see TramitacioClient
     */
    public static TramitacioV1Client getClient(){
        DadesConnexioTramitacioV1 dct = new DadesConnexioTramitacioV1("");
        return _getClient(dct);
    }
    


    private static final QName SERVICE_NAME = new QName(DadesConnexioTramitacioV1._QNAME,
            DadesConnexioTramitacioV1._SERVICE_NAME);

    private void getServicePort() {

        /*
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                // Trust always
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                // Trust always
            }
        }};
        // Install the all-trusting trust manager
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(TramitacioV1Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Create empty HostnameVerifier
        HostnameVerifier hv = new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        };

        try {
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (KeyManagementException ex) {
            Logger.getLogger(TramitacioV1Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
        */
        
        AuthenticatorReplacer.verifyHost();
        
        URL wsdlURL = null;

        //final DadesConnexioSOAP dadesConnexio = new DadesConnexioTramitacioV1(propertyBase);

        try {
            LOG.info(dadesConnexio.getWsdlLocation());
            wsdlURL = new URL(dadesConnexio.getWsdlLocation());
        } catch (MalformedURLException ex) {
            Logger.getLogger(TramitacioV1Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        dadesConnexio.getUserName(),
                        dadesConnexio.getPassword().toCharArray()
                );
            }
        });
        */
        
//        String userName = dadesConnexio.getUserName();
//        String password = dadesConnexio.getPassword();
//        
//        AuthenticatorReplacer.setAuthenticator(userName, password);
//        
//        LOG.log(Level.INFO, "Servicio:  {0}", SERVICE_NAME);
//        LOG.log(Level.INFO, "URL: {0}", wsdlURL);
//        
//        TramitacioService_Service ss = new TramitacioService_Service(wsdlURL, SERVICE_NAME);
//        TramitacioService port = ss.getTramitacioPort();
//
//        Map<String, Object> req = ((BindingProvider) port).getRequestContext();
//
//        req.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, dadesConnexio.getEndPoint());
//
//        req.put(BindingProvider.USERNAME_PROPERTY, dadesConnexio.getUserName());
//        req.put(BindingProvider.PASSWORD_PROPERTY, dadesConnexio.getPassword());
//
//        return port;

    }

    private static void dummy(int port) {

        LOG.log(Level.INFO, "Invoking dummy...");

        // port.consultaFormulariTasca(_CODAPP, _CODAPP)
    }

//    public String iniciExpedient(String entorn, String expedientTipus, String usuari,
//            String numero, String titol, List<ParellaCodiValor> valors) {
//
//        LOG.log(Level.INFO, valors.toString());
//        
//        TramitacioService port = getServicePort();
//
//        String response = null;
//
//        try {
//            response = iniciExpedient(port, entorn, expedientTipus, usuari, numero, titol, valors);
//        } catch (TramitacioException_Exception ex) {
//            Logger.getLogger(TramitacioV1Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return response;
//
//    }
//
//    private static String iniciExpedient(TramitacioService port,
//            String entorn, String expedientTipus, String usuari, String numero, String titol,
//            List<ParellaCodiValor> valors) throws TramitacioException_Exception {
//
//        String _entorn = entorn;
//        String _expedientTipus = expedientTipus;
//        String _usuari = usuari;
//        String _numero = numero;
//        String _titol = titol;
//        List<ParellaCodiValor> _valors = valors;
//        
//        
//        String _iniciExpedient__return = port.iniciExpedient(_entorn, _expedientTipus, _usuari, _numero, _titol, _valors);
//        return _iniciExpedient__return;
//
//    }
//
//    public List<TascaTramitacio> consultaTasquesPersonals(String entorn, String usuari) {
//
//        TramitacioService port = getServicePort();
//
//        List<TascaTramitacio> response = new ArrayList<TascaTramitacio>();
//
//        try {
//            response = consultaTasquesPersonals(port, entorn, usuari);
//        } catch (TramitacioException_Exception ex) {
//            Logger.getLogger(TramitacioV1Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return response;
//
//    }
//
//    private static List<TascaTramitacio> consultaTasquesPersonals(TramitacioService port,
//            String entorn, String usuari) throws TramitacioException_Exception {
//
//        String _entorn = entorn;
//        String _usuari = usuari;
//
//        List<TascaTramitacio> _consultaTasquesPersonals__return = port.consultaTasquesPersonals(_entorn, _usuari);
//        return _consultaTasquesPersonals__return;
//    }
//
//    
//
//
//    public List<TascaTramitacio> consultaTasquesGrup(String entorn, String usuari) {
//
//        TramitacioService port = getServicePort();
//
//        List<TascaTramitacio> response = new ArrayList<TascaTramitacio>();
//
//        try {
//            response = consultaTasquesGrup(port, entorn, usuari);
//        } catch (TramitacioException_Exception ex) {
//            Logger.getLogger(TramitacioV1Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return response;
//    }
//    
//    
//    private static List<TascaTramitacio> consultaTasquesGrup(TramitacioService port, String entorn, String usuari) throws TramitacioException_Exception {
//
//        String _entorn = entorn;
//        String _usuari = usuari;
//
//        List<TascaTramitacio> _consultaTasquesGrup__return = port.consultaTasquesGrup(_entorn, _usuari);
//        return _consultaTasquesGrup__return;
//
//    }
//    
//    
//    public List<CampTasca> consultaFormulariTasca(String entorn, String usuari, String tascaId) {
//
//        TramitacioService port = getServicePort();
//
//        List<CampTasca> response = new ArrayList<CampTasca>();
//
//        try {
//            response = consultaFormulariTasca(port, entorn, usuari, tascaId);
//        } catch (TramitacioException_Exception ex) {
//            Logger.getLogger(TramitacioV1Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return response;
//    }
//    
//    
//
//    private static List<CampTasca> consultaFormulariTasca(TramitacioService port, String entorn, String usuari, String tascaId) throws TramitacioException_Exception {
//
//        String _entorn = entorn;
//        String _usuari = usuari;
//        String _tascaId = tascaId;
//        
//        List<CampTasca> _consultaFormulariTasca__return = port.consultaFormulariTasca(_entorn, _usuari, _tascaId);
//        return _consultaFormulariTasca__return;
//
//    }
//
// 
//
//    public CampProces consultarVariableProces(String entorn, String usuari, String processInstanceId, String variable) {
//
//        List<CampProces> variables = consultarVariablesProces(entorn, usuari, processInstanceId);
//
//        for (CampProces cp : variables) {
//            if (cp.getCodi().equals(variable)) {
//                return cp;
//            }
//        }
//
//        return null;
//    }
//
//    public Map<String, CampProces> consultarVariablesProcesMap(String entorn, String usuari, String processInstanceId) {
//
//        Map<String, CampProces> response = new HashMap<String, CampProces>();
//
//        List<CampProces> variables = consultarVariablesProces(entorn, usuari, processInstanceId);
//
//        for (CampProces cp : variables) {
//            response.put(cp.getCodi(), cp);
//        }
//
//        return response;
//    }
//
//    public Map<String, Object> consultarVariablesProcesMapValues(String entorn, String usuari, String processInstanceId) {
//
//        Map<String, Object> response = new HashMap<String, Object>();
//
//        List<CampProces> variables = consultarVariablesProces(entorn, usuari, processInstanceId);
//
//        for (CampProces cp : variables) {
//            response.put(cp.getCodi(), cp.getValor());
//        }
//
//        return response;
//    }
//
//    public String consultarVariablesProcesJson(String entorn, String usuari, String processInstanceId) {
//
//        JSONObject json = new JSONObject();
//
//        List<CampProces> variables = consultarVariablesProces(entorn, usuari, processInstanceId);
//
//        for (CampProces cp : variables) {
//
//            if (cp.getValor() == null) {
//                continue;
//            }
//
//            Object valor = cp.getValor();
//
//            if (cp.getValor() instanceof XMLGregorianCalendar) {
//
//                valor = ((XMLGregorianCalendar) cp.getValor()).toString();
//
//                json.put(cp.getCodi(), valor);
//                continue;
//            }
//
//            if (cp.getValor() instanceof AnyTypeArrayArray) {
//
//                AnyTypeArrayArray aa = (AnyTypeArrayArray) cp.getValor();
//                JSONArray jsonArrayArray = new JSONArray();
//
//                for (AnyTypeArray a : aa.getItem()) {
//                    List<Object> items = new ArrayList<Object>();
//                    for (Object obj : a.getItem()) {
//                        items.add((obj instanceof XMLGregorianCalendar) ? obj.toString() : obj);
//                    }
//                    JSONArray jsonArray = new JSONArray();
//                    jsonArray.addAll(items);
//                    jsonArrayArray.add(jsonArray);
//                }
//
//                valor = jsonArrayArray;
//                json.put(cp.getCodi(), valor);
//                continue;
//            }
//
//            if (cp.getValor() instanceof AnyTypeArray) {
//
//                AnyTypeArray a = (AnyTypeArray) cp.getValor();
//
//                List<Object> items = new ArrayList<Object>();
//                for (Object obj : a.getItem()) {
//                    items.add((obj instanceof XMLGregorianCalendar) ? obj.toString() : obj);
//                }
//
//                JSONArray jsonArray = new JSONArray();
//                jsonArray.addAll(items);
//                valor = jsonArray;
//                json.put(cp.getCodi(), valor);
//                continue;
//            }
//
//            json.put(cp.getCodi(), valor);
//        }
//
//        return json.toJSONString();
//    }
//
//    public List<CampProces> consultarVariablesProces(String entorn, String usuari, String processInstanceId) {
//
//        TramitacioService port = getServicePort();
//        List<CampProces> response = new ArrayList<CampProces>();
//
//        try {
//            response = consultarVariablesProces(port, entorn, usuari, processInstanceId);
//        } catch (TramitacioException_Exception ex) {
//            Logger.getLogger(TramitacioV1Client.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            return response;
//        }
//
//    }
//
//    private static List<CampProces> consultarVariablesProces(TramitacioService port, String entorn, String usuari, String processInstanceId) throws TramitacioException_Exception {
//
//        String _entorn = entorn;
//        String _usuari = usuari;
//        String _processInstanceId = processInstanceId;
//
//        List<CampProces> _consultarVariablesProces__return = port.consultarVariablesProces(_entorn, _usuari, _processInstanceId);
//
//        return _consultarVariablesProces__return;
//
//    }
//
//    public void setVariableProces(String entorn, String usuari, String processInstanceId, String varCodi, Object valor) {
//
//        TramitacioService port = getServicePort();
//
//        try {
//            setVariableProces(port, entorn, usuari, processInstanceId, varCodi, valor);
//        } catch (TramitacioException_Exception ex) {
//            Logger.getLogger(TramitacioV1Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    private static void setVariableProces(TramitacioService port, String entorn, String usuari, String processInstanceId, String varCodi, Object valor) throws TramitacioException_Exception {
//
//        String _entorn = entorn;
//        String _usuari = usuari;
//        String _processInstanceId = processInstanceId;
//        String _varCodi = varCodi;
//        Object _valor = valor;
//
//        port.setVariableProces(_entorn, _usuari, _processInstanceId, _varCodi, _valor);
//    }
//
//    /*
//    private static void setVariableProcesOnHelium(TramitacioService port, String idEntorn, String idProces, String idVariable, String valor) throws TramitacioException_Exception {
//
//        // entorn, usuari, prodesinstanceid, 
//        String idAccio = "setVariable";
//
//        port.executarAccioProces(idEntorn, idProces, idAccio);
//        //port.setVariableProces(idEntorn, idProces, idVariable, valor);
//        return;
//    }
//     */
//    public List<DocumentProces> consultarDocumentsProces(String entorn, String usuari, String processInstanceId) {
//
//        TramitacioService port = getServicePort();
//        List<DocumentProces> response = new ArrayList<DocumentProces>();
//
//        try {
//            response = consultarDocumentsProces(port, entorn, usuari, processInstanceId);
//        } catch (TramitacioException_Exception ex) {
//            Logger.getLogger(TramitacioV1Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return response;
//
//    }
//
//    private static List<DocumentProces> consultarDocumentsProces(TramitacioService port, String entorn, String usuari, String processInstanceId) throws TramitacioException_Exception {
//
//        String _entorn = entorn;
//        String _usuari = usuari;
//        String _processInstanceId = processInstanceId;
//
//        List<DocumentProces> _consultarDocumentsProces__return = port.consultarDocumentsProces(_entorn, _usuari, _processInstanceId);
//        return _consultarDocumentsProces__return;
//
//    }
//
//    public ArxiuProces getArxiuProces(String entorn, Long documentId) {
//
//        TramitacioService port = getServicePort();
//
//        ArxiuProces response = null;
//
//        try {
//            response = getArxiuProces(port, documentId);
//        } catch (TramitacioException_Exception ex) {
//            Logger.getLogger(TramitacioV1Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return response;
//
//    }
//
//    private static ArxiuProces getArxiuProces(TramitacioService port, Long documentId) throws TramitacioException_Exception {
//
//        Long _documentId = documentId;
//        ArxiuProces _getArxiuProces__return = port.getArxiuProces(_documentId);
//        return _getArxiuProces__return;
//
//    }
//
//    public ExpedientInfo consultaExpedient(String entorn, String usuari, String numero) {
//
//        TramitacioService port = getServicePort();
//
//        ExpedientInfo response = null;
//
//        List<ExpedientInfo> expedients = null;
//        try {
//            expedients = consultaExpedients(port, entorn, usuari, numero);
//        } catch (TramitacioException_Exception ex) {
//            Logger.getLogger(TramitacioV1Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        if (expedients == null) {
//            return response;
//        }
//
//        for (ExpedientInfo expedient : expedients) {
//            response = expedient;
//            break;
//        }
//
//        return response;
//    }
//
//    public List<ExpedientInfo> consultaExpedients(String entorn, String usuari, String numero) {
//
//        TramitacioService port = getServicePort();
//
//        List<ExpedientInfo> response = null;
//
//        try {
//            response = consultaExpedients(port, entorn, usuari, numero);
//        } catch (TramitacioException_Exception ex) {
//            Logger.getLogger(TramitacioV1Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return response;
//    }
//
//    private static List<ExpedientInfo> consultaExpedients(TramitacioService port, String entorn, String usuari, String numero) throws TramitacioException_Exception {
//
//        String _entorn = entorn;
//        String _usuari = usuari;
//        String _titol = null;
//        String _numero = numero;
//        //Date _dataInici1 = null;
//        //Date _dataInici2 = null;
//        XMLGregorianCalendar _dataInici1 = null;
//        XMLGregorianCalendar _dataInici2 = null;
//        String _expedientTipusCodi = null;
//        String _estatCodi = null;
//        boolean _iniciat = false;
//        boolean _finalitzat = false;
//        Double _geoPosX = null;
//        Double _geoPosY = null;
//        String _geoReferencia = null;
//
//        List<ExpedientInfo> _consultaExpedients__return = port.consultaExpedients(_entorn, _usuari, _titol, _numero,
//                _dataInici2, _dataInici2, _expedientTipusCodi, _estatCodi, _iniciat, _finalitzat, _geoPosY, _geoPosY, _geoReferencia);
//        return _consultaExpedients__return;
//
//    }

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
        
        
        

//        String app = "es.caib.cmaib.";
//
//        String str = JAXBToStringBuilder.valueOf(app, JAXBToStringStyle.DEFAULT_STYLE);
//
//        DadesConnexioTramitacioV1 dadesConnexio = new DadesConnexioTramitacioV1(app);
//
//        System.setProperty(app + dadesConnexio.getCodClient() + ".username", "$cmaib_helium");
//        System.setProperty(app + dadesConnexio.getCodClient() + ".password", "cmaib_helium");
//        System.setProperty(app + dadesConnexio.getCodClient() + ".baseURL", "https://proves.caib.es/helium");
//
//        System.setProperty(app + dadesConnexio.getCodClient() + ".entorno", "CBMA");
//        System.setProperty(app + dadesConnexio.getCodClient() + ".grupo", "CMI_USER");
//
//        //System.setProperty("es.caib.subdepen.helium.client.entorno", "CONAFESOC");
//        //System.setProperty("es.caib.subdepen.helium.client.usuario", "u82545");
//        //System.setProperty("es.caib.subdepen.helium.client.grupo", "SDE_TRAMIT_JEFSEC");
//        //System.setProperty("es.caib.subdepen.helium.client.baseURL", "http://sdesapplin1.caib.es:28080/helium/ws");
//        TramitacioV1Client client = TramitacioV1Client.getClient();
//
//        TramitacioService port = client.getServicePort();
//
//        //List lista = consultaFormulariTasca(port, "EntornCMAIB", "5990");
//        //System.out.println(lista);
//        //port.consultaExpedients(_CODAPP, _CODAPP, _CODAPP, dataInici1, dataInici2, _CODCLIENT, _CODAPP, true, true, Double.NaN, Double.NaN, _CODCLIENT)
//        String entorn = "CBMA";
//        String usuari = "$cmaib_helium" ;
//        //String titol = null;
//
//        String numero = "";
//
//        String processInstanceId = "50340598";
//       
//        numero = "AIA-7a-2019";  // "AIAs/212a-2017";
//        //numero = "AIAs/212a-2017";  // "AIAs/212a-2017";
//
//        System.out.println("-----------------------------------------------------------------------------------------");
//        System.out.println("                                     Expedient");
//        System.out.println("-----------------------------------------------------------------------------------------");
//
//        ExpedientInfo expediente = client.consultaExpedient(entorn, usuari, numero);
//
//        if (expediente != null) {
//
//            for (Field fi : expediente.getClass().getDeclaredFields()) {
//                fi.setAccessible(true);
//                System.out.println(fi.getName() + ":   " + fi.get(expediente));
//            }
//        }
//
//        numero = "AIA-7a-2019"; 
//
//        System.out.println("-----------------------------------------------------------------------------------------");
//        System.out.println("                                     Expedients");
//        System.out.println("-----------------------------------------------------------------------------------------");
//        String titol = null;
//        //numero = null; 
//        //Date dataInici1 = null;//GregorianCalendarUtils.timestampToXMLGregorianCalendar(new Timestamp(System.currentTimeMillis()-System.currentTimeMillis()));
//        //Date dataInici2 = null;// GregorianCalendarUtils.timestampToXMLGregorianCalendar(new Timestamp(System.currentTimeMillis()));
//        XMLGregorianCalendar dataInici1 = null;//GregorianCalendarUtils.timestampToXMLGregorianCalendar(new Timestamp(System.currentTimeMillis()-System.currentTimeMillis()));
//        XMLGregorianCalendar dataInici2 = null;// GregorianCalendarUtils.timestampToXMLGregorianCalendar(new Timestamp(System.currentTimeMillis()));
//        String expedientTipusCodi = null; //"CMAIB_AIA_SIMPL";
//        String estatCodi = null;
//        boolean iniciat = false;
//        boolean finalitzat = false;
//        Double geoPosX = null;
//        Double geoPosY = null;
//        String geoReferencia = null;
//
//        //port.consultaExpedients(usuari, usuari, usuari, dataInici2, dataInici2, usuari, usuari, iniciat, iniciat, geoPosY, geoPosY, numero)
//        List<ExpedientInfo> expedients = port.consultaExpedients(entorn, usuari, numero, titol, dataInici1, dataInici2, 
//                expedientTipusCodi, estatCodi, iniciat, iniciat, geoPosY, geoPosY, geoReferencia);
//        
//        
//        //Gson gson = new Gson();
//        for (ExpedientInfo expedient : expedients) {
//
//            String line = Long.toString(expedient.getProcessInstanceId()) + " " + expedient.getNumero() + " "
//                    + expedient.getExpedientTipusCodi() + " "
//                    + expedient.getExpedientTipusNom() + " " + expedient.getIdentificador() + " "
//                    + expedient.getEntornCodi() + " " + expedient.getIniciadorCodi() + " "
//                    + expedient.getInteressatNif() + " " + expedient.getInteressatNom();
//
//            System.out.println(line);
//
//            //  System.out.println(gson.toJson(expedient));
//        }
//
//        System.out.println("-----------------------------------------------------------------------------------------");
//        System.out.println("                                     Variables JSON");
//        System.out.println("-----------------------------------------------------------------------------------------");
//
//        System.out.println("-----------------------------------------------------------------------------------------");
//        System.out.println("                                     Fi Variables JSON");
//        System.out.println("-----------------------------------------------------------------------------------------");
//
//        //System.out.println("CONSULTAR VARIABLE PRE");
//        //String strVar;
//        //strVar = TramitacioClient.consultarVariablesProcesJson("EntornCMAIB", "115002");
//        //System.out.println(strVar);
//        //System.out.println("CONSULTAR VARIABLE POST");
//        System.out.println("-----------------------------------------------------------------------------------------");
//        System.out.println("                                     Variables");
//        System.out.println("-----------------------------------------------------------------------------------------");
//
//        /*
//        try {
//            
//            System.out.println("SET VARIABLE PRE");
//            port.setVariableProces("EntornCMAIB", "114171", "osObservacions", "Escrit per WS");
//            System.out.println("SET VARIABLE POST");
//
//        } catch (TramitacioException_Exception ex) {
//            Logger.getLogger(TramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         */
//        List<CampProces> variables = null;
//        //variables = port.consultarVariablesProces("EntornCMAIB","100694" /*"78000"*/ /**"60770"**/ /*"60941"*/ /*"45568"*/ /*"37185"*/);
//
//        try {
//            System.out.println("CONSULTAR VARIABLE PRE");
//            variables = port.consultarVariablesProces(entorn, usuari,  processInstanceId);
//
//            System.out.println(variables);
//
//            System.out.println("CONSULTAR VARIABLE POST");
//            //variables = port.consultarVariablesProces("EntornCMAIB","101022");
//        } catch (TramitacioException_Exception ex) {
//            Logger.getLogger(TramitacioV1Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        if (variables != null) {
//
//            JSONObject json = new JSONObject();
//
//            for (CampProces cp : variables) {
//
//                if (cp.getValor() == null) {
//                    continue;
//                }
//
//                Object valor = cp.getValor();
//
//                if (cp.getValor() instanceof AnyTypeArrayArray) {
//
//                    AnyTypeArrayArray aa = (AnyTypeArrayArray) cp.getValor();
//                    JSONArray jsonArrayArray = new JSONArray();
//
//                    for (AnyTypeArray a : aa.getItem()) {
//                        JSONArray jsonArray = new JSONArray();
//                        jsonArray.addAll(a.getItem());
//                        jsonArrayArray.add(jsonArray);
//                    }
//
//                    valor = jsonArrayArray;
//                    json.put(cp.getCodi(), valor);
//                    continue;
//                }
//
//                if (cp.getValor() instanceof AnyTypeArray) {
//
//                    AnyTypeArray a = (AnyTypeArray) cp.getValor();
//                    JSONArray jsonArray = new JSONArray();
//                    jsonArray.addAll(a.getItem());
//                    valor = jsonArray;
//                    json.put(cp.getCodi(), valor);
//                    continue;
//                }
//
//                json.put(cp.getCodi(), valor);
//            }
//
//            System.out.println(json.toJSONString());
//
//            for (CampProces variable : variables) {
//
//                String linea = variable.getCodi() + " " + variable.getDominiCampText() + " "
//                        + variable.getDominiCampValor() + " " + variable.getDominiId() + " "
//                        + variable.getObservacions() + " " + variable.getTipus() + " "
//                        //  + variable.getValor().toString() + " "
//                        + variable.getValor() + " "
//                        +//" " + variable.getJbpmAction() + " " + 
//                        variable.getDominiParams() + " " + variable.getEtiqueta();
//
//                System.out.println(linea);
//
//                String line = "public static final DVariablesAiaSimplificada " + variable.getCodi().toUpperCase()
//                        + " = new DVariablesAiaSimplificada(\"" + variable.getCodi() + "\",\"" + variable.getEtiqueta() + "\");";
//
//                //System.out.println("------------------------------Fin valores");
//                //System.out.println(line);
//            }
//
//            System.out.println("-----------------------------------------------------------------------------------------");
//            System.out.println("                                     Fi Variables");
//            System.out.println("-----------------------------------------------------------------------------------------");
//
//            for (CampProces variable : variables) {
//                /*
//            String line = variable.getCodi() + " " +  variable.getDominiCampText()  + " " +
//                    variable.getDominiCampValor() + " " + variable.getDominiId() + " " + 
//                    variable.getObservacions() + " " + variable.getTipus() + " " + 
//                    variable.getValor() + " " + variable.getJbpmAction() + " " + 
//                    variable.getDominiParams() + " " + variable.getEtiqueta();
//                 */
//
//                String line = "DVariablesAiaSimplificada." + variable.getCodi().toUpperCase() + ",";
//
//                System.out.println(line);
//            }
//
//            List lista = consultaTasquesGrup(port, entorn, usuari);
//            System.out.println(lista);
//
//            lista = port.consultaTasquesPersonals(entorn, usuari);
//
//            for (Object tasca : lista) {
//                TascaTramitacio task = (TascaTramitacio) tasca;
//                System.out.println(task.getCodi() + " " + task.getExpedient() + " " + task.getId() + " " + task.getProcessInstanceId());
//                //System.out.println(tasca.toString());
//            }
//
////        System.out.println(lista);
//        }

    }

}
