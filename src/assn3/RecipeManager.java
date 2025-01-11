/* File Name: RecipeManager.java
 * Author: Yimi Ding, 041106161
 * Course: 24F_CST8284_320 OOP
 * Assignment: Assignment3
 * Date: 12/2/2024
 * Professor: Moshiur Rahman
 * Purpose: Recipe Manager class to manage the list o f recipes, including load recipes 
 * from recipelist.txt file and save recipes to the shoppinglist.txt file.
 */
package assn3;

import java.io.*;
import java.util.*;

/**
/* Maintains the list of recipes and the way that the RecipeManagerTest gets things done.
 * 1. It will read recipes from recipelist.txt file.
 * 2. It has methods that access the list of Recipes in various ways .
 * and is the only class that RecipeManagertest should communicate with.
 * 3. It will also create a shopping list text file, "shoppinglist.txt", 
 * which contains all the ingredients the baker will need to purchase based on their orders.
 * 
 * @author Yimi Ding
 * @version 1.0
 */


public class RecipeManager {
	
	/** property: ArrayList for store list of recipes */
    private ArrayList<Recipe> recipes;
    
    /** constants  for relative path */
    private static final String recipeFilePath = "recipelist.txt";
    private static final String shoppingListFilePath = "shoppinglist.txt";
    
    /** constructor for creating a new list of recipes */
    public RecipeManager() {
        recipes = new ArrayList<>();
    }
    
    /**
     * get the list of all recipes
     * @return ArrayList that has all recipes
     */
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    /** Loads recipes from the recipe file and populates the recipes ArrayList */
    public void loadRecipes() {
    	// Scanner object for reading the recipe file
        Scanner fileScanner = null;
        
        try {
        	// 1. initialize scanner with recipe file
            fileScanner = new Scanner(new File(recipeFilePath));
            // Recipe object for the current recipe
            Recipe currentRecipe = null;
            
            // 2. continue reading while file has more lines
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                
                // create new Recipe object if line starts with "Recipe"
                if (line.startsWith("Recipe")) {
                    currentRecipe = new Recipe(line.substring(7).trim()); // extract recipe name
                    
                    // add new recipe to the Recipe ArrayList
                    recipes.add(currentRecipe);
                    
                } else if (line.length() > 0 && currentRecipe != null) { // if line is not empty and we have a current recipe
                    try {
                    	// 3. Scanner for parsing individual line
                        Scanner lineScanner = new Scanner(line);
                        String ingredient = lineScanner.next().toLowerCase();

                        double value = lineScanner.nextDouble(); // get ingredient amount
                        
                        // 4. set ingredient value
                        switch (ingredient) {
                            case "flour":
                            	currentRecipe.setFlour(value); 
                            	break;
                            case "sugar":
                            	currentRecipe.setSugar(value);
                            	break;
                            case "yeast":
                            	currentRecipe.setYeast(value);
                            	break;
                            case "butter":
                            	currentRecipe.setButter(value);
                            	break;
                            case "eggs":
                            	currentRecipe.setEggs(value);
                            	break;
                        }
                        lineScanner.close();
                    } catch (NoSuchElementException e) { // invalid ingredient line format
                        System.out.println("Error parsing ingredient line: " + line);
                    }
                }
            }
        } catch (FileNotFoundException e) { // missing recipe file
            System.out.println("Recipe file not found: " + recipeFilePath);
        } finally {
            if (fileScanner != null) { // close the file if file scanner is not closed
                fileScanner.close();
            }
        }
    }
    
    
    
    
    /**
     * place an order for a specific quantity of a recipe
     * @param index Recipe index
     * @param quantityAdded added quantity of bread
     * @return true if order was successful, false if invalid index
     */
    public boolean orderRecipe(int index, int quantityAdded) {
        if (index < 1 || index > recipes.size()) { // validate recipe index
            return false;
        } else {  
        // add quantity for the recipe
        recipes.get(index - 1).addQuantity(quantityAdded);
        return true;
        }
    }

    
    
    /**
     * generates shopping list and optionally saves to file
     * @param save Whether to save list to file
     * @return generated shopping list as string
     */
    public String generateShoppingList(boolean save) {
    	// 1. StringBuilder for constructing shopping list
        StringBuilder list = new StringBuilder();
        // variables to track total ingredients needed
        double totalFlour = 0, totalSugar = 0, totalYeast = 0, totalButter = 0, totalEggs = 0;
        
        // 2. calculate total for each ordered recipe
        for (Recipe recipe : recipes) {
            	// add recipe to shopping list
        	if (recipe.getQuantity() > 0) {
                list.append(recipe.getQuantity())
                .append(" ")
                .append(recipe.getName())
                .append(" loaf/loaves.\n");
                
                // calculate total of each ingredient
                totalFlour += recipe.getFlour() * recipe.getQuantity();
                totalSugar += recipe.getSugar() * recipe.getQuantity();
                totalYeast += recipe.getYeast() * recipe.getQuantity();
                totalButter += recipe.getButter() * recipe.getQuantity();
                totalEggs += recipe.getEggs() * recipe.getQuantity();
        	}
        }
        
        // 3. add total of each ingredient to the shopping list
        list.append("\nYou will need a total of:\n");
        if (totalYeast > 0) list.append((int)totalYeast).append(" grams of yeast\n");
        if (totalFlour > 0) list.append((int)totalFlour).append(" grams of flour\n");
        if (totalSugar > 0) list.append((int)totalSugar).append(" grams of sugar\n");
        if (totalEggs > 0) list.append((int)totalEggs).append(" egg(s)\n");
        if (totalButter > 0) list.append((int)totalButter).append(" grams of butter\n");
        
        // 4. save to file if requested
        if (save) {
            PrintWriter writer = null; // declare a character output object
            try {
                writer = new PrintWriter(shoppingListFilePath);
                writer.print(list.toString()); // save list.toString() to file
                
                System.out.println("Shopping list has been saved to " + shoppingListFilePath);
                System.out.println();
                
            } catch (FileNotFoundException e) {
                System.out.println("Error saving shopping list: " + e.getMessage());
            } finally {
                if (writer != null) { // ensure the recipes are written to the file
                    writer.close();
                }
            }
        }

        return list.toString();
    }
}