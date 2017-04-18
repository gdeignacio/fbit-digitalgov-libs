/*
 * Copyright 2016 gdeignacio.
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

package org.fundaciobit.administraciodigital.utils.data;

/**
 *
 * @author gdeignacio
 */
import java.io.Serializable;

public interface Data extends Serializable {

    /**
     * Función que devuelve el valor de cada uno los atributos de la clase Data
     *
     * @return el valor de cada uno los atributos de la clase Data
     */
    public String toString();

    /**
     * Función que devuelve el código de la clase data en formato string para
     * las listas de valores
     *
     * @return código de la clase data en formato string para las listas de
     * valores
     */
    public String getCodigoLOV();

    /**
     * Función que devuelve la descripción que deseamos que aprarezca en las
     * listas de valores
     *
     * @return Descripción que deseamos que aparezca en las listas de valores
     */
    public String getValorLOV();
}
