/*
 * Copyright 2017 gdeignacio.
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
package org.fundaciobit.administraciodigital.utils.cxf.authentication;

import java.net.Authenticator;
import java.net.PasswordAuthentication;


/**
 *
 * @author gdeignacio
 */
public class AuthenticatorReplacer {

    public static void main(String args[]) throws Exception {
        
           Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "admin",
                        "admincmaib".toCharArray()
                 );
            }
        });
        
    }

}