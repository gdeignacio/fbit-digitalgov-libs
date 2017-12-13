package org.fundaciobit.administraciodigital.helium.ws.tramitacio.v1.client;

import java.lang.reflect.Field;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
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
import net.java.dev.jaxb.array.AnyTypeArray;
import net.java.dev.jaxb.array.AnyTypeArrayArray;
import org.apache.cxf.jaxb.JAXBToStringBuilder;
import org.apache.cxf.jaxb.JAXBToStringStyle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.fundaciobit.administraciodigital.utils.ws.connexio.DadesConnexioSOAP;

/**
 *
 * @author gdeignacio
 */
public class TramitacioClient {

    private String propertyBase = "es.caib.cmaib.";

    protected static final Logger LOG = Logger.getLogger(TramitacioClient.class.getName());

    /**
     * Objecte que emmagatzema la instancia de la classe segons el patro
     * singleton
     *
     */
    private static final TramitacioClient client = new TramitacioClient();

    /**
     * Construeix un objecte de la classe. Aquest metode es privat per forcar el
     * patro singleton.
     */
    private TramitacioClient() {
        super();
    }

    /**
     * Recupera l objecte singleton.
     *
     * @return objete singleton de la clase CMAIBDocumentOrganismeWsClient.
     */
    public static TramitacioClient getClient() {
        return client;
    }

    public void setPropertyBase(String propertyBase) {
        this.propertyBase = propertyBase;
    }

    private static final QName SERVICE_NAME = new QName(DadesConnexioTramitacio._QNAME,
            DadesConnexioTramitacio._SERVICE_NAME);

