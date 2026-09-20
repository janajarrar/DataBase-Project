package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage stage;

	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		stage.setTitle("Friendship Supermarket - Welcome");
		goWelcome();
		stage.show();
	}

	public void goWelcome() {
		stage.setScene(new Scene(new WelcomeView(this), 980, 640));
	}

	public void goCustomerLogin() {
		stage.setTitle("Friendship Supermarket - Customer Login");
		stage.setScene(new Scene(new CustomerLoginView(this), 980, 640));
	}

	public void goCustomerRegister() {
		stage.setTitle("Friendship Supermarket - Create New Account");
		stage.setScene(new Scene(new CustomerRegisterView(this), 980, 640));
	}

	public void goEmployeeLogin() {
		stage.setTitle("Friendship Supermarket - Employee Portal");
		stage.setScene(new Scene(new EmployeeLoginView(this), 980, 640));
	}

	public void goEmployeeRegister() {
		stage.setTitle("Friendship Supermarket - Register Staff");
		stage.setScene(new Scene(new EmployeeRegisterView(this), 980, 640));
	}

	public void goCustomerDashboard(String customerName) {
		stage.setTitle("Customer Dashboard");
		stage.setScene(new Scene(new CustomerDashboardView(this, customerName), 980, 640));
	}

	public void goEmployeeDashboard(String employeeName) {
		stage.setTitle("Employee Dashboard");
		stage.setScene(new Scene(new EmployeeDashboardView(this, employeeName), 980, 640));
	}

	public void goProductsManage() {
		stage.setTitle("Manage Products");
		stage.setScene(new Scene(new ProductsManagementView(this), 980, 640));

	}

	public void goInventoryManage() {
		stage.setTitle("Manage Inventory");
		stage.setScene(new Scene(new InventoryManagementView(this), 980, 640));
	}

	public void goOffers() {
		stage.setTitle("Offers");
		stage.setScene(new Scene(new OffersView(), 980, 640));
	}

	public void goOffersManage() {
		stage.setTitle("Offers Management");
		stage.setScene(new Scene(new OffersManagementView(this), 980, 640));
	}

	public void goCustomerOffers(String customerName) {
		stage.setTitle("Available Offers");
		stage.setScene(new Scene(new CustomerOffersView(this, customerName), 980, 640));
	}

	public void goCustomerShop(String customerName) {
		stage.setTitle("Shop");
		stage.setScene(new Scene(new CustomerShopView(customerName), 980, 640));
	}

	public void goCustomerCategories(String customerName) {
		stage.setTitle("Categories");
		stage.setScene(new Scene(new CustomerCategoriesView(this, customerName), 980, 640));
	}

	public void goProductsByCategory(String customerName, String category) {
		stage.setTitle("Products - " + category);
		stage.setScene(new Scene(new ProductsByCategoryView(this, customerName, category), 980, 640));
	}

	public void goCustomerCart(String customerName) {
		stage.setTitle("Cart");
		stage.setScene(new Scene(new CustomerCartView(this, customerName), 980, 640));
	}

	public void goEmployeeReports() {
		stage.setTitle("Reports");
		stage.setScene(new Scene(new EmployeeReportsView(this), 980, 640));
	}

	public static void main(String[] args) {
		launch(args);
	}
}