package org.fundaciobit.administraciodigital.helium.commons;

import java.util.HashMap;

/**
 *
 * @author gdeignacio
 */
@Deprecated
public final class DominiHeliumItem extends HashMap<String, Object> implements IDominiHeliumItem {
   
    @Override
    public Object getCodigoLOV() {
        return get(ID);
    }

    @Override
    public Object getValorLOV() {
        return get(VAL);
    }

    @Override
    public void setCodigoLOV(Object id) {
        put(ID, id);
    }

    @Override
    public void setValorLOV(Object val) {
         put(VAL, val);
    }
  
}
