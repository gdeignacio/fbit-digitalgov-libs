
package org.fundaciobit.administraciodigital.helium.jbpm3.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.conselldemallorca.helium.jbpm3.api.HeliumActionHandler;
import net.conselldemallorca.helium.jbpm3.api.HeliumApi;
import net.conselldemallorca.helium.jbpm3.handlers.exception.HeliumHandlerException;
import net.conselldemallorca.helium.jbpm3.handlers.tipus.ExpedientInfo;
import org.fundaciobit.administraciodigital.helium.jbpm3.utils.CustomProcessDefinitionInfo;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.ProcessInstance;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author gdeignacio
 */
public class DocumentUrlHandler extends HeliumActionHandler {

    private static final String DOMINI = "cmaibDominiHelium";
    private static final String DOMINIEXPORT = "es.caib.cmaib.helium.domini.DominiEncryptedUrl";
    
    @Override
    public void execute(HeliumApi api) throws HeliumHandlerException {
        
        JbpmContext context = JbpmConfiguration.getInstance().createJbpmContext();
        ProcessInstance processInstance = context.getToken(api.getToken().getId()).getProcessInstance();
        ProcessInstance subProcessInstance = context.getToken(api.getToken().getId()).getSubProcessInstance();
        
        ExpedientInfo expedientInfo = api.getExpedient();
        
        CustomProcessDefinitionInfo customProcessDefinitionInfo = new CustomProcessDefinitionInfo(api.getProcessDefinition());
        
        Gson gson;
        
        Map<String, Object> parametros = new HashMap<String, Object>();
        
        GsonBuilder gsonBuilder = new GsonBuilder();
        
        gson = gsonBuilder.create();
        String jsonExpedientInfo = gson.toJson(expedientInfo);
        parametros.put("jsonExpedientInfo", jsonExpedientInfo);
        
        gson = gsonBuilder.create();
        String jsonSubProcessExpedientInfo = gson.toJson(expedientInfo);
        
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        
        try {
            json = (JSONObject) parser.parse(jsonSubProcessExpedientInfo);
        } catch (ParseException ex) {
            Logger.getLogger(DocumentUrlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        long processInstanceId = (subProcessInstance!=null)?subProcessInstance.getContextInstance().getProcessInstance().getId():processInstance.getContextInstance().getProcessInstance().getId();
        
        json.put("processInstanceId", processInstanceId);
        
        jsonSubProcessExpedientInfo = json.toJSONString();
        
        
        parametros.put("jsonSubProcessExpedientInfo", jsonSubProcessExpedientInfo);
        
        
        gson = gsonBuilder.create();
        String jsonProcessDefinitionInfo = gson.toJson(customProcessDefinitionInfo);
        parametros.put("jsonProcessDefinitionInfo", jsonProcessDefinitionInfo);
        
        Map<String, Object> variablesMap = (subProcessInstance!=null)?subProcessInstance.getContextInstance().getVariables():processInstance.getContextInstance().getVariables();
        
        String jsonVariables;
        
        gson = gsonBuilder.create();
        jsonVariables = (variablesMap!=null)?gson.toJson(variablesMap):"";
        
        //if (variablesMap!=null) parametros.putAll(variablesMap);
        
        parametros.put("jsonVariables", jsonVariables);
        
        api.consultaDomini(DOMINI, DOMINIEXPORT, parametros);
        
        
        
        //Map<String, Object> expedientInfoMap = new BeanMap(expedientInfo);
        //parametros.putAll(expedientInfoMap);
        //parametros.put("iniciadorTipus", expedientInfo.getIniciadorTipus().toString());   
        //Gson gson = new Gson();
        //String expedientJson = gson.toJson(expedient);
        //Map<String, Object> parametros;
        //parametros = processInstance.getContextInstance().getVariables();
        //parametros = (parametros!=null)?parametros: new HashMap<String, Object>();
        //parametros.put("jsonExpedientInfo", expedientJson);
       
    }

    @Override
    public void retrocedir(HeliumApi api, List<String> list) throws Exception {
        return;
    }
    
}
