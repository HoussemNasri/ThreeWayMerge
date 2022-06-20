package com.example.threewaymerge.threemergeentries.gridcell;

import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Paint;

public class MergedFieldCell extends AbstractFieldCell {
    private TextInputControl textInputControl = new TextArea();

    public MergedFieldCell(String text, Paint fill) {
        super(text, fill);
        initialize();

    }

    public MergedFieldCell(String text) {
        super(text);
        initialize();
    }

    private void initialize() {
        setAlignment(Pos.CENTER);
        setStyle("-fx-border-color: #dfdfdf;-fx-border-width: 0 0 0 1.5");
        if (isMultiline()) {
            textInputControl = new TextArea();
            ((TextArea) textInputControl).setWrapText(true);
        } else {
            textInputControl = new TextField();
        }

        textInputControl.textProperty().bindBidirectional(textProperty());
        textInputControl.setEditable(true);

        HBox.setHgrow(textInputControl, Priority.ALWAYS);

        getChildren().add(textInputControl);

        // TODO: Listen for key store events and notify left and right cell.

    }

    private boolean isMultiline() {
        return getText().length() > 50;
    }
}
