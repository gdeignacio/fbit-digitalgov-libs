package org.fundaciobit.administraciodigital.helium.jbpm3.handlers;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;

import net.conselldemallorca.helium.jbpm3.api.HeliumActionHandler;
import net.conselldemallorca.helium.jbpm3.api.HeliumApi;
import net.conselldemallorca.helium.jbpm3.handlers.exception.HeliumHandlerException;

public class DesestimacioHandler extends HeliumActionHandler {

    String nodeDesti = "desestimacio";
    private static final long serialVersionUID = -6632834302344198737L;

    public void execute(HeliumApi api) throws HeliumHandlerException {
        JbpmContext context = JbpmConfiguration.getInstance().createJbpmContext();

        ExecutionContext executionContext = new ExecutionContext(context.getToken(api.getToken().getId()));

        suspendreTasquesPendents(executionContext.getProcessInstance());

        Token tokenArrel = executionContext.getProcessInstance().getRootToken();

        Map<String, Token> fills = tokenArrel.getActiveChildren();
        for (Token fill : fills.values()) {
            if (fill.getSubProcessInstance() != null) {
                suspendreTasquesPendents(fill.getSubProcessInstance());
            }
            fill.end(false);
        }

        unsignal(executionContext.getProcessInstance());

        api.expedientTokenRedirigir(Long.valueOf(tokenArrel.getId()), this.nodeDesti, false);
    }

    public final void setNodeDesti(String nodeDesti) {
        this.nodeDesti = nodeDesti;
    }

    private void suspendreTasquesPendents(ProcessInstance processInstance) {
        Collection<TaskInstance> tasks = processInstance.getTaskMgmtInstance().getTaskInstances();
        for (TaskInstance task : tasks) {
            if ((task.isOpen()) && (!task.hasEnded())) {
                task.suspend();
            }
        }
    }

    private void unsignal(ProcessInstance processInstance) {
        Collection<TaskInstance> tasks = processInstance.getTaskMgmtInstance().getTaskInstances();
        for (TaskInstance task : tasks) {
            if ((task.isOpen()) && (!task.hasEnded())) {
                task.setSignalling(false);
            }
        }
    }

    public void retrocedir(HeliumApi heliumApi, List<String> parametres) throws Exception {
    }
}
