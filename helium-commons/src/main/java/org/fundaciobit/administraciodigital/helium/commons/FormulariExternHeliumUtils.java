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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.conselldemallorca.helium.core.extern.formulari.ParellaCodiValor;
import net.conselldemallorca.helium.core.extern.formulari.RespostaIniciFormulari;

/**
 *
 * @author gdeignacio
 */
public class FormulariExternHeliumUtils {
    
    public static final String ID = "id";
    public static final String VAL = "val";
    public static final String TASKID = "taskId";
    public static final String URL = "url";
    public static final String FORM_WIDTH = "width";
    public static final String FORM_HEIGHT = "height";
    
    private static final int DEFAULT_FORM_WIDTH = 1024;
    private static final int DEFAULT_FORM_HEIGHT = 768;
    
    
    public static Map parametresFormulari(String taskId, List<ParellaCodiValor> pcvs){
        Map<String, Object> map = new HashMap<String, Object>();
        if (taskId==null || pcvs==null) return map;
        map.put(TASKID, taskId);
        for (ParellaCodiValor pcv:pcvs){
            map.put(pcv.getCodi(), pcv.getValor());
        }
        return map;
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
