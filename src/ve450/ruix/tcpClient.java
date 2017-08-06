package ve450.ruix;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.Reader;
import java.net.Socket;
import java.net.ServerSocket;  

public class tcpClient {
    public static void main(String[] args) throws Exception{  
        int port = 8899;   
        ServerSocket server = new ServerSocket(port);  
        System.out.println("GOGOGO");
        Socket socket = server.accept();  
        System.out.println("YEP"); 
        Reader reader = new InputStreamReader(socket.getInputStream());  
        char chars[] = new char[64];  
        int len;  
        StringBuilder sb = new StringBuilder();  
        while ((len=reader.read(chars)) != -1) {  
           sb.append(new String(chars, 0, len));  
        }  
        System.out.println("from client: " + sb);  
        reader.close();  
        socket.close();  
        server.close(); 
    }  
}
