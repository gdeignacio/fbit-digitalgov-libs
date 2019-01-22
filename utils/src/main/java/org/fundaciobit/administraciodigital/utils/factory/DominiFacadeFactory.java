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
package org.fundaciobit.administraciodigital.utils.factory;

import java.util.List;
import org.fundaciobit.administraciodigital.utils.data.Data;

/**
 *
 * @author gdeignacio
 * @param <Facade>
 */
public abstract class DominiFacadeFactory<Facade> {
    
    private Class<Facade> facadeClass;
    
    private Facade dominiFacade;
    
    private List<Data> parametros;
    
    private List<Data> resultado;

    public List<Data> getResultado() {
        return resultado;
    }

    public void setResultado(List<Data> resultado) {
        this.resultado = resultado;
    }

    public DominiFacadeFactory(Class<Facade> facadeClass){
        this.facadeClass = facadeClass;
    }
    
    public abstract List<Data> getResult();
    
    public Facade getDominiFacade(){
        return dominiFacade;
    }
    
    public void setDominiFacade(Facade dominiFacade){
        this.dominiFacade = dominiFacade;
    }
    
    
    public List<Data> getParametros() {
        return parametros;
    }

    public void setParametros(List<Data> parametros) {
        this.parametros = parametros;
    }
    
}
