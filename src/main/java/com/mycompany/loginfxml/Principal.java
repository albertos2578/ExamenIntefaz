package com.mycompany.loginfxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;

public class Principal implements Initializable{

    private TextField txtUser;
    private PasswordField txtPassword;
    @FXML
    private Label info;
    @FXML
    private Button btnCarta;
    @FXML
    private Button btnComandas;
    @FXML
    private Button btnSalir;

  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try( Session s = HibernateUtil.getSessionFactory().openSession()){
            System.out.println("Conexión realizada con éxito");
        }
    }

    @FXML
    private void verCarta(ActionEvent event) {
        try {
            App.setRoot("carta");
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void verComandas(ActionEvent event) {
        try {
            App.setRoot("pedidos");
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Salir(ActionEvent event) {
        
    System.exit(0);
    }

    
}