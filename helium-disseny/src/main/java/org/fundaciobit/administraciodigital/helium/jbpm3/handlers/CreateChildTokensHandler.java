package org.fundaciobit.administraciodigital.helium.jbpm3.handlers;

import java.util.Arrays;
import java.util.List;
import net.conselldemallorca.helium.jbpm3.api.HeliumActionHandler;
import net.conselldemallorca.helium.jbpm3.api.HeliumApi;
import net.conselldemallorca.helium.jbpm3.handlers.exception.HeliumHandlerException;
import org.fundaciobit.administraciodigital.helium.jbpm3.utils.Dir3Client;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;

/**
 *
 * @author gdeignacio
 */
public abstract class CreateChildTokensHandler extends HeliumActionHandler {

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

        //List tokenSuffixes =  new ArrayList();
        JbpmContext context = JbpmConfiguration.getInstance().createJbpmContext();

        ExecutionContext executionContext = new ExecutionContext(context.getToken(api.getToken().getId()));

        ProcessInstance processInstance = context.getToken(api.getToken().getId()).getProcessInstance();

        Object obj = executionContext.getVariable(varMultiple);
        boolean multiple = "true".equalsIgnoreCase(esMultiple);
        if (obj == null) {
            return;
        }
        Object[] tokenSuffixes = (multiple && obj instanceof Object[]) ? (Object[]) obj : new Object[]{obj};

        /*
        if (o instanceof Object[]){
            for (Object element:(Object[])o){
                //tokenSuffixes.add(getTokenSuffixElement(element));
                tokenSuffixes.add(element);
            }
        } else {
            //tokenSuffixes.add(getTokenSuffixElement(o));
            tokenSuffixes.add(o);
        }*/
        for (Object tokenSuffix : tokenSuffixes) {

            Object[] aTokenSuffix = null;
            String sTokenSuffix = null;
            boolean isArray;
            String lastItem = null;
            if (tokenSuffix instanceof Object[]){
                aTokenSuffix = (Object[]) tokenSuffix;
                isArray = true;
                lastItem = aTokenSuffix[aTokenSuffix.length -1].toString();
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
            
            String description = fillDescription(lastItem); 
            
            executionContext.setVariable(varFillaDesc, description);
            printInfo(tokenName, nodeDesti, varFilla, tokenSuffix);
            //tokenRedirigir(tokenFill.getId(), nodeDesti, false);

            api.expedientTokenRedirigir(tokenFill.getId(), nodeDesti, multiple);

        }
    }

    protected abstract String fillDescription(String key);
    
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
