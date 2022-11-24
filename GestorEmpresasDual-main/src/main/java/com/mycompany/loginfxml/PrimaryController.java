package com.mycompany.loginfxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import models.Alumno;
import models.Profesor;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class PrimaryController implements Initializable{

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAceptar;
    @FXML
    private CheckBox chkSesion;
    @FXML
    private Label info;

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void aceptar(ActionEvent event) {
        
        
        try( Session s = HibernateUtil.getSessionFactory().openSession()){
            
            Query q = s.createQuery("from Alumno where email=:param and password=:pwd");
            Query h = s.createQuery("from Profesor where email=:param and password=:pwd");
            q.setParameter("param",txtUser.getText() );
            q.setParameter("pwd", txtPassword.getText() );
              h.setParameter("param",txtUser.getText() );
            h.setParameter("pwd", txtPassword.getText() );

            ArrayList<Alumno> resultado = (ArrayList<Alumno>) q.list();
               ArrayList<Profesor> resultados = (ArrayList<Profesor>) h.list();
            
            if( resultado.size()>0){
                info.setText("Usuario existe");
                info.setStyle("-fx-background-color:green; -fx-text-fill:white;");
                
               
                
                try {
                    App.setRoot("principal");
                } catch (IOException ex) {
                    Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else if ( resultados.size()>0){
                info.setText("Profesor existe");
                info.setStyle("-fx-background-color:green; -fx-text-fill:white;");
                
          
                
                 try {
                    App.setRoot("ListadoAlumnos");
                    System.out.println("");
                } catch (IOException ex) {
                    Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
        
             }else{ 
                info.setText("Usuario no existe");
                info.setStyle("-fx-background-color:red; -fx-text-fill:white;");  
            }
        }
        
//        if(("francisco".equals( txtUser.getText()))
//             &&("1234".equals(txtPassword.getText()) ) ) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            
//            if( chkSesion.isSelected() ){
//                alert.setContentText("Usuario logueado correctamente y\nquiere mantener abierta la sesión");
//            }else{
//                alert.setContentText("Usuario logueado correctamente");
//            }
//            
//            info.setText("Usuario y contraseña correctas");
//            info.setStyle("-fx-background-color:green; -fx-text-fill:white;");
//            
//            alert.showAndWait();
//            
//            try {
//                App.setRoot("principal");
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            
//        }else{
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("Usuario no registrado");
//            alert.showAndWait();            
//        }
        
//        if(("francisco".equals( txtUser.getText()))
//             &&("1234".equals(txtPassword.getText()) ) ) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            
//            if( chkSesion.isSelected() ){
//                alert.setContentText("Usuario logueado correctamente y\nquiere mantener abierta la sesión");
//            }else{
//                alert.setContentText("Usuario logueado correctamente");
//            }
//            
//            info.setText("Usuario y contraseña correctas");
//            info.setStyle("-fx-background-color:green; -fx-text-fill:white;");
//            
//            alert.showAndWait();
//            
//            try {
//                App.setRoot("principal");
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            
//        }else{
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("Usuario no registrado");
//            alert.showAndWait();            
//        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try( Session s = HibernateUtil.getSessionFactory().openSession()){
            System.out.println("Conexión realizada con éxito");
        }
    }

    
}