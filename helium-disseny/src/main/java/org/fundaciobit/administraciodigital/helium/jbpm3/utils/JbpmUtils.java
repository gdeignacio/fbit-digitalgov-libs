/*
 * Copyright 2017 gdeignacio.
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
package org.fundaciobit.administraciodigital.helium.jbpm3.utils;

import java.util.Collection;
import java.util.Map;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 *
 * @author gdeignacio
 */
public class JbpmUtils {

    public Collection<TaskInstance> getPendingTasks(ProcessInstance processInstance){
        
        @SuppressWarnings("unchecked")
        Collection<TaskInstance> tasks = processInstance.getTaskMgmtInstance().getTaskInstances();
        for (TaskInstance taskInstance:tasks){
            if (taskInstance.isOpen() && !taskInstance.hasEnded()) {
                tasks.add(taskInstance);
            }
        }
        
        return tasks;
    }
    
    
    public void suspendTokenPendingTasks(Token token) {
        
        for (TaskInstance taskInstance:getPendingTasks(token.getProcessInstance())) {
            taskInstance.suspend();
        }
        
    }
    
    public void endChildTokensPendingTasks(Token token) {

        @SuppressWarnings("unchecked")
        Map<String, Token> children = token.getActiveChildren();
        
        for (Token child : children.values()) {
            suspendTokenPendingTasks(child);
        }

    }
    
    
    
    
    
    public void suspendTasks(Token token) {
        Collection<TaskInstance> tasks = token.getProcessInstance().getTaskMgmtInstance().getTaskInstances();
        for (TaskInstance task : tasks) {
            if (task.isOpen() && !task.hasEnded()) {
                task.suspend();
            }
        }
    }

    public void suspendChildrenTokenTasks(Token token) {

        @SuppressWarnings("unchecked")
        Map<String, Token> children = token.getActiveChildren();
        
        for (Token child : children.values()) {
            suspendTasks(child);
        }

    }


}
