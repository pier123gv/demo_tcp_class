/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tcpdemoclient;

import com.mycompany.networklayer.TCPClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pier
 */
public class TCPDemoClien {

    public static void main(String[] args) {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(new File("configuration.properties")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TCPDemoClien.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TCPDemoClien.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sslRoute = p.getProperty("SSL_CERTIFICATE_ROUTE");
        String sslPassword = p.getProperty("SSL_CERTIFICATE_ROUTE");
        System.setProperty("javax.net.ssl.keyStore",sslRoute);
        System.setProperty("javax.net.ssl.keyStorePassword",sslPassword);
        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.trustStore", sslRoute);
        System.setProperty("javax.net.ssl.trustStorePassword", sslPassword);
        System.setProperty("javax.net.ssl.trustStoreType", "PKCS12");
        Scanner in = new Scanner(System.in);
        System.out.println("Type your name: ");
        String name = in.nextLine();
        System.out.println("Type your last name: ");
        String lastName = in.nextLine();
        System.out.println("Name: "+ name + " Last name:" + lastName);
        TCPClient client = new TCPClient("10.244.230.154",9090); 
        client.sendMessage(name, lastName);
        System.out.println("Mensaje enviado");
                
    }
}
