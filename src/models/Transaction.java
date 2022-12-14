package models;

public class Transaction {
    private int id = 0;
    private String description;
    private int sellerId;
    private int buyerId;
    private int productId;

    // constructor to create a new transaction
    public Transaction(int id, String description, int sellerId, int buyerId, int productId) {
        this.id = id;
        this.description = description;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", sellerId=" + sellerId +
                ", buyerId=" + buyerId +
                ", productId=" + productId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
