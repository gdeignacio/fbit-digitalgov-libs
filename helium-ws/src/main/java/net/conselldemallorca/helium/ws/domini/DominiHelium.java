/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.conselldemallorca.helium.ws.domini;

import java.util.List;
import javax.jws.WebService;

@Deprecated
@WebService(targetNamespace = "http://domini.integracio.helium.conselldemallorca.net/")
public interface DominiHelium {

    public List<FilaResultat> consultaDomini(String string, List<ParellaCodiValor> list) throws DominiHeliumException;
}