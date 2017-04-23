/**
 *
 */
package org.fundaciobit.administraciodigital.helium.jbpm3.handlers;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import net.conselldemallorca.helium.jbpm3.api.HeliumActionHandler;
import net.conselldemallorca.helium.jbpm3.api.HeliumApi;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;

import net.conselldemallorca.helium.jbpm3.handlers.exception.HeliumHandlerException;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;

/**
 * Handler per a cancel·lar les tasques pendents, finalitzar els tokens fills i
 * redirigir el token principal cap al node especificat. - El paràmetre
 * nodeDesti indica el node al qual redirigir l'execució de l'expedient.
 *
 * @author Limit Tecnologies <limit@limit.es>
 */
public class FinalitzacioHandler extends HeliumActionHandler {

    String nodeDesti = "fi";

    @SuppressWarnings("unchecked")
    public void execute(HeliumApi api) throws HeliumHandlerException {

        JbpmContext context = JbpmConfiguration.getInstance().createJbpmContext();

        ExecutionContext executionContext = new ExecutionContext(context.getToken(api.getToken().getId()));

        // Cancel·la les tasques pendents
        Collection<TaskInstance> tasks = executionContext.getProcessInstance().getTaskMgmtInstance().getTaskInstances();
        for (TaskInstance task : tasks) {
            if (task.isOpen() && !task.hasEnded()) {
                task.setSignalling(false);
                task.cancel();
            }
        }
        // Cancel·la els tokens fills del token arrel
        Token tokenArrel = executionContext.getProcessInstance().getRootToken();
        Map<String, Token> fills = tokenArrel.getActiveChildren();
        for (Token fill : fills.values()) {
            fill.end(false);
        }
        // Redirigeix el token cap al node desti
        api.expedientTokenRedirigir(
                tokenArrel.getId(),
                nodeDesti,
                false);
    }

    public void setNodeDesti(String nodeDesti) {
        this.nodeDesti = nodeDesti;
    }

    private static final long serialVersionUID = -6632834302344198737L;

    @Override
    public void retrocedir(HeliumApi heliumApi, List<String> parametres) throws Exception {
        return;
    }

}
