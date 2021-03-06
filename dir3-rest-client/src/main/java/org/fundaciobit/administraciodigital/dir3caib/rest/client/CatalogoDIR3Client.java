package org.fundaciobit.administraciodigital.dir3caib.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fundaciobit.administraciodigital.utils.cxf.authentication.AuthenticatorReplacer;
import org.fundaciobit.administraciodigital.utils.util.Strings;
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
    
    private DadesConnexioREST dadesConnexio;

    public DadesConnexioREST getDadesConnexio() {
        return dadesConnexio;
    }

    public void setDadesConnexio(DadesConnexioREST dadesConnexio) {
        this.dadesConnexio = dadesConnexio;
    }
    
    private final static String NO_DIR3 = "NDIR3";
    
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
     * @return objete singleton de la clase.
     */
    private static CatalogoDIR3Client _getClient(DadesConnexioDIR3 dadesConnexio) {
        client.setDadesConnexio(dadesConnexio);
        return client;
    }
    
    /**
     * Recupera el singleton amb dadesConnexio prèviament inicialitzat
     * new DadesConnexioDIR3("foo.bar")
     * properties foo.bar.dir3caib.client.xxx
     * @param dadesConnexio
     * @return 
     * @see DadesConnexioDIR3
     * @see CatalogoDIR3Client
     */
    public static CatalogoDIR3Client getClient(DadesConnexioDIR3 dadesConnexio) {
        DadesConnexioDIR3 dct = (dadesConnexio!=null)?dadesConnexio:new DadesConnexioDIR3("");
        return _getClient(dct);
    }
    
    /**
     * Recupera el singleton i inicialitza DadesConnexio 
     * new DadesConnexio("")
     * properties dir3caib.client.xxx
     * @return 
     * @see DadesConnexioDIR3
     * @see CatalogoDIR3Client
     */
    public static CatalogoDIR3Client getClient(){
        DadesConnexioDIR3 dct = new DadesConnexioDIR3("");
        return _getClient(dct);
    }
    
    
    private URL getUrl(String requestMapping, String requestParams){
        
        //final DadesConnexioREST dadesConnexio = new DadesConnexioDIR3(propertyBase);
        
        String endPoint = dadesConnexio.getEndPoint();
        
        //String requestMapping= (String)parametrosMap.get("requestMapping");
        //String requestParams = (String)parametrosMap.get("requestParams");
        
        StringBuilder strbUrl = new StringBuilder();
        
        strbUrl.append(endPoint);
        strbUrl.append(requestMapping);
        strbUrl.append(requestParams);
        
        AuthenticatorReplacer.verifyHost();
        
        URL url;
        try {
            url = new URL(strbUrl.toString());
            return url;
        } catch (MalformedURLException ex) {
            Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
      private boolean isDir3(Map parametrosMap){
        
        String requestMapping= (String)parametrosMap.get("requestMapping");
        String requestParams = (String)parametrosMap.get("requestParams");
        
        for (Object key:parametrosMap.keySet()){
            String param = (String)key;
            if (param.startsWith("arg")){
                String valor = (String)parametrosMap.get(key);
                if (NO_DIR3.equals(valor)){
                    return false;
                }
            }
            if (param.startsWith("arg0")){
                String valor = (String)parametrosMap.get(key);
                if ((valor == null) || "".equals(valor)){
                    return false;
                }
            }
        }
        return true;
    }
    
    
    private URL getUrl(Map parametrosMap){
        
        String requestMapping= (String)parametrosMap.get("requestMapping");
        String requestParams = (String)parametrosMap.get("requestParams");
        
        for (Object key:parametrosMap.keySet()){
            String param = (String)key;
            if (param.startsWith("arg")){
                requestParams = requestParams.replaceAll(param, (String)parametrosMap.get(key));
            }
        }
        
        return getUrl(requestMapping, requestParams);
    }
    
   

    private static void dummy(HttpURLConnection conn) {

        LOG.log(Level.INFO, "Invoking dummy...");
        // port.consultaFormulariTasca(_CODAPP, _CODAPP)

    }

    public String getByCodigo(String codigo){
        
        
        //List<Map<String, Object>> l = list(url, "id", "descripcion");
        //return l;
        
        if (codigo == null) return null;
        
        if ("".equals(codigo)) return "";
        
        if (NO_DIR3.equals(codigo)) return "Altres";
        
        Map parametrosMap = new HashMap<String, Object>();
        
        String requestMapping = "/unidad/denominacion";
        String requestParams = "?codigo=" + codigo;
        
        parametrosMap.put("requestMapping", requestMapping);
        parametrosMap.put("requestParams", requestParams);
        
        Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.INFO, "{0}{1}", new Object[]{requestMapping, requestParams});
        
        URL url = getUrl(parametrosMap);
        
        //URL url = getUrl(parametrosMap);     
        Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.INFO, url.toString());
        return getByCodigo(url);
       
    }
    
    
    private static String getByCodigo(URL url){
    
        String denominacion = null;
        
        try {
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                return denominacion;
                //throw new RuntimeException("Failed : HTTP error code : "
                //        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String output;
            StringBuilder stringResult = new StringBuilder();
            while ((output = br.readLine()) != null) {
                stringResult.append(output);
            }

            String jsonString = stringResult.toString();
            
            denominacion = jsonString;
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return denominacion;
    }
    
    public List<Map<String, Object>> getNivelesAdministracion(){
        
        Map parametrosMap = new HashMap<String, Object>();
        
        parametrosMap.put("requestMapping", "/catalogo/nivelesAdministracion");
        parametrosMap.put("requestParams", "");
        
        URL url = getUrl(parametrosMap);        
        List<Map<String, Object>> l = list(url, "id", "descripcion");
        return l;
    }
    
    public List<Map<String, Object>> getEntidadesGeograficas(){
        
        Map parametrosMap = new HashMap<String, Object>();
        
        parametrosMap.put("requestMapping", "/catalogo/entidadesGeograficas");
        parametrosMap.put("requestParams", "");
        
        URL url = getUrl(parametrosMap);        
        List<Map<String, Object>> l = list(url, "id", "descripcion");
        return l;
    }
    
    
    public List<Map<String, Object>> getComunidadesAutonomas(){
        
        Map parametrosMap = new HashMap<String, Object>();
        
        parametrosMap.put("requestMapping", "/catalogo/comunidadesAutonomas");
        parametrosMap.put("requestParams", "");
        
        URL url = getUrl(parametrosMap);        
        List<Map<String, Object>> l = list(url, "id", "descripcion");
        return l;
    }
    
    public List<Map<String, Object>> getProvinciasByComunidadAutonoma(String comunidadautonoma) {

        String id = (comunidadautonoma!=null)?comunidadautonoma:"4";
        
        Map parametrosMap = new HashMap<String, Object>();

        parametrosMap.put("requestMapping", "/catalogo/provincias/comunidadAutonoma");
        parametrosMap.put("requestParams", "?id=".concat(id));

        URL url = getUrl(parametrosMap);
        List<Map<String, Object>> l = list(url, "id", "descripcion");
        return l;
    }
    
    
     public List<Map<String, Object>> getProvinciasIllesBalears() {

        Map parametrosMap = new HashMap<String, Object>();

        parametrosMap.put("requestMapping", "/catalogo/provincias/comunidadAutonoma");
        parametrosMap.put("requestParams", "?id=4");

        URL url = getUrl(parametrosMap);
        List<Map<String, Object>> l = list(url, "id", "descripcion");
        return l;
    }
    
     
    public List<Map<String, Object>> getMunicipiosIllesBalears() {

        Map parametrosMap = new HashMap<String, Object>();

        parametrosMap.put("requestMapping", "/catalogo/localidades/provincia/entidadGeografica");
        parametrosMap.put("requestParams", "?codigoProvincia=7&codigoEntidadGeografica=01");
        

        URL url = getUrl(parametrosMap);
        List<Map<String, Object>> l = list(url, "id", "descripcion");
        return l;
    }
    
    
     public List<Map<String, Object>> getLocalidadesByProvinciaEntidadGeografica(String codigoProvincia, String codigoEntidadGeografica) {

        String idProvincia = (codigoProvincia!=null)?codigoProvincia:"7";
        String idEntidadGeografica = (codigoEntidadGeografica!=null)?codigoEntidadGeografica:"01"; 
        
        Map parametrosMap = new HashMap<String, Object>();

        parametrosMap.put("requestMapping", "/catalogo/localidades/provincia/entidadGeografica");
        parametrosMap.put("requestParams", "?codigoProvincia=" + idProvincia + "&codigoEntidadGeografica="+ idEntidadGeografica);
        
        URL url = getUrl(parametrosMap);
        List<Map<String, Object>> l = list(url, "id", "descripcion");
        return l;
    }
    
    
    public List<Map<String, Object>> getCodigosByFiltro(String codigo,
            String denominacion, String codNivelAdministracion, String codComunidadAutonoma, String provincia, String localidad) {
        
        String id = (codigo!=null)?codigo:"";
        String idDenominacion = (denominacion!=null)?denominacion:"";
        String idNivelAdministracion = (codNivelAdministracion!=null)?codNivelAdministracion:"3";
        String idComunidadAutonoma = (codComunidadAutonoma!=null)?codComunidadAutonoma:"4";
        String idProvincia = (provincia!=null)?provincia:"7";
        String idLocalidad = (localidad!=null)?localidad:"407"; 
        
        Map parametrosMap = new HashMap<String, Object>();

        parametrosMap.put("requestMapping", "/busqueda/organismos");
        
        String wpar = "?codigo=" + id + "&denominacion=" + idDenominacion 
                + "&codNivelAdministracion=" + idNivelAdministracion 
                + "&codComunidadAutonoma=" + idComunidadAutonoma 
                + "&conOficinas=false&unidadRaiz=false&provincia=" + idProvincia 
                + "&localidad=" + idLocalidad + "&vigentes=true";
        
        parametrosMap.put("requestParams",  wpar);
        
        URL url = getUrl(parametrosMap);
        List<Map<String, Object>> l = list(url, "codigo", "denominacion");
        return l;
    }
     
    public List<Map<String, Object>> getOrganismosByFiltro(String codigo,
            String denominacion, String codNivelAdministracion, String codComunidadAutonoma, String provincia, String localidad) {
        
        String id = (codigo!=null)?codigo:"";
        String idDenominacion = (denominacion!=null)?denominacion:"";
        String idNivelAdministracion = (codNivelAdministracion!=null)?codNivelAdministracion:"";
        String idComunidadAutonoma = (codComunidadAutonoma!=null)?codComunidadAutonoma:"";
        String idProvincia = (provincia!=null)?provincia:"";
        String idLocalidad = (localidad!=null)?localidad:""; 
        
        Map parametrosMap = new HashMap<String, Object>();

        parametrosMap.put("requestMapping", "/busqueda/organismos");
        
        String wpar = "?codigo=" + id + "&denominacion=" + idDenominacion 
                + "&codNivelAdministracion=" + idNivelAdministracion 
                + "&codComunidadAutonoma=" + idComunidadAutonoma 
                + "&conOficinas=false&unidadRaiz=false&provincia=" + idProvincia 
                + "&localidad=" + idLocalidad + "&vigentes=true";
        
        parametrosMap.put("requestParams",  wpar);
        
        URL url = getUrl(parametrosMap);
        List<Map<String, Object>> l = list(url);
        return l;
    }
     
    
    
    
    
    public List<Map<String, Object>> list(Map parametrosMap, String codigo, String valor){
        
        if (!isDir3(parametrosMap)){
            return new ArrayList<Map<String, Object>>();
        }
        
        URL url = getUrl(parametrosMap);     
        Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.INFO, url.toString());
        return list(url, codigo, valor);
       
    }
    
    
    private static List<Map<String, Object>> list(URL url) {

        List<Map<String, Object>> listaCodigoValor = new ArrayList<Map<String, Object>>();
        
        if (url == null) {
            return listaCodigoValor;
        }
        
        try {
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                return listaCodigoValor;
                //throw new RuntimeException("Failed : HTTP error code : "
                //        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String output;
            StringBuilder stringResult = new StringBuilder();
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
                   
                    for (Object key:jsonUnidad.keySet()){
                        unidadMap.put((String)key, jsonUnidad.get(key));
                    }
                    
                    //Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.INFO, "Recuperat: " + unidadMap.get("id")+ " " + unidadMap.get("val"));
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
        
        /*
        for (Map<String, Object> unidadMap: listaCodigoValor){
            Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.INFO, "Testing: " + unidadMap.get("id")+ " " + unidadMap.get("val"));
        }
        */
        
        return listaCodigoValor;
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
                return listaCodigoValor;
                //throw new RuntimeException("Failed : HTTP error code : "
                //        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String output;
            StringBuilder stringResult = new StringBuilder();
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
                    //Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.INFO, "Recuperat: " + unidadMap.get("id")+ " " + unidadMap.get("val"));
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
        
        /*
        for (Map<String, Object> unidadMap: listaCodigoValor){
            Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.INFO, "Testing: " + unidadMap.get("id")+ " " + unidadMap.get("val"));
        }
        */
        
        return listaCodigoValor;
    }
    
    
    
    
    
    private static JSONArray getJSONOrganismos(URL url) {
        
        JSONArray jsonUnidades = new JSONArray();
        
        if (url == null) {
            return jsonUnidades;
        }
        
        try {
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                return jsonUnidades;
                //throw new RuntimeException("Failed : HTTP error code : "
                //        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String output;
            StringBuilder stringResult = new StringBuilder();
            while ((output = br.readLine()) != null) {
                stringResult.append(output);
            }

            String jsonString = stringResult.toString();
            JSONParser parser = new JSONParser();

            try {
                jsonUnidades = (JSONArray) parser.parse(jsonString);        
            } catch (ParseException ex) {
                Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                conn.disconnect();
            }

        } catch (IOException ex) {
            Logger.getLogger(CatalogoDIR3Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return jsonUnidades;
    }
    
    
    

    public static void main(String args[]) throws Exception {

        String app = "org.fundaciobit.administraciodigital.";
        
        DadesConnexioDIR3 dadesConnexio = new DadesConnexioDIR3(app);
        
        System.setProperty(app + dadesConnexio.getCodClient() + ".username", "");
        System.setProperty(app + dadesConnexio.getCodClient()  + ".password", "");
        System.setProperty(app + dadesConnexio.getCodClient() + ".baseURL", "https://proves.caib.es/dir3caib");
        //System.setProperty(app + dadesConnexio.getCodClient() + ".baseURL", "http://192.168.35.151:8080/dir3caib");
        
        
        System.setProperty(app +  dadesConnexio.getCodClient() + ".entorno", "EntornCMAIB");
        System.setProperty(app +  dadesConnexio.getCodClient() + ".grupo", "CMI_ADMIN");

        CatalogoDIR3Client client = CatalogoDIR3Client.getClient(dadesConnexio);

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
        parametrosMap.put("requestParams", "?id=8");
        
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
        
        Collections.reverse(l);
        
        for (Map mp: l){
            //System.out.println(mp.entrySet());
            // public static final DMunicipiiosBalearsDIR3 ALMERIA = new DMunicipiiosBalearsDIR3("04", "Almería", "01");
            
            String id = mp.get("id").toString();
            String descripcion = mp.get("val").toString();
            String name = Strings.quitarAcentos(descripcion).replace(",","").replace(" ", "_").toUpperCase();
            
            String miembro = "public static final DMunicipiosBalearsDIR3 " + name + " = new DMunicipiosBalearsDIR3(\"" + id  + "\",\"" + descripcion + "\", DIsla.MALLORCA.getCodigoLOV());";
            
            System.out.println(miembro);
        }
        
           for (Map mp: l){
            //System.out.println(mp.entrySet());
            // public static final DMunicipiiosBalearsDIR3 ALMERIA = new DMunicipiiosBalearsDIR3("04", "Almería", "01");
            
            String id = mp.get("id").toString();
            String descripcion = mp.get("val").toString();
            String name = Strings.quitarAcentos(descripcion).replace(",","").replace(" ", "_").toUpperCase();
            
            String miembro = "DMunicipiosBalearsDIR3." + name + ",";
            
            System.out.println(miembro);
        }
      
           
        String codigo = "LA0008237"; 
          
        String resultado = client.getOrganismosByFiltro(codigo, "", "3", "", "", "407").toString();
        
        System.out.println(resultado);
           
        
        
   /*
    ResponseEntity<List<Nodo>> busquedaOrganismos(@RequestParam String codigo, @RequestParam String denominacion, @RequestParam Long codNivelAdministracion, @RequestParam Long codComunidadAutonoma, @RequestParam boolean conOficinas, @RequestParam boolean unidadRaiz, @RequestParam Long provincia, @RequestParam String localidad) throws Exception {
*/
        parametrosMap = new HashMap<String, Object>();
        
        parametrosMap.put("requestMapping", "/busqueda/organismos");
        
        StringBuilder par = new StringBuilder();
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
        par.append("\"vigentes\":\"true\",");
        par.append("}");
        
        String wpar = "?codigo=&denominacion=&codNivelAdministracion=3&codComunidadAutonoma=&conOficinas=false&unidadRaiz=false&provincia=&localidad=407&vigentes=true";
        
        //parametrosMap.put("requestParams",  par.toString());
        parametrosMap.put("requestParams",  wpar);
        
        System.out.println(par.toString());
        
        System.out.println(par.toString().replace("\"", "_").replace(":", "-").replace(",", "|"));
        
        //parametrosMap.put("requestParams",  "{\"codnivelAdministracion\":\"3\", \"codComunidadAutonoma\":\"4\", \"provincia\":\"7\", \"localidad\":\"407\"}");
        //parametrosMap.put("requestParams",  "{}");
        
        
        //http://localhost:8080/dir3caib/rest/busqueda/organismos?
        
        //codigo=&denominacion=Catarroja&codNivelAdministracion=&codComunidadAutonoma=&conOficinas=false&unidadRaiz=false&provincia=&localidad=
        
        url = client.getUrl(parametrosMap);        
        
        System.out.println("Url: " + url);
        
        // http://registre3.fundaciobit.org/dir3caib/rest/busqueda/organismos?codigo=&denominacion=&codNivelAdministracion=3&codComunidadAutonoma=&conOficinas=false&unidadRaiz=false&provincia=&localidad=407&vigentes=true
        
        // Url: http://registre3.fundaciobit.org/dir3caib/rest/busqueda/organismos?codigo=&denominacion=&codNivelAdministracion=3&codComunidadAutonoma=&conOficinas=false&unidadRaiz=false&provincia=&localidad=407&vigentes=true
        
        l = list(url, "codigo", "denominacion");
        
        /*
        for (Map mp: l){
            System.out.println(mp.entrySet());
        }*/
        
        
        
    }

    

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




 /*
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
         
        
        return null;
    }
    */



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



//private static final String REST_URL = ;
    
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

  