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
package org.fundaciobit.administraciodigital.helium.commons;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.conselldemallorca.helium.core.extern.domini.FilaResultat;
import net.conselldemallorca.helium.core.extern.formulari.RespostaIniciFormulari;
import org.fundaciobit.administraciodigital.utils.data.HashItemData;
import org.fundaciobit.administraciodigital.utils.data.MapItemData;

/**
 *
 * @author gdeignacio
 */
public class ParellaCodiValorUtils {
    
    public static final String ID = "id";
    public static final String VAL = "val";
    public static final String TASKID = "taskId";
    public static final String URL = "url";
    public static final String FORM_WIDTH = "width";
    public static final String FORM_HEIGHT = "height";
    
    private static final int DEFAULT_FORM_WIDTH = 1024;
    private static final int DEFAULT_FORM_HEIGHT = 768;
    
    
    
    @Deprecated
    public static FilaResultat novaFila(MapItemData mid){
        FilaResultat resposta = new FilaResultat();
        resposta.getColumnes().add(novaParella((String)mid.getIdKey(), mid.getCodigoLOV()));
        resposta.getColumnes().add(novaParella((String)mid.getValKey(), mid.getValorLOV()));
        return resposta;
    }
    
    @Deprecated
    public static FilaResultat novaFila(IDominiHeliumItem domini) {
        FilaResultat resposta = new FilaResultat();
        resposta.getColumnes().add(novaParella(IDominiHeliumItem.ID, domini.getCodigoLOV()));
        resposta.getColumnes().add(novaParella(IDominiHeliumItem.VAL, domini.getValorLOV()));
        return resposta;
    }

    public static FilaResultat novaFila(Map.Entry entry){
        return novaFila(entry, ID, VAL);
    }
    
    public static FilaResultat novaFila(Map.Entry entry, String id, String val){
        FilaResultat resposta = new FilaResultat();
        resposta.getColumnes().add(novaParella(id, entry.getKey()));
        resposta.getColumnes().add(novaParella(val, entry.getValue()));
        return resposta;
    }
    
    
    public static net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor novaParella(String codi, Object valor) {
        net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor parella = new net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor();
        parella.setCodi(codi);
        parella.setValor(valor);
        return parella;
    }

    public static FilaResultat filaError() {
        FilaResultat resposta = new FilaResultat();
        net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor parella = new net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor();
        parella.setCodi("");
        parella.setValor("ERROR: Domini desconegut");
        resposta.getColumnes().add(parella);
        return resposta;
    }

    public static FilaResultat filaError(Exception e) {
        FilaResultat resposta = new FilaResultat();
        net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor parella = new net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor();
        parella.setCodi("");
        parella.setValor(e.getMessage());
        resposta.getColumnes().add(parella);
        return resposta;

    }
    
    public static List<FilaResultat> respostaError(Exception e){
        List<FilaResultat> resposta = new ArrayList<FilaResultat>();
        resposta.add(ParellaCodiValorUtils.filaError(e));
        return resposta;
    }
    
    public static List<FilaResultat> respostaError(){
        List<FilaResultat> resposta = new ArrayList<FilaResultat>();
        resposta.add(ParellaCodiValorUtils.filaError());
        return resposta;
    }
    
    @Deprecated
    public static MapItemData newItemData(net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor pcv){
        MapItemData mid = new HashItemData();
        mid.setCodigoLOV(pcv.getCodi());
        mid.setValorLOV(pcv.getValor());
        return mid;
    }
    
    @Deprecated
    public static List<MapItemData> transformParelles2Items(List<net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor> pcvs){
        Function<net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor, MapItemData> pcv2mid
                = new Function<net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor, MapItemData>() {
            public MapItemData apply(net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor pcv) {
                return newItemData(pcv);
            }
        };
        return Lists.transform(pcvs, pcv2mid); 
    }
        
    @Deprecated
    public static List<FilaResultat> transformItems2Resultats(List<MapItemData> mids){
        Function<MapItemData, FilaResultat> mid2fres
                = new Function<MapItemData, FilaResultat>() {
            public FilaResultat apply(MapItemData mid) {
                return novaFila(mid);
            }
        };
        return Lists.transform(mids, mid2fres); 
    }
    
    public static Map parametresConsulta(List<net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor> pcvs){
        Map<String, Object> map = new HashMap<String, Object>();
        if (pcvs==null) return map;
        for (net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor pcv:pcvs){
            map.put(pcv.getCodi(), pcv.getValor());
        }
        return map;
    }
    
    public static Map parametresFormulari(String taskId, List<net.conselldemallorca.helium.core.extern.formulari.ParellaCodiValor> pcvs){
        Map<String, Object> map = new HashMap<String, Object>();
        if (taskId==null || pcvs==null) return map;
        map.put(TASKID, taskId);
        for (net.conselldemallorca.helium.core.extern.formulari.ParellaCodiValor pcv:pcvs){
            map.put(pcv.getCodi(), pcv.getValor());
        }
        return map;
    }
   
    
    public static List<FilaResultat> resultatsConsulta(Map resultats){
        
        List<FilaResultat> resposta = new ArrayList<FilaResultat>();
        
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
    
    
    public static RespostaIniciFormulari formulariError() {
        RespostaIniciFormulari resposta = new RespostaIniciFormulari();
        return resposta;
    }
    
    public static RespostaIniciFormulari formulariError(Exception e) {
        RespostaIniciFormulari resposta = new RespostaIniciFormulari();
        return resposta;
    }
    
    public static RespostaIniciFormulari formulariResposta(Map resultats){
        
        if (resultats==null) return formulariError();
        
        String url = (resultats.containsKey(URL))?(String)resultats.get(URL):null;
        String taskId = (resultats.containsKey(TASKID))?(String)resultats.get(TASKID):null;
        
        if (url==null || taskId == null) return formulariError();
        
        int width = (resultats.containsKey(FORM_WIDTH))?(Integer)resultats.get(FORM_WIDTH):DEFAULT_FORM_WIDTH;  
        int height = (resultats.containsKey(FORM_HEIGHT))?(Integer)resultats.get(FORM_HEIGHT):DEFAULT_FORM_HEIGHT;

        RespostaIniciFormulari resposta = new RespostaIniciFormulari(taskId, url);
        resposta.setWidth(width);
        resposta.setHeight(height);
        
        return resposta;
    }
    
}
