package application;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class OffersView extends BorderPane {
	private final TableView<Offer> table = new TableView<>();
	private final Label msg = new Label();

	public OffersView() {
		setPadding(new Insets(16));
		setStyle("-fx-background-color:#e6f2f1;");

		Label title = new Label("Offers");
		title.setStyle("-fx-font-size:24px; -fx-font-weight:900;");
		setTop(title);
		BorderPane.setAlignment(title, Pos.CENTER);

		TableColumn<Offer, Integer> id = new TableColumn<>("ID");
		id.setCellValueFactory(new PropertyValueFactory<>("offerId"));

		TableColumn<Offer, String> desc = new TableColumn<>("Description");
		desc.setCellValueFactory(new PropertyValueFactory<>("description"));

		TableColumn<Offer, Double> rate = new TableColumn<>("Discount %");
		rate.setCellValueFactory(new PropertyValueFactory<>("discountRate"));

		table.getColumns().addAll(id, desc, rate);
		setCenter(table);

		TextField txtId = new TextField();
		txtId.setPromptText("Offer ID");
		TextField txtDesc = new TextField();
		txtDesc.setPromptText("Description");
		TextField txtRate = new TextField();
		txtRate.setPromptText("Rate (e.g., 20)");

		Button add = new Button("Add");
		Button del = new Button("Delete Selected");
		Button upd = new Button("Update");

		upd.setOnAction(e -> {
			Offer o = table.getSelectionModel().getSelectedItem();
			if (o == null) {
				msg.setText("Select an offer first.");
				return;
			}
			try {
				boolean ok = OfferService.updateOffer(Integer.parseInt(txtId.getText().trim()),
						txtDesc.getText().trim(), Double.parseDouble(txtRate.getText().trim()));
				msg.setText(ok ? "Updated ✅" : "Not updated");
				load();
			} catch (Exception ex) {
				msg.setText("Error: " + ex.getMessage());
			}
		});

		add.setOnAction(e -> {
			try {
				OfferService.addOffer(Integer.parseInt(txtId.getText().trim()), txtDesc.getText().trim(),
						Double.parseDouble(txtRate.getText().trim()));
				msg.setText("Added offer.");
				load();
			} catch (Exception ex) {
				msg.setText("Error: " + ex.getMessage());
			}
		});

		del.setOnAction(e -> {
			Offer o = table.getSelectionModel().getSelectedItem();
			if (o == null) {
				msg.setText("Select an offer.");
				return;
			}
			try {
				OfferService.deleteOffer(o.getOfferId());
				msg.setText("Deleted offer.");
				load();
			} catch (Exception ex) {
				msg.setText("Error: " + ex.getMessage());
			}
		});

		HBox form = new HBox(10, txtId, txtDesc, txtRate, add, upd, del);
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
			table.setItems(FXCollections.observableArrayList(OfferService.getAllOffers()));
		} catch (Exception ex) {
			msg.setText("Load error: " + ex.getMessage());
		}
	}
}