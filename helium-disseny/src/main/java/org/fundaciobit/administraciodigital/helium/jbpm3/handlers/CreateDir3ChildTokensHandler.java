package org.fundaciobit.administraciodigital.helium.jbpm3.handlers;

import java.util.Arrays;
import java.util.List;
import net.conselldemallorca.helium.jbpm3.api.HeliumActionHandler;
import net.conselldemallorca.helium.jbpm3.api.HeliumApi;
import net.conselldemallorca.helium.jbpm3.handlers.exception.HeliumHandlerException;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.fundaciobit.administraciodigital.dir3caib.rest.client.CatalogoDIR3Client;

/**
 *
 * @author gdeignacio
 */
public class CreateDir3ChildTokensHandler extends HeliumActionHandler {
    
    private final static String NO_DIR3 = "NDIR3";

    String nodeDesti;
    String varFilla;
    String varFillaDesc;
    String varMultiple;
    String esMultiple;

    private static final long serialVersionUID = 3513847803530877133L;

    /**
     *
     * @param api
     * @throws HeliumHandlerException
     */
    @Override
    public void execute(HeliumApi api) throws HeliumHandlerException {

        JbpmContext context = JbpmConfiguration.getInstance().createJbpmContext();

        ExecutionContext executionContext = new ExecutionContext(context.getToken(api.getToken().getId()));

        ProcessInstance processInstance = context.getToken(api.getToken().getId()).getProcessInstance();

        Object obj = executionContext.getVariable(varMultiple);
        boolean multiple = "true".equalsIgnoreCase(esMultiple);
        
        if (obj == null) {
            return;
        }
        
        Object[] tokenSuffixes = (multiple && obj instanceof Object[]) ? (Object[]) obj : new Object[]{obj};

        for (Object tokenSuffix : tokenSuffixes) {

            Object[] aTokenSuffix = null;
            String sTokenSuffix = null;
            boolean isArray;
            String lastItem = null;
            String lastButOneItem = null;
            if (tokenSuffix instanceof Object[]){
                aTokenSuffix = (Object[]) tokenSuffix;
                isArray = true;
                lastItem = aTokenSuffix[aTokenSuffix.length -1].toString();
                if (aTokenSuffix.length>1){   
                    lastButOneItem = aTokenSuffix[aTokenSuffix.length -2].toString();
                    lastItem = (NO_DIR3.equals(lastButOneItem))?lastItem:lastButOneItem;
                }    
            } else {
                sTokenSuffix = tokenSuffix.toString();
                isArray = false;
                lastItem = sTokenSuffix;
            }
            
            String strTokenSuffix = (isArray)?Arrays.toString(aTokenSuffix).replace(", ", "#").replaceAll("[\\[\\]]", ""):sTokenSuffix;
            
            //String.join("_", (List)Arrays.asList((Object[])tokenSuffix) )
            //String strTokenSuffix = (tokenSuffix instanceof Object[]) ? Arrays.toString((Object[]) tokenSuffix).replace(", ", "#").replaceAll("[\\[\\]]", "") : tokenSuffix.toString();
            
            Token tokenPare = executionContext.getToken();
            String tokenName = getTokenName(tokenPare, nodeDesti + "_" + strTokenSuffix);
            Token tokenFill = new Token(tokenPare, tokenName);
            executionContext.setVariable(varFilla, tokenSuffix);
            
            System.out.println("Last item abans: "  + lastItem);
            
            String description = fillDescription(lastItem); 
            
            System.out.println("Last item: "  + lastItem);
            System.out.println("Descripcion: "  + description);
            
            executionContext.setVariable(varFillaDesc, description);
            printInfo(tokenName, nodeDesti, varFilla, tokenSuffix);
            
            //tokenRedirigir(tokenFill.getId(), nodeDesti, false);

            api.expedientTokenRedirigir(tokenFill.getId(), nodeDesti, multiple);

        }
    }
    
    
    private String fillDescription(String key){
        CatalogoDIR3Client client = CatalogoDIR3Client.getClient();
        String denominacion = client.getByCodigo(key);
        return denominacion;
    }

    /*
    private String fillDescription(String key) {
        Dir3Client client = Dir3Client.getClient();
        System.out.println("Denominacion[" + key + "]: ");
        String denominacion = client.getDenominacion(key);
        System.out.println(denominacion);
        return denominacion;
    }
    */
    
    public void setNodeDesti(String nodeDesti) {
        this.nodeDesti = nodeDesti;
    }

    public void setVarFilla(String varFilla) {
        this.varFilla = varFilla;
    }
    
     public void setVarFillaDesc(String varFillaDesc) {
        this.varFillaDesc = varFillaDesc;
    }

    public void setVarMultiple(String varMultiple) {
        this.varMultiple = varMultiple;
    }

    public void setEsMultiple(String esMultiple) {
        this.esMultiple = esMultiple;
    }

    //protected abstract String getTokenSuffixElement(Object o);
    private void printInfo(String tokenName, String nodeDesti, String varFilla, Object valor) {

        String s = ">>> Redirigint token fill (tokenName=" + tokenName + ", "
                + "nodeDesti=" + nodeDesti + ", "
                + "varFilla=" + varFilla + ", "
                + "varFillaValor=" + valor + ")";
        System.out.println(s);
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

}
