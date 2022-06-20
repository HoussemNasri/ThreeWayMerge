module com.example.threewaymerge {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.graphics;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.fxmisc.richtext;
    requires io.github.javadiffutils;
    requires wellbehavedfx;

    opens com.example.threewaymerge to javafx.fxml;

    exports com.example.threewaymerge;
}