/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fundaciobit.administraciodigital.helium.jbpm3.utils;

import java.util.List;
import net.conselldemallorca.helium.jbpm3.handlers.tipus.NodeInfo;
import net.conselldemallorca.helium.jbpm3.handlers.tipus.ProcessDefinitionInfo;

/**
 *
 * @author gdeignacio
 */
public class CustomProcessDefinitionInfo extends ProcessDefinitionInfo {
 
    public CustomProcessDefinitionInfo(ProcessDefinitionInfo info) {
        super(info.getId(), info.getName(), info.getDescription(), info.getVersion(), info.isTerminationImplicit(), null, null, null);
    }
    
   // public CustomProcessDefinitionInfo(long id, String name, String description, int version, boolean isTerminationImplicit, Node startState, List<Node> nodes, Map<String, Action> actions) {
   //     super(id, name, description, version, isTerminationImplicit, startState, nodes, actions);
   // }

    @Override
    public List<NodeInfo> getNodes() {
        return null; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NodeInfo getStartState() {
        return null; //To change body of generated methods, choose Tools | Templates.
    }
 
    
}
