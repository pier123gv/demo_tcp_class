/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tcpdemoserver.networklayer;

import com.mycompany.tcpcom.mycompany.tcpdemoserver.business.NameManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

/**
 *
 * @author pier
 */
public class TCPServer {
    private int port;

    public TCPServer(int port) {
        this.port = port;
    }
    
    public  void start(){
        try {
            SSLServerSocketFactory socketFactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
            SSLServerSocket serverSocket = (SSLServerSocket)socketFactory.createServerSocket(port);
            System.out.println("Server listening on port: "+port);
            while(true){
                //Connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected from: "+clientSocket.getInetAddress());
                //Defining input and output
                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
                
                String clientMessage = inputStream.readUTF().trim();
                String[] parts = clientMessage.split(":");
                System.out.println("Message: "+clientMessage);
                NameManager manager = new NameManager();
                String response = manager.generateMessage(parts[0],parts[1]);
                System.out.println("Response: "+ response);
                outputStream.writeUTF(response);
                clientSocket.close();
                System.out.println("Connection closed");
                
            }
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }
}
