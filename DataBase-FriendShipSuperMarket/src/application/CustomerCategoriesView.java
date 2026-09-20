package application;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class CustomerCategoriesView extends BorderPane {

	private final MainApp app;
	private final String customerName;

	public CustomerCategoriesView(MainApp app, String customerName) {
		this.app = app;
		this.customerName = customerName;

		setStyle("-fx-background-color:#2f6f6b;");
		setPadding(new Insets(16));

		setTop(UI.topBar("Shop Categories", () -> app.goCustomerDashboard(customerName)));

		GridPane grid = new GridPane();
		grid.setHgap(18);
		grid.setVgap(18);
		grid.setAlignment(Pos.CENTER);

		grid.add(categoryCard("Beverages", "Drinks & juices", "☕", "#06b6d4"), 0, 0);
		grid.add(categoryCard("Dairy", "Milk & yogurt", "🥛", "#3b82f6"), 1, 0);
		grid.add(categoryCard("Bakery", "Bread & pastries", "🥖", "#f59e0b"), 2, 0);

		grid.add(categoryCard("Frozen", "Frozen items", "❄", "#60a5fa"), 0, 1);
		grid.add(categoryCard("Snacks", "Chips & sweets", "🍪", "#fb7185"), 1, 1);
		grid.add(categoryCard("Cleaning", "Home cleaning", "🧽", "#22c55e"), 2, 1);

		setCenter(grid);
	}

	private VBox categoryCard(final String category, String subtitle, String emoji, String colorHex) {

		VBox card = new VBox(10);
		card.setAlignment(Pos.CENTER_LEFT);
		card.setPadding(new Insets(16));
		card.setPrefSize(260, 170);

		card.setStyle("-fx-background-color: linear-gradient(to bottom right," + " rgba(255,255,255,0.35),"
				+ " rgba(255,255,255,0.18)," + " rgba(255,255,255,0.10));" + "-fx-background-radius:18;"
				+ "-fx-border-radius:18;" + "-fx-border-color: rgba(255,255,255,0.55);" + "-fx-border-width:1.2;");

		DropShadow baseShadow = new DropShadow();
		baseShadow.setRadius(18);
		baseShadow.setOffsetY(8);
		baseShadow.setColor(Color.rgb(0, 0, 0, 0.45));
		card.setEffect(baseShadow);

		StackPane iconCircle = new StackPane();
		iconCircle.setPrefSize(46, 46);
		iconCircle.setStyle("-fx-background-color:" + colorHex + ";" + "-fx-background-radius:999;");

		Label icon = new Label(emoji);
		icon.setFont(Font.font("Segoe UI Symbol", 20));
		icon.setStyle("-fx-text-fill:white; -fx-font-weight:900;");
		iconCircle.getChildren().add(icon);

		Label title = new Label(category);
		title.setFont(Font.font("Georgia", 18));
		title.setStyle("-fx-text-fill:white; -fx-font-weight:900;");

		DropShadow titleShadow = new DropShadow();
		titleShadow.setRadius(6);
		titleShadow.setOffsetY(2);
		titleShadow.setColor(Color.rgb(0, 0, 0, 0.65));
		title.setEffect(titleShadow);

		Label sub = new Label(subtitle);
		sub.setFont(Font.font("Segoe UI", 12));
		sub.setStyle("-fx-text-fill: rgba(255,255,255,0.88); -fx-font-weight:700;");

		VBox text = new VBox(2, title, sub);
		HBox head = new HBox(12, iconCircle, text);
		head.setAlignment(Pos.CENTER_LEFT);

		Button open = new Button("Open");
		open.setPrefHeight(38);
		open.setPrefWidth(110);
		open.setFont(Font.font("Segoe UI", 14));
		open.setStyle("-fx-background-color:#22c55e;" + "-fx-text-fill:white;" + "-fx-font-weight:900;"
				+ "-fx-background-radius:12;");
		open.setOnAction(e -> app.goProductsByCategory(customerName, category));

		HBox btnRow = new HBox(open);
		btnRow.setAlignment(Pos.CENTER_RIGHT);

		card.getChildren().addAll(head, new Region(), btnRow);
		VBox.setVgrow(card.getChildren().get(1), Priority.ALWAYS);

		card.setOnMouseEntered(e -> {
			ScaleTransition st = new ScaleTransition(Duration.millis(160), card);
			st.setToX(1.07);
			st.setToY(1.07);
			st.play();

			card.setStyle("-fx-background-color: linear-gradient(to bottom right," + " rgba(255,255,255,0.55),"
					+ " rgba(255,255,255,0.25)," + " rgba(255,255,255,0.12));" + "-fx-background-radius:18;"
					+ "-fx-border-radius:18;" + "-fx-border-color: rgba(255,255,255,0.75);" + "-fx-border-width:1.4;");

			DropShadow hoverShadow = new DropShadow();
			hoverShadow.setRadius(28);
			hoverShadow.setOffsetY(12);
			hoverShadow.setColor(Color.rgb(0, 0, 0, 0.55));
			card.setEffect(hoverShadow);
		});

		card.setOnMouseExited(e -> {
			ScaleTransition st = new ScaleTransition(Duration.millis(160), card);
			st.setToX(1.0);
			st.setToY(1.0);
			st.play();

			card.setStyle("-fx-background-color: linear-gradient(to bottom right," + " rgba(255,255,255,0.35),"
					+ " rgba(255,255,255,0.18)," + " rgba(255,255,255,0.10));" + "-fx-background-radius:18;"
					+ "-fx-border-radius:18;" + "-fx-border-color: rgba(255,255,255,0.55);" + "-fx-border-width:1.2;");

			card.setEffect(baseShadow);
		});

		return card;
	}
}