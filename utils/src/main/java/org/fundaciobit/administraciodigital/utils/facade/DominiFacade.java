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
package org.fundaciobit.administraciodigital.utils.facade;

import java.util.List;
import java.util.Map;

/**
 *
 * @author gdeignacio
 * @param <Facade>
 * @param <Argument>
 * @param <Result>
 */
public abstract class DominiFacade<Facade, Argument, Result> {
    
    private Class<Facade> facadeClass;
    private Class<Result> resultClass;
    private Class<Argument> argumentClass;
    
    private DominiFacade(Class<Facade> facadeClass,
                         Class<Argument> argumentClass,
                         Class<Result> resultClass){
        this.facadeClass = facadeClass;
        this.argumentClass = argumentClass;
        this.resultClass = resultClass;
    }
    
    public abstract Result get(Argument argument);
    
    
    
    /*
    private F facade;
    
    public F getFacade(){
        return facade;
    }
    
    public void setFacade(F facade){
        this.facade = facade;
    }
    
    public abstract List<E> getListaValores(Map parametros);
    
    public T getDominiFacade(){
        return (T) this;
    }
*/
    
}
