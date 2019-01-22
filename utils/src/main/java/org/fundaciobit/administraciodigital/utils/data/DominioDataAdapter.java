package org.fundaciobit.administraciodigital.utils.data;

/**
 * Extiende de DominioData. Se utiliza como comodin para las librerias internas
 * No se permite su uso en las aplicaciones
 * @author ELU
 * @version 1.0
 * @created 15-FEB-2005 11:12:00
 */
public class DominioDataAdapter extends DominioData
{
  public DominioDataAdapter()
  {
  }

  /**
     * Retorna getValorLOV()
     * @return String getValorLOV()
     */
  public String getDominio()
  {
    return getValorLOV();
  }

  /**
     * Retorna el literal "DummyData"
     * @return String "DummyData"
     */
  public String getNombreTabla()
  {
    return "DummyData";
  }
}
