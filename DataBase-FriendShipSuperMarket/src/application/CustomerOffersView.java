package application;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class CustomerOffersView extends BorderPane {

	private final MainApp app;
	private final String customerName;

	private final TableView<Offer> table = new TableView<>();
	private final Label msg = new Label();

	public CustomerOffersView(MainApp app, String customerName) {
		this.app = app;
		this.customerName = customerName;

		setStyle("-fx-background-color:#2f6f6b;");
		setPadding(new Insets(16));

		setTop(UI.topBar("Available Offers", () -> app.goCustomerDashboard(customerName)));

		TableColumn<Offer, Integer> c1 = new TableColumn<>("ID");
		c1.setCellValueFactory(new PropertyValueFactory<>("offerId"));

		TableColumn<Offer, String> c2 = new TableColumn<>("Description");
		c2.setCellValueFactory(new PropertyValueFactory<>("description"));

		TableColumn<Offer, Double> c3 = new TableColumn<>("Discount %");
		c3.setCellValueFactory(new PropertyValueFactory<>("discountRate"));

		table.getColumns().addAll(c1, c2, c3);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setEditable(false);
		UI.cardStyle(table);

		Button apply = new Button("Apply Selected Offer");
		apply.setStyle(
				"-fx-background-color:#22c55e; -fx-text-fill:white; -fx-font-weight:900; -fx-background-radius:12;");
		apply.setPrefHeight(36);

		apply.setOnAction(e -> {
			Offer o = table.getSelectionModel().getSelectedItem();
			if (o == null) {
				msg.setText("Select an offer first");
				return;
			}

			SelectedOfferStore.setSelected(customerName, o);

			msg.setText("Applied ✅ (" + o.getDiscountRate() + "%)");
		});

		msg.setStyle("-fx-text-fill:white; -fx-font-weight:900;");

		VBox center = new VBox(12, table, apply, msg);
		center.setAlignment(Pos.CENTER);
		setCenter(center);

		load();
	}

	private void load() {
		try {
			table.setItems(FXCollections.observableArrayList(OfferService.getAllOffers()));
		} catch (Exception ex) {
			msg.setText("Error loading offers");
		}
	}
}