/*
 * Copyright 2017 gdeignacio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fundaciobit.administraciodigital.helium.jbpm3.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author gdeignacio
 */
public class Dir3Client {
    
    //private final Log log = LogFactory.getLog(getClass());
    
    private final static String NO_DIR3 = "NDIR3";
    
    //private String dir3RestUrlByCode = "http://registre3.fundaciobit.org/dir3caib/rest/unidad/denominacion?codigo=";
    private String dir3RestUrlByCode = "http://192.168.35.151:8080/dir3caib/rest/unidad/denominacion?codigo=";
    
    
    
    /**
     * Objecte que emmagatzema la instancia de la classe segons el patro
     * singleton
     *
     */
    private static final Dir3Client client = new Dir3Client();

    /**
     * Construeix un objecte de la classe. Aquest metode es privat per forcar el
     * patro singleton.
     */
    private Dir3Client() {
        super();
    }

    /**
     * Recupera l objecte singleton.
     *
     * @return objete singleton de la clase CMAIBDocumentOrganismeWsClient.
     */
    public static Dir3Client getClient() {
        return client;
    }

    
    public String getDenominacion(String codigo){
        return getDenominacion(dir3RestUrlByCode, codigo);
    }
    
    
    private static String getDenominacion(String dir3Url, String codigo){
    
        if (codigo == null) return null;
        
        if ("".equals(codigo)) return "";
        
        if (NO_DIR3.equals(codigo)) return "Altres";
        
        if (codigo.startsWith(NO_DIR3)) return  codigo.replace(NO_DIR3, "");
        
        String denominacion = null;
        
        URL url;
        try {
            url = new URL(dir3Url + codigo);
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            //System.out.println(conn.getResponseCode());
            
            if (conn.getResponseCode() != 200) {
                System.out.println(conn.getResponseCode());
               // System.out.println(conn.getResponseCode());
                //return denominacion;
                //return "Error 200";
                //throw new RuntimeException("Failed : HTTP error code : "
                //        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String output;
            StringBuffer stringResult = new StringBuffer();
            while ((output = br.readLine()) != null) {
                stringResult.append(output);
            }

            String jsonString = stringResult.toString();
            
            denominacion = jsonString;
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Dir3Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dir3Client.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        return denominacion;
    }
    
    
    public static void main(String args[]) throws Exception {
        
      //  http://registre3.fundaciobit.org/dir3caib/rest/unidad/denominacion?codigo=E04096103
        
       // E03143601
        
        //String codi = "E03143601"; // "E04096103";
        
        String codi =  "ARBITRARIO";  //"NDIR3Perico"; // "E04096103";
        
        System.out.println(codi);
                
        Dir3Client client = Dir3Client.getClient();
         
        String denom = client.getDenominacion(codi);
     
        if ("".equals(denom)) System.out.println("Vacio");
        
        System.out.println(denom);
     
    }
    
}
