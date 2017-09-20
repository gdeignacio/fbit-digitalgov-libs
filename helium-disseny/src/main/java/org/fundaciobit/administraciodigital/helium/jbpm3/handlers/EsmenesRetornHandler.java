package org.fundaciobit.administraciodigital.helium.jbpm3.handlers;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;

import net.conselldemallorca.helium.jbpm3.api.HeliumActionHandler;
import net.conselldemallorca.helium.jbpm3.api.HeliumApi;
import net.conselldemallorca.helium.jbpm3.handlers.exception.HeliumHandlerException;

public class EsmenesRetornHandler extends HeliumActionHandler {

    private static final long serialVersionUID = 1946035049615657941L;

    public void execute(HeliumApi api) throws HeliumHandlerException {
        JbpmContext context = JbpmConfiguration.getInstance().createJbpmContext();
        ExecutionContext executionContext = new ExecutionContext(context.getToken(api.getToken().getId()));

        reprendreTasquesSuspeses(executionContext.getProcessInstance());

        Token tokenArrel = executionContext.getProcessInstance().getRootToken();
        Map<String, Token> fills = tokenArrel.getActiveChildren();
        for (Token fill : fills.values()) {
            if (fill.getSubProcessInstance() != null) {
                reprendreTasquesSuspeses(fill.getSubProcessInstance());
            }
        }

        signal(executionContext.getProcessInstance());

        Node nodeGuardat = (Node) executionContext.getVariable("nodeGuardat");
        tokenArrel.setNode(nodeGuardat);
    }

    private void reprendreTasquesSuspeses(ProcessInstance processInstance) {
        Collection<TaskInstance> tasks = processInstance.getTaskMgmtInstance().getTaskInstances();
        for (TaskInstance task : tasks) {
            if ((task.isOpen()) && (task.isSuspended())) {
                task.resume();
            }
        }
    }

    private void signal(ProcessInstance processInstance) {
        Collection<TaskInstance> tasks = processInstance.getTaskMgmtInstance().getTaskInstances();
        for (TaskInstance task : tasks) {
            if (task.isOpen() && !task.hasEnded() && !task.isSignalling()) {
                task.setSignalling(true);
            }
        }
    }

    public void retrocedir(HeliumApi arg0, List<String> arg1) throws Exception {
    }
}
