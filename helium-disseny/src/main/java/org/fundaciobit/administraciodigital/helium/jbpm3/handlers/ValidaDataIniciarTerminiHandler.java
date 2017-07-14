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
package org.fundaciobit.administraciodigital.helium.jbpm3.handlers;

import java.util.Date;
import net.conselldemallorca.helium.jbpm3.handlers.TerminiIniciarHandler;
import org.jbpm.graph.exe.ExecutionContext;

/**
 *
 * @author gdeignacio
 */
@SuppressWarnings("serial")
public class ValidaDataIniciarTerminiHandler extends TerminiIniciarHandler {

    private String varData;

    /**
     * Ejecuta las operaciones del handler.
     */
    public void execute(ExecutionContext context) throws Exception {

        if (varData != null && varData.length() > 0) {
            Object obj = context.getVariable(varData);
            if (obj != null && obj instanceof Date) {
                cridaSuperHandler(context);
            }
        }
    }

    private void cridaSuperHandler(ExecutionContext context) throws Exception {

        super.setVarData(varData);
        super.execute(context);

    }

    public void setVarData(String varData) {
        this.varData = varData;
    }
}
