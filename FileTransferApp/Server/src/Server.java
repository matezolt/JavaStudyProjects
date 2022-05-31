import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    static ArrayList<MyFile> myFiles= new ArrayList<>();  //holds the file objects to send over

    public static void main(String[] args) throws IOException {

        int fileId = 0;  // number of files
        //Setup the Frame,which serves as container for JPanel, JLabel
        JFrame jFrame = new JFrame("Server Side for File Transfer");
        jFrame.setSize(400,400);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ////Specify the Panel Box for Files listed
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        //Specify the Scrollbar, which will be always visible
        JScrollPane jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Specify the Text for the Header Texts
        JLabel jlTitle = new JLabel("File Receiver");
        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder(20,0,10,0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Add Title, Scrollbar and panel to the Frame
        jFrame.add(jlTitle);
        jFrame.add(jScrollPane);
        jFrame.setVisible(true);
        //Connection with the Client
        ServerSocket serverSocket = new ServerSocket(1234);

        while (true){   //will run continuosly
            try{
                Socket socket= serverSocket.accept();  //Server waits for a Client and returns a sokcet Object
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream()); //allow to get incoming data from Client

                int fileNameLength= dataInputStream.readInt(); //We need to read first the Int of the name

                if(fileNameLength >0){  //if the file is sent
                    byte[] fileNameBytes = new byte[fileNameLength]; //create Byte array
                    dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);//Read int the file
                    String fileName = new String(fileNameBytes); //Coverting to String

                    int fileContentLength = dataInputStream.readInt(); //We need to read first the Int of the content
                    if(fileContentLength > 0 ){
                        byte [] fileContentBytes = new byte[fileContentLength]; //create Byte array
                        dataInputStream.readFully(fileContentBytes, 0, fileContentLength);//Read int the file array,which will be filled

                        //House the Content
                        JPanel jpFileRow= new JPanel();
                        jpFileRow.setLayout(new BoxLayout(jpFileRow, BoxLayout.Y_AXIS));
                        //File Name Specs
                        JLabel jlFileName = new JLabel(fileName);
                        jlFileName.setFont(new Font("Arial", Font.BOLD,20));
                        jlFileName.setBorder(new EmptyBorder(10,0,10,0));
                        jlFileName.setAlignmentX(Component.CENTER_ALIGNMENT);

                        if(getFileExtension(fileName).equalsIgnoreCase("txt")){
                            jpFileRow.setName(String.valueOf(fileId)); //each row will have an Id(fileId)
                            jpFileRow.addMouseListener(getMyMouseListener()); //to click when to open
                            //Add the files to the row
                            jpFileRow.add(jlFileName);
                            jPanel.add(jpFileRow);
                            jFrame.validate();
                        }else {  //if it is not text
                            jpFileRow.setName(String.valueOf(fileId));  //each row will have an Id(fileId)
                            jpFileRow.addMouseListener(getMyMouseListener());//to click when to open

                            jpFileRow.add(jlFileName); //Add the files to the row
                            jPanel.add(jpFileRow); //Add to the panel, the file name

                            jFrame.validate();
                        }
                        //Array is Empty, so need to add the actual data
                        myFiles.add(new MyFile(fileId, fileName, fileContentBytes, getFileExtension(fileName)));
                        fileId++;  //increase the Id, when new item selected
                    }
                }
            }catch (IOException error){
                error.printStackTrace();
            }
        }

    }
    //Filling the
    public static JFrame createFrame(String fileName, byte[] fileData, String fileExtension){
        //Define the container
        JFrame jFrame = new JFrame("File Downloader");
        jFrame.setSize(400,400);
        //Panel to contain the Title, Prompt Message
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        //Define Header
        JLabel jlTitle = new JLabel("Server File Downloader");
        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder(20,0,10,0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Define Text when file is selected
        JLabel jlPrompt = new JLabel("Are you sure you want to download " + fileName);
        jlPrompt.setFont(new Font("Arial", Font.BOLD, 20));
        jlPrompt.setBorder(new EmptyBorder(20,0,10,0));
        jlPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Define Button Yes
        JButton jbYes= new JButton("Yes");
        jbYes.setPreferredSize(new Dimension(150, 75));
        jbYes.setFont(new Font("Arial", Font.BOLD, 20));
        //Define Button No
        JButton jbNo = new JButton("No");
        jbNo.setPreferredSize(new Dimension(150, 75));
        jbNo.setFont(new Font("Arial", Font.BOLD, 20));
        //Define how to display the File content
        JLabel jlFileContent = new JLabel();
        jlFileContent.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Panel for Buttons
        JPanel jpButtons = new JPanel();
        jpButtons.setBorder(new EmptyBorder(20,0,10,0));
        jpButtons.add(jbYes);
        jpButtons.add(jbNo);
        //check the extensions
        if(fileExtension.equalsIgnoreCase("txt")){  //for text file
            jlFileContent.setText("<html>" + new String(fileData) +"</html>");  //html will allow line breaks
        }else {
            jlFileContent.setIcon(new ImageIcon(fileData));  //for image
        }
        jbYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File fileToDownload = new File(fileName); //download it to the server(where it is), name is current name
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload); //to be able to write to the file
                    fileOutputStream.write(fileData); //write the array of bytes
                    fileOutputStream.close();   //close the stream
                    jFrame.dispose();        //close Frame
                }catch (IOException error){
                    error.printStackTrace();
                }
            }
        });
        jbNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  //if No selected close the window
                jFrame.dispose();
            }
        });
        jPanel.add(jlTitle);   //add Title to the panel
        jPanel.add(jlPrompt);   //add Prompt to the panel
        jPanel.add(jlFileContent); //add Img or Text  to the panel
        jPanel.add(jpButtons); //add Buttons to the panel

        jFrame.add(jPanel);  //Add the whole Panel the frame
        return jFrame;
    }
    public static MouseListener getMyMouseListener(){
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel jPanel = (JPanel) e.getSource();  //e is the click event, source is jPanel
                int fileId = Integer.parseInt(jPanel.getName()); //id of the jPanel(fileId) to know which one was clicked
                for(MyFile myfile:myFiles){ //loop through to the array to see which one was clicked
                    if(myfile.getId() ==fileId){ //when Id matches the one was clicked
                        JFrame jfPreview= createFrame(myfile.getName(), myfile.getData(), myfile.getFileExtension()); //PopUp when file is clicked
                        jfPreview.setVisible(true);
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }
    public static String getFileExtension(String fileName){
        int i = fileName.lastIndexOf('.');
        if(i>0){ //if there is . need to return the second half of the String
            return fileName.substring((i+1));
        }else { //no match if . not used
            return "No extension found.";
        }
    }

}
