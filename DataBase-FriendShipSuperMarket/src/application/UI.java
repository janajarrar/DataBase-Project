package application;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class UI {

	public static HBox topBar(String titleText, Runnable onBack) {
		Label title = new Label(titleText);
		title.setStyle("-fx-text-fill:white; -fx-font-size:34px; -fx-font-weight:900;");

		Button back = new Button("Back");
		back.setOnAction(e -> onBack.run());
		back.setStyle(
				"-fx-background-color:transparent; -fx-text-fill:white; -fx-font-weight:900; -fx-font-size:14px;");

		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		HBox bar = new HBox(10, title, spacer, back);
		bar.setAlignment(Pos.CENTER_LEFT);
		return bar;
	}

	public static void cardStyle(Node n) {
		n.setStyle(
				"-fx-background-color:#f3f6f7; -fx-background-radius:16; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.18), 18, 0.2, 0, 6);");
	}

	public static Button bigBtn(String text) {
		Button b = new Button(text);
		b.setPrefWidth(280);
		b.setPrefHeight(70);
		b.setStyle("-fx-background-color:#0b1f3a;" + "-fx-text-fill:white;" + "-fx-font-weight:900;"
				+ "-fx-font-size:16px;" + "-fx-background-radius:16;");
		return b;
	}

	public static javafx.scene.control.Button bigBtnWithIcon(String text, javafx.scene.layout.StackPane icon) {
		javafx.scene.control.Label t = new javafx.scene.control.Label(text);
		t.setStyle("-fx-text-fill:white; -fx-font-weight:900; -fx-font-size:16px;");

		javafx.scene.layout.HBox box = new javafx.scene.layout.HBox(12, icon, t);
		box.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

		javafx.scene.control.Button b = new javafx.scene.control.Button();
		b.setGraphic(box);
		b.setText(null);
		b.setPrefWidth(320);
		b.setPrefHeight(72);
		b.setStyle("-fx-background-color:#0b1f3a;" + "-fx-background-radius:18;");
		return b;
	}
}