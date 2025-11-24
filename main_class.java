import java.util.Scanner;
import java.time.LocalDate;

public class main_class {
    private static Scanner input_var = new Scanner(System.in);
    public static void main(String[] args){

        // Display greeting and display menu

        main_class.greeting();

        System.out.println();

        main_class.display_menu();

        System.out.println();

        // ask user to enter menu number between 1 - 6

        int menu_num = safeInt("Enter a menu number (1-6): ");

        System.out.println();

        // call the function based on selected menu option then repeat until 6 is entered

        while(true){

            if (menu_num == 1){
                main_class.AddProd();
            } else if (menu_num == 2){
                main_class.ViewAllProds();
            } else if (menu_num == 3){
                main_class.SearchProd();
            } else if(menu_num == 4){
                main_class.UpdateProds();
            } else if (menu_num == 5){
                main_class.DeleteProd();
            } else if (menu_num == 6){
                System.out.println("Thankyou for using this application!");
                return;
            } else {
                System.out.println("Invalid option. Please enter a number between 1 - 6.");
            }
            
            System.out.println();

            main_class.display_menu();

            System.out.println();

            menu_num = safeInt("Enter a menu number (1-6): ");

            System.out.println();
        }
    }

    public static void greeting(){
        System.out.print("Please Enter Your Name: ");

        String name = input_var.nextLine();

        System.out.println();

        System.out.println("Hello " + name + "! Welcome to your Inventory Manager");
        
        System.out.println();

        System.out.println("What would you like to do today?");
    }

    public static void display_menu(){
        System.out.println("1. Add a product");
        System.out.println("2. View All Products");
        System.out.println("3. Search for a product by its ID");
        System.out.println("4. Update a product");
        System.out.println("5. Delete a product");
        System.out.println("6. Exit"); 
    }

    public static void AddProd(){

        // get menu number : 1 for perishable, 2 for non - perishable and 3 to return to the main menu (exit function)
        // if num is not 1,2, or 3, keep asking user to enter number until valid
        // if num == 3, exit the function

        int num = safeInt("Enter 1 to add a perishable product, 2 to add a non-perishable product, or 3 to return to the main menu: ");

        while (num != 1 && num != 2 && num != 3){
            num = safeInt("Invalid option. Please enter either 1, 2 or 3: ");
        }

        if (num == 3){
            return;
        }

        // enter product ID, if product with same ID already exists throw exception and exit function
        // if not, enter other info
        // if num == 1, get and verify expiry date. If invalid show error message, else add perishable product
        // if num == 2, add product

        try {
            System.out.println();
            int prod_id = safeInt("Enter the Product ID: ");

            if (InventoryManager.productExists(prod_id)){
                throw new InvalidProductIdException("A product with this ID already exists.");
            } else {
                System.out.print("Enter the Product Name: ");
                String prod_name = input_var.nextLine();

                double prod_price = safeDouble("Enter the Product Price ($): ");

                int prod_quantity = safeInt("Enter the Quantity: ");

                if (num == 1){
                    System.out.print("Enter the Expiry Date (dd-mm-yyyy): ");
                    String expiry = input_var.nextLine();
                    System.out.println();

                    if(!(checkDateFormat(expiry).equals("success"))){
                        System.out.println(checkDateFormat(expiry));
                    } else {
                        System.out.println(InventoryManager.AddPerishableProduct(new PerishableProduct(prod_id, prod_name, prod_price, prod_quantity, expiry)));
                    }
                }
                else if(num == 2){
                    System.out.println();
                    System.out.println(InventoryManager.AddProduct(new Product(prod_id, prod_name, prod_price, prod_quantity)));
                }
            }
        }
        catch (InvalidProductIdException e){
            System.out.println(e.getMessage());
            System.out.println();
            return;
        }
    }

    public static void ViewAllProds(){

        // get menu number : 1 for perishable, 2 for non - perishable and 3 to return to the main menu (exit function)
        // if num is not 1,2, or 3, keep asking user to enter number until valid
        // if num == 3, exit the function. If num == 1, display perishable. If num == 2 display non-perishable

        int num = safeInt("Enter 1 to view all perishable products, 2 to view all non-perishable products, or 3 to return to the main menu: ");
        System.out.println();

        while (num != 1 && num != 2 && num != 3){
            num = safeInt("Incorrect Number. Enter 1, 2, or 3: ");
            System.out.println();
        }
        
        if(num == 1){
            InventoryManager.viewAllPerishable();
        }
        else if(num == 2){
            InventoryManager.viewAll();
        } else if (num == 3){
            return;
        }
    }

