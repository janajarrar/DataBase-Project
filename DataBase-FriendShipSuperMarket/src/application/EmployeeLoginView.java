package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class EmployeeLoginView extends StackPane {

	public EmployeeLoginView(MainApp app) {
		setStyle("-fx-background-color: #2f6f6b;");

		VBox container = new VBox(18);
		container.setAlignment(Pos.CENTER);

		Label header = new Label("Employee Portal");
		header.setStyle("-fx-text-fill: white; -fx-font-size: 54px; -fx-font-weight: 900;");

		VBox card = new VBox(12);
		card.setPadding(new Insets(22));
		card.setMaxWidth(560);
		card.setStyle(
				"-fx-background-color: #f3f6f7; -fx-background-radius: 22; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 18, 0.2, 0, 6);");

		Label title = new Label("Staff Authentication");
		title.setStyle("-fx-font-size: 22px; -fx-font-weight: 900;");

		TextField fullName = new TextField();
		fullName.setPromptText("Full Name");

		TextField empId = new TextField();
		empId.setPromptText("Enter your ID number");

		PasswordField pass = new PasswordField();
		pass.setPromptText("Enter password");

		Label msg = new Label();
		msg.setStyle("-fx-text-fill: #b00020; -fx-font-weight: 700;");

		Button login = new Button("Login to Dashboard");
		login.setPrefHeight(48);
		login.setMaxWidth(Double.MAX_VALUE);
		login.setStyle(
				"-fx-background-color: #0b1f3a; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: 900; -fx-background-radius: 16;");

		login.setOnAction(e -> {
			try {
				if (fullName.getText().isBlank() || empId.getText().isBlank() || pass.getText().isBlank()) {
					msg.setText("Please fill all fields.");
					return;
				}
				int id = Integer.parseInt(empId.getText().trim());
				boolean ok = AuthService.employeeLogin(fullName.getText(), id, pass.getText());
				if (ok)
					app.goEmployeeDashboard(fullName.getText().trim());
				else
					msg.setText("Invalid employee credentials.");
			} catch (NumberFormatException nf) {
				msg.setText("Employee ID must be a number.");
			} catch (Exception ex) {
				msg.setText("DB Error: " + ex.getMessage());
			}
		});

		Separator sep = new Separator();

		Label newStaff = new Label("New staff member?");
		newStaff.setStyle("-fx-text-fill: #374151; -fx-font-weight: 700;");

		Button register = new Button("Register New Account");
		register.setPrefHeight(44);
		register.setMaxWidth(Double.MAX_VALUE);
		register.setStyle(
				"-fx-background-color: #22c55e; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: 900; -fx-background-radius: 16;");
		register.setOnAction(e -> app.goEmployeeRegister());

		Button back = new Button("Back");
		back.setOnAction(e -> app.goWelcome());
		back.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: 800;");

		card.getChildren().addAll(title, label("Full Name:"), fullName, label("Employee ID:"), empId,
				label("Password:"), pass, login, msg, sep, newStaff, register);

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