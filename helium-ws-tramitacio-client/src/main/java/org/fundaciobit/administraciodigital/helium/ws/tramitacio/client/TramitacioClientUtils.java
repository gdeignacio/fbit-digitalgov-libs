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
package org.fundaciobit.administraciodigital.helium.ws.tramitacio.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.conselldemallorca.helium.integracio.tramitacio.ParellaCodiValor;

/**
 *
 * @author gdeignacio
 */
public class TramitacioClientUtils {
    
    public static final String ID = "id";
    public static final String VAL = "val";
    
    
    public static ParellaCodiValor novaParella(Map.Entry entry) {
        ParellaCodiValor parella = new ParellaCodiValor();
        parella.setCodi((String)entry.getKey());
        if (entry.getValue() instanceof Object[]){
            parella.setValor((Object[])entry.getValue());
            return parella;
        }
        parella.setValor(entry.getValue());
        return parella;
    }
    
    public static ParellaCodiValor novaParella(String codi, Object valor) {
        ParellaCodiValor parella = new ParellaCodiValor();
        parella.setCodi(codi);
        parella.setValor(valor);
        return parella;
    }


    public static Map parametresConsulta(List<ParellaCodiValor> pcvs){
        Map<String, Object> map = new HashMap<String, Object>();
        if (pcvs==null) return map;
        for (ParellaCodiValor pcv:pcvs){
            map.put(pcv.getCodi(), pcv.getValor());
        }
        return map;
    }
    
    
    public static List<ParellaCodiValor> parellesModel(Map model){
        
        List<ParellaCodiValor> parelles = new ArrayList<ParellaCodiValor>();
        
        if (model == null) return parelles;
        
        for (Map.Entry entry:(Set<Map.Entry>)model.entrySet()){
            parelles.add(novaParella(entry));
        }
        
        return parelles;
    }
    
    
   
    
  
    
}
