package org.fundaciobit.administraciodigital.utils.data;

import java.util.HashMap;

/**
 *
 * @author gdeignacio
 */
public final class HashItemData extends HashMap<String, Object> implements MapItemData {
   
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
