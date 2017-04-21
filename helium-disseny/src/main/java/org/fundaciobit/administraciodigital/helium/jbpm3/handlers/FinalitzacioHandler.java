/**
 * 
 */
package org.fundaciobit.administraciodigital.helium.jbpm3.handlers;

import java.util.Collection;
import java.util.Map;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;

import net.conselldemallorca.helium.jbpm3.handlers.BasicActionHandler;

/**
 * Handler per a cancel·lar les tasques pendents, finalitzar els tokens fills
 * i redirigir el token principal cap al node especificat.
 * - El paràmetre nodeDesti indica el node al qual redirigir l'execució de l'expedient.
 * 
 * @author Limit Tecnologies <limit@limit.es>
 */
public class FinalitzacioHandler extends BasicActionHandler {

	String nodeDesti = "fi";

	@SuppressWarnings("unchecked")
	public void execute(ExecutionContext executionContext) throws Exception {
		// Cancel·la les tasques pendents
		Collection<TaskInstance> tasks = executionContext.getProcessInstance().getTaskMgmtInstance().getTaskInstances();
		for (TaskInstance task: tasks) {
			if (task.isOpen() && !task.hasEnded()) {
				task.setSignalling(false);
				task.cancel();
			}
		}
		// Cancel·la els tokens fills del token arrel
		Token tokenArrel = executionContext.getProcessInstance().getRootToken();
		Map<String, Token> fills = tokenArrel.getActiveChildren();
		for (Token fill: fills.values()) {
			fill.end(false);
		}
		// Redirigeix el token cap al node desti
		tokenRedirigir(
				tokenArrel.getId(),
				nodeDesti,
				false);
	}

	public void setNodeDesti(String nodeDesti) {
		this.nodeDesti = nodeDesti;
	}

	private static final long serialVersionUID = -6632834302344198737L;

}
