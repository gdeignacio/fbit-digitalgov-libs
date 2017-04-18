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

import java.util.*;

/**
 * Implementa una clase Data.
 * Se utiliza cuando queremos crear una Data pero no queremos implementar todos
 * los métodos de Data. En ese caso extenderemos de la clase DataAdapter.
 *
 * @author ELU
 * @author gdeignacio
 * @version 1.0.1
 * @created 16-02-2017
 */
public class DataAdapter implements Data
{

  /**
     * Constructor por defecto.
     */
  public DataAdapter()
  {
  }

  

  /**
     * Función que devuelve el valor de cada uno de los atributos de la clase Data.
     * @return Devuelve el resultado por defecto
     */
  public String toString()
  {
    return this.toString();
  }

  
  /**
     * Función que devuelve el código de la clase data en formato string para las
     * listas de valores.
     * @return En este caso devuelve un String vacío.
     */
  public String getCodigoLOV()
  {
    return "";
  }

  /**
     * Función que devuelve la descripción que deseamos que aparezca en las
     * listas de valores.
     * @return En este caso devuelve un String vacío.
     */
  public String getValorLOV()
  {
    return "";
  }

  /**
   * Se añade para que DWR no provoque fallos
   * @param codigo
   */
  public void setCodigoLOV(String codigo)
  {

  }

  /**
   * Idem que setCodigoLOV.
   * @param valor
   */
  public void setValorLOV(String valor)
  {
     
  }


}
