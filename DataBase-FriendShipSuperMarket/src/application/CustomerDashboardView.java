package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomerDashboardView extends StackPane {

	public CustomerDashboardView(MainApp app, String customerName) {
		setStyle("-fx-background-color: #2f6f6b;");

		VBox box = new VBox(16);
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(22));

		Label title = new Label("Welcome, " + customerName + " 👋");
		title.setFont(Font.font("Times New Roman", 42));
		title.setStyle("-fx-text-fill: white; -fx-font-weight: 900;");

		DropShadow titleShadow = new DropShadow();
		titleShadow.setRadius(10);
		titleShadow.setOffsetX(3);
		titleShadow.setOffsetY(3);
		titleShadow.setColor(Color.rgb(0, 0, 0, 0.55));
		title.setEffect(titleShadow);

		Label hint = new Label("Customer Dashboard (basic)");
		hint.setFont(Font.font("Georgia", 16));
		hint.setStyle("-fx-text-fill: #e8f3f2; -fx-font-weight: 700;");

		Button viewProducts = btn("View Products");
		Button viewOffers = btn("View Offers");
		Button logout = btn("Logout");

		Button shop = UI.bigBtnWithIcon("Shop Categories", Icons.customer());
		Button cart = UI.bigBtnWithIcon("Cart", Icons.cart());
		Button offers = UI.bigBtnWithIcon("Offers", Icons.offersManage());

		viewProducts.setOnAction(e -> app.goCustomerCategories(customerName));
		viewOffers.setOnAction(e -> app.goCustomerOffers(customerName));
		logout.setOnAction(e -> app.goWelcome());

		box.getChildren().addAll(title, hint, viewProducts, viewOffers, logout);
		getChildren().add(box);
	}

	private Button btn(String t) {
		Button b = new Button(t);
		b.setPrefWidth(320);
		b.setPrefHeight(46);
		b.setFont(Font.font("Segoe UI", 15));
		b.setStyle("-fx-background-color: #0b1f3a;" + "-fx-text-fill: white;" + "-fx-font-weight: 900;"
				+ "-fx-background-radius: 14;");
		return b;
	}

	private void info(String text) {
		System.out.println(text);
	}
}