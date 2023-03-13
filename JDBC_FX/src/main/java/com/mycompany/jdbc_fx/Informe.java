package com.mycompany.jdbc_fx;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.SQLException;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;


public class Informe {

    public static void showReport() throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();
        String report = "asignaturasNotas.jasper";

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report, 
                hm, 
                JdbcUtil.getConnection()
        );

        JRViewer viewer = new JRViewer(jasperPrint);

        javax.swing.JFrame frame = new javax.swing.JFrame("Listado de alumnos");
        frame.getContentPane().add(viewer);
        frame.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);

        System.out.print("Done!");
    }

    public static void pdfReport() throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();
        String report = "asignaturasNotas.jasper";
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report, 
                hm, 
                JdbcUtil.getConnection()
        );
        
        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("asignaturasNotas.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();
        System.out.print("Done!");
    }

}
