package org.fundaciobit.administraciodigital.helium.commons;

import java.util.Map;

/**
 *
 * @author gdeignacio
 */
public interface IDominiHeliumItem extends Map<String, Object> {
    
    public static final String ID = "id";
    public static final String VAL = "val";
    public Object getCodigoLOV();
    public Object getValorLOV();
    public void setCodigoLOV(Object id);
    public void setValorLOV(Object val);
}
