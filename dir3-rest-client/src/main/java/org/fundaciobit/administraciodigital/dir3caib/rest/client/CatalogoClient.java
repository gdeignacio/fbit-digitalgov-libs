package org.fundaciobit.administraciodigital.dir3caib.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author gdeignacio
 */
public class CatalogoClient {
    
    private String propertyBase = "es.caib.cmaib";

    protected static final Logger LOG = Logger.getLogger(CatalogoClient.class.getName());

    /**
     * Objecte que emmagatzema la instancia de la classe segons el patro
     * singleton
     *
     */
    private static final CatalogoClient client = new CatalogoClient();

    /**
     * Construeix un objecte de la classe. Aquest metode es privat per forcar el
     * patro singleton.
     */
    private CatalogoClient() {
        super();
    }

    /**
     * Recupera l objecte singleton.
     *
     * @return objete singleton de la clase CMAIBDocumentOrganismeWsClient.
     */
    public static CatalogoClient getClient() {
        return client;
    }

    public void setPropertyBase(String propertyBase) {
        this.propertyBase = propertyBase;
    }

    private static final QName SERVICE_NAME = new QName(DadesConnexioTramitacio._QNAME,
            DadesConnexioTramitacio._SERVICE_NAME);

    private TramitacioService getServicePort() {

        URL wsdlURL = null;
        
        final DadesConnexio dadesConnexio = new DadesConnexioTramitacio(propertyBase);

        try {
            wsdlURL = new URL(dadesConnexio.getWsdlLocation());
        } catch (MalformedURLException ex) {
            Logger.getLogger(CatalogoClient.class.getName()).log(Level.SEVERE, null, ex);
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
 
        return port;

    }

    private static void dummy(TramitacioService port) {

        LOG.log(Level.INFO, "Invoking dummy...");
        // port.consultaFormulariTasca(_CODAPP, _CODAPP)

    }

 

    public List<Map<String, Object>> dir3Busqueda(Map parametrosMap) throws I18NException {
        
        List<Map<String, Object>> listaCodigoValor = new ArrayList<Map<String, Object>>();
    
        String urlBase = System.getProperty(dir3PropertyrBaseUrl);
        String urlRest = "/rest/busqueda/unidades/denominacion/comunidad";

        String param = (String) parametrosMap.get("param");
        
        //String denominacionParam = (String) parametrosMap.get("denominacion");
        //String codComunidadParam = (String) parametrosMap.get("codComunidad");

        String denominacion = (denominacionParam != null && !denominacionParam.isEmpty()) ? denominacionParam : "";
        String codComunidad = (codComunidadParam != null && !codComunidadParam.isEmpty()) ? codComunidadParam : "-1";

        try {

            URL url = new URL(urlBase + urlRest + "?denominacion=" + denominacion + "&codComunidad=" + codComunidad);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String output;
            StringBuffer stringResult = new StringBuffer();
            while ((output = br.readLine()) != null) {
                stringResult.append(output);
            }

            String jsonString = stringResult.toString();

            JSONParser parser = new JSONParser();

            try {

                JSONArray jsonUnidades = (JSONArray) parser.parse(jsonString);

                for (Object obj : jsonUnidades) {
                    JSONObject jsonUnidad = (JSONObject) parser.parse(obj.toString());
                    Map<String, Object> unidadMap = new HashMap<String, Object>();
                    unidadMap.put("codigo", jsonUnidad.get("codigo"));
                    unidadMap.put("denominacion", jsonUnidad.get("denominacion"));
                    listaCodigoValor.add(unidadMap);

                }

            } catch (ParseException ex) {
                Logger.getLogger(DominiHeliumLogicaEJB.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                conn.disconnect();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(DominiHeliumLogicaEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DominiHeliumLogicaEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return listaCodigoValor;
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
            Logger.getLogger(CatalogoClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }

  
    
    
    public static void main(String args[]) throws Exception {

        String app = "es.caib.cmaib";
        
        DadesConnexioTramitacio dadesConnexio = new DadesConnexioTramitacio(app);
        
        System.setProperty(app + "." + dadesConnexio.getCodClient() + ".username", "admin");
        System.setProperty(app + "." + dadesConnexio.getCodClient()  + ".password", "admincmaib");
        System.setProperty(app + "." + dadesConnexio.getCodClient() + ".baseURL", "http://helium.fundaciobit.org/helium");

        System.setProperty(app + "." + dadesConnexio.getCodClient() + ".entorno", "EntornCMAIB");
        System.setProperty(app + "." + dadesConnexio.getCodClient() + ".grupo", "CMI_ADMIN");

       
    }

}
