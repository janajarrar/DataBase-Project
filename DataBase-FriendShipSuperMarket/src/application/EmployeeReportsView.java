package application;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class EmployeeReportsView extends BorderPane {

	private final TableView<ReportRow> branchTable = new TableView<>();
	private final TableView<ReportRow> dayTable = new TableView<>();
	private final TableView<TopProductRow> topTable = new TableView<>();
	private final TableView<InventoryReportRow> invTable = new TableView<>();
	private final Label msg = new Label();

	public EmployeeReportsView(MainApp app) {
		setStyle("-fx-background-color:#2f6f6b;");
		setPadding(new Insets(16));

		setTop(UI.topBar("Reports", () -> app.goEmployeeDashboard("Employee")));

		TabPane tabs = new TabPane();
		tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		tabs.getTabs().add(new Tab("Revenue By Branch", paneBranch()));
		tabs.getTabs().add(new Tab("Revenue By Day", paneDay()));
		tabs.getTabs().add(new Tab("Top Products", paneTop()));
		tabs.getTabs().add(new Tab("Inventory Remaining", paneInv()));

		msg.setStyle("-fx-text-fill:white; -fx-font-weight:900;");
		VBox root = new VBox(10, tabs, msg);
		root.setAlignment(Pos.CENTER);
		setCenter(root);

		loadAll();
	}

	private Pane paneBranch() {
		TableColumn<ReportRow, String> c1 = new TableColumn<>("Branch");
		c1.setCellValueFactory(new PropertyValueFactory<>("label"));
		TableColumn<ReportRow, Double> c2 = new TableColumn<>("Total");
		c2.setCellValueFactory(new PropertyValueFactory<>("total"));
		TableColumn<ReportRow, Integer> c3 = new TableColumn<>("Orders");
		c3.setCellValueFactory(new PropertyValueFactory<>("count"));

		branchTable.getColumns().setAll(c1, c2, c3);
		branchTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		UI.cardStyle(branchTable);

		Button refresh = new Button("Refresh");
		refresh.setOnAction(e -> loadBranch());

		VBox box = new VBox(10, branchTable, refresh);
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		return box;
	}

	private Pane paneDay() {
		TableColumn<ReportRow, String> c1 = new TableColumn<>("Date");
		c1.setCellValueFactory(new PropertyValueFactory<>("label"));
		TableColumn<ReportRow, Double> c2 = new TableColumn<>("Total");
		c2.setCellValueFactory(new PropertyValueFactory<>("total"));
		TableColumn<ReportRow, Integer> c3 = new TableColumn<>("Orders");
		c3.setCellValueFactory(new PropertyValueFactory<>("count"));

		dayTable.getColumns().setAll(c1, c2, c3);
		dayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		UI.cardStyle(dayTable);

		Button refresh = new Button("Refresh");
		refresh.setOnAction(e -> loadDay());

		VBox box = new VBox(10, dayTable, refresh);
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		return box;
	}

	private Pane paneTop() {
		TableColumn<TopProductRow, Integer> c1 = new TableColumn<>("Product ID");
		c1.setCellValueFactory(new PropertyValueFactory<>("productId"));
		TableColumn<TopProductRow, String> c2 = new TableColumn<>("Product");
		c2.setCellValueFactory(new PropertyValueFactory<>("productName"));
		TableColumn<TopProductRow, Integer> c3 = new TableColumn<>("Sold Qty");
		c3.setCellValueFactory(new PropertyValueFactory<>("totalQty"));

		topTable.getColumns().setAll(c1, c2, c3);
		topTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		UI.cardStyle(topTable);

		Button refresh = new Button("Refresh");
		refresh.setOnAction(e -> loadTop());

		VBox box = new VBox(10, topTable, refresh);
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		return box;
	}

	private Pane paneInv() {
		TableColumn<InventoryReportRow, Integer> c1 = new TableColumn<>("Product ID");
		c1.setCellValueFactory(new PropertyValueFactory<>("productId"));
		TableColumn<InventoryReportRow, String> c2 = new TableColumn<>("Product");
		c2.setCellValueFactory(new PropertyValueFactory<>("productName"));
		TableColumn<InventoryReportRow, String> c3 = new TableColumn<>("Branch");
		c3.setCellValueFactory(new PropertyValueFactory<>("branchName"));
		TableColumn<InventoryReportRow, Integer> c4 = new TableColumn<>("Remaining");
		c4.setCellValueFactory(new PropertyValueFactory<>("totalQuantity"));

		invTable.getColumns().setAll(c1, c2, c3, c4);
		invTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		UI.cardStyle(invTable);

		Button refresh = new Button("Refresh");
		refresh.setOnAction(e -> loadInv());

		VBox box = new VBox(10, invTable, refresh);
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		return box;
	}

	private void loadAll() {
		loadBranch();
		loadDay();
		loadTop();
		loadInv();
	}

	private void loadBranch() {
		try {
			branchTable.setItems(FXCollections.observableArrayList(ReportService.revenueByBranch()));
			msg.setText("");
		} catch (Exception ex) {
			msg.setText("Branch report error: " + ex.getMessage());
		}
	}

	private void loadDay() {
		try {
			dayTable.setItems(FXCollections.observableArrayList(ReportService.revenueByDay()));
			msg.setText("");
		} catch (Exception ex) {
			msg.setText("Day report error: " + ex.getMessage());
		}
	}

	private void loadTop() {
		try {
			topTable.setItems(FXCollections.observableArrayList(ReportService.topProducts()));
			msg.setText("");
		} catch (Exception ex) {
			msg.setText("Top products error: " + ex.getMessage());
		}
	}

	private void loadInv() {
		try {
			invTable.setItems(FXCollections.observableArrayList(ReportService.inventoryRemaining()));
			msg.setText("");
		} catch (Exception ex) {
			msg.setText("Inventory error: " + ex.getMessage());
		}
	}
}