package application;

public class InventoryRow {

    private int inventoryId;
    private String branchName;
    private String productName;
    private int quantity;

    public InventoryRow(int inventoryId, String branchName, String productName, int quantity) {
        this.inventoryId = inventoryId;
        this.branchName = branchName;
        this.productName = productName;
        this.quantity = quantity;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
}