/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fundaciobit.administraciodigital.helium.ws.domini;

import java.util.List;

/**
 *
 * @author gdeignacio
 */


public interface DominiHelium {
	public List<FilaResultat> consultaDomini(
		String id,
		List<ParellaCodiValor> parametres)
		throws DominiHeliumException;
}
