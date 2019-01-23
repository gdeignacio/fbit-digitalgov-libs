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

/**
 *
 * @author gdeignacio
 * @param <Facade>
 * @param <ItemParam>
 * @param <ItemResult>
 */
public abstract class DominiFacadeFactory<Facade, ItemParam, ItemResult> {

    private Facade facade;

    private List<ItemParam> param;

    public DominiFacadeFactory(){
    }

    public abstract List<ItemResult> getResult();

    public Facade getFacade() {
        return facade;
    }

    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    public List<ItemParam> getParam() {
        return param;
    }

    public void setParam(List<ItemParam> param) {
        this.param = param;
    }

}
