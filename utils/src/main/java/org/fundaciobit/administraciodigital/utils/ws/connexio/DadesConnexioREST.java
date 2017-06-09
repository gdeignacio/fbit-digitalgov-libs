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
package org.fundaciobit.administraciodigital.utils.ws.connexio;

import org.fundaciobit.administraciodigital.utils.util.NVL;

/**
 *
 * @author gdeignacio
 */
public abstract class DadesConnexioREST {
    
    protected final String codapp;
    
    /**
     *
     * @param codapp
     */
    public DadesConnexioREST(String codapp){
        this.codapp = codapp;
        check();
    };
    
    protected abstract String getCodClient();
    
    public final String getUserName(){
        return  NVL.nvl(System.getProperty(this.codapp +  getCodClient() + ".username"), "").trim();
    }
    
    public final String getPassword(){
        return  NVL.nvl(System.getProperty(this.codapp +  getCodClient() + ".password"), "").trim();
    }
    
    public final String getBaseUrl(){
        return  NVL.nvl(System.getProperty(this.codapp +  getCodClient() + ".baseURL"), "").trim();
    }
    
    //public abstract String getQNAME();
    
    public abstract String getServiceName();
    
    protected abstract String getServiceContext();
    
    //protected abstract String getWsdlEnding();
    
    public final String getEndPoint(){
        return getBaseUrl() + getServiceContext();
    }
    
    //public final String getWsdlLocation(){
    //    return getBaseUrl() + getServiceContext() + getWsdlEnding();
    //}
    
    private void check(){
        
        NVL.nvl(getUserName(), new BadConfiguredConnectionException("La propiedad " + this.codapp  + getCodClient() + ".username no está bien definida."));
        NVL.nvl(getPassword(), new BadConfiguredConnectionException("La propiedad " + this.codapp  + getCodClient() + ".password no está bien definida."));
        NVL.nvl(getBaseUrl(), new BadConfiguredConnectionException("La propiedad " + this.codapp  + getCodClient() + ".baseURL no está bien definida."));
      
    }
    
    
}
