import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main (String[] args){
        File[] fileToSend = new File[1];
        //Setup the Frame,which serves as container for JPanel, JLabel and JButton
        JFrame jFrame = new JFrame("Client's Side for File Transfer");
        jFrame.setSize(450, 450);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS)); //vertical arrangement
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //program stops upon closing
        //Specify the Text  for the Header Texts
        JLabel jlTitle = new JLabel("File Sender");
        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder(20,0 , 10, 0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Specify the Text  for the Header Texts
        JLabel jlFileName = new JLabel("Select a file to send.");
        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
        jlFileName.setBorder(new EmptyBorder(50,0,0,0));
        jlFileName.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Specify the Panel Box for Buttons
        JPanel jpButton = new JPanel();
        jpButton.setBorder(new EmptyBorder(75,0,10,0));
        //Specify the Send Button
        JButton jbSendFile = new JButton("Send File");
        jbSendFile.setPreferredSize(new Dimension(150,75));
        jbSendFile.setFont(new Font("Arial", Font.BOLD, 20));
        //Specify the Choose File Button
        JButton jbChooseFile = new JButton("Choose File");
        jbChooseFile.setPreferredSize(new Dimension(150,75));
        jbChooseFile.setFont(new Font("Arial", Font.BOLD, 20));
        //Add both buttons to the JPanel, which is JButton
        jpButton.add(jbSendFile);
        jpButton.add(jbChooseFile);

        jbChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();   //pop-up when click on ChooseFile
                jFileChooser.setDialogTitle("Select a file to send"); //with this dialoge text

                if(jFileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){  //appears center of the screen
                    fileToSend[0] = jFileChooser.getSelectedFile();  //when file is selected, will return the object of fileToSend
                    jlFileName.setText("The file you want to send is: " + fileToSend[0].getName()); //display text when selected
                }
            }
        });
        jbSendFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileToSend[0] ==null){    //if something was selected at all
                    jlFileName.setText("Pls select a file first.");
                }else {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(fileToSend[0].getAbsolutePath());
                        //via fileInputstream can reach the destination of the file
                        Socket socket = new Socket("localhost", 1234); //To connect with the Server
                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        //Use the connection socket and wrap with the data outputstream, to write from Client to the Server

                        String fileName = fileToSend[0].getName();   //Want to send the filename too, as bytes
                        byte[] fileNameBytes = fileName.getBytes();

                        byte[] fileContentBytes = new byte[(int) fileToSend[0].length()];//Send the content too
                        fileInputStream.read(fileContentBytes); //Read the file content and fill the Array

                        // sending
                        dataOutputStream.writeInt(fileName.length());  //how much data is sent, server will know form Int
                        dataOutputStream.write(fileNameBytes);   //actual file name

                        dataOutputStream.writeInt(fileContentBytes.length); //how big the byte array is, server will know
                        dataOutputStream.write(fileContentBytes); //comes the actual data
                    }catch(IOException error){
                        error.printStackTrace();
                    }
                }
            }
        });
        //Fill jFrame container with the Title, FileName & Buttons
        jFrame.add(jlTitle);
        jFrame.add(jlFileName);
        jFrame.add(jpButton);
        jFrame.setVisible(true);
    }
}
