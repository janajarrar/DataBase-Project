package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class EmployeeRegisterView extends StackPane {

	public EmployeeRegisterView(MainApp app) {
		setStyle("-fx-background-color: #2f6f6b;");

		VBox container = new VBox(18);
		container.setAlignment(Pos.CENTER);

		Label header = new Label("Register Staff Account");
		header.setStyle("-fx-text-fill: white; -fx-font-size: 46px; -fx-font-weight: 900;");

		VBox card = new VBox(12);
		card.setPadding(new Insets(22));
		card.setMaxWidth(560);
		card.setStyle(
				"-fx-background-color: #f3f6f7; -fx-background-radius: 22; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 18, 0.2, 0, 6);");

		TextField fullName = new TextField();
		TextField empId = new TextField();
		TextField role = new TextField();
		TextField salary = new TextField();
		TextField branchId = new TextField();
		PasswordField pass = new PasswordField();

		fullName.setPromptText("Full Name");
		empId.setPromptText("Employee ID (number)");
		role.setPromptText("Role (Cashier / Manager / Stocker)");
		salary.setPromptText("Salary (e.g., 3000)");
		branchId.setPromptText("Branch ID (e.g., 1)");
		pass.setPromptText("Password");

		Label msg = new Label();
		msg.setStyle("-fx-text-fill: #b00020; -fx-font-weight: 700;");

		Button create = new Button("Create Staff Account");
		create.setPrefHeight(48);
		create.setMaxWidth(Double.MAX_VALUE);
		create.setStyle(
				"-fx-background-color: #22c55e; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: 900; -fx-background-radius: 16;");

		create.setOnAction(e -> {
			try {
				int id = Integer.parseInt(empId.getText().trim());
				double sal = Double.parseDouble(salary.getText().trim());
				int bId = Integer.parseInt(branchId.getText().trim());

				boolean ok = AuthService.employeeRegister(id, fullName.getText(), role.getText(), sal, bId,
						pass.getText());
				if (ok) {
					msg.setStyle("-fx-text-fill: #166534; -fx-font-weight: 900;");
					msg.setText("Staff account created! Go login.");
				} else
					msg.setText("Could not create staff account.");
			} catch (Exception ex) {
				msg.setText("Error: " + ex.getMessage());
			}
		});

		Hyperlink backToLogin = new Hyperlink("Back to Employee Login");
		backToLogin.setOnAction(e -> app.goEmployeeLogin());

		card.getChildren().addAll(label("Full Name:"), fullName, label("Employee ID:"), empId, label("Role:"), role,
				label("Salary:"), salary, label("Branch ID:"), branchId, label("Password:"), pass, create, msg,
				backToLogin);

		Button back = new Button("Back");
		back.setOnAction(e -> app.goEmployeeLogin());
		back.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: 800;");

		container.getChildren().addAll(header, card, back);
		getChildren().add(container);
	}

	private Label label(String t) {
		Label l = new Label(t);
		l.setStyle("-fx-font-weight: 800; -fx-font-size: 14px;");
		return l;
	}
}