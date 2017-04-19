
package org.fundaciobit.administraciodigital.helium.jbpm3.handlers;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.conselldemallorca.helium.jbpm3.api.HeliumActionHandler;
import net.conselldemallorca.helium.jbpm3.api.HeliumApi;
import net.conselldemallorca.helium.jbpm3.handlers.exception.HeliumHandlerException;
import net.conselldemallorca.helium.jbpm3.handlers.tipus.ExpedientInfo;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.ProcessInstance;

/**
 *
 * @author gdeignacio
 */
public class ObtenirUrlDocumentacioHandler extends HeliumActionHandler {

    private static final String DOMINI = "cmaibDominiHelium";
    private static final String DOMINIEXPORT = "es.caib.cmaib.helium.domini.DominiDocumentUploadToCMAIB";
    
    @Override
    public void execute(HeliumApi api) throws HeliumHandlerException {
        
        
        
        JbpmContext context = JbpmConfiguration.getInstance().createJbpmContext();
        ProcessInstance processInstance = context.getToken(api.getToken().getId()).getProcessInstance();
        
        ExpedientInfo expedientInfo = api.getExpedient();
        Map<String, Object> parametros = new HashMap<String, Object>();
        
        String jsonExpedientInfo = (new Gson()).toJson(expedientInfo);
        parametros.put("jsonExpedientInfo", jsonExpedientInfo);
        
        Map<String, Object> variablesMap = processInstance.getContextInstance().getVariables();
        
        if (variablesMap!=null) parametros.putAll(variablesMap);
        
        api.consultaDomini(DOMINI, DOMINIEXPORT, parametros);
        
        
       
    }

    @Override
    public void retrocedir(HeliumApi api, List<String> list) throws Exception {
        return;
    }
    
}
