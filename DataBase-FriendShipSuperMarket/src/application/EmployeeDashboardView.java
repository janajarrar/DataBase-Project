package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class EmployeeDashboardView extends BorderPane {

	public EmployeeDashboardView(MainApp app, String employeeName) {

		setStyle("-fx-background-color:#2f6f6b;");
		setPadding(new Insets(16));

		setTop(UI.topBar("Employee Dashboard", () -> app.goWelcome()));

		Label hello = new Label("Welcome, " + employeeName);
		hello.setStyle("-fx-text-fill:white; -fx-font-size:28px; -fx-font-weight:900;");

		Button reports = UI.bigBtnWithIcon("Reports", Icons.reports());
		Button products = UI.bigBtnWithIcon("Manage Products", Icons.productsManage());
		Button inventory = UI.bigBtnWithIcon("Manage Inventory", Icons.inventoryManage());
		Button offers = UI.bigBtnWithIcon("Manage Offers", Icons.offersManage());
		Button logout = UI.bigBtnWithIcon("Logout", Icons.logout());

		products.setOnAction(e -> app.goProductsManage());
		inventory.setOnAction(e -> app.goInventoryManage());
		offers.setOnAction(e -> app.goOffersManage());
		reports.setOnAction(e -> app.goEmployeeReports());
		logout.setOnAction(e -> app.goWelcome());

		GridPane grid = new GridPane();
		grid.setHgap(18);
		grid.setVgap(18);
		grid.setAlignment(Pos.CENTER);

		grid.add(products, 0, 0);
		grid.add(inventory, 1, 0);
		grid.add(offers, 0, 1);
		grid.add(reports, 1, 1);
		grid.add(logout, 0, 2, 2, 1);

		setCenter(new javafx.scene.layout.VBox(20, hello, grid));
		((javafx.scene.layout.VBox) getCenter()).setAlignment(Pos.CENTER);
	}

}