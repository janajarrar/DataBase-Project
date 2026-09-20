package application;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class ProductsByCategoryView extends BorderPane {

	private final MainApp app;
	private final String customerName;
	private final String category;

	private final TableView<Product> table = new TableView<>();
	private final Label msg = new Label();

	public ProductsByCategoryView(MainApp app, String customerName, String category) {
		this.app = app;
		this.customerName = customerName;
		this.category = category;

		setStyle("-fx-background-color:#2f6f6b;");
		setPadding(new Insets(16));

		setTop(UI.topBar("Category: " + category, () -> app.goCustomerCategories(customerName)));

		TableColumn<Product, Integer> c1 = new TableColumn<>("ID");
		c1.setCellValueFactory(new PropertyValueFactory<>("productId"));
		TableColumn<Product, String> c2 = new TableColumn<>("Name");
		c2.setCellValueFactory(new PropertyValueFactory<>("productName"));
		TableColumn<Product, Double> c3 = new TableColumn<>("Price");
		c3.setCellValueFactory(new PropertyValueFactory<>("price"));

		table.getColumns().addAll(c1, c2, c3);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TextField qty = new TextField();
		qty.setPromptText("Qty");
		qty.setPrefWidth(80);

		Button add = new Button("Add To Cart");
		add.setOnAction(e -> {
			Product p = table.getSelectionModel().getSelectedItem();
			if (p == null) {
				msg.setText("Select product");
				return;
			}
			CartStore.add(customerName, p, Integer.parseInt(qty.getText()));
			msg.setText("Added ✅");
		});

		Button cart = new Button("Go To Cart");
		cart.setOnAction(e -> app.goCustomerCart(customerName));

		HBox controls = new HBox(10, qty, add, cart);
		controls.setAlignment(Pos.CENTER);

		msg.setStyle("-fx-text-fill:white; -fx-font-weight:900;");

		VBox center = new VBox(12, table, controls, msg);
		center.setAlignment(Pos.CENTER);

		setCenter(center);
		load();
	}

	private void load() {
		try {
			table.setItems(FXCollections.observableArrayList(ProductService.getProductsByCategory(category)));
		} catch (Exception e) {
			msg.setText("Error loading products");
		}
	}
}