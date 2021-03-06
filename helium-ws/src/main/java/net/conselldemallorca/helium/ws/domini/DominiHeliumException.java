/**
 *
 */
package net.conselldemallorca.helium.ws.domini;


/**
 * Excepció que indica que han sorgit errors durant la
 * consulta a un domini
 *
 * @author Limit Tecnologies <limit@limit.es>
 */

@Deprecated
public class DominiHeliumException extends Exception {

	private static final long serialVersionUID = 1L;

	public DominiHeliumException(String msg) {
        super(msg);
    }

    public DominiHeliumException(String msg, Throwable t) {
        super(msg, t);
    }

}
