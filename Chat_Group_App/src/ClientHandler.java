import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    //track all the clients in arraylist, to send a message to all
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket; //passed from the Server
    private BufferedReader bufferedReader; //read message from the clients
    private BufferedWriter bufferedWriter; //send messages to clients
    private String clientUsername;

    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));  //byte stream wrapped into char stream
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));  //byte stream wrapped into char stream
            this.clientUsername = bufferedReader.readLine();  //read until enter is pressed
            clientHandlers.add(this);
            broadcastMessage("Server: "+ clientUsername + " has entered the chat.");
        }catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    @Override
    public void run() {
        //everything runs on separate thread
        String messageFromClient;
        while (socket.isConnected()){
            try{
                messageFromClient = bufferedReader.readLine(); //stops here until we get message
                broadcastMessage(messageFromClient);
            }catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }
    public void broadcastMessage(String messageToSend){
        for(ClientHandler clientHandler : clientHandlers){
            try{
                if(!clientHandler.clientUsername.equals(clientUsername)){
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine(); //enter press
                    clientHandler.bufferedWriter.flush(); //manually fill the buffer
                }
            }catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }
    public void removeClientHandler(){
        clientHandlers.remove(this);  //user left the chat
        broadcastMessage("Server: " + clientUsername + " has left the chat");
    }
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClientHandler();
        //close everything
        try {
            if(bufferedReader !=null){
                bufferedReader.close();
            }
            if(bufferedWriter !=null){
                bufferedWriter.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}











