package application;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class OffersManagementView extends BorderPane {

	private final TableView<Offer> table = new TableView<>();
	private final Label msg = new Label();

	public OffersManagementView(MainApp app) {
		setStyle("-fx-background-color:#2f6f6b;");
		setPadding(new Insets(16));

		setTop(UI.topBar("Offers Management", () -> app.goEmployeeDashboard("Employee")));

		TableColumn<Offer, Integer> c1 = new TableColumn<>("ID");
		c1.setCellValueFactory(new PropertyValueFactory<>("offerId"));

		TableColumn<Offer, String> c2 = new TableColumn<>("Description");
		c2.setCellValueFactory(new PropertyValueFactory<>("description"));

		TableColumn<Offer, Double> c3 = new TableColumn<>("Discount %");
		c3.setCellValueFactory(new PropertyValueFactory<>("discountRate"));

		table.getColumns().addAll(c1, c2, c3);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		UI.cardStyle(table);

		TextField id = new TextField();
		id.setPromptText("Offer ID");
		TextField desc = new TextField();
		desc.setPromptText("Description");
		TextField rate = new TextField();
		rate.setPromptText("Rate (e.g., 20)");

		Button add = new Button("Add");
		Button upd = new Button("Update");
		Button del = new Button("Delete Selected");
		Button refresh = new Button("Refresh");

		add.setOnAction(e -> {
			try {
				boolean ok = OfferService.addOffer(Integer.parseInt(id.getText().trim()), desc.getText().trim(),
						Double.parseDouble(rate.getText().trim()));
				msg.setText(ok ? "Added ✅" : "Not added");
				load();
			} catch (Exception ex) {
				msg.setText("Error: " + ex.getMessage());
			}
		});

		upd.setOnAction(e -> {
			try {
				boolean ok = OfferService.updateOffer(Integer.parseInt(id.getText().trim()), desc.getText().trim(),
						Double.parseDouble(rate.getText().trim()));
				msg.setText(ok ? "Updated ✅" : "Not updated");
				load();
			} catch (Exception ex) {
				msg.setText("Error: " + ex.getMessage());
			}
		});

		del.setOnAction(e -> {
			try {
				Offer o = table.getSelectionModel().getSelectedItem();
				if (o == null) {
					msg.setText("Select offer first");
					return;
				}
				boolean ok = OfferService.deleteOffer(o.getOfferId());
				msg.setText(ok ? "Deleted ✅" : "Not deleted");
				load();
			} catch (Exception ex) {
				msg.setText("Error: " + ex.getMessage());
			}
		});

		refresh.setOnAction(e -> load());

		table.setOnMouseClicked(e -> {
			Offer o = table.getSelectionModel().getSelectedItem();
			if (o != null) {
				id.setText(String.valueOf(o.getOfferId()));
				desc.setText(o.getDescription());
				rate.setText(String.valueOf(o.getDiscountRate()));
			}
		});

		HBox form = new HBox(10, id, desc, rate, add, upd, del, refresh);
		form.setAlignment(Pos.CENTER);
		form.setPadding(new Insets(10));
		UI.cardStyle(form);

		msg.setStyle("-fx-text-fill:white; -fx-font-weight:900;");

		VBox center = new VBox(12, table, form, msg);
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