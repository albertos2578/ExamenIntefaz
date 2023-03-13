package com.mycompany.jdbc_fx;

import controler.AlumnosDAOMYSQL;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.asignaturas;
import net.sf.jasperreports.engine.JRException;

public class PrimaryController implements Initializable {
     
    static AlumnosDAOMYSQL dau = new AlumnosDAOMYSQL();
   
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
    // Validar el campo SGE
    if (!validarNumeroEntreCeroYDiez(SGE)) {
        return;
    }
    nuevoAlumno.setSGE(Integer.parseInt(SGE.getText()));
    // Validar el campo PMDM
    if (!validarNumeroEntreCeroYDiez(PMDM)) {
        return;
    }
    nuevoAlumno.setPMDM(Integer.parseInt(PMDM.getText()));
    // Validar el campo PSP
    if (!validarNumeroEntreCeroYDiez(PSP)) {
        return;
    }
    nuevoAlumno.setPSP(Integer.parseInt(PSP.getText()));
    // Validar el campo EIE
    if (!validarNumeroEntreCeroYDiez(EIE)) {
        return;
    }
    nuevoAlumno.setEIE(Integer.parseInt(EIE.getText()));
    // Validar el campo HLC
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
       Nombre.setText("");
       Apellidos.setText("");
       AD.setText("");
       DI.setText("");
       EIE.setText("");
       PSP.setText("");
       HLC.setText("");
       PMDM.setText("");
       SGE.setText("");
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
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();
    }

    @FXML
    private void Descargar(ActionEvent event) {
         try {
            Informe.showReport();
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Informe.pdfReport();
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
@FXML
private void mostrarAlumno(MouseEvent event) {
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
