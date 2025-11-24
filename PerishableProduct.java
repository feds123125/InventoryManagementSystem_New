
public class PerishableProduct extends Product{

    // Attributes

    private String expiryDate;

    //CONSTRUCTORS

    //default
    public PerishableProduct(){
        super(); // calls parent (Product) for its constructors
        this.expiryDate="N/A"; // adds new attributes
    }

    //parameterised
    public PerishableProduct(int productId, String productName, double price, int quantity, String expiryDate){
        super(productId, productName, price, quantity);
        this.expiryDate = expiryDate;
    }


    // Getters and Setters

    public String getExpiryDate(){
        return expiryDate;
    }
    public void setExpiryDate(String expiryDate){
        this.expiryDate = expiryDate;
    }


    // overriding

    public void displayProductInfo(){
        super.displayProductInfo(); // calls the parent class
        System.out.println("Expiry Date: " + this.expiryDate); // adds our line
    }

}

