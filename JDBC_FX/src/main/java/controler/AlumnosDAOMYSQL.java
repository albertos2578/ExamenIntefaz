/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import models.asignaturas;

/**
 *
 * @author AlbertoMoralesGÃ¡lvez
 */

  

    public class AlumnosDAOMYSQL implements AlumnosDAO {
    
   


       private static final String alumnos = "SELECT * FROM `asignaturas` order by nombre";
       private static final String add_query = "INSERT INTO `asignaturas` (`nombre`, `apellidos`, `AD`, `SGE`, `DI`, `PMDM`, `PSP`, `EIE`, `HLC`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static Connection conexion= Conexion.getConexion();
    public AlumnosDAOMYSQL() {
        
    }
    
       public  ArrayList<asignaturas> getAllAlumnos(){
       {
       
          var salida = new ArrayList<asignaturas>();
        try(var pst=conexion.prepareStatement(alumnos)){
            ResultSet resultado = pst.executeQuery();
            while(resultado.next()){
                  var  alumno = new asignaturas();
            alumno.setNombre(resultado.getString("nombre"));
            alumno.setApellidos(resultado.getString("apellidos"));
            alumno.setDI(resultado.getInt("DI"));
            alumno.setAD(resultado.getInt("AD"));
            alumno.setEIE(resultado.getInt("EIE"));
            alumno.setHLC(resultado.getInt("HLC"));
            alumno.setPMDM(resultado.getInt("PMDM"));
            alumno.setPSP(resultado.getInt("PSP"));
            alumno.setSGE(resultado.getInt("SGE"));
               salida.add(alumno);        
            }
        } catch (SQLException ex) {
              Logger.getLogger(AlumnosDAOMYSQL.class.getName()).log(Level.SEVERE, null, ex);
          }
     return salida;
    }
       
   
    }
       
    public void add(asignaturas a) {
          try(var pst =  conexion.prepareStatement(add_query)){
              pst.setString(1, a.getNombre());
              pst.setString(2, a.getApellidos());
              pst.setInt(3, a.getAD());
              pst.setInt(4, a.getSGE());
              pst.setInt(5, a.getDI());
              pst.setInt(6, a.getPMDM());
              pst.setInt(7, a.getPSP());
              pst.setInt(8, a.getEIE());
              pst.setInt(9, a.getHLC());
                 if (pst.executeUpdate()>0){
                       Alert alerta = new Alert(Alert.AlertType.NONE);
         alerta.setAlertType(Alert.AlertType.INFORMATION);
         alerta.setHeaderText("Alumno añadido con éxito");
         alerta.setTitle("Mensaje de añadido");
     
                alerta.show(); 
                 };
          } catch (SQLException ex) {
              Logger.getLogger(AlumnosDAOMYSQL.class.getName()).log(Level.SEVERE, null, ex);
          }
    }


   
    }
 

 
   
        

    

