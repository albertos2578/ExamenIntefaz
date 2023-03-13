module com.mycompany.jdbc_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;
    requires jasperreports;
    requires javafx.swing;
    
    opens com.mycompany.jdbc_fx to javafx.fxml;
    opens models;
    exports com.mycompany.jdbc_fx;
 
}
