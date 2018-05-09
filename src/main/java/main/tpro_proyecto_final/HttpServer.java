package main.tpro_proyecto_final;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HttpServer implements Runnable {
    private ServerSocket socket;    
    private EjercicioTpro ejercicio;
    

    HttpServer(ServerSocket socket) {
        this.socket = socket;        
    }

    @Override
    public void run() {
        while (true) {
            try {
                init();
            } catch (IOException ex) {
                Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    private void init() throws IOException{
        Socket clientSocket = null;
        try {            
            clientSocket = socket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        
        String inputLine, outputLine;
        
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            if(inputLine.startsWith("GET")){
                String path = inputLine.split(" ")[1];
                if(path.equals("/") || path.equals("/index.html")){                                        
                    File indexFile =new File("src/main/resources/index.html");                    
                    String output="";
                    String text;                    
                    try {                        
                        FileReader input = new FileReader(indexFile);                        
                        BufferedReader br = new BufferedReader(input);
                        
                        while((text = br.readLine())!=null) {
                            output+=text;
                        }                        
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n\r\n" + output;
                    
                    out.println(outputLine);
                }
                else if(path.contains("solicitud")){
                    
                    System.out.println("BOOOOOOOOOOOOOOOOM");                                                          
                    String cadena = path.split("/")[2]; 
                    List<Character> xh = new ArrayList<Character>();
                    for(int i=0; i<cadena.length()-1; i++){
                       xh.add(cadena.charAt(i));
                    }
                    System.out.println(xh);                                                          
                    boolean bo = false;
                    bo = ejercicio.ejercicio(xh);
                    
                    try{    
                        String res = "NO";
                        
                        if(bo){
                            res = "SI";
                        }
                        
                        
                        
                        outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n\r\n"
                        + res;

                        out.println(outputLine);                    
                    }catch(Exception e){
                    }                                                            
                }
            }
            
            if (!in.ready()) {
                break;
            }
            
            if (inputLine.equals("")) break;
        }
        
        out.close();
        in.close();
        clientSocket.close();
    }
    
}
