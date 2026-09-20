package application;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class InventoryManagementView extends BorderPane {

	private final TableView<InventoryRow> table = new TableView<>();
	private final Label msg = new Label();

	public InventoryManagementView(MainApp app) {

		setStyle("-fx-background-color:#2f6f6b;");
		setPadding(new Insets(16));

		Label title = new Label("Inventory Management");
		title.setStyle("-fx-text-fill:white; -fx-font-size:28px; -fx-font-weight:900;");

		Button back = new Button("Back");
		back.setPrefWidth(70);
		back.setPrefHeight(28);
		back.setStyle("-fx-background-color:transparent;" + "-fx-text-fill:white;" + "-fx-font-size:12px;"
				+ "-fx-font-weight:700;");
		back.setOnAction(e -> app.goEmployeeDashboard("Employee"));

		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		HBox top = new HBox(10, title, spacer, back);
		top.setAlignment(Pos.CENTER_LEFT);
		setTop(top);

		TableColumn<InventoryRow, Integer> c1 = new TableColumn<>("Inventory ID");
		c1.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));

		TableColumn<InventoryRow, String> c2 = new TableColumn<>("Branch");
		c2.setCellValueFactory(new PropertyValueFactory<>("branchName"));

		TableColumn<InventoryRow, String> c3 = new TableColumn<>("Product");
		c3.setCellValueFactory(new PropertyValueFactory<>("productName"));

		TableColumn<InventoryRow, Integer> c4 = new TableColumn<>("Quantity");
		c4.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		table.getColumns().addAll(c1, c2, c3, c4);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		UI.cardStyle(table);

		Button refresh = new Button("Refresh");
		refresh.setOnAction(e -> load());

		refresh.setStyle("-fx-background-color:#0b1f3a;" + "-fx-text-fill:white;" + "-fx-font-weight:900;"
				+ "-fx-background-radius:12;");

		msg.setStyle("-fx-text-fill:white; -fx-font-weight:900;");

		VBox center = new VBox(12, table, refresh, msg);
		center.setAlignment(Pos.CENTER);
		setCenter(center);

		load();
	}

	private void load() {
		try {
			table.setItems(FXCollections.observableArrayList(InventoryService.getInventoryView()));

			msg.setText("");
		} catch (Exception ex) {
			msg.setText("Error loading inventory");
		}
	}
}