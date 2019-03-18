/*
 * Copyright 2019 gdeignacio.
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
package org.fundaciobit.administraciodigital.sistra.ws.commons;

import es.caib.sistra.ws.v2.model.documentoconsulta.DocumentoConsulta;
import es.caib.sistra.ws.v2.model.documentoconsulta.DocumentosConsulta;
import es.caib.sistra.ws.v2.model.formularioconsulta.FormularioConsulta;
import es.caib.sistra.ws.v2.model.formularioconsulta.FormulariosConsulta;
import es.caib.sistra.ws.v2.model.sistrafacade.ParametrosDominio;
import es.caib.sistra.ws.v2.model.valoresdominio.Columna;
import es.caib.sistra.ws.v2.model.valoresdominio.Fila;
import es.caib.sistra.ws.v2.model.valoresdominio.ValoresDominio;
import es.caib.sistra.ws.v2.model.valoresdominio.Filas;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBElement;


/**
 *
 * @author gdeignacio
 */
public class SistraUtils {
    
    public static final String ID = "id";
    public static final String VAL = "val";
    
 
  
    public static ValoresDominio dominioError(){
        es.caib.sistra.ws.v2.model.valoresdominio.ObjectFactory of = 
            new es.caib.sistra.ws.v2.model.valoresdominio.ObjectFactory();
        ValoresDominio resposta = of.createValoresDominio();
        String error = "ERROR: Domini desconegut";
        resposta.setDescripcionError(
                of.createValoresDominioDescripcionError(error));
        resposta.setError(true);
        resposta.setFilas(of.createValoresDominioFilas(filasError()));
        return resposta;
    }
    
     public static ValoresDominio dominioError(Exception e){
        es.caib.sistra.ws.v2.model.valoresdominio.ObjectFactory of = 
            new es.caib.sistra.ws.v2.model.valoresdominio.ObjectFactory();
        ValoresDominio resposta = of.createValoresDominio();
        String error = e.getMessage();
        resposta.setDescripcionError(
                of.createValoresDominioDescripcionError(error)); 
        resposta.setError(true);
        resposta.setFilas(of.createValoresDominioFilas(filasError()));
        return resposta;
    }
    
    public static Filas filasError() {
        Filas resposta = new Filas();
        resposta.getFila().add(filaError());
        return resposta;
    }
    
    public static Filas filasError(Exception e) {
        Filas resposta = new Filas();
        resposta.getFila().add(filaError(e));
        return resposta;
    }

    public static Fila filaError(){
        Fila resposta = new Fila();
        Columna columna = new Columna();
        columna.setCodigo("");
        columna.setValor("ERROR: Domini desconegut");
        resposta.getColumna().add(columna);
        return resposta;
    }
    
    public static Fila filaError(Exception e){
        Fila resposta = new Fila();
        Columna columna = new Columna();
        columna.setCodigo("");
        columna.setValor(e.getMessage());
        resposta.getColumna().add(columna);
        return resposta;
    }
    
    public static ValoresDominio valoresDominio(Map resultats){
        es.caib.sistra.ws.v2.model.valoresdominio.ObjectFactory of = 
            new es.caib.sistra.ws.v2.model.valoresdominio.ObjectFactory();
        ValoresDominio resposta = of.createValoresDominio();
        resposta.setError(false);
        resposta.setFilas(
                of.createValoresDominioFilas(filasDominio(resultats)));
        return resposta;
    }
    
    public static Filas filasDominio(Map resultats) {
        Filas resposta = new Filas();
        resposta.getFila().addAll(filasResultat(resultats));
        return resposta;
    }
    
    public static Fila novaFila(Map.Entry entry){
        return novaFila(entry, ID, VAL);
    }
    
    public static Fila novaFila(Map.Entry entry, String id, String val){
        Fila resposta = new Fila();
        resposta.getColumna().add(novaColumna(id, entry.getKey()));
        resposta.getColumna().add(novaColumna(val, entry.getValue()));
        return resposta;
    }
    
