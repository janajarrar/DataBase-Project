package application;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CustomerCartView extends BorderPane {

    private final MainApp app;
    private final String customerName;

    private final TableView<CartItem> table = new TableView<>();
    private final Label msg = new Label();

    private final Label subtotalLbl = new Label();
    private final Label discountLbl = new Label();
    private final Label totalLbl = new Label();

    public CustomerCartView(MainApp app, String customerName) {
        this.app = app;
        this.customerName = customerName;

        setStyle("-fx-background-color:#2f6f6b;");
        setPadding(new Insets(16));

        setTop(UI.topBar("Your Cart", () -> app.goCustomerCategories(customerName)));

        // ===== Table Columns =====
        TableColumn<CartItem, String> c1 = new TableColumn<>("Product");
        c1.setCellValueFactory(
                c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getProduct().getProductName()));

        c1.setCellFactory(col -> new TableCell<CartItem, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    setStyle("-fx-text-fill: white;" +
                             "-fx-font-weight: bold;" +
                             "-fx-font-size: 15px;" +
                             "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.9), 2, 0, 0, 1);");
                }
            }
        });

        TableColumn<CartItem, Integer> c2 = new TableColumn<>("Qty");
        c2.setCellValueFactory(
                c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getQuantity()).asObject());
        c2.setStyle("-fx-alignment: CENTER;");

        c2.setCellFactory(col -> new TableCell<CartItem, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.valueOf(item));
                    setStyle("-fx-text-fill: white;" +
                             "-fx-font-weight: bold;" +
                             "-fx-font-size: 15px;" +
                             "-fx-alignment: CENTER;" +
                             "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.9), 2, 0, 0, 1);");
                }
            }
        });

        TableColumn<CartItem, Double> c3 = new TableColumn<>("Line Total");
        c3.setCellValueFactory(
                c -> new javafx.beans.property.SimpleDoubleProperty(c.getValue().getLineTotal()).asObject());
        c3.setStyle("-fx-alignment: CENTER-RIGHT;");

        c3.setCellFactory(col -> new TableCell<CartItem, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.format("%.2f", item));
                    setStyle("-fx-text-fill: white;" +
                             "-fx-font-weight: bold;" +
                             "-fx-font-size: 15px;" +
                             "-fx-alignment: CENTER-RIGHT;" +
                             "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.9), 2, 0, 0, 1);");
                }
            }
        });

        table.getColumns().addAll(c1, c2, c3);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        styleGlassTable(table);

    
        Label branchLabel = new Label("Branch:");
        branchLabel.setStyle("-fx-text-fill:white; -fx-font-weight: bold; -fx-font-size: 14px;");

        ComboBox<String> branchBox = new ComboBox<>();
        branchBox.getItems().addAll("📍 Ramallah", "📍 Jenin");
        branchBox.setValue("📍 Ramallah");
        branchBox.setPrefWidth(160);
        branchBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14;" +
                "-fx-font-size: 14px;" +
                "-fx-text-fill: black;" +
                "-fx-cursor: hand;"
        );
        branchBox.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item);
                    setStyle("-fx-text-fill: black;");
                }
            }
        });

        // ضع Label + ComboBox في HBox صغيرة مع بعض
        HBox branchHBox = new HBox(5, branchLabel, branchBox);
        branchHBox.setAlignment(Pos.CENTER); // محاذاة مركزية مع الأزرار

        // ===== Buttons =====
        Button remove = new Button("Remove Selected");
        Button clear = new Button("Clear Cart");
        Button checkout = new Button("Checkout");
        Button clearOffer = new Button("Remove Offer");

        HBox actions = new HBox(10, branchHBox, checkout, remove, clear, clearOffer);
        actions.setAlignment(Pos.CENTER);

        remove.setOnAction(e -> {
            CartItem item = table.getSelectionModel().getSelectedItem();
            if (item == null) {
                msg.setText("Select item");
                return;
            }
            CartStore.get(customerName).remove(item);
            refresh();
            msg.setText("Removed ✅");
        });

        clear.setOnAction(e -> {
            CartStore.clear(customerName);
            refresh();
            msg.setText("Cleared ✅");
        });

        clearOffer.setOnAction(e -> {
            SelectedOfferStore.clear(customerName);
            refresh();
            msg.setText("Offer removed ✅");
        });

        checkout.setOnAction(e -> {
            try {
                String branch = branchBox.getValue();
                if (CartStore.get(customerName).isEmpty()) {
                    msg.setText("Cart is empty");
                    return;
                }

                int branchId = branch.contains("Ramallah") ? 1 : 2; 
                int orderId = OrderService.createOrder(customerName, branchId,
                        CartStore.get(customerName), SelectedOfferStore.getSelected(customerName));

                CartStore.clear(customerName);
                SelectedOfferStore.clear(customerName);
                refresh();

                msg.setText("Order Created ✅  ID = " + orderId + " | Branch: " + branch);
            } catch (Exception ex) {
                msg.setText("Checkout error: " + ex.getMessage());
            }
        });

        HBox actions1 = new HBox(10, branchHBox, checkout, remove, clear, clearOffer);
        actions1.setAlignment(Pos.CENTER);

        // ===== Labels =====
        msg.setStyle("-fx-text-fill:white; -fx-font-weight:900;");
        subtotalLbl.setStyle("-fx-text-fill:white; -fx-font-weight:900; -fx-font-size:16px;");
        discountLbl.setStyle("-fx-text-fill:white; -fx-font-weight:900; -fx-font-size:16px;");
        totalLbl.setStyle("-fx-text-fill:white; -fx-font-weight:900; -fx-font-size:18px;");

        VBox totalsBox = new VBox(6, subtotalLbl, discountLbl, totalLbl);
        totalsBox.setAlignment(Pos.CENTER);

        VBox center = new VBox(14, table, totalsBox, actions1, msg);
        center.setAlignment(Pos.CENTER);
        setCenter(center);

        refresh();
    }

    private void styleGlassTable(TableView<CartItem> t) {
        t.setPrefWidth(760);
        t.setPrefHeight(340);
        t.setFixedCellSize(44);

        t.setStyle("-fx-background-color: rgba(255,255,255,0.10);" +
                "-fx-background-radius: 20;" +
                "-fx-border-radius: 20;" +
                "-fx-border-color: rgba(255,255,255,0.45);" +
                "-fx-border-width: 1.6;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.35), 16, 0.2, 0, 6);" +
                "-fx-padding: 8;");

        t.lookupAll(".viewport").forEach(n -> n.setStyle("-fx-background-color: transparent;"));
        t.lookupAll(".scroll-pane").forEach(n -> n.setStyle("-fx-background-color: transparent; -fx-padding: 0;"));
        t.lookupAll(".scroll-pane .corner").forEach(n -> n.setStyle("-fx-background-color: transparent;"));

        t.lookupAll(".column-header-background").forEach(
                n -> n.setStyle("-fx-background-color: rgba(255,255,255,0.20);" +
                        "-fx-background-radius: 20 20 0 0;"));

        t.lookupAll(".column-header .label")
                .forEach(n -> n.setStyle("-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: 900;"));

        t.setRowFactory(tv -> new TableRow<CartItem>() {
            @Override
            protected void updateItem(CartItem item, boolean empty) {
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

        t.setPlaceholder(new Label("Your cart is empty"));
        t.getPlaceholder().setStyle("-fx-text-fill:white; -fx-font-weight:900;");
    }

    private void refresh() {
        table.setItems(FXCollections.observableArrayList(CartStore.get(customerName)));

        double subtotal = 0;
        for (CartItem it : CartStore.get(customerName))
            subtotal += it.getLineTotal();

        Offer o = SelectedOfferStore.getSelected(customerName);
        double rate = (o == null) ? 0 : o.getDiscountRate();
        double discount = subtotal * (rate / 100.0);

        subtotalLbl.setText("Subtotal: " + round2(subtotal));
        discountLbl.setText("Discount: " + (o == null ? "0" : rate + "%"));
        totalLbl.setText("Total: " + round2(subtotal - discount));
    }

    private String round2(double v) {
        return String.format(java.util.Locale.US, "%.2f", v);
    }
}
