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

import java.io.*;

/**
 * Clase abstracta DominioData. Se debe extender de esta subclase para cada Dominio de la aplicaci�n
 * @author Paco Ros
 * @author gdeignacio
 * @version 2004.10.04
 */
public abstract class DominioData extends DataAdapter implements Serializable
{

  /*
     * Constante para indicar que las listas de dominios deben aparecer ordenadas por código
     */
  public static String ORDEN_CODIGO = "dom_codigo";

  /*
     * Constante para indicar que las listas de dominios deben aparecer ordenadas por valor
     */
  public static String ORDEN_VALOR = "dom_valor";
  
 /*
  * Constantes para determinar los valores nulos que deebn tomar las variables numéricas.
 */
 public static final String CODIGO_STRING_NULO = "";
 public static final Integer CODIGO_INTEGER_NULO = new Integer(-1);
 public static final Double CODIGO_DOUBLE_NULO = new Double(-1.0d);
 
 private static final String CADENA_VACIA="";


  /*
     * Código del elemento del dominio
     */
  private String codigo = null;

  /*
     * Valor del elemento del dominio
     */
  private String valor = null;

  /*
     * Orden de recuperación en las listas (ORDEN_CODIGO u ORDEN_VALOR). Por defecto el segundo
     */
  private String orden = null;


  public DominioData()
  {
    this.orden = ORDEN_VALOR;
  }

  public DominioData(String orden)
  {
    this.orden = orden;
  }


  /**
     * Recupera el código del dominio
     * @return codigo del dominio
     */
  public final String getCodigoLOV()
  {
    if (codigo == null)
    {
      return CADENA_VACIA;
    }

    return codigo;
  }

  /**
     * Recupera el nombre del dominio representado por la subclase
     * @return  nombre del dominio
     */
  public abstract String getDominio();

  /**
     * Recupera el nombre de la tabla que contiene los dominios de la aplicación
     * @return Nombre de la tabla que contiene los dominios de la aplicación
     */
  public abstract String getNombreTabla();

  /**
     * Recupera el valor del orden
     * @return valor del orden
     */
  public String getOrden()
  {
    if (orden == null)
    {
      return CADENA_VACIA;
    }
    else
    {
      return orden;
    }
  }

  /**
     * Recupera el valor del dominio en el objeto
     * @return valor del dominio
     */
  public final String getValorLOV()
  {
    if (valor == null)
    {
      return CADENA_VACIA;
    }
    else
    {
      return valor;
    }
  }


  /**
     *  Pone el valor del código del dominio
     * @param s nuevo código
     */
  public final void setCodigoLOV(String s)
  {
    this.codigo = s;
  }

  /**
     * Asigna el valor del orden
     * @param s Valor del orden
     */
  public void setOrden(String s)
  {
    orden = s;
  }

  /**
     *  Asigna el valor del dominio al objeto
     * @param s Valor del dominio
     */
  public final void setValorLOV(String s)
  {
    valor = s;
  }
}