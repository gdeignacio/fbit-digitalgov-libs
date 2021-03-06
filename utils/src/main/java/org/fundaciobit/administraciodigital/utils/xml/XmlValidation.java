/*
 * Copyright 2016 gdeignacio.
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
package org.fundaciobit.administraciodigital.utils.xml;

/**
 *
 * @author gdeignacio
 */
import java.io.IOException;
import javax.activation.DataHandler;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XmlValidation {

    public static boolean validateXMLSchema(DataHandler xsd, DataHandler xml) {

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source xsdSource = new StreamSource(xsd.getInputStream());
            Schema schema = factory.newSchema(xsdSource);
            Validator validator = schema.newValidator();
            Source xmlSource = new StreamSource(xml.getInputStream());
            validator.validate(xmlSource);

        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        } catch (SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }

        return true;
    }
}