    public static Columna novaColumna(String codi, Object valor) {
        Columna columna = new Columna();
        columna.setCodigo(codi);
        columna.setValor((String)valor);
        return columna;
    }

  
    public static Map parametresDomini(ParametrosDominio parametros){
        Map<String, Object> map = new HashMap<String, Object>();
        if (parametros==null) return map;
        List<String> pcvs = parametros.getParametro();
        for (String pcv:pcvs){
            
            //map.put(pcv.getCodi(), pcv.getValor());
        }
        return map;
    }
   
    
    public static List<Fila> filasResultat(Map resultats){
        
        List<Fila> resposta = new ArrayList<Fila>();
        
        if (resultats==null) return resposta;
        
        String id = ID;
        String val = VAL;
       
        if (resultats.containsKey(id)){
            id = (String)resultats.get(id);
            resultats.remove(id);
        }
        
        if (resultats.containsKey(val)){
            val = (String)resultats.get(val);
            resultats.remove(val);
        }
      
        for (Map.Entry entry:(Set<Map.Entry>)resultats.entrySet()){
            resposta.add(novaFila(entry, id, val));
        }
        
        return resposta;
    }
    
    
    public static DocumentosConsulta consultaError(){
        es.caib.sistra.ws.v2.model.documentoconsulta.ObjectFactory of
                = new es.caib.sistra.ws.v2.model.documentoconsulta.ObjectFactory();
        DocumentosConsulta resposta = of.createDocumentosConsulta();
        resposta.getDocumento().addAll(documentosError());
        return resposta;
    }
    
    public static DocumentosConsulta consultaError(Exception e){
        es.caib.sistra.ws.v2.model.documentoconsulta.ObjectFactory of
                = new es.caib.sistra.ws.v2.model.documentoconsulta.ObjectFactory();
        DocumentosConsulta resposta = of.createDocumentosConsulta();
        resposta.getDocumento().addAll(documentosError(e));
        return resposta;
    }
    
    public static List<DocumentoConsulta> documentosError() {
        List<DocumentoConsulta> resposta = new ArrayList<DocumentoConsulta>();
        resposta.add(documentoError());
        return resposta;
    }
    
    public static List<DocumentoConsulta> documentosError(Exception e) {
        List<DocumentoConsulta> resposta = new ArrayList<DocumentoConsulta>();
        resposta.add(documentoError(e));
        return resposta;
    }
    
    public static DocumentoConsulta documentoError(){
        es.caib.sistra.ws.v2.model.documentoconsulta.ObjectFactory of
                = new es.caib.sistra.ws.v2.model.documentoconsulta.ObjectFactory();
        DocumentoConsulta resposta = of.createDocumentoConsulta();
        
        return resposta;
    }
    
    public static DocumentoConsulta documentoError(Exception e){
        es.caib.sistra.ws.v2.model.documentoconsulta.ObjectFactory of
                = new es.caib.sistra.ws.v2.model.documentoconsulta.ObjectFactory();
        DocumentoConsulta resposta = of.createDocumentoConsulta();
        
        return resposta;
    }
    
    public static DocumentosConsulta documentosConsulta(Map resultats){
        es.caib.sistra.ws.v2.model.documentoconsulta.ObjectFactory of = 
            new es.caib.sistra.ws.v2.model.documentoconsulta.ObjectFactory();
        DocumentosConsulta resposta = of.createDocumentosConsulta();
        resposta.getDocumento().addAll(documentosResultat(resultats));
        return resposta;
    }
    
    public static Map formularisConsulta(FormulariosConsulta forms){
        Map<String, Object> map = new HashMap<String, Object>();
        if (forms==null) return map;
        for (FormularioConsulta form:forms.getFormularios()){
            
        }
        return map;
    }
    
    public static List<DocumentoConsulta> documentosResultat(Map resultats) {
        es.caib.sistra.ws.v2.model.documentoconsulta.ObjectFactory of = 
            new es.caib.sistra.ws.v2.model.documentoconsulta.ObjectFactory();
        List<DocumentoConsulta> resposta = new ArrayList<DocumentoConsulta>();
      
        if (resultats==null) return resposta;
        
        String id = ID;
        String val = VAL;
       
        if (resultats.containsKey(id)){
            id = (String)resultats.get(id);
            resultats.remove(id);
        }
        
        if (resultats.containsKey(val)){
            val = (String)resultats.get(val);
            resultats.remove(val);
        }
      
        for (Map.Entry entry:(Set<Map.Entry>)resultats.entrySet()){
            resposta.add(nouDocument(entry, id, val));
        }
        
        return resposta;
    
    }
    
     public static DocumentoConsulta nouDocument(Map.Entry entry){
        return nouDocument(entry, ID, VAL);
    }
    
    public static DocumentoConsulta nouDocument(Map.Entry entry, String id, String val){
        
        es.caib.sistra.ws.v2.model.documentoconsulta.ObjectFactory of
                = new es.caib.sistra.ws.v2.model.documentoconsulta.ObjectFactory();
        DocumentoConsulta resposta = of.createDocumentoConsulta();
        
        //Fila resposta = new Fila();
        //resposta.getColumna().add(novaColumna(id, entry.getKey()));
        //resposta.getColumna().add(novaColumna(val, entry.getValue()));
        return resposta;
    }
    
    
}
