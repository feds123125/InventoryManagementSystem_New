public class Product {

    // Attributes

    private int productId;
    private String productName;
    private double price;
    private int quantity;

    // Constructors

    // Without parameters

    public Product () {
        this.productId = 0000;
        this.productName = "N/A";
        this.price = 0.0;
        this.quantity = 0;
    }

    // With parameters

    public Product(int productId, String productName, double price, int quantity){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId){
        this.productId = productId;
    }

    public String getProductName(){
        return this.productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    // Methods

    public void displayProductInfo(){
        System.out.println("Product Name: " + this.productName);
        System.out.println("----------------------------");
        System.out.println("Product ID: " + this.productId);
        System.out.println("Price: " + this.price);
        System.out.println("Quantity: " + this.quantity);
    }
}