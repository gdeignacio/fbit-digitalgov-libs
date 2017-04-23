/**
 *
 */
package org.fundaciobit.administraciodigital.helium.jbpm3.handlers;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import net.conselldemallorca.helium.jbpm3.api.HeliumActionHandler;
import net.conselldemallorca.helium.jbpm3.api.HeliumApi;
import net.conselldemallorca.helium.jbpm3.handlers.exception.HeliumHandlerException;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * Handler per a cancelar les tasques pendents, finalitzar els tokens fills i
 * redirigir el token principal cap al node especificat. - El paràmetre
 * nodeDesti indica el node al qual redirigir l'execucio de l'expedient.
 *
 * @author gdeignacio
 */
public class DesestimacioHandler extends HeliumActionHandler {

    String nodeDesti = "desestimacio";

    public void execute(HeliumApi api) throws HeliumHandlerException {
 
        JbpmContext context = JbpmConfiguration.getInstance().createJbpmContext();
        
        ExecutionContext executionContext = new ExecutionContext(context.getToken(api.getToken().getId()));

        suspendreTasquesPendents(
                executionContext.getProcessInstance());
        // Cancel�la els tokens fills del token arrel
        Token tokenArrel = executionContext.getProcessInstance().getRootToken();
        @SuppressWarnings("unchecked")
        Map<String, Token> fills = tokenArrel.getActiveChildren();
        for (Token fill : fills.values()) {
            if (fill.getSubProcessInstance() != null) {
                suspendreTasquesPendents(
                        fill.getSubProcessInstance());
            }
            fill.end(false);
        }
        // Redirigeix el token cap al node desti
        
        api.expedientTokenRedirigir(
                tokenArrel.getId(),
                nodeDesti,
                false);
        /*
        tokenRedirigir(
                tokenArrel.getId(),
                nodeDesti,
                false);
*/
    }

    public final void setNodeDesti(String nodeDesti) {
        this.nodeDesti = nodeDesti;
    }

    private void suspendreTasquesPendents(
            ProcessInstance processInstance) {
        @SuppressWarnings("unchecked")
        Collection<TaskInstance> tasks = processInstance.getTaskMgmtInstance().getTaskInstances();
        for (TaskInstance task : tasks) {
            if (task.isOpen() && !task.hasEnded()) {
                task.suspend();

            }
        }
    }

    private static final long serialVersionUID = -6632834302344198737L;

    @Override
    public void retrocedir(HeliumApi heliumApi, List<String> parametres) throws Exception {
        return;
    }

}
