package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.Random;

public class CustomerRegisterView extends StackPane {

	public CustomerRegisterView(MainApp app) {
		setStyle("-fx-background-color: #2f6f6b;");

		VBox container = new VBox(18);
		container.setAlignment(Pos.CENTER);

		Label header = new Label("Create New Account");
		header.setStyle("-fx-text-fill: white; -fx-font-size: 54px; -fx-font-weight: 900;");

		VBox card = new VBox(12);
		card.setPadding(new Insets(22));
		card.setMaxWidth(560);
		card.setStyle(
				"-fx-background-color: #f3f6f7; -fx-background-radius: 22; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 18, 0.2, 0, 6);");

		TextField fullName = new TextField();
		fullName.setPromptText("Full Name");

		TextField email = new TextField();
		email.setPromptText("example@mail.com");

		TextField phone = new TextField();
		phone.setPromptText("059XXXXXXXX");

		PasswordField pass = new PasswordField();
		pass.setPromptText("Create a password");

		Label msg = new Label();
		msg.setStyle("-fx-text-fill: #b00020; -fx-font-weight: 700;");

		Button create = new Button("Create Account");
		create.setPrefHeight(48);
		create.setMaxWidth(Double.MAX_VALUE);
		create.setStyle(
				"-fx-background-color: #22c55e; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: 900; -fx-background-radius: 16;");

		create.setOnAction(e -> {
			try {
				if (fullName.getText().isBlank() || email.getText().isBlank() || phone.getText().isBlank()
						|| pass.getText().isBlank()) {
					msg.setText("Please fill all fields.");
					return;
				}

				int customerId = 1000 + new Random().nextInt(900000);

				boolean ok = AuthService.customerRegister(customerId, fullName.getText(), email.getText(),
						phone.getText(), pass.getText());
				if (ok) {
					msg.setStyle("-fx-text-fill: #166534; -fx-font-weight: 900;");
					msg.setText("Account created successfully! You can log in now.");
				} else {
					msg.setText("Could not create account.");
				}
			} catch (Exception ex) {
				msg.setText("DB Error: " + ex.getMessage());
			}
		});

		Hyperlink loginLink = new Hyperlink("Already have an account? Log In");
		loginLink.setOnAction(e -> app.goCustomerLogin());

		Button back = new Button("Back");
		back.setOnAction(e -> app.goWelcome());
		back.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: 800;");

		card.getChildren().addAll(label("Full Name:"), fullName, label("Email Address:"), email, label("Phone Number:"),
				phone, label("Password:"), pass, create, msg, loginLink);

		container.getChildren().addAll(header, card, back);
		getChildren().add(container);
		StackPane.setMargin(container, new Insets(20));
	}

	private Label label(String t) {
		Label l = new Label(t);
		l.setStyle("-fx-font-weight: 800; -fx-font-size: 14px;");
		return l;
	}
}