    private TramitacioService getServicePort() {

        URL wsdlURL = null;

        final DadesConnexioSOAP dadesConnexio = new DadesConnexioTramitacio(propertyBase);

        try {
            wsdlURL = new URL(dadesConnexio.getWsdlLocation());
        } catch (MalformedURLException ex) {
            Logger.getLogger(TramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        dadesConnexio.getUserName(),
                        dadesConnexio.getPassword().toCharArray()
                );
            }
        });

        /*
         btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anteriorContacto();
            }
});
         */
        TramitacioServiceImplService ss = new TramitacioServiceImplService(wsdlURL, SERVICE_NAME);
        TramitacioService port = ss.getTramitacioServiceImplPort();

        Map<String, Object> req = ((BindingProvider) port).getRequestContext();

        req.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, dadesConnexio.getEndPoint());

        req.put(BindingProvider.USERNAME_PROPERTY, dadesConnexio.getUserName());
        req.put(BindingProvider.PASSWORD_PROPERTY, dadesConnexio.getPassword());

        //req.put("com.sun.xml.ws.request.timeout", 60000);
        //req.put("com.sun.xml.ws.connect.timeout", 60000);
        return port;

    }

    private static void dummy(TramitacioService port) {

        LOG.log(Level.INFO, "Invoking dummy...");

        // port.consultaFormulariTasca(_CODAPP, _CODAPP)
    }

    public List<ExpedientInfo> consultaExpedients(String idEntorn, String numero) {

        TramitacioService port = getServicePort();

        List<ExpedientInfo> response = null;

        try {
            response = consultaExpedients(port, idEntorn, numero);
        } catch (TramitacioException_Exception ex) {
            Logger.getLogger(TramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;

    }

    private static List<ExpedientInfo> consultaExpedients(TramitacioService port, String entorn, String numero) throws TramitacioException_Exception {

        String _entorn = entorn;
        String _usuari = null;
        String _numero = numero;
        //Date _dataInici1 = null;
        //Date _dataInici2 = null;
        XMLGregorianCalendar _dataInici1 = null;
        XMLGregorianCalendar _dataInici2 = null;
        String _expedientTipusCodi = null;
        String _estatCodi = null;
        boolean _iniciat = false;
        boolean _finalitzat = false;
        Double _geoPosX = null;
        Double _geoPosY = null;
        String _geoReferencia = null;

        List<ExpedientInfo> _consultaExpedients__return = port.consultaExpedients(_entorn, _usuari, _numero,
                _dataInici1, _dataInici2, _expedientTipusCodi, _estatCodi,
                _iniciat, _finalitzat, _geoPosX, _geoPosY, _geoReferencia);

        return _consultaExpedients__return;

    }

    public ExpedientInfo consultaExpedient(String idEntorn, String numero) {

        TramitacioService port = getServicePort();

        ExpedientInfo response = null;

        List<ExpedientInfo> expedients = null;
        try {
            expedients = consultaExpedients(port, idEntorn, numero);
        } catch (TramitacioException_Exception ex) {
            Logger.getLogger(TramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (ExpedientInfo expedient : expedients) {
            response = expedient;
            break;
        }

        return response;
    }

    public CampProces consultarVariableProces(String idEntorn, String idProces, String variable) {

        List<CampProces> variables = consultarVariablesProces(idEntorn, idProces);

        for (CampProces cp : variables) {
            if (cp.getCodi().equals(variable)) {
                return cp;
            }
        }

        return null;
    }

    public Map<String, CampProces> consultarVariablesProcesMap(String idEntorn, String idProces) {

        Map<String, CampProces> response = new HashMap<String, CampProces>();

        List<CampProces> variables = consultarVariablesProces(idEntorn, idProces);

        for (CampProces cp : variables) {
            response.put(cp.getCodi(), cp);
        }

        return response;
    }

    public Map<String, Object> consultarVariablesProcesMapValues(String idEntorn, String idProces) {

        Map<String, Object> response = new HashMap<String, Object>();

        List<CampProces> variables = consultarVariablesProces(idEntorn, idProces);

        for (CampProces cp : variables) {
            response.put(cp.getCodi(), cp.getValor());
        }

        return response;
    }

    public String consultarVariablesProcesJson(String idEntorn, String idProces) {

        JSONObject json = new JSONObject();

        //Map<String, Object> variablesMap = new HashMap<String, Object>();
        List<CampProces> variables = consultarVariablesProces(idEntorn, idProces);

        for (CampProces cp : variables) {

            if (cp.getValor() == null) {
                continue;
            }

            Object valor = cp.getValor();
            
            if (cp.getValor() instanceof XMLGregorianCalendar) {

                valor = ((XMLGregorianCalendar)cp.getValor()).toString();
                
                json.put(cp.getCodi(), valor);
                continue;
            }
            

            if (cp.getValor() instanceof AnyTypeArrayArray) {

                AnyTypeArrayArray aa = (AnyTypeArrayArray) cp.getValor();
                JSONArray jsonArrayArray = new JSONArray();

                for (AnyTypeArray a : aa.getItem()) {
                    List<Object> items = new ArrayList<Object>();
                    for (Object obj: a.getItem()){
                        items.add((obj instanceof XMLGregorianCalendar)?obj.toString():obj);
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.addAll(items);
                    jsonArrayArray.add(jsonArray);
                }

                valor = jsonArrayArray;
                json.put(cp.getCodi(), valor);
                continue;
            }

            if (cp.getValor() instanceof AnyTypeArray) {

                AnyTypeArray a = (AnyTypeArray) cp.getValor();
                
                List<Object> items = new ArrayList<Object>();
                for (Object obj: a.getItem()){
                    items.add((obj instanceof XMLGregorianCalendar)?obj.toString():obj);
                }
                
                JSONArray jsonArray = new JSONArray();
                jsonArray.addAll(a.getItem());
                valor = jsonArray;
                json.put(cp.getCodi(), valor);
                continue;
            }

            json.put(cp.getCodi(), valor);
        }

        return json.toJSONString();
    }

    public void setVariableProces(String idEntorn, String idProces, String idVariable, String valor) {

        TramitacioService port = getServicePort();

        try {
            setVariableProces(port, idEntorn, idProces, idVariable, valor);
        } catch (TramitacioException_Exception ex) {
            Logger.getLogger(TramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return;

    }

    private static void setVariableProces(TramitacioService port, String idEntorn, String idProces, String idVariable, String valor) throws TramitacioException_Exception {

        port.setVariableProces(idEntorn, idProces, idVariable, valor);
        return;
    }

    private static void setVariableProcesOnHelium(TramitacioService port, String idEntorn, String idProces, String idVariable, String valor) throws TramitacioException_Exception {

        // entorn, usuari, prodesinstanceid, 
        String idAccio = "setVariable";

        port.executarAccioProces(idEntorn, idProces, idAccio);
        //port.setVariableProces(idEntorn, idProces, idVariable, valor);
        return;
    }

    public ArxiuProces getArxiuProces(String idEntorn, Long idArxiuProces) {

        TramitacioService port = getServicePort();

        ArxiuProces response = null;

        try {
            response = getArxiuProces(port, idArxiuProces);
        } catch (TramitacioException_Exception ex) {
            Logger.getLogger(TramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<DocumentProces> consultarDocumentsProces(String idEntorn, String idProces) {

        TramitacioService port = getServicePort();
        List<DocumentProces> response = new ArrayList<DocumentProces>();

        try {
            response = consultarDocumentsProces(port, idEntorn, idProces);
        } catch (TramitacioException_Exception ex) {
            Logger.getLogger(TramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;

    }

    private static List<CampProces> consultarVariablesProces(TramitacioService port, String idEntorn, String idProces) throws TramitacioException_Exception {

        /*
        btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anteriorContacto();
            }
        });
         */
        List<CampProces> _consultarVariablesProces__return = port.consultarVariablesProces(idEntorn, idProces);
        return _consultarVariablesProces__return;

    }

    public List<CampProces> consultarVariablesProces(String idEntorn, String idProces) {

        TramitacioService port = getServicePort();
        List<CampProces> response = new ArrayList<CampProces>();

        try {
            response = consultarVariablesProces(port, idEntorn, idProces);
        } catch (TramitacioException_Exception ex) {
            Logger.getLogger(TramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return response;
        }

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
            Logger.getLogger(TramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
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

        String app = "es.caib.cmaib.";

        String str = JAXBToStringBuilder.valueOf(app, JAXBToStringStyle.DEFAULT_STYLE);

        DadesConnexioTramitacio dadesConnexio = new DadesConnexioTramitacio(app);

        System.setProperty(app + dadesConnexio.getCodClient() + ".username", "admin");
        System.setProperty(app + dadesConnexio.getCodClient() + ".password", "admincmaib");
        System.setProperty(app + dadesConnexio.getCodClient() + ".baseURL", "http://helium.fundaciobit.org/helium");

        System.setProperty(app + dadesConnexio.getCodClient() + ".entorno", "EntornCMAIB");
        System.setProperty(app + dadesConnexio.getCodClient() + ".grupo", "CMI_ADMIN");

        //System.setProperty("es.caib.subdepen.helium.client.entorno", "CONAFESOC");
        //System.setProperty("es.caib.subdepen.helium.client.usuario", "u82545");
        //System.setProperty("es.caib.subdepen.helium.client.grupo", "SDE_TRAMIT_JEFSEC");
        //System.setProperty("es.caib.subdepen.helium.client.baseURL", "http://sdesapplin1.caib.es:28080/helium/ws");
        TramitacioClient client = TramitacioClient.getClient();

        TramitacioService port = client.getServicePort();

        //List lista = consultaFormulariTasca(port, "EntornCMAIB", "5990");
        //System.out.println(lista);
        //port.consultaExpedients(_CODAPP, _CODAPP, _CODAPP, dataInici1, dataInici2, _CODCLIENT, _CODAPP, true, true, Double.NaN, Double.NaN, _CODCLIENT)
        String entorn = "EntornCMAIB";
        String usuari = null;
        //String titol = null;

        String numero = "";

        numero = "AIAs/261a-2017";
        numero = "AIAs/288a-2017";  // "AIAs/212a-2017";
        //numero = "AIAs/212a-2017";  // "AIAs/212a-2017";

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("                                     Expedient");
        System.out.println("-----------------------------------------------------------------------------------------");

        ExpedientInfo expediente = client.consultaExpedient(entorn, numero);

        for (Field fi : expediente.getClass().getDeclaredFields()) {
            fi.setAccessible(true);
            System.out.println(fi.getName() + ":   " + fi.get(expediente));
        }

        numero = "AIAs/288a-2017";

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("                                     Expedients");
        System.out.println("-----------------------------------------------------------------------------------------");

        //numero = null; 
        //Date dataInici1 = null;//GregorianCalendarUtils.timestampToXMLGregorianCalendar(new Timestamp(System.currentTimeMillis()-System.currentTimeMillis()));
        //Date dataInici2 = null;// GregorianCalendarUtils.timestampToXMLGregorianCalendar(new Timestamp(System.currentTimeMillis()));
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
        List<ExpedientInfo> expedients = port.consultaExpedients(entorn, usuari, numero,
                dataInici1, dataInici2, expedientTipusCodi, estatCodi,
                iniciat, finalitzat, geoPosX, geoPosY,
                geoReferencia);

        //Gson gson = new Gson();
        for (ExpedientInfo expedient : expedients) {

            String line = Long.toString(expedient.getProcessInstanceId()) + " " + expedient.getNumero() + " "
                    + expedient.getExpedientTipusCodi() + " "
                    + expedient.getExpedientTipusNom() + " " + expedient.getIdentificador() + " "
                    + expedient.getEntornCodi() + " " + expedient.getIniciadorCodi() + " "
                    + expedient.getInteressatNif() + " " + expedient.getInteressatNom();

            System.out.println(line);

            //  System.out.println(gson.toJson(expedient));
        }
        
        
        
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("                                     Variables JSON");
        System.out.println("-----------------------------------------------------------------------------------------");
        
        
        
        
        
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("                                     Fi Variables JSON");
        System.out.println("-----------------------------------------------------------------------------------------");
        
        
        //System.out.println("CONSULTAR VARIABLE PRE");
        //String strVar;
        //strVar = TramitacioClient.consultarVariablesProcesJson("EntornCMAIB", "115002");
        //System.out.println(strVar);
        //System.out.println("CONSULTAR VARIABLE POST");
        

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("                                     Variables");
        System.out.println("-----------------------------------------------------------------------------------------");

        /*
        try {
            
            System.out.println("SET VARIABLE PRE");
            port.setVariableProces("EntornCMAIB", "114171", "osObservacions", "Escrit per WS");
            System.out.println("SET VARIABLE POST");

        } catch (TramitacioException_Exception ex) {
            Logger.getLogger(TramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        List<CampProces> variables = null;
        //variables = port.consultarVariablesProces("EntornCMAIB","100694" /*"78000"*/ /**"60770"**/ /*"60941"*/ /*"45568"*/ /*"37185"*/);

        try {
            System.out.println("CONSULTAR VARIABLE PRE");
            variables = port.consultarVariablesProces("EntornCMAIB", "115002");

          
            
            System.out.println(variables);

            System.out.println("CONSULTAR VARIABLE POST");
            //variables = port.consultarVariablesProces("EntornCMAIB","101022");
        } catch (TramitacioException_Exception ex) {
            Logger.getLogger(TramitacioClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (variables != null) {

            JSONObject json = new JSONObject();

            for (CampProces cp : variables) {

                if (cp.getValor() == null) {
                    continue;
                }

                Object valor = cp.getValor();

                if (cp.getValor() instanceof AnyTypeArrayArray) {

                    AnyTypeArrayArray aa = (AnyTypeArrayArray) cp.getValor();
                    JSONArray jsonArrayArray = new JSONArray();

                    for (AnyTypeArray a : aa.getItem()) {
                        JSONArray jsonArray = new JSONArray();
                        jsonArray.addAll(a.getItem());
                        jsonArrayArray.add(jsonArray);
                    }

                    valor = jsonArrayArray;
                    json.put(cp.getCodi(), valor);
                    continue;
                }

                if (cp.getValor() instanceof AnyTypeArray) {

                    AnyTypeArray a = (AnyTypeArray) cp.getValor();
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.addAll(a.getItem());
                    valor = jsonArray;
                    json.put(cp.getCodi(), valor);
                    continue;
                }

                json.put(cp.getCodi(), valor);
            }

            System.out.println(json.toJSONString());

            for (CampProces variable : variables) {

                String linea = variable.getCodi() + " " + variable.getDominiCampText() + " "
                        + variable.getDominiCampValor() + " " + variable.getDominiId() + " "
                        + variable.getObservacions() + " " + variable.getTipus() + " "
                        //  + variable.getValor().toString() + " "
                        + variable.getValor() + " "
                        +//" " + variable.getJbpmAction() + " " + 
                        variable.getDominiParams() + " " + variable.getEtiqueta();

                System.out.println(linea);

                String line = "public static final DVariablesAiaSimplificada " + variable.getCodi().toUpperCase()
                        + " = new DVariablesAiaSimplificada(\"" + variable.getCodi() + "\",\"" + variable.getEtiqueta() + "\");";

                //System.out.println("------------------------------Fin valores");
                //System.out.println(line);
            }

            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("                                     Fi Variables");
            System.out.println("-----------------------------------------------------------------------------------------");

            for (CampProces variable : variables) {
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

}
