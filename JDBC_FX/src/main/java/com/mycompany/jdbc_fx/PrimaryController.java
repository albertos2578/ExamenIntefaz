package com.mycompany.jdbc_fx;

import controler.Conexion;
import controler.AlumnosDAOMYSQL;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.asignaturas;

public class PrimaryController implements Initializable {
     //el modal es un alert
    static AlumnosDAOMYSQL dau = new AlumnosDAOMYSQL();
    ArrayList<Timestamp> tiemposDePedidos = new ArrayList<Timestamp>();
    int numerito;
    asignaturas pedidosActualizar= new asignaturas();     
   
    @FXML
    private Button btnAñadir;
    @FXML
    private TableView<asignaturas> tabla;
    @FXML
    private TextField Apellidos;
    @FXML
    private TextField AD;
    @FXML
    private TextField SGE;
    @FXML
    private TextField DI;
    @FXML
    private TextField Nombre;
    @FXML
    private TextField PMDM;
    @FXML
    private TextField PSP;
    @FXML
    private TextField EIE;
    @FXML
    private TextField HLC;
    @FXML
    private Button salir;
    @FXML
    private Button Descargar;
    @FXML
    private TableColumn<asignaturas, String> nombre;
    @FXML
    private TableColumn<asignaturas, String> apellidos;
    @FXML
    private TableColumn<asignaturas, Integer> ad;
    @FXML
    private TableColumn<asignaturas, Integer> sge;
    @FXML
    private TableColumn<asignaturas, Integer> di;
    @FXML
    private TableColumn<asignaturas, Integer> pmdm;
    @FXML
    private TableColumn<asignaturas, Integer> psp;
    @FXML
    private TableColumn<asignaturas, Integer> eie;
    @FXML
    private TableColumn<asignaturas, Integer> hlc;

   
  
      @Override
    public void initialize(URL url, ResourceBundle rb) {
             this.nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
	     this.apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
             this.ad.setCellValueFactory(new PropertyValueFactory<>("AD"));
              this.sge.setCellValueFactory(new PropertyValueFactory<>("SGE"));
              this.di.setCellValueFactory(new PropertyValueFactory<>("DI"));
              this.pmdm.setCellValueFactory(new PropertyValueFactory<>("PMDM"));
              this.psp.setCellValueFactory(new PropertyValueFactory<>("PSP"));
              this.eie.setCellValueFactory(new PropertyValueFactory<>("EIE"));
              this.hlc.setCellValueFactory(new PropertyValueFactory<>("HLC"));
            asignaturas p= new asignaturas();
       ArrayList<asignaturas> items= dau.getAllAlumnos();
    var   itemss = FXCollections.observableList(items);
       this.tabla.setItems(itemss);
    }
  
  @FXML
private void añadirAlumno(ActionEvent event) {
    // Crear una instancia de la clase asignaturas
    asignaturas nuevoAlumno = new asignaturas();

    // Validar el campo Nombre
   
    nuevoAlumno.setNombre(Nombre.getText());
    nuevoAlumno.setApellidos(Apellidos.getText());

    // Validar el campo AD
    if (!validarNumeroEntreCeroYDiez(AD)) {
        return;
    }
    nuevoAlumno.setAD(Integer.parseInt(AD.getText()));

    // Validar el campo DI
    if (!validarNumeroEntreCeroYDiez(DI)) {
        return;
    }
    nuevoAlumno.setDI(Integer.parseInt(DI.getText()));
    
    if (!validarNumeroEntreCeroYDiez(SGE)) {
        return;
    }
    nuevoAlumno.setSGE(Integer.parseInt(SGE.getText()));
    
    if (!validarNumeroEntreCeroYDiez(PMDM)) {
        return;
    }
    nuevoAlumno.setPMDM(Integer.parseInt(PMDM.getText()));
    
    if (!validarNumeroEntreCeroYDiez(PSP)) {
        return;
    }
    nuevoAlumno.setPSP(Integer.parseInt(PSP.getText()));
    
    if (!validarNumeroEntreCeroYDiez(EIE)) {
        return;
    }
    nuevoAlumno.setEIE(Integer.parseInt(EIE.getText()));
    
    if (!validarNumeroEntreCeroYDiez(HLC)) {
        return;
    }
    nuevoAlumno.setHLC(Integer.parseInt(HLC.getText()));
    // Añadir la nueva fila a la tabla
    dau.add(nuevoAlumno);
      asignaturas p= new asignaturas();
       ArrayList<asignaturas> items= dau.getAllAlumnos();
    var   itemss = FXCollections.observableList(items);
       this.tabla.setItems(itemss);
}
    
public boolean validarNumeroEntreCeroYDiez(TextField textField) {
    String input = textField.getText();
    try {
        int numero = Integer.parseInt(input);
        if (numero >= 0 && numero <= 10) {
            return true;
        } else {
            
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Valor inválido");
            alert.setContentText("El número debe estar entre 0 y 10");
            alert.showAndWait();
            return false;
        }
    } catch (NumberFormatException e) {
    
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText("Valor inválido");
        alert.setContentText("Debe ingresar un número entero válido");
        alert.showAndWait();
        return false;
    }
}
    

    @FXML
    private void salir(ActionEvent event) {
    }

    @FXML
    private void Descargar(ActionEvent event) {
    }

    @FXML
private void mostrarPedido(MouseEvent event) {
    asignaturas asig = tabla.getSelectionModel().getSelectedItem();
    if (asig != null) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información del alumno");
        alert.setHeaderText("Alumno: " + asig.getNombre());

        // Calcular la media de las asignaturas aprobadas
        int numAprobadas = 0;
        double sumNotasAprobadas = 0;
        if (asig.getAD() >= 5) {
            numAprobadas++;
            sumNotasAprobadas += asig.getAD();
        }
        if (asig.getSGE() >= 5) {
            numAprobadas++;
            sumNotasAprobadas += asig.getSGE();
        }
        if (asig.getDI() >= 5) {
            numAprobadas++;
            sumNotasAprobadas += asig.getDI();
        }
        if (asig.getPMDM() >= 5) {
            numAprobadas++;
            sumNotasAprobadas += asig.getPMDM();
        }
        if (asig.getPSP() >= 5) {
            numAprobadas++;
            sumNotasAprobadas += asig.getPSP();
        }
        if (asig.getEIE() >= 5) {
            numAprobadas++;
            sumNotasAprobadas += asig.getEIE();
        }
        if (asig.getHLC() >= 5) {
            numAprobadas++;
            sumNotasAprobadas += asig.getHLC();
        }
        
        // Si todas las asignaturas están aprobadas, mostrar la media
        if (numAprobadas == 7) {
            double media = sumNotasAprobadas / 7;
            alert.setContentText("Nota media: " + media);
        } else { // Si hay alguna asignatura suspendida, mostrar el número de asignaturas suspendidas
            int numSuspensos = 7 - numAprobadas;
            alert.setContentText("Número de asignaturas suspendidas: " + numSuspensos);
        }

        alert.showAndWait();
    }
}
}
