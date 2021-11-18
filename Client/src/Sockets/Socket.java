package Sockets;

import Interface.GameWindow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class Socket {

    private final int port = 25578;
    protected String serverMessage;
    protected ServerSocket serverSocket;
    protected java.net.Socket cs;
    static String message;
    static Boolean conection = false;


    public static String getMessage() {
        return message;
    }

    public static Boolean tryConection() {
        return conection;
    }


    @SuppressWarnings("deprecation")
    public Socket() throws IOException {
        try {

            serverSocket = new ServerSocket(port);

            while (true) {
                message = "";
                System.out.print("New Conection ");
                cs = new java.net.Socket();
                System.out.println("Waiting for new object");
                cs = serverSocket.accept();
                System.out.println("Successful connection ");
                conection = true;

                DataOutputStream outputBuffer =
                        new DataOutputStream(cs.getOutputStream());
                DataInputStream inputBuffer =
                        new DataInputStream(cs.getInputStream());

                while((serverMessage = inputBuffer.readLine()) != null) {

                    int ascii;
                    String auxiliar = "";
                    for(int i = 0; i < serverMessage.length(); i++){
                        ascii   = (int) serverMessage.charAt(i);
                        if(ascii >= 48 && ascii <= 57){
                            auxiliar += serverMessage.charAt(i);
                        } else if(ascii >= 97 && ascii <= 122){
                            auxiliar += serverMessage.charAt(i);
                        } else if(ascii == 44){
                            auxiliar += serverMessage.charAt(i);
                        }
                    }

                    serverMessage = auxiliar;

                    System.out.println("------Receiving server data-------");
                    System.out.print("Server message: ");
                    System.out.println(serverMessage);
                    message = serverMessage;
                    GameWindow.putText(message);
                }
                cs.close();
                inputBuffer.close();
                outputBuffer.close();
                Thread.sleep(1);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
