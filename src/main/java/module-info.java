module es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    requires java.sql;
    requires java.xml.bind;

    opens es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo to javafx.fxml;
    exports es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo;
    exports es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.test;
    exports es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO;
    exports es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;
}
