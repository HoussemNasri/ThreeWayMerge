package com.example.threewaymerge;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class Playground extends GridPane {

    public Playground() {
        this.setGridLinesVisible(true);
        Label fixed = new Label("Hiiiiii");
        Label b1 = new Label("Hello Houssem! How are you?");
        b1.setPadding(new Insets(20));
        b1.setWrapText(true);

        Label b2 = new Label("FUCK OFF");
        Label b3 = new Label("OK!");


        b1.setMaxWidth(Double.MAX_VALUE);

        b2.setMaxWidth(Double.MAX_VALUE);

        b3.setMaxWidth(Double.MAX_VALUE);

        ColumnConstraints fixedConstraint = new ColumnConstraints(50, 50, 50);
        fixedConstraint.setHgrow(Priority.NEVER);

        ColumnConstraints column1 = new ColumnConstraints();
        // column1.setMaxWidth(Double.MAX_VALUE);
        // column1.setPercentWidth(50);
        column1.setHgrow(Priority.SOMETIMES);

        ColumnConstraints column2 = new ColumnConstraints();
        // column2.setMaxWidth(Double.MAX_VALUE);
        //column2.setPercentWidth(50);
        column2.setHgrow(Priority.SOMETIMES);
        ColumnConstraints column3 = new ColumnConstraints();
        // column3.setMaxWidth(Double.MAX_VALUE);
        //column3.setPercentWidth(50);
        column3.setHgrow(Priority.SOMETIMES);
        // this.getRowConstraints().add(new RowConstraints(200, 200, 200));

        this.getColumnConstraints().addAll(fixedConstraint, column1, column2, column3);
        this.addRow(0, fixed, createPane(b1, Color.DEEPSKYBLUE), b2, b3);

        // constraints
    }

    private HBox createPane(Label label, Paint color) {
        label.setFont(Font.font(13));
        label.setPadding(new Insets(20));
        label.setAlignment(Pos.TOP_LEFT);
        HBox p = new HBox(label);
        p.setAlignment(Pos.TOP_LEFT);
        p.setBackground(new Background(new BackgroundFill(color, null, null)));
        HBox.setHgrow(label, Priority.ALWAYS);
        p.setMaxHeight(Double.MAX_VALUE);
        return p;
    }
}
