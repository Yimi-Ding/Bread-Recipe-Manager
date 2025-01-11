/* File Name: RecipeManagerTest.java
 * Author: Yimi Ding, 041106161
 * Course: 24F_CST8284_320 OOP
 * Assignment: Assignment3
 * Date: 12/2/2024
 * Professor: Moshiur Rahman
 * Purpose: Driver class for RecipeManager class.
 */
package assn3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * the driver class which will generate all the menus, read input from the user, 
 * and make calls to RecipeManager.
 * @author Yimi Ding
 * @version 1.0
 */


public class RecipeManagerTest {
	// declare user input scanner and an instance of RecipeManager
    private static Scanner scanner;
    private static RecipeManager manager;
    

    /**
     * The main method serves as the entry point for the RecipeManagerTest program.
     * It initializes necessary resources, displays a menu for user interaction, 
     * and handles user input to perform various actions such as displaying recipes, 
     * creating a shopping list, and printing the shopping list.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
    	// initialize scanner for user input
        scanner = new Scanner(System.in);
        // create recipe manager instance
        manager = new RecipeManager();
        
        // load recipes from file
        manager.loadRecipes();
        
        System.out.println("Welcome to Yimi Ding Recipe Manager");
        
        // continuously display the menu and handle user actions until they choose to quit
        while (true) {
            try {
                // show menu
                printMenu();
                
            	// get user menu selection
                System.out.print("Please enter your choice: ");
                String input = scanner.nextLine();
                int choice = Integer.parseInt(input); // convert user input to an integer
                
                // process menu selection
                switch (choice) {
                    case 0:
                    	printMenu(); 
                    	break;
                    case 1:
                    	showRecipes(); 
                    	break;
                    case 2:
                        handleBreadOrder();
                        break;
                    case 3:
                    	printShoppingList(); 
                    	break;
                    case 4:
                        System.out.println("Goodbye!");
                        scanner.close();
                        return;
                    default: // if user type others instead of option choose
                        System.out.println("Valid input are digits from 0 to 4.");
                }
            } catch (NumberFormatException e) { // non-numeric input error
                System.out.println("Please only type digits.");
                System.out.println("Valid input are digits from 0 to 4.");
            }
        }
    }

    
   
    /** option 0: show menu options */
    private static void printMenu() {
        System.out.println("Please select one of the following options:");
        System.out.println("1. Show available recipes.");
        System.out.println("2. Create Shopping List.");
        System.out.println("3. Print Shopping List.");
        System.out.println("4. Quit Program.");
        System.out.println("0. to reprint this menu.");
    }

    /** option 1: displays all available recipes with their index numbers */ 
    private static void showRecipes() {
        System.out.println("\nAvailable Recipes:");
        ArrayList<Recipe> recipes = manager.getRecipes();
        for (int i = 0; i < recipes.size(); i++) { // loop through recipe list asnd print each
            System.out.println((i + 1) + ". " + recipes.get(i).getName());
        }
        System.out.println();
    }
    
    
    
    /**
     * processes an order for a specified bread type and quantity
     * @param breadNumber The bread type number
     * @param quantity The quantity of the bread to order
     */
    private static void createShoppingList(int breadNumber, int quantity) {
        if (!manager.orderRecipe(breadNumber, quantity)) {
            System.out.println("Failed to order bread. Please try again.");
        }
    }
    
    /**
     * option 2: handles the process of ordering bread by prompting the user to select a bread 
     * type and quantity.
     * 
     * Steps:
	 * 1. Prompts the user to select a bread type by entering its index.
	 * 2. Validates the bread type index to ensure it corresponds to a valid recipe.
	 * 3. Prompts the user to specify the quantity of the selected bread.
	 * 4. Validates the quantity to ensure it is a positive integer.
	 * 5. Passes the selected bread number and quantity to the `createShoppingList` method.
	 */
    private static void handleBreadOrder() {
        int breadNumber;
        do {
            System.out.print("Which bread would you like? "); // bread type selection
            String breadType = scanner.nextLine();

            try {
                breadNumber = Integer.parseInt(breadType); 
                // validate bread type number input
                if (breadNumber < 1 || breadNumber > manager.getRecipes().size()) {
                    System.out.println("Valid input are digits from 0 to 7.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) { // non-number error
                System.out.println("Please only type digits.");
            }
        } while (true);

        int quantity; // bread quantity
        do {
            System.out.print("How much of this bread would you like? ");
            String quantityInput = scanner.nextLine();

            try {
                quantity = Integer.parseInt(quantityInput);
                break;

            } catch (NumberFormatException e) { // non-number error
                System.out.println("Please only type digits.");
            }
        } while (true);
        
        // process order
        createShoppingList(breadNumber, quantity);
    }
    
    /**
     * option 3: displays shopping list and handles saving to file.
     * asks user if they want to save the list.
     */
    private static void printShoppingList() {
    	String list = manager.generateShoppingList(false);  // set saving file : false
    	System.out.println("\n" + list);
    	 
    	// ask user if they want to save the current shopping list
    	System.out.print("Do you want to save this list (Y/n)? ");
	    String response = scanner.nextLine().trim().toLowerCase();
	    boolean save = response.equals("y");
        
    	if (save) { // if user chooses yes
    		manager.generateShoppingList(true);
    		}
    }
}