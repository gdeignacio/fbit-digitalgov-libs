/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fundaciobit.administraciodigital.helium.ws.formulari;

import java.util.List;

/**
 *
 * @author gdeignacio
 */

public interface IniciFormulari {
	public RespostaIniciFormulari iniciFormulari(
			String codi,
			String taskId,
			List<ParellaCodiValor> valors);
}
