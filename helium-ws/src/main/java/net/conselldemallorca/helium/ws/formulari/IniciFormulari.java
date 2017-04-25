package net.conselldemallorca.helium.ws.formulari;

import java.util.List;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "http://forms.integracio.helium.conselldemallorca.net/")
@XmlSeeAlso(value = {Object[].class, Object[][].class})
public interface IniciFormulari {

    public RespostaIniciFormulari iniciFormulari(String string, String string1, List<ParellaCodiValor> list);
}
