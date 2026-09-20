package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomerLoginView extends StackPane {

	public CustomerLoginView(MainApp app) {
		setStyle("-fx-background-color: #2f6f6b;");

		VBox container = new VBox(18);
		container.setAlignment(Pos.CENTER);

		Label header = new Label("Customer Login");
		header.setFont(Font.font("Times New Roman", 52));
		header.setStyle("-fx-text-fill: white; -fx-font-weight: 900;");

		DropShadow headerShadow = new DropShadow();
		headerShadow.setRadius(10);
		headerShadow.setOffsetX(3);
		headerShadow.setOffsetY(3);
		headerShadow.setColor(Color.rgb(0, 0, 0, 0.55));
		header.setEffect(headerShadow);

		VBox card = new VBox(14);
		card.setPadding(new Insets(22));
		card.setMaxWidth(520);
		card.setStyle("-fx-background-color: #f3f6f7;" + "-fx-background-radius: 22;"
				+ "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 18, 0.2, 0, 6);");

		Label q = new Label("Do you have an account?");
		q.setFont(Font.font("Georgia", 18));
		q.setStyle("-fx-font-weight: 800;");

		TextField name = new TextField();
		name.setPromptText("Name");
		name.setFont(Font.font("Segoe UI", 14));
		name.setStyle("-fx-background-radius: 10; -fx-border-radius: 10;");

		PasswordField pass = new PasswordField();
		pass.setPromptText("Enter your password");
		pass.setFont(Font.font("Segoe UI", 14));
		pass.setStyle("-fx-background-radius: 10; -fx-border-radius: 10;");

		Label msg = new Label();
		msg.setFont(Font.font("Segoe UI", 13));
		msg.setStyle("-fx-text-fill: #b00020; -fx-font-weight: 700;");

		Button login = new Button("Log In");
		login.setFont(Font.font("Segoe UI", 16));
		login.setPrefHeight(46);
		login.setMaxWidth(Double.MAX_VALUE);
		login.setStyle("-fx-background-color: #3b82f6;" + "-fx-text-fill: white;" + "-fx-font-weight: 900;"
				+ "-fx-background-radius: 16;");

		login.setOnAction(e -> {
			try {
				if (name.getText().isBlank() || pass.getText().isBlank()) {
					msg.setText("Please enter name and password.");
					return;
				}
				boolean ok = AuthService.customerLogin(name.getText(), pass.getText());
				if (ok)
					app.goCustomerDashboard(name.getText().trim());
				else
					msg.setText("Invalid login. Check your name/password.");
			} catch (Exception ex) {
				msg.setText("DB Error: " + ex.getMessage());
			}
		});

		Separator sep = new Separator();

		Label noAcc = new Label("If you don't have an account?");
		noAcc.setFont(Font.font("Georgia", 14));
		noAcc.setStyle("-fx-text-fill: #374151; -fx-font-weight: 700;");

		Button signup = new Button("Sign Up");
		signup.setFont(Font.font("Segoe UI", 16));
		signup.setPrefHeight(44);
		signup.setMaxWidth(Double.MAX_VALUE);
		signup.setStyle("-fx-background-color: #22c55e;" + "-fx-text-fill: white;" + "-fx-font-weight: 900;"
				+ "-fx-background-radius: 16;");
		signup.setOnAction(e -> app.goCustomerRegister());

		Button back = new Button("Back");
		back.setFont(Font.font("Segoe UI", 14));
		back.setOnAction(e -> app.goWelcome());
		back.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: 800;");

		card.getChildren().addAll(q, new Label("Name:"), name, new Label("Password:"), pass, login, msg, sep, noAcc,
				signup);

		container.getChildren().addAll(header, card, back);
		getChildren().add(container);
	}
}