    public static void UpdateProds(){

        // get product ID. If product does not exist throw exception and exit function

        int prod_Id = safeInt("Enter the Product ID: ");

        try{
            if(!InventoryManager.productExists(prod_Id)){
                throw new InvalidProductIdException("Product does not exist");
            }
        } catch (InvalidProductIdException e){
            System.out.println();
            System.out.println(e.getMessage());
            return;
        }
    
        // Otherwise, print menu : 1 for prod name, 2 for price, 3 for quantity, 4 for expiry date, and 5 to return to main menu

        System.out.println();
        System.out.println("Enter:");
        System.out.println("1 to update Product Name");
        System.out.println("2 to update Product Price");
        System.out.println("3 to update Quantity");
        System.out.println("4 to update Expiry Date (if perishable product)");
        System.out.println("5 to return to the main menu");

        System.out.println();

        int num = safeInt("Enter a number 1 - 4 or 5 to return to the main menu: ");

        System.out.println();

        // based on menu options, update field. If invalid menu option, keep asking user to enter a valid option

        while(true){
            if (num == 1){
                System.out.print("Enter a new product name: ");
                String prod_name = input_var.nextLine();
                System.out.println(InventoryManager.updateProductName(prod_Id, prod_name));
                break;
            } 
            else if (num == 2){
                double prod_price = safeDouble("Enter a new product price: ");
                System.out.println(InventoryManager.updateProductPrice(prod_Id, prod_price));
                break;
            }
            else if (num == 3){
                System.out.print("Enter a new quantity: ");
                int prod_quantity = safeInt("Enter a new quantity: ");
                System.out.println(InventoryManager.updateProductQuantity(prod_Id, prod_quantity));
                break;
            }

            // if updating expiry date, check if expiry date is valid (checkDateFormate returns "success"). If not print error message
            // also check if product is perishable. If not throw exception

            else if (num == 4){
                System.out.print("Enter a new expiry date (dd-mm-yyyy): ");
                String prod_expiry = input_var.nextLine();
                System.out.println();

                if (!(checkDateFormat(prod_expiry).equals("success"))){
                    System.out.println(checkDateFormat(prod_expiry));
                } else {
                    try {
                        if (InventoryManager.updateProductExpiry(prod_Id, prod_expiry).equals("Product does not exist")){
                            throw new InvalidProductIdException("This product is not perishable. Cannot update expiry.");
                        } else {
                            System.out.println(InventoryManager.updateProductExpiry(prod_Id, prod_expiry));
                        }
                    } catch (InvalidProductIdException e){
                        System.out.println(e.getMessage());
                    }
                }

                break;
            }
            else if (num == 5){
                return;
            }
            else {
                num = safeInt("Invalid option. Please enter a number between 1 - 4 or 5 to return to the main menu: ");
                System.out.println();
            }
        }
    }

    public static void SearchProd(){

        // Get product ID, if exists display product else throw exception

        int prod_Id = safeInt("Enter the Product ID: ");
        
        System.out.println();

        try{
            if(InventoryManager.productExists(prod_Id)){      
                InventoryManager.searchProduct(prod_Id);
                return;
            } else{
                throw new InvalidProductIdException("Product does not exist");
            }
        } catch(InvalidProductIdException e){
            System.out.println(e.getMessage());
        }

        System.out.println();
    }
    
    public static void DeleteProd(){

        // get product ID, if exists delete product else throw exception

        int prod_Id = safeInt("Enter the Product ID: ");
        
        System.out.println();

        try{
            if(InventoryManager.productExists(prod_Id)){      
                InventoryManager.deleteProduct(prod_Id);
                System.out.println("Product Deleted Successfully");
            } else{
                throw new InvalidProductIdException("Product does not exist");
            }
        } catch(InvalidProductIdException e){
            System.out.println(e.getMessage());
        }

        System.out.println();

    }

    public static int safeInt(String message){

        // print message
        // take input as string and try to convert into int and return. catch NumberFormatException and print error message
        // keep repeating until value is returned

        System.out.print(message);

        while(true){
            try{
                return Integer.parseInt(input_var.nextLine());
            } catch (NumberFormatException e){
                System.out.print("Input is not an Integer. Try again: ");
            }
        }

    }

    public static double safeDouble(String message){

        // print message
        // take input as string and try to convert into double and return. catch NumberFormatException and print error message
        // keep repeating until value is returned

        System.out.print(message);

        while(true){
            try{
                return Double.parseDouble(input_var.nextLine());
            } catch (NumberFormatException e){
                System.out.print("Input is not a Double. Try again: ");
            }
        }
    }

    public static String checkDateFormat(String date){

        // get current date and split in d,m,y
        // split entered date into d,m,y

        String currentDate = LocalDate.now().toString();

        String[] date_split = date.split("-");
        String[] currentDateSplit = currentDate.split("-");

        int currentDay = Integer.parseInt(currentDateSplit[2]);
        int currentMonth = Integer.parseInt(currentDateSplit[1]);
        int currentYear = Integer.parseInt(currentDateSplit[0]);

        int day = Integer.parseInt(date_split[0]);
        int month = Integer.parseInt(date_split[1]);
        int year = Integer.parseInt(date_split[2]);

        // check date format :
        // y should be 4 digits
        // m should be 2 digits and between 1 - 12
        // d should be 2 digits and either between 1 - 31 or 1 - 30 depending on month
        // throw exception if any condition is not met which returns error message

        try{
            if (date_split[2].length() != 4){
                throw new incorrectDateException("Incorrect year format (must be in yyyy format)");
            } else if (month < 1 || month > 12 || date_split[1].length() != 2){
                throw new incorrectDateException("Incorrect Month. Must be between 1-12 and in mm format");
            } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ){
                if (day < 1 || day > 31 || date_split[0].length() != 2 ){
                    throw new incorrectDateException("Incorrect Day. Based on month, Must be between 1-31 and in dd format.");
                }
            } else if (month == 2 || month == 4 || month == 6 || month == 9 || month == 11){
                if (day < 1 || day > 30 || date_split[0].length() != 2 ){
                    throw new incorrectDateException("Incorrect Day. Based on month, Must be between 1-30 and in dd format.");
                }
            }

        // check year, month, day to ensure product is not expired
        // throw exception if any condition is not met which returns error message "Product Expired."

            if (year < currentYear){
                throw new incorrectDateException("Product Expired.");
            } else if (year == currentYear && month < currentMonth){
                throw new incorrectDateException("Product Expired.");
            } else if (year == currentYear && month == currentMonth && day < currentDay){
               throw new incorrectDateException("Product Expired.");
            }
        } catch(incorrectDateException e){
            return e.getMessage();
        }

        // if all conditions are met, return "success"

        return "success";
    }
}