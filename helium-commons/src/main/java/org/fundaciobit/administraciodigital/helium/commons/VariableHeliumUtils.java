/*
 * Copyright 2019 gdeignacio.
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
package org.fundaciobit.administraciodigital.helium.commons;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gdeignacio
 */
public class VariableHeliumUtils {
    

    
    public static String getVariableName(String expedientTipus, String tableName, String fieldName){
        String variableName = expedientTipus + "_" + tableName + "_" + fieldName;
        return variableName.toUpperCase();
    }
    
    public static List<String> getVariablesFromFieldName(String expedientTipus, String tableName, List<String> fieldNames){
        List<String> variables = new ArrayList<String>();
        for (String fieldName:fieldNames){
            variables.add(getVariableName(expedientTipus, tableName, fieldName));
        }
        return variables;
    }

  
    
}
