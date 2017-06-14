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
import org.fundaciobit.administraciodigital.utils.ws.connexio.DadesConnexioREST;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author gdeignacio
 */
public class CatalogoDIR3Client {
    
    private String propertyBase = "es.caib.cmaib.";

    protected static final Logger LOG = Logger.getLogger(CatalogoDIR3Client.class.getName());

    /**
     * Objecte que emmagatzema la instancia de la classe segons el patro
     * singleton
     *
     */
    private static final CatalogoDIR3Client client = new CatalogoDIR3Client();

    /**
     * Construeix un objecte de la classe. Aquest metode es privat per forcar el
     * patro singleton.
     */
    private CatalogoDIR3Client() {
        super();
    }

    /**
     * Recupera l objecte singleton.
     *
     * @return objete singleton de la clase CMAIBDocumentOrganismeWsClient.
     */
    public static CatalogoDIR3Client getClient() {
        return client;
    }

    public void setPropertyBase(String propertyBase) {
        this.propertyBase = propertyBase;
    }

    //rivate static final String REST_URL = ;
    
    /*
    private static final QName SERVICE_NAME = new QName(DadesConnexioTramitacio._QNAME,
            DadesConnexioTramitacio._SERVICE_NAME);
    */
    
    /*
    private TramitacioService getServicePort() {

        URL wsdlURL = null;
        
        final DadesConnexio dadesConnexio = new DadesConnexioTramitacio(propertyBase);

        try {
            wsdlURL = new URL(dadesConnexio.getWsdlLocation());
        } catch (MalformedURLException ex) {
            Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.SEVERE, null, ex);
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

       
        
        TramitacioServiceImplService ss = new TramitacioServiceImplService(wsdlURL, SERVICE_NAME);
        TramitacioService port = ss.getTramitacioServiceImplPort();

        Map<String, Object> req = ((BindingProvider) port).getRequestContext();

        req.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, dadesConnexio.getEndPoint());

        req.put(BindingProvider.USERNAME_PROPERTY, dadesConnexio.getUserName());
        req.put(BindingProvider.PASSWORD_PROPERTY, dadesConnexio.getPassword());
 
        return port;

    }
    */
    
    
    private URL getUrl(Map parametrosMap){
        
        final DadesConnexioREST dadesConnexio = new DadesConnexioDIR3(propertyBase);
        
        String endPoint = dadesConnexio.getEndPoint();
        
        String requestMapping= (String)parametrosMap.get("requestMapping");
        String requestParams = (String)parametrosMap.get("requestParams");
        StringBuffer strbUrl = new StringBuffer();
        
        strbUrl.append(endPoint);
        strbUrl.append(requestMapping);
        strbUrl.append(requestParams);
        
        URL url;
        try {
            url = new URL(strbUrl.toString());
            return url;
        } catch (MalformedURLException ex) {
            Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        
        /*
        JSONParser parser = new JSONParser();
        
        try {
            
            
            if (parser.parse(requestParams) instanceof JSONObject)  {
                JSONObject jsonParams = (JSONObject) parser.parse(requestParams);
                String separador = "?";
                for (Object obj : jsonParams.entrySet()) {
                    strbUrl.append(separador);
                    Map.Entry<String, Object> entry = (Map.Entry<String, Object>) obj;
                    strbUrl.append(entry.getKey());
                    strbUrl.append("=");
                    strbUrl.append((String) entry.getValue());
                    separador = "&";
                }
                URL url = new URL(strbUrl.toString());
                return url;
            }
            
            
            
            
        } catch (ParseException ex) {
            Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        */
        
        
        return null;
    }

    private static void dummy(HttpURLConnection conn) {

        LOG.log(Level.INFO, "Invoking dummy...");
        // port.consultaFormulariTasca(_CODAPP, _CODAPP)

    }

    
    public List<Map<String, Object>> list(Map parametrosMap, String codigo, String valor){
        
        URL url = getUrl(parametrosMap);     
        Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.INFO, url.toString());
        return list(url, codigo, valor);
       
    }
    
