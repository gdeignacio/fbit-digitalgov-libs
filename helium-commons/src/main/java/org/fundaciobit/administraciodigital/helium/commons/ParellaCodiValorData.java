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

import net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor;
import org.fundaciobit.administraciodigital.utils.data.DataAdapter;

/**
 *
 * @author gdeignacio
 */
public class ParellaCodiValorData extends DataAdapter {
    
    private ParellaCodiValor pcv;
    
    public ParellaCodiValorData(ParellaCodiValor pcv){
        super();
        this.pcv = pcv;
    }
    
    @Override
    public String getCodigoLOV(){
        return pcv.getCodi();
    }
    
    @Override
    public String getValorLOV(){
        return (pcv.getValor()!=null)?pcv.getValor().toString():"";
    }
    
}
