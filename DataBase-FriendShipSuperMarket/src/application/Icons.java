package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class Icons {

	public static StackPane badge(String emoji, String bgHex) {
		Label icon = new Label(emoji);
		icon.setFont(Font.font(22));
		icon.setStyle("-fx-text-fill:white; -fx-font-weight:900;");

		StackPane box = new StackPane(icon);
		box.setPrefSize(44, 44);
		box.setAlignment(Pos.CENTER);
		box.setStyle("-fx-background-color:" + bgHex + ";" + "-fx-background-radius:14;");
		return box;
	}

	public static StackPane customer() {
		return badge("🧑‍🛒", "#16a34a");
	}

	public static StackPane employee() {
		return badge("🧑‍💼", "#2563eb");
	}

	public static StackPane logout() {
		return badge("🚪", "#ef4444");
	}

	public static StackPane back() {
		return badge("⬅", "#0b1f3a");
	}

	public static StackPane productsManage() {
		return badge("📦", "#0ea5e9");
	}

	public static StackPane inventoryManage() {
		return badge("🏬", "#f97316");
	}

	public static StackPane offersManage() {
		return badge("🏷️", "#a855f7");
	}

	public static StackPane reports() {
		return badge("📊", "#22c55e");
	}

	public static StackPane cart() {
		return badge("🛒", "#22c55e");
	}

	public static StackPane category(String category) {
		String c = (category == null) ? "" : category.trim().toLowerCase();

		if (c.contains("beverage") || c.contains("drink") || c.contains("juice") || c.contains("مشروب"))
			return badge("🥤", "#06b6d4");

		if (c.contains("dairy") || c.contains("milk") || c.contains("yogurt") || c.contains("ألبان")
				|| c.contains("مفرزات"))
			return badge("🥛", "#3b82f6");

		if (c.contains("bakery") || c.contains("bread") || c.contains("مخبوزات") || c.contains("خبز"))
			return badge("🥖", "#f59e0b");

		if (c.contains("frozen") || c.contains("مجمد"))
			return badge("🧊", "#60a5fa");

		if (c.contains("snack") || c.contains("chips") || c.contains("سناكس"))
			return badge("🍪", "#fb7185");

		if (c.contains("clean") || c.contains("soap") || c.contains("منظف") || c.contains("تنظيف"))
			return badge("🧼", "#22c55e");

		return badge("🧺", "#64748b");
	}
}