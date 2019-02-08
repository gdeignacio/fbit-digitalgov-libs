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
package org.fundaciobit.administraciodigital.utils.io;

import freemarker.core.Environment;
import freemarker.core.NonStringException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModelException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateException;
import net.sf.jooreports.templates.DocumentTemplateFactory;
import org.fundaciobit.administraciodigital.utils.util.DataHandlers;

/**
 *
 * @author gdeignacio
 */
public class JooReports {
    
    public static DataHandler generar(DataHandler template, Map<String,Object> model) throws IOException{
        try {
            byte[] result = generarAmbJooReports(DataHandlers.dataHandlerToByteArray(template), model);
            return DataHandlers.byteArrayToDataHandler(result, "application/vnd.oasis.opendocument.text");
        } catch (DocumentTemplateException ex) {
            Logger.getLogger(JooReports.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private static byte[] generarAmbJooReports(
            byte[] plantillaContingut,
            Map<String, Object> model) throws IOException, DocumentTemplateException {
        DocumentTemplateFactory documentTemplateFactory = new DocumentTemplateFactory();
        documentTemplateFactory.getFreemarkerConfiguration().setTemplateExceptionHandler(new TemplateExceptionHandler() {
            public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
                try {
                    if (te instanceof TemplateModelException || te instanceof NonStringException) {
                        out.write("[exception]");
                    } else {
                        out.write("[???]");
                    }
                    out.write("<office:annotation><dc:creator>" + "None" + "</dc:creator><dc:date>" + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()) + "</dc:date><text:p><![CDATA[" + te.getFTLInstructionStack() + "\n" + te.getMessage() + "]]></text:p></office:annotation>");
                } catch (IOException e) {
                    throw new TemplateException("Failed to print error message. Cause: " + e, env);
                }
            }
        });
        documentTemplateFactory.getFreemarkerConfiguration().setLocale(new Locale("ca", "ES"));
        DocumentTemplate template = documentTemplateFactory.getTemplate(
                new ByteArrayInputStream(plantillaContingut));
        ByteArrayOutputStream resultat = new ByteArrayOutputStream();
        template.setContentWrapper(new DocumentTemplate.ContentWrapper() {
            public String wrapContent(String content) {
                return "[#ftl]\n"
                        + "[#escape any as any?xml?replace(\"[\\n|\\r|\\r\\n|\\n\\r]\",\"</text:p> <text:p>\")]\n"
                        + content
                        + "[/#escape]";
            }
        });
        template.createDocument(model, resultat);
        return resultat.toByteArray();
    }
    
    
    
}
