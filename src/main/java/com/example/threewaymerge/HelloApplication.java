package com.example.threewaymerge;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.example.threewaymerge.data.BibEntry;
import com.example.threewaymerge.data.EntryProvider;
import com.example.threewaymerge.threemergeentries.diffhighlighter.UnifiedDiffHighlighter;
import com.example.threewaymerge.threemergeentries.gridcell.AbstractFieldCell;
import com.example.threewaymerge.threemergeentries.gridcell.FieldValueCell;
import com.example.threewaymerge.threemergeentries.gridcell.MergedFieldCell;
import org.fxmisc.richtext.InlineCssTextArea;

public class HelloApplication extends Application {
    public static final int CELL_COLUMN_MIN_WIDTH = 250;

    private GridPane headerContainer;

    private ColumnConstraints fieldNamesColumnConstraints;
    private ColumnConstraints leftEntryColumnConstraints;
    private ColumnConstraints rightEntryColumnConstraints;
    private ColumnConstraints mergedEntryColumnConstraints;

    private void initializeColumnConstraints() {
        fieldNamesColumnConstraints = new ColumnConstraints(150);
        fieldNamesColumnConstraints.setHgrow(Priority.NEVER);

        leftEntryColumnConstraints = new ColumnConstraints(CELL_COLUMN_MIN_WIDTH, 256, Double.MAX_VALUE);
        rightEntryColumnConstraints = new ColumnConstraints(CELL_COLUMN_MIN_WIDTH, 256, Double.MAX_VALUE);
        mergedEntryColumnConstraints = new ColumnConstraints(CELL_COLUMN_MIN_WIDTH, 256, Double.MAX_VALUE);

        leftEntryColumnConstraints.setHgrow(Priority.ALWAYS);
        rightEntryColumnConstraints.setHgrow(Priority.ALWAYS);
        mergedEntryColumnConstraints.setHgrow(Priority.ALWAYS);
    }

    private void createHeader() {
        if (headerContainer == null) {
            AbstractFieldCell emptyCell = createFieldCell("", Color.valueOf("#F7F7F7"), false, false);
            AbstractFieldCell leftEntryLabel = createFieldCell("Left entry", Color.valueOf("#F7F7F7"), false, true);
            AbstractFieldCell rightEntryLabel = createFieldCell("Right entry", Color.valueOf("#F7F7F7"), false, true);
            AbstractFieldCell mergedEntryLabel = createFieldCell("Merged entry", Color.valueOf("#F7F7F7"), false, true);

            // I used a GridPane instead of a Hbox because Hbox allocates more space for cells
            // with longer text, but I wanted all cells to have the same width
            headerContainer = new GridPane();
            headerContainer.setMaxHeight(Control.USE_PREF_SIZE);
            headerContainer.setMinHeight(Control.USE_PREF_SIZE);
            headerContainer.addRow(
                    0,
                    emptyCell,
                    leftEntryLabel,
                    rightEntryLabel,
                    mergedEntryLabel
            );

            bindHeaderContainerWidthToFieldGridWidth(headerContainer);

            headerContainer.getRowConstraints().setAll(new RowConstraints(50, 50, 50));
            headerContainer.getColumnConstraints().setAll(
                    fieldNamesColumnConstraints,
                    leftEntryColumnConstraints,
                    rightEntryColumnConstraints,
                    mergedEntryColumnConstraints
            );
        }
    }

    private AbstractFieldCell createFieldCell(String text, Paint color, boolean isMergedEntry, boolean setBold) {
        if (isMergedEntry) {
            return new MergedFieldCell(text, color);
        } else {
            FieldValueCell cell = new FieldValueCell(text, color);
            if (setBold)
                cell.setTextLabelBold();
            cell.setOnMouseClicked(e -> cell.toggle());
            return cell;
        }
    }

    private AbstractFieldCell createFieldCell(String text, int rowIndex, boolean isMergedEntry) {
        return createFieldCell(text, rowIndex % 2 == 1 ? Color.WHITE : Color.valueOf("#F7F7F7"), isMergedEntry, false);
    }

    @Override
    public void start(Stage stage) {

        BibEntry leftEntry = EntryProvider.getLeftEntry();
        BibEntry rightEntry = EntryProvider.getRightEntry();
        BibEntry mergedEntry = EntryProvider.getMergedEntry();

        AnchorPane root = new AnchorPane();

        GridPane fieldGrid = new GridPane();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(950, 500);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(fieldGrid);

        int rowIndex = 0;
        for (String field : leftEntry.getFields()) {
            String leftValue = leftEntry.getField(field);
            String rightValue = rightEntry.getField(field);
            String mergedValue = mergedEntry.getField(field);
            FieldValueCell leftEntryCell = (FieldValueCell) createFieldCell(leftValue, rowIndex, false);
            FieldValueCell rightEntryCell = (FieldValueCell) createFieldCell(rightValue, rowIndex, false);


            fieldGrid.addRow(
                    rowIndex,
                    createFieldCell(field, rowIndex, false),
                    leftEntryCell,
                    rightEntryCell,
                    createFieldCell(mergedValue, rowIndex, true)
            );
            new UnifiedDiffHighlighter(leftEntryCell.getTextLabel(), rightEntryCell.getTextLabel()).highlight();
            rowIndex++;
        }

        initializeColumnConstraints();
        createHeader();

        fieldGrid.getColumnConstraints().setAll(
                fieldNamesColumnConstraints,
                leftEntryColumnConstraints,
                rightEntryColumnConstraints,
                mergedEntryColumnConstraints
        );


        HBox mergeDialogToolbar = new HBox();
        mergeDialogToolbar.setStyle("-fx-background-color: #d3d3d3");
        mergeDialogToolbar.setPrefHeight(36);
        mergeDialogToolbar.setMinHeight(Control.USE_PREF_SIZE);
        mergeDialogToolbar.setMaxHeight(Control.USE_PREF_SIZE);

        VBox mergeDialogContent = new VBox();
        mergeDialogContent.getChildren().addAll(mergeDialogToolbar, headerContainer, scrollPane);

        Dialog<Void> mergeDialog = new Dialog<>();
        mergeDialog.setResizable(true);
        mergeDialog.setTitle("Merge Dialog");
        mergeDialog.initStyle(StageStyle.UTILITY);
        mergeDialog.getDialogPane().setContent(mergeDialogContent);
        mergeDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Button openMergedDialogButton = new Button("Open Merge Dialog");
        openMergedDialogButton.setOnAction(e -> mergeDialog.show());

        AnchorPane.setTopAnchor(openMergedDialogButton, 24d);
        AnchorPane.setLeftAnchor(openMergedDialogButton, 16d);

        root.getChildren().add(openMergedDialogButton);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("/App.css");

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


        mergeDialog.initOwner(stage);
        mergeDialog.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * The fields grid pane is contained within a scroll pane, thus it doesn't allocate the full available width. In
     * fact, it uses the available width minus the size of the scrollbar which is 8. This leads to header columns being
     * always larger than fields columns. This hack should solve this problem.
     */
    private void bindHeaderContainerWidthToFieldGridWidth(GridPane headerContainer) {
        headerContainer.setPadding(new Insets(0, 8, 0, 0));
        headerContainer.setStyle("-fx-background-color: #EFEFEF");
    }
}