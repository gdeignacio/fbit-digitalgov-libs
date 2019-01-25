package org.fundaciobit.administraciodigital.utils.data;

import java.util.HashMap;

/**
 *
 * @author gdeignacio
 */
public final class HashItemData extends HashMap<String, Object> implements MapItemData {
   
    private String idKey;
    private String valKey;
    
    public HashItemData(){
        this.idKey = ID;
        this.valKey = VAL;
    }
    
    public HashItemData(String idKey, String valKey){
        this.idKey = idKey;
        this.valKey = valKey;
    } 
    
    @Override
    public Object getCodigoLOV() {
        return get(idKey);
    }

    @Override
    public Object getValorLOV() {
        return get(valKey);
    }

    @Override
    public void setCodigoLOV(Object id) {
        put(idKey, id);
    }

    @Override
    public void setValorLOV(Object val) {
         put(valKey, val);
    }

    @Override
    public Object getIdKey() {
        return idKey;
    }

    @Override
    public Object getValKey() {
        return valKey;
    }
  
}
