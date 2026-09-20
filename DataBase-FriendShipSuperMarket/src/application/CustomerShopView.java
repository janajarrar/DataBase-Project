package application;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerShopView extends BorderPane {

	private final String customerName;
	private final TableView<Product> productsTable = new TableView<>();
	private final TableView<CartItem> cartTable = new TableView<>();
	private final Label msg = new Label();
	private final List<CartItem> cart = new ArrayList<>();

	public CustomerShopView(String customerName) {
		this.customerName = customerName;

		setPadding(new Insets(16));
		setStyle("-fx-background-color:#2f6f6b;");

		Label title = new Label("Shop - Products");
		title.setStyle("-fx-font-size:34px; -fx-font-weight:900; -fx-text-fill:white;");
		BorderPane.setAlignment(title, Pos.CENTER_LEFT);
		BorderPane.setMargin(title, new Insets(4, 0, 10, 0));
		setTop(title);

		TableColumn<Product, Integer> pid = new TableColumn<>("ID");
		pid.setCellValueFactory(new PropertyValueFactory<>("id"));
		pid.setStyle("-fx-alignment: CENTER;");

		TableColumn<Product, String> pname = new TableColumn<>("Name");
		pname.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Product, Double> pprice = new TableColumn<>("Price");
		pprice.setCellValueFactory(new PropertyValueFactory<>("price"));
		pprice.setStyle("-fx-alignment: CENTER-RIGHT;");

		productsTable.getColumns().addAll(pid, pname, pprice);
		productsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<CartItem, String> cName = new TableColumn<>("Product");
		cName.setCellValueFactory(
				c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getProduct().getProductName()));

		TableColumn<CartItem, Integer> cQty = new TableColumn<>("Qty");
		cQty.setCellValueFactory(
				c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getQuantity()).asObject());
		cQty.setStyle("-fx-alignment: CENTER;");

		TableColumn<CartItem, Double> cTotal = new TableColumn<>("Line Total");
		cTotal.setCellValueFactory(
				c -> new javafx.beans.property.SimpleDoubleProperty(c.getValue().getLineTotal()).asObject());
		cTotal.setStyle("-fx-alignment: CENTER-RIGHT;");

		cartTable.getColumns().addAll(cName, cQty, cTotal);
		cartTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		styleCartLikeTable(productsTable);
		styleCartLikeTable(cartTable);

		TextField txtQty = new TextField();
		txtQty.setPromptText("Qty");
		txtQty.setPrefWidth(120);

		TextField txtBranch = new TextField();
		txtBranch.setPromptText("Branch ID (e.g., 1)");
		txtBranch.setPrefWidth(180);

		Button addToCart = new Button("Add To Cart");
		Button remove = new Button("Remove Selected");
		Button checkout = new Button("Checkout (Create Order)");

		addToCart.setOnAction(e -> {
			Product p = productsTable.getSelectionModel().getSelectedItem();
			if (p == null) {
				msg.setText("Select a product.");
				return;
			}
			try {
				int q = Integer.parseInt(txtQty.getText().trim());
				if (q <= 0) {
					msg.setText("Qty must be > 0");
					return;
				}
				cart.add(new CartItem(p, q));
				refreshCart();
				msg.setText("Added ✅");
			} catch (Exception ex) {
				msg.setText("Error: " + ex.getMessage());
			}
		});

		remove.setOnAction(e -> {
			CartItem item = cartTable.getSelectionModel().getSelectedItem();
			if (item == null) {
				msg.setText("Select cart item.");
				return;
			}
			cart.remove(item);
			refreshCart();
			msg.setText("Removed ✅");
		});

		checkout.setOnAction(e -> {
			try {
				int branchId = Integer.parseInt(txtBranch.getText().trim());
				int orderId = OrderService.createOrder(customerName, branchId, cart);
				cart.clear();
				refreshCart();
				msg.setText("Order created ✅  ID = " + orderId);
			} catch (Exception ex) {
				msg.setText("Checkout error: " + ex.getMessage());
			}
		});

		HBox controls = new HBox(10, txtQty, addToCart, remove, new Label(" | "), txtBranch, checkout);
		controls.setAlignment(Pos.CENTER);
		controls.setPadding(new Insets(12));
		controls.setStyle("-fx-background-color: transparent;");

		msg.setStyle("-fx-text-fill:white; -fx-font-weight:900; -fx-font-size:14px;");

		SplitPane split = new SplitPane(productsTable, cartTable);
		split.setDividerPositions(0.60);
		split.setStyle("-fx-background-color: transparent;");

		setCenter(split);
		setBottom(new VBox(8, controls, msg));

		loadProducts();
		refreshCart();
	}

	private <T> void styleCartLikeTable(TableView<T> t) {

		t.setPrefHeight(420);
		t.setFixedCellSize(44);

		t.setStyle("-fx-background-color: rgba(255,255,255,0.10);" + "-fx-background-radius: 20;"
				+ "-fx-border-radius: 20;" + "-fx-border-color: rgba(255,255,255,0.45);" + "-fx-border-width: 1.6;"
				+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.35), 16, 0.2, 0, 6);" + "-fx-padding: 8;");

		t.lookupAll(".viewport").forEach(n -> n.setStyle("-fx-background-color: transparent;"));
		t.lookupAll(".scroll-pane").forEach(n -> n.setStyle("-fx-background-color: transparent; -fx-padding: 0;"));
		t.lookupAll(".scroll-pane .corner").forEach(n -> n.setStyle("-fx-background-color: transparent;"));

		t.lookupAll(".column-header-background").forEach(
				n -> n.setStyle("-fx-background-color: rgba(255,255,255,0.20);" + "-fx-background-radius: 20 20 0 0;"));

		t.lookupAll(".column-header .label")
				.forEach(n -> n.setStyle("-fx-text-fill: #111;" + "-fx-font-size: 14px;" + "-fx-font-weight: 900;"));

		t.setRowFactory(tv -> new TableRow<T>() {
			@Override
			protected void updateItem(T item, boolean empty) {
				super.updateItem(item, empty);

				if (empty) {
					setStyle("-fx-background-color: transparent;");
				} else if (isSelected()) {
					setStyle("-fx-background-color: rgba(255,255,255,0.22);");
				} else {
					setStyle("-fx-background-color: rgba(255,255,255,0.08);");
				}
			}
		});

		t.getColumns().forEach(col -> {
			@SuppressWarnings("unchecked")
			TableColumn<T, Object> c = (TableColumn<T, Object>) col;

			c.setCellFactory(column -> new TableCell<T, Object>() {
				@Override
				protected void updateItem(Object item, boolean empty) {
					super.updateItem(item, empty);

					if (empty || item == null) {
						setText(null);
						setStyle("");
					} else {
						setText(String.valueOf(item));
						setStyle("-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-font-size: 15px;"
								+ "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.90), 2, 0, 0, 1);");
					}
				}
			});
		});

		t.setPlaceholder(new Label("No data"));
		t.getPlaceholder().setStyle("-fx-text-fill:white; -fx-font-weight:900;");
	}

	private void loadProducts() {
		try {
			productsTable.setItems(FXCollections.observableArrayList(ProductService.getAllProducts()));
		} catch (Exception ex) {
			msg.setText("Load products error: " + ex.getMessage());
		}
	}

	private void refreshCart() {
		cartTable.setItems(FXCollections.observableArrayList(cart));
	}
}