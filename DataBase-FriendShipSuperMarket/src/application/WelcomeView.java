package application;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class WelcomeView extends BorderPane {

	public WelcomeView(MainApp app) {

		setStyle("-fx-background-color:#2f6f6b;");
		setPadding(new Insets(30));

		Label cartIcon = new Label("🛒");
		cartIcon.setFont(Font.font("Segoe UI Symbol", 52));
		cartIcon.setStyle("-fx-text-fill:white;");

		Label title = new Label("Friendship Supermarket");
		title.setFont(Font.font("Times New Roman", 42));
		title.setStyle("-fx-text-fill:white; -fx-font-weight:900;");

		DropShadow titleShadow = new DropShadow();
		titleShadow.setRadius(10);
		titleShadow.setOffsetX(3);
		titleShadow.setOffsetY(3);
		titleShadow.setColor(Color.rgb(0, 0, 0, 0.55));
		title.setEffect(titleShadow);

		Label sub = new Label("The best shopping experience for your family!");
		sub.setStyle("-fx-text-fill:#e5e7eb; -fx-font-size:16px;");

		VBox header = new VBox(10, cartIcon, title, sub);
		header.setAlignment(Pos.CENTER);
		header.setPadding(new Insets(10, 0, 15, 0));

		setTop(header);
		BorderPane.setAlignment(header, Pos.CENTER);

		Label customerIcon = new Label("👨‍👩‍👧‍👦");
		customerIcon.setFont(Font.font("Segoe UI Symbol", 26));

		Label employeeIcon = new Label("🧑‍💼✍");
		employeeIcon.setFont(Font.font("Segoe UI Symbol", 26));

		Button customerBtn = bigButton("I'm a Customer", customerIcon);
		Button employeeBtn = bigButton("I'm an Employee", employeeIcon);

		customerBtn.setOnAction(e -> app.goCustomerLogin());
		employeeBtn.setOnAction(e -> app.goEmployeeLogin());

		HBox buttons = new HBox(30, customerBtn, employeeBtn);
		buttons.setAlignment(Pos.CENTER);

		VBox card = new VBox(18, buttons);
		card.setAlignment(Pos.CENTER);
		card.setPadding(new Insets(35));

		card.setStyle("-fx-background-color: rgba(255,255,255,0.08);" + "-fx-background-radius: 26;"
				+ "-fx-border-radius: 26;" + "-fx-border-color: rgba(255,255,255,0.18);" + "-fx-border-width: 1.2;");

		DropShadow cardShadow = new DropShadow();
		cardShadow.setRadius(22);
		cardShadow.setOffsetY(10);
		cardShadow.setColor(Color.rgb(0, 0, 0, 0.35));
		card.setEffect(cardShadow);

		StackPane centerWrap = new StackPane(card);
		centerWrap.setPadding(new Insets(10, 0, 0, 0));

		setCenter(centerWrap);
	}

	private Button bigButton(String text, Label icon) {

		Label t = new Label(text);
		t.setStyle("-fx-text-fill:white; -fx-font-size:18px; -fx-font-weight:900;");

		HBox content = new HBox(12, icon, t);
		content.setAlignment(Pos.CENTER);

		Button b = new Button();
		b.setGraphic(content);
		b.setPrefWidth(320);
		b.setPrefHeight(92);

		b.setStyle("-fx-background-color:#0b1f3a;" + "-fx-background-radius:22;" + "-fx-cursor: hand;");

		DropShadow btnShadow = new DropShadow();
		btnShadow.setRadius(16);
		btnShadow.setOffsetY(8);
		btnShadow.setColor(Color.rgb(0, 0, 0, 0.35));
		b.setEffect(btnShadow);

		b.setOnMouseEntered(e -> {
			ScaleTransition st = new ScaleTransition(Duration.millis(120), b);
			st.setToX(1.03);
			st.setToY(1.03);
			st.play();

			DropShadow hoverShadow = new DropShadow();
			hoverShadow.setRadius(22);
			hoverShadow.setOffsetY(10);
			hoverShadow.setColor(Color.rgb(0, 0, 0, 0.45));
			b.setEffect(hoverShadow);
		});

		b.setOnMouseExited(e -> {
			ScaleTransition st = new ScaleTransition(Duration.millis(120), b);
			st.setToX(1.0);
			st.setToY(1.0);
			st.play();

			b.setEffect(btnShadow);
		});

		return b;
	}
}