    private static List<Map<String, Object>> list(URL url, String codigo, String valor) {

        List<Map<String, Object>> listaCodigoValor = new ArrayList<Map<String, Object>>();
        if (url == null) {
            return listaCodigoValor;
        }
        try {
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

            JSONArray jsonUnidades;
            try {
                jsonUnidades = (JSONArray) parser.parse(jsonString);
                for (Object obj : jsonUnidades) {
                    JSONObject jsonUnidad = (JSONObject) parser.parse(obj.toString());
                    Map<String, Object> unidadMap = new HashMap<String, Object>();
                    unidadMap.put("id", jsonUnidad.get(codigo));
                    unidadMap.put("val", jsonUnidad.get(valor));
                    listaCodigoValor.add(unidadMap);
                }
            } catch (ParseException ex) {
                Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                conn.disconnect();
            }

        } catch (IOException ex) {
            Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCodigoValor;
    }
    

    /*
    public List<Map<String, Object>> dir3Busqueda(Map parametrosMap) throws I18NException {
        
        List<Map<String, Object>> listaCodigoValor = new ArrayList<Map<String, Object>>();
    
        String urlBase = System.getProperty(dir3PropertyBaseUrl);
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
    
    */
    
    /*

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
            Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }

*/  
  
    
    
    public static void main(String args[]) throws Exception {

        String app = "es.caib.cmaib.";
        
        DadesConnexioDIR3 dadesConnexio = new DadesConnexioDIR3(app);
        
        System.setProperty(app + dadesConnexio.getCodClient() + ".username", "admin");
        System.setProperty(app + dadesConnexio.getCodClient()  + ".password", "admincmaib");
        System.setProperty(app + dadesConnexio.getCodClient() + ".baseURL", "http://registre3.fundaciobit.org/dir3caib");

        System.setProperty(app +  dadesConnexio.getCodClient() + ".entorno", "EntornCMAIB");
        System.setProperty(app +  dadesConnexio.getCodClient() + ".grupo", "CMI_ADMIN");

        CatalogoDIR3Client client = CatalogoDIR3Client.getClient();

        Map parametrosMap = new HashMap<String, Object>();
        
        parametrosMap.put("requestMapping", "/catalogo/nivelesAdministracion");
        parametrosMap.put("requestParams", "");
        
        //parametrosMap.put("requestParams", "{\"codigo\":\"coduno\", \"valor\":\"valor\"}");
        
        URL url = client.getUrl(parametrosMap);        
        
        System.out.println(url);
        
        List<Map<String, Object>> l = list(url, "id", "descripcion");
        
        for (Map mp: l){
            System.out.println(mp.entrySet());
        }
        
        
        parametrosMap = new HashMap<String, Object>();
        
        parametrosMap.put("requestMapping", "/catalogo/entidadesGeograficas");
        parametrosMap.put("requestParams", "");
        
        
        url = client.getUrl(parametrosMap);        
        
        System.out.println(url);
        
        l = list(url, "id", "descripcion");
        
        for (Map mp: l){
            System.out.println(mp.entrySet());
        }
        
        
        parametrosMap = new HashMap<String, Object>();
        
        parametrosMap.put("requestMapping", "/catalogo/comunidadesAutonomas");
        parametrosMap.put("requestParams", "");
        
        
        url = client.getUrl(parametrosMap);        
        
        System.out.println(url);
        
        l = list(url, "id", "descripcion");
        
        for (Map mp: l){
            System.out.println(mp.entrySet());
        }
        
        parametrosMap = new HashMap<String, Object>();
        
        parametrosMap.put("requestMapping", "/catalogo/provincias/comunidadAutonoma");
        //parametrosMap.put("requestParams", "{\"id\":\"4\"}");
        parametrosMap.put("requestParams", "?id=4");
        
        url = client.getUrl(parametrosMap);        
        
        System.out.println(url);
        
        l = list(url, "id", "descripcion");
        
        for (Map mp: l){
            System.out.println(mp.entrySet());
        }
        
        
        parametrosMap = new HashMap<String, Object>();
        
        parametrosMap.put("requestMapping", "/catalogo/localidades/provincia/entidadGeografica");
        //parametrosMap.put("requestParams", "{\"codigoProvincia\":\"7\", \"codigoEntidadGeografica\":\"01\"}");
        parametrosMap.put("requestParams", "?codigoProvincia=7&codigoEntidadGeografica=01");
        
        url = client.getUrl(parametrosMap);        
        
        System.out.println(url);
        
        l = list(url, "id", "descripcion");
        
        for (Map mp: l){
            System.out.println(mp.entrySet());
        }
        
        
        
   /*
    ResponseEntity<List<Nodo>> busquedaOrganismos(@RequestParam String codigo, @RequestParam String denominacion, @RequestParam Long codNivelAdministracion, @RequestParam Long codComunidadAutonoma, @RequestParam boolean conOficinas, @RequestParam boolean unidadRaiz, @RequestParam Long provincia, @RequestParam String localidad) throws Exception {
*/
        parametrosMap = new HashMap<String, Object>();
        
        parametrosMap.put("requestMapping", "/busqueda/organismos");
        
        StringBuffer par = new StringBuffer();
        /*
        par.append("{");
        par.append("\"codigo\":\"\"");
        par.append("\"denominacion\":\"Catarroja\"");
        par.append("\"codNivelAdministracion\":\"\"");
        par.append("\"codComunidadAutonoma\":\"\"");
        par.append("\"conOficinas\":\"false\"");
        par.append("\"unidadRaiz\":\"false\"");
        par.append("\"provincia\":\"\"");
        par.append("\"localidad\":\"\"");
        par.append("}");
        */
        
        /*
        par.append("{");
        par.append("\"codigo\":\"\"");
        par.append("\"denominacion\":\"\"");
        par.append("\"codNivelAdministracion\":\"3\"");
        par.append("\"codComunidadAutonoma\":\"4\"");
        par.append("\"conOficinas\":\"false\"");
        par.append("\"unidadRaiz\":\"false\"");
        par.append("\"provincia\":\"7\"");
        par.append("\"localidad\":\"407\"");
        par.append("}");
        */
        
        par.append("{");
        par.append("\"codigo\":\"\",");
        par.append("\"denominacion\":\"\",");
        par.append("\"codNivelAdministracion\":\"3\",");
        par.append("\"codComunidadAutonoma\":\"\",");
        par.append("\"conOficinas\":\"false\",");
        par.append("\"unidadRaiz\":\"false\",");
        par.append("\"provincia\":\"\",");
        par.append("\"localidad\":\"407\"");
        par.append("}");
        
        String wpar = "?codigo=&denominacion=&codNivelAdministracion=3&codComunidadAutonoma=&conOficinas=false&unidadRaiz=false&provincia=&localidad=407";
        
        //parametrosMap.put("requestParams",  par.toString());
        parametrosMap.put("requestParams",  wpar);
        
        
        System.out.println(par.toString());
        
        System.out.println(par.toString().replace("\"", "_").replace(":", "-").replace(",", "|"));
        
        //parametrosMap.put("requestParams",  "{\"codnivelAdministracion\":\"3\", \"codComunidadAutonoma\":\"4\", \"provincia\":\"7\", \"localidad\":\"407\"}");
        //parametrosMap.put("requestParams",  "{}");
        
        
        //http://localhost:8080/dir3caib/rest/busqueda/organismos?
        
        //codigo=&denominacion=Catarroja&codNivelAdministracion=&codComunidadAutonoma=&conOficinas=false&unidadRaiz=false&provincia=&localidad=
        
        url = client.getUrl(parametrosMap);        
        
        System.out.println(url);
        
        
        l = list(url, "codigo", "denominacion");
        
        for (Map mp: l){
            System.out.println(mp.entrySet());
        }
        
        
        
        
        
        
        
    }

}
