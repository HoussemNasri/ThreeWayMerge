package com.example.threewaymerge;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class FieldCell extends HBox {

    private StringProperty text;
    private BooleanProperty selected;
    private final HBox selectionBox = new HBox(2);

    private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

    public FieldCell(String text, Paint backgroundColor) {
        setText(text);
        getStyleClass().add("field-cell");
        setBackground(new Background(
                new BackgroundFill(
                        backgroundColor,
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        ));

        selectionBox.getStyleClass().add("selection-box");

        HBox.setHgrow(selectionBox, Priority.ALWAYS);

        Label textLabel = new Label();
        textLabel.textProperty().bind(textProperty());
        textLabel.setWrapText(true);
        HBox.setHgrow(textLabel, Priority.ALWAYS);


        Ellipse ellipse = new Ellipse(5, 5);

        ellipse.getStyleClass().add("checkmark");


        this.getChildren().add(selectionBox);
        FontIcon icon = new FontIcon(FontAwesomeSolid.CHECK_CIRCLE);
        icon.getStyleClass().add("checkmark");

        VBox checkmarkContainer = new VBox(icon);
        checkmarkContainer.setPadding(new Insets(1, 0, 0, 0));
        checkmarkContainer.setAlignment(Pos.TOP_RIGHT);

        selectionBox.getChildren().addAll(textLabel, checkmarkContainer);
    }

    public FieldCell(String text) {
        this(text, Color.WHITE);
    }

    public void setText(String text) {
        textProperty().set(text);
    }

    public String getText() {
        return text == null ? "" : text.get();
    }

    public StringProperty textProperty() {
        if (text == null) {
            text = new SimpleStringProperty(this, "text", "");
        }
        return text;
    }


    public void setSelected(boolean selected) {
        selectedProperty().set(selected);
    }

    public boolean isSelected() {
        return selected == null ? false : selected.get();
    }

    public BooleanProperty selectedProperty() {
        if (selected == null) {
            selected = new BooleanPropertyBase() {
                @Override
                public Object getBean() {
                    return FieldCell.class;
                }

                @Override
                public String getName() {
                    return "selected";
                }

                @Override
                protected void invalidated() {
                    pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, get());
                }
            };
        }
        return selected;
    }

    public void toggle() {
        setSelected(!isSelected());
    }

}
