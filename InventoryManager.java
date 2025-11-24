import javax.swing.*;
import java.awt.*;

public class InventoryManager {
    public static Product[] products = new Product[50];
    public static PerishableProduct[] perishableProducts = new PerishableProduct[50];

    public static String AddProduct(Product obj){
        for(int i = 0; i <= products.length - 1; i++){
            if (products[i] == null){
                products[i] = obj;
                return "product added successfully";
            }
        }

        return "Array is full.";
    }

    public static String AddPerishableProduct(PerishableProduct obj){
        for(int i = 0; i <= perishableProducts.length - 1; i++){
            if (perishableProducts[i] == null){
                perishableProducts[i] = obj;
                return "product added successfully";
            }
        }

        return "Array is full.";
    }

    public static void viewAll(){
        JFrame frame = new JFrame("Inventory (Non-perishable Products)");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Define column names
        String[] columnNames = {"#","Product ID", "Product Name", "Price ($)", "Quantity"};

        // Define table data
        Object[][] data = new Object[50][5];

        for (int i = 0; i <= products.length - 1; i++){
            data[i][0] = i + 1;
            data[i][1] = products[i] == null ? null : products[i].getProductId();
            data[i][2] = products[i] == null ? null : products[i].getProductName();
            data[i][3] = products[i] == null ? null : products[i].getPrice();
            data[i][4] = products[i] == null ? null : products[i].getQuantity();
        }

        // Create a JTable with the data and column names
        JTable table = new JTable(data, columnNames);

        // Add the table to a JScrollPane for scrolling capabilities
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame's content pane
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Pack the frame and make it visible
        frame.pack();
        frame.setVisible(true);
    }

    public static void viewAllPerishable(){
        JFrame frame = new JFrame("Inventory (Perishable Products)");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Define column names
        String[] columnNames = {"#","Product ID", "Product Name", "Price ($)", "Quantity", "Expiry Date"};

        // Define table data
        Object[][] data = new Object[50][6];

        for (int i = 0; i <= perishableProducts.length - 1; i++){
            data[i][0] = i + 1;
            data[i][1] = perishableProducts[i] == null ? null : perishableProducts[i].getProductId();
            data[i][2] = perishableProducts[i] == null ? null : perishableProducts[i].getProductName();
            data[i][3] = perishableProducts[i] == null ? null : perishableProducts[i].getPrice();
            data[i][4] = perishableProducts[i] == null ? null : perishableProducts[i].getQuantity();
            data[i][5] = perishableProducts[i] == null ? null : perishableProducts[i].getExpiryDate();
        }

        // Create a JTable with the data and column names
        JTable table = new JTable(data, columnNames);

        // Add the table to a JScrollPane for scrolling capabilities
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame's content pane
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Pack the frame and make it visible
        frame.pack();
        frame.setVisible(true);
    }
    
    public static String updateProductName(int productId, String productName){
        for(Product x:products){
            if (x!= null && x.getProductId() == productId){
                x.setProductName(productName);
                return "Product Updated";
            }
        }

        for (PerishableProduct y: perishableProducts){
            if (y != null && y.getProductId() == productId){
                y.setProductName(productName);
                return "Product Updated";
            }
        }

        return "Product does not exist";
    }

    public static String updateProductPrice(int productId, double price){
        for(Product x:products){
            if (x != null & x.getProductId() == productId){
                x.setPrice(price);
                return "Product Updated";
            }
        }

        for(PerishableProduct y: perishableProducts){
            if(y != null && y.getProductId() == productId){
                y.setPrice(price);
                return "Product Updated";
            }
        }

        return "Product does not exist";
    }

    public static String updateProductQuantity(int productId, int quantity){
        for(Product x:products){
            if (x.getProductId() == productId){
                x.setQuantity(quantity);
                return "Product Updated";
            }
        }

        for(PerishableProduct y:perishableProducts){
            if (y != null && y.getProductId() == productId){
                y.setQuantity(quantity);
                return "Product Updated";
            }
        }

        return "Product does not exist";
    }

    public static String updateProductExpiry(int productId, String expiry){
        for(PerishableProduct x:perishableProducts){
            if (x != null && x.getProductId() == productId){
                x.setExpiryDate(expiry);
                return "Product Updated";
            }
        }

        return "Product does not exist";
    }

    public static void deleteProduct(int productId){
        for(int i = 0; i <= products.length - 1; i++){
            if (products[i] != null && products[i].getProductId() == productId){
                products[i] = null;
                return;
            }
        }

        for(int i = 0; i <= perishableProducts.length - 1; i++){
            if (perishableProducts[i] != null && perishableProducts[i].getProductId() == productId){
                perishableProducts[i] = null;
                return;
            }
        }
    }

    public static boolean productExists(int productId){
        for(int i = 0; i <= products.length - 1; i++){
            if (products[i] != null && products[i].getProductId() == productId){
                return true;
            }
        }

        for(int i = 0; i <= perishableProducts.length - 1; i++){
            if (perishableProducts[i] != null && perishableProducts[i].getProductId() == productId){
                return true;
            }
        }

        return false;
    }

    public static void searchProduct(int productId){
        for(int i = 0; i <= products.length - 1; i++){
            if (products[i] != null && products[i].getProductId() == productId){
                products[i].displayProductInfo();
                return;
            }
        }

        for(int i = 0; i <= perishableProducts.length - 1; i++){
            if (perishableProducts[i] != null && perishableProducts[i].getProductId() == productId){
                perishableProducts[i].displayProductInfo();
                return;
            }
        }
    }
}