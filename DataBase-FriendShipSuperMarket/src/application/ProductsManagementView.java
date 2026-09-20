package application;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.sql.Date;

public class ProductsManagementView extends BorderPane {

	private final TableView<Product> table = new TableView<>();

	public ProductsManagementView(MainApp app) {
		setStyle("-fx-background-color:#2f6f6b;");
		setPadding(new Insets(16));

		setTop(UI.topBar("Manage Products", () -> app.goEmployeeDashboard("Employee")));

		TableColumn<Product, Integer> c1 = new TableColumn<>("ID");
		c1.setCellValueFactory(new PropertyValueFactory<>("productId"));

		TableColumn<Product, String> c2 = new TableColumn<>("Name");
		c2.setCellValueFactory(new PropertyValueFactory<>("productName"));

		TableColumn<Product, Double> c3 = new TableColumn<>("Price");
		c3.setCellValueFactory(new PropertyValueFactory<>("price"));

		TableColumn<Product, Date> c4 = new TableColumn<>("Expiry");
		c4.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));

		TableColumn<Product, Integer> c5 = new TableColumn<>("Supplier");
		c5.setCellValueFactory(new PropertyValueFactory<>("supplierId"));

		TableColumn<Product, String> c6 = new TableColumn<>("Category");
		c6.setCellValueFactory(new PropertyValueFactory<>("category"));

		table.getColumns().addAll(c1, c2, c3, c4, c5, c6);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		UI.cardStyle(table);

		TextField id = tf("Product_id (number)");
		TextField name = tf("Product_name");
		TextField price = tf("Price (e.g., 5.5)");
		DatePicker expiry = new DatePicker();
		expiry.setPromptText("Expiry_date");
		TextField supplier = tf("Supplier_id (number)");
		TextField category = tf("Category (Beverages/Dairy/Bakery...)");

		Label msg = msg();

		Button add = btn("Add");
		Button upd = btn("Update");
		Button del = btn("Delete");
		Button refresh = btn("Refresh");

		add.setOnAction(e -> {
			try {
				if (expiry.getValue() == null) {
					msg.setText("Pick expiry date");
					return;
				}

				boolean ok = ProductService.addProduct(Integer.parseInt(id.getText().trim()), name.getText().trim(),
						Double.parseDouble(price.getText().trim()), Date.valueOf(expiry.getValue()),
						Integer.parseInt(supplier.getText().trim()),
						category.getText().trim().isEmpty() ? "Other" : category.getText().trim());
				msg.setText(ok ? "Added ✅" : "Not added");
				load();
			} catch (Exception ex) {
				msg.setText("Error: " + ex.getMessage());
			}
		});

		upd.setOnAction(e -> {
			try {
				if (expiry.getValue() == null) {
					msg.setText("Pick expiry date");
					return;
				}

				boolean ok = ProductService.updateProduct(Integer.parseInt(id.getText().trim()), name.getText().trim(),
						Double.parseDouble(price.getText().trim()), Date.valueOf(expiry.getValue()),
						Integer.parseInt(supplier.getText().trim()),
						category.getText().trim().isEmpty() ? "Other" : category.getText().trim());
				msg.setText(ok ? "Updated ✅" : "Not updated");
				load();
			} catch (Exception ex) {
				msg.setText("Error: " + ex.getMessage());
			}
		});

		del.setOnAction(e -> {
			try {
				boolean ok = ProductService.deleteProduct(Integer.parseInt(id.getText().trim()));
				msg.setText(ok ? "Deleted ✅" : "Not deleted");
				load();
			} catch (Exception ex) {
				msg.setText("Error: " + ex.getMessage());
			}
		});

		refresh.setOnAction(e -> load());

		table.setOnMouseClicked(e -> {
			Product p = table.getSelectionModel().getSelectedItem();
			if (p != null) {
				id.setText(String.valueOf(p.getProductId()));
				name.setText(p.getProductName());
				price.setText(String.valueOf(p.getPrice()));
				if (p.getExpiryDate() != null)
					expiry.setValue(p.getExpiryDate().toLocalDate());
				supplier.setText(String.valueOf(p.getSupplierId()));
				category.setText(p.getCategory());
			}
		});

		VBox form = new VBox(8, row("ID:", id), row("Name:", name), row("Price:", price), row("Expiry:", expiry),
				row("Supplier:", supplier), row("Category:", category), new HBox(10, add, upd, del, refresh), msg);
		form.setPadding(new Insets(14));
		form.setMaxWidth(440);
		UI.cardStyle(form);

		HBox center = new HBox(14, table, form);
		center.setAlignment(Pos.CENTER);
		setCenter(center);

		load();
	}

	private void load() {
		try {
			table.setItems(FXCollections.observableArrayList(ProductService.getAllProducts()));
		} catch (Exception ex) {

		}
	}

	private TextField tf(String p) {
		TextField t = new TextField();
		t.setPromptText(p);
		return t;
	}

	private Button btn(String t) {
		Button b = new Button(t);
		b.setStyle("-fx-background-color:#0b1f3a; -fx-text-fill:white; -fx-font-weight:900; -fx-background-radius:12;");
		b.setPrefHeight(36);
		return b;
	}

	private Label msg() {
		Label l = new Label();
		l.setStyle("-fx-text-fill:#ffffff; -fx-font-weight:900;");
		return l;
	}

	private HBox row(String l, Control c) {
		Label lb = new Label(l);
		lb.setStyle("-fx-font-weight:900; -fx-text-fill:#0b1f3a;");
		HBox h = new HBox(10, lb, c);
		h.setAlignment(Pos.CENTER_LEFT);
		HBox.setHgrow(c, Priority.ALWAYS);
		return h;
	}
}