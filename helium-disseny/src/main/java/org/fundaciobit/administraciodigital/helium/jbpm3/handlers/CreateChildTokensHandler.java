package org.fundaciobit.administraciodigital.helium.jbpm3.handlers;

import java.util.Arrays;

//import net.conselldemallorca.helium.jbpm3.handlers.BasicActionHandler;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.Token;


/**
 *
 * @author gdeignacio
 */
public class CreateChildTokensHandler extends BasicActionHandler {

    String nodeDesti;
    String varFilla;
    String varMultiple;
    String esMultiple;
    
    private static final long serialVersionUID = 3513847803530877133L;

    @Override
    public final void execute(ExecutionContext executionContext) throws Exception {

        //List tokenSuffixes =  new ArrayList();
         
        Object obj = executionContext.getVariable(varMultiple);
        boolean multiple = "true".equalsIgnoreCase(esMultiple);
        if (obj==null) return;
        Object[] tokenSuffixes = (multiple && obj instanceof Object[])?(Object[])obj:new Object[]{obj};
        
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
            
            
            //String.join("_", (List)Arrays.asList((Object[])tokenSuffix) )
                    
            String strTokenSuffix = (tokenSuffix instanceof Object[])?Arrays.toString((Object[])tokenSuffix).replace(", ", "#").replaceAll("[\\[\\]]", ""):tokenSuffix.toString();
            Token tokenPare = executionContext.getToken();
            String tokenName = getTokenName(tokenPare, nodeDesti + "_" + strTokenSuffix);
            Token tokenFill = new Token(tokenPare, tokenName);
            executionContext.setVariable(varFilla, tokenSuffix);
            printInfo(tokenName, nodeDesti, varFilla, tokenSuffix);
            tokenRedirigir(tokenFill.getId(), nodeDesti, false);
        }
    }

    public final void setNodeDesti(String nodeDesti) {
        this.nodeDesti = nodeDesti;
    }

    public final void setVarFilla(String varFilla) {
        this.varFilla = varFilla;
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

}
