package org.fundaciobit.administraciodigital.helium.jbpm3.handlers;

import java.util.List;
import net.conselldemallorca.helium.jbpm3.api.HeliumActionHandler;
import net.conselldemallorca.helium.jbpm3.api.HeliumApi;
import net.conselldemallorca.helium.jbpm3.handlers.exception.HeliumHandlerException;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;

/**
 *
 * @author gdeignacio
 */
public class ExpedientGeorefETRS89ModificarHandler extends HeliumActionHandler {

    String posETRS89x;
    String varPosETRS89x;
    String posETRS89y;
    String varPosETRS89y;
    String Referencia;
    String varReferencia;

    private static final long serialVersionUID = 3513847803530877133L;

    /**
     *
     * @param api
     * @throws HeliumHandlerException
     */
    @Override
    public void execute(HeliumApi api) throws HeliumHandlerException {


        JbpmContext context = JbpmConfiguration.getInstance().createJbpmContext();

        ExecutionContext executionContext = new ExecutionContext(context.getToken(api.getToken().getId()));

        ProcessInstance processInstance = context.getToken(api.getToken().getId()).getProcessInstance();

  
              
       

   }

  
    @Override
    public void retrocedir(HeliumApi api, List<String> list) throws Exception {
        return;
    }

    public void setPosETRS89x(String posETRS89x) {
        this.posETRS89x = posETRS89x;
    }

    public void setVarPosETRS89x(String varPosETRS89x) {
        this.varPosETRS89x = varPosETRS89x;
    }

    public void setPosETRS89y(String posETRS89y) {
        this.posETRS89y = posETRS89y;
    }

    public void setVarPosETRS89y(String varPosETRS89y) {
        this.varPosETRS89y = varPosETRS89y;
    }

    public void setReferencia(String Referencia) {
        this.Referencia = Referencia;
    }

    public void setVarReferencia(String varReferencia) {
        this.varReferencia = varReferencia;
    }
    
    

}
