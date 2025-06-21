
package com.example.buiduykien_2122110362;

public class CartItem {
    private String productId;
    private String name;
    private String price;
    private String imageUrl;
    private int quantity;

    public CartItem(String productId, String name, String price, String imageUrl, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    // Getter và Setter
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
