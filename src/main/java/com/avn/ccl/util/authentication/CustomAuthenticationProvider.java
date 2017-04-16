/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.ccl.util.authentication;

import com.avn.ccl.util.ApplicationUtil;
import com.avn.ccl.util.varlist.CommonVarList;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//
//import org.springframework.security.core.AuthenticationException;
import org.wso2.carbon.authenticator.stub.AuthenticationAdminStub;
import org.wso2.carbon.authenticator.stub.LoginAuthenticationExceptionException;

public class CustomAuthenticationProvider/* implements AuthenticationProvider */{

//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String userName = authentication.getName().trim();
//        String password = authentication.getCredentials().toString().trim();
//
//        boolean status;
//        ClassLoader classLoader = getClass().getClassLoader();
//        Authentication auth = null;
//        try {
//            System.setProperty("javax.net.ssl.trustStore", classLoader.getResource("wso2carbon.jks").getFile());
//            System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
//            System.setProperty("javax.net.ssl.trustStoreType", "JKS");
//
//            AuthenticationAdminStub stub = new AuthenticationAdminStub(ApplicationUtil.SERVLET_CONTEXT.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_LDAP_LOGIN));
//            stub._getServiceClient().getOptions().setManageSession(true);
//            status = stub.login(userName, password, ApplicationUtil.SERVLET_CONTEXT.getInitParameter(CommonVarList.CONTEXT_PARAM_SERVICE_LDAP_AUTHENTICATION_HOST));
//            System.out.println("username : " + userName);
////            System.out.println("password : " + password);
//            System.out.println("Login Status : " + status);
////            status = true;
//            if (status) {
//                auth = new UsernamePasswordAuthenticationToken(userName, password);
//            }
//        } catch (RemoteException | LoginAuthenticationExceptionException ex) {
//            Logger.getLogger(CustomAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
//        }
//        return auth;
//    }
//
//    @Override
//    public boolean supports(Class<? extends Object> authentication) {
//        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
//    }
}
