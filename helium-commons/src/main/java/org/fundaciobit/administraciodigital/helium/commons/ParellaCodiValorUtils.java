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
package org.fundaciobit.administraciodigital.helium.commons;

import net.conselldemallorca.helium.core.extern.domini.FilaResultat;
import net.conselldemallorca.helium.core.extern.domini.ParellaCodiValor;
import org.fundaciobit.administraciodigital.utils.data.Data;
import org.fundaciobit.administraciodigital.utils.data.DataAdapter;

/**
 *
 * @author gdeignacio
 */
public class ParellaCodiValorUtils {
    
    public FilaResultat novaFila(IDominiHeliumItem domini) {
        FilaResultat resposta = new FilaResultat();
        resposta.getColumnes().add(novaParella(IDominiHeliumItem.ID, domini.getCodigoLOV()));
        resposta.getColumnes().add(novaParella(IDominiHeliumItem.VAL, domini.getValorLOV()));
        return resposta;
    }

    public ParellaCodiValor novaParella(String codi, Object valor) {
        ParellaCodiValor parella = new ParellaCodiValor();
        parella.setCodi(codi);
        parella.setValor(valor);
        return parella;
    }

    public FilaResultat filaError() {
        FilaResultat resposta = new FilaResultat();
        ParellaCodiValor parella = new ParellaCodiValor();
        parella.setCodi("");
        parella.setValor("ERROR: Domini desconegut");
        resposta.getColumnes().add(parella);
        return resposta;
    }

    public FilaResultat filaError(Exception e) {
        FilaResultat resposta = new FilaResultat();
        ParellaCodiValor parella = new ParellaCodiValor();
        parella.setCodi("");
        parella.setValor(e.getMessage());
        resposta.getColumnes().add(parella);
        return resposta;

    }
    
    
}
