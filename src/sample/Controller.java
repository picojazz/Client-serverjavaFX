package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller  implements Initializable{
    public String text;
    @FXML
    private TextField txtField;
    @FXML
    private Button serverButton;
    @FXML
    private Button sendButton;
    @FXML
    private Pane dialogpane;
    @FXML
    private Label server;

    private boolean isConnect = false;

    private Socket ss = new Socket();
   /* BufferedReader inn;
    PrintWriter outt;*/
    String line;

    @FXML
    void connectServer(ActionEvent event){

        try {
            Socket s = new Socket(InetAddress.getLocalHost(), 5555);
            ss = s;
            BufferedReader in = new BufferedReader(new InputStreamReader(ss.getInputStream()));
           // inn=in;
            PrintWriter out = new PrintWriter(ss.getOutputStream(), true);
           // outt=out;
            // Scanner clavier = new Scanner(System.in);
            isConnect = true;

            boolean more = true;
            //while(more){
             line = in.readLine();

            server.setText(line);
            System.out.println(line);
            line="";

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @FXML
    public void send(ActionEvent event){
        if(isConnect) {
            text = txtField.getText();


            //System.out.println(text);
            try {
                BufferedReader inn = new BufferedReader(new InputStreamReader(ss.getInputStream()));
                PrintWriter outt = new PrintWriter(ss.getOutputStream(), true);
                // Scanner clavier = new Scanner(System.in);

                boolean more = true;
                //while(more){


                if (line == null)
                    more = false;
                else {

                    if (!text.isEmpty()) {
                        //System.out.println(line);
                        line = text;
                        outt.println(line);
                        line = inn.readLine();
                        System.out.println(txtField.getText());
                        server.setText(server.getText() + "\n" + text);
                        System.out.println(line);
                        server.setText(server.getText() + "\n" + line);

                    } else {

                        outt.println(text);
                        server.setText(server.getText() + "\n" + "vous etes deconnecté");
                        isConnect = false;
                    }

                }
           /* if(!line.isEmpty()){
                System.out.println(line);
            }*/
                //}

            } catch (Exception e) {
                e.printStackTrace();
            }

            //System.out.println(txtField.getText());
            txtField.clear();
        }else {
            server.setText(server.getText() + "\n" + "vous etes deconnecté");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

   /* public TextField getTxtField() {
        txtField.setOnAction(this::send);
        return txtField;
    }*/
}
