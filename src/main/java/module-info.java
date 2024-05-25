module br.com.brunoarouca.twagendafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires mysql.connector.java;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;


    opens br.com.brunoarouca.twagendafx to javafx.fxml;
    exports br.com.brunoarouca.twagendafx;
    exports br.com.brunoarouca.twagendafx.entidades;
}