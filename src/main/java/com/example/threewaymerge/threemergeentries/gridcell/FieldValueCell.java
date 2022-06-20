package com.example.threewaymerge.threemergeentries.gridcell;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

import org.fxmisc.richtext.StyleClassedTextArea;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class FieldValueCell extends AbstractFieldCell {

    public static final String SELECTION_BOX_STYLE_CLASS = "selection-box";
    private final HBox selectionBox = new HBox();
    private final StyleClassedTextArea textLabel = new StyleClassedTextArea();

    public void setTextLabelBold() {
        textLabel.setStyle("-fx-font-weight: bold;-fx-text-fill: #313131");

    }

    public FieldValueCell(String text) {
        super(text);
        initialize();
    }

    public FieldValueCell(String text, Paint fill) {
        super(text, fill);
        initialize();
    }

    private void initialize() {
        getStyleClass().add(DEFAULT_STYLE_CLASS);
        setCursor(Cursor.HAND);
        initializeTextLabel();
        initializeSelectionBox();

        this.getChildren().add(selectionBox);
    }

    private void initializeTextLabel() {
        textLabel.setEditable(false);
        textLabel.setStyle("-fx-background-color: #0000");
        textLabel.appendText(textProperty().get());
        textLabel.setAutoHeight(true);
        textLabel.setCursor(Cursor.HAND);

        /*textLabel.textProperty().bind(textProperty());*/
        textLabel.setMaxWidth(Double.MAX_VALUE);
        textLabel.setWrapText(true);
        HBox.setHgrow(textLabel, Priority.ALWAYS);
    }

    private void initializeSelectionBox() {
        selectionBox.getStyleClass().add(SELECTION_BOX_STYLE_CLASS);
        selectionBox.setSpacing(2);
        HBox.setHgrow(selectionBox, Priority.ALWAYS);
        Ellipse ellipse = new Ellipse(5, 5);
        ellipse.getStyleClass().add("checkmark");

        FontIcon checkmarkIcon = new FontIcon(FontAwesomeSolid.CHECK_CIRCLE);
        checkmarkIcon.getStyleClass().add("checkmark");

        VBox checkmarkContainer = new VBox(checkmarkIcon);
        checkmarkContainer.setPadding(new Insets(1, 0, 0, 0));
        checkmarkContainer.setAlignment(Pos.TOP_RIGHT);

        selectionBox.getChildren().addAll(textLabel, checkmarkContainer);
    }

    private static final String DEFAULT_STYLE_CLASS = "lr-field-cell";

    private BooleanProperty selected = new BooleanPropertyBase() {
        @Override
        public Object getBean() {
            return FieldValueCell.class;
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

    public void setSelected(boolean selected) {
        selectedProperty().set(selected);
    }

    public boolean isSelected() {
        return selectedProperty().get();
    }


    public BooleanProperty selectedProperty() {
        return selected;
    }

    private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

    public void toggle() {
        setSelected(!isSelected());
    }

    public StyleClassedTextArea getTextLabel() {
        return textLabel;
    }
}
