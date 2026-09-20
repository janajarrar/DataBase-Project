package application;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.time.LocalDate;

public class SalesView extends BorderPane {
	private final TableView<Sale> table = new TableView<>();
	private final Label msg = new Label();

	public SalesView() {
		setPadding(new Insets(16));
		setStyle("-fx-background-color:#e6f2f1;");

		Label title = new Label("Sales");
		title.setStyle("-fx-font-size:24px; -fx-font-weight:900;");
		setTop(title);
		BorderPane.setAlignment(title, Pos.CENTER);

		TableColumn<Sale, Integer> id = new TableColumn<>("Sale ID");
		id.setCellValueFactory(new PropertyValueFactory<>("saleId"));

		TableColumn<Sale, LocalDate> date = new TableColumn<>("Date");
		date.setCellValueFactory(new PropertyValueFactory<>("saleDate"));

		TableColumn<Sale, Double> total = new TableColumn<>("Total");
		total.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

		TableColumn<Sale, Integer> branch = new TableColumn<>("Branch");
		branch.setCellValueFactory(new PropertyValueFactory<>("branchId"));

		table.getColumns().addAll(id, date, total, branch);
		setCenter(table);

		DatePicker dp = new DatePicker(LocalDate.now());
		TextField txtTotal = new TextField();
		txtTotal.setPromptText("Total Amount");
		TextField txtBranch = new TextField();
		txtBranch.setPromptText("Branch ID");
		Button add = new Button("Add Sale");

		add.setOnAction(e -> {
			try {
				SalesService.addSale(dp.getValue(), Double.parseDouble(txtTotal.getText().trim()),
						Integer.parseInt(txtBranch.getText().trim()));
				msg.setText("Added sale.");
				load();
			} catch (Exception ex) {
				msg.setText("Error: " + ex.getMessage());
			}
		});

		HBox form = new HBox(10, dp, txtTotal, txtBranch, add);
		form.setPadding(new Insets(10));
		form.setAlignment(Pos.CENTER);

		msg.setStyle("-fx-font-weight:800;");
		VBox bottom = new VBox(6, form, msg);
		bottom.setAlignment(Pos.CENTER);
		setBottom(bottom);

		load();
	}

	private void load() {
		try {
			table.setItems(FXCollections.observableArrayList(SalesService.getAllSales()));
		} catch (Exception ex) {
			msg.setText("Load error: " + ex.getMessage());
		}
	}
}