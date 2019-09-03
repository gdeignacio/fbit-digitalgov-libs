package org.fundaciobit.administraciodigital.helium.jbpm3.api.handlers;

import java.util.List;
import net.conselldemallorca.helium.jbpm3.api.HeliumActionHandler;
import net.conselldemallorca.helium.jbpm3.api.HeliumApi;
import net.conselldemallorca.helium.jbpm3.handlers.exception.HeliumHandlerException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.Token;
import org.fundaciobit.administraciodigital.helium.jbpm3.utils.Dir3Client;
import org.json.simple.JSONObject;

/**
 *
 * @author gdeignacio
 */
public class CreateChildTokensHandler extends HeliumActionHandler {
    
    private final Log log = LogFactory.getLog(getClass());
    
    String destinationNode;
    String varChildren;
    String varChild;
    String multiple;

    private static final long serialVersionUID = 3513847803530877133L;

    /**
     *
     * @param api
     * @throws HeliumHandlerException
     */
    @Override
    public void execute(HeliumApi api) throws HeliumHandlerException {
        
        JbpmContext context = JbpmConfiguration.getInstance().createJbpmContext();
        Token mainToken = context.getToken(api.getToken().getId());
        
        //ExecutionContext executionContext = new ExecutionContext(mainToken);
        //ProcessInstance processInstance = mainToken.getProcessInstance();
        
        Object obj = api.getVariable(varChildren);
        boolean esMultiple = "true".equalsIgnoreCase(multiple);
        if (obj == null) {return;}
        Object[] tokenSuffixes = (esMultiple && obj instanceof Object[]) ? (Object[]) obj : new Object[]{obj};
        
        for (Object tokenSuffix : tokenSuffixes) {
            String tokenName=destinationNode;
            tokenName+="_";
            JSONObject json = new JSONObject();
            json.put(varChild, tokenSuffix);
            tokenName+=json.toJSONString();
            Token token = new Token(mainToken, tokenName);
            printInfo(tokenName, destinationNode, varChild, tokenSuffix);
            api.expedientTokenRedirigir(token.getId(), destinationNode, false);
        }
      
    }
    
   
    private String fillDescription(String key) {
        Dir3Client client = Dir3Client.getClient();
        log.info("Denominacion[" + key + "]: ");
        String denominacion = client.getDenominacion(key);
        log.info(denominacion);
        return denominacion;
    }
    
   
    private void printInfo(String tokenName, String destinationNode, String child, Object valor) {

        String s = ">>> Redirigint token fill (tokenName=" + tokenName + ", "
                + "nodeDesti=" + destinationNode + ", "
                + "varFilla=" + child + ", "
                + "varFillaValor=" + valor + ")";
        log.info(s);
    }

    private static String getTokenName(Token parent, String transitionName) {

        if (transitionName == null) {
            int size = parent.getChildren() != null ? parent.getChildren().size() + 1 : 1;
            return Integer.toString(size);
        }

        String tokenName = transitionName;

        for (int i = 2; parent.hasChild(tokenName); i++) {
            tokenName = transitionName + Integer.toString(i);
        }

        return tokenName;

    }

    @Override
    public void retrocedir(HeliumApi api, List<String> list) throws Exception {
        return;
    }

    public String getDestinationNode() {
        return destinationNode;
    }

    public void setDestinationNode(String destinationNode) {
        this.destinationNode = destinationNode;
    }

    public String getVarChildren() {
        return varChildren;
    }

    public void setVarChildren(String varChildren) {
        this.varChildren = varChildren;
    }

    public String getVarChild() {
        return varChild;
    }

    public void setVarChild(String varChild) {
        this.varChild = varChild;
    }

    public String getMultiple() {
        return multiple;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }
    
}
