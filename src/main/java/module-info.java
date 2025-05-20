module es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.base;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    requires java.sql;
    requires java.xml.bind;
    requires java.desktop;

    opens es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo to javafx.graphics, javafx.fxml;
    opens es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers to javafx.fxml;
    opens es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos to java.xml.bind;
    exports es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers to javafx.fxml;
    exports es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO;
    exports es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

}
