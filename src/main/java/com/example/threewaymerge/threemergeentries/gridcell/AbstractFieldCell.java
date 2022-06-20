package com.example.threewaymerge.threemergeentries.gridcell;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Objects;

public abstract class AbstractFieldCell extends HBox {

    public AbstractFieldCell(String text, Paint fill) {
        setText(text);
        setFill(fill);
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);

        fillProperty().addListener(e -> setBackground(new Background(new BackgroundFill(getFill(), CornerRadii.EMPTY, Insets.EMPTY))));
        setBackground(new Background(new BackgroundFill(getFill(), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public AbstractFieldCell(String text) {
        this(text, Color.WHITE);
    }

    private StringProperty text;

    public void setText(String text) {
        Objects.requireNonNull(text);
        textProperty().set(text);
    }

    public String getText() {
        return textProperty().get();
    }

    public StringProperty textProperty() {
        if (text == null) {
            text = new SimpleStringProperty(this, "text", "");
        }
        System.out.println(text.get());
        return text;
    }

    private ObjectProperty<Paint> fill;

    public void setFill(Paint fill) {
        Objects.requireNonNull(fill);
        fillProperty().set(fill);
    }

    public Paint getFill() {
        return fillProperty().get();
    }

    public ObjectProperty<Paint> fillProperty() {
        if (fill == null) {
            fill = new SimpleObjectProperty<>(this, "fill", Color.WHITE);
        }
        return fill;
    }

    private static final String DEFAULT_STYLE_CLASS = "field-cell";

}
