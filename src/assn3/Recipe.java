/* File Name: Recipe.java
 * Author: Yimi Ding, 041106161
 * Course: 24F_CST8284_320 OOP
 * Assignment: Assignment3
 * Date: 12/2/2024
 * Professor: Moshiur Rahman
 * Purpose: Recipe class represents a single recipe.
 */
package assn3;

/**
 * A Recipe represents a bread recipe with its ingredients and quantity ordered.
 * This is a plain Java class that mainly stores data with getters and setters.
 * Each recipe contains ingredients measurements and tracks how many loaves are ordered.
 * 
 * @author Yimi Ding
 * @version 1.0
 */

public class Recipe {
	
	//properties of recipe, including name; the weight of flour, sugar, yeast, butter used, number of eggs; the quantity of recipe.
    private String name;
    private double flour, sugar, yeast, eggs, butter;
    private int quantity;

	/**
	 * Constructor
	 * @param name  The name of the recipe
	 */
    public Recipe(String name) {
        this.name = name;
        this.quantity = 0;
    }

	/**
	 * Getter for name property.
	 * @return The name of the recipe
	 */
    public String getName() {
        return this.name;
    }

	/**
	 * Setter for name property.
	 * @param name The name for the recipe
	 */
    public void setName(String name) {
        this.name = name;
    }
    
	/**
	 * Getter for flour property.
	 * @return flour The weight of flour
	 */
    public double getFlour() {
        return this.flour;
    }
    
	/**
	 * Setter for flour property.
	 * @param flour The weight of flour
	 */
    public void setFlour(double flour) {
        this.flour = flour;
    }

	/**
	 * Getter for sugar property.
	 * @return sugar The weight of sugar
	 */
    public double getSugar() {
        return this.sugar;
    }

	/**
	 * Setter for sugar property.
	 * @param sugar The weight of sugar
	 */
    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

	/**
	 * Getter for yeast property.
	 * @return yeast The weight of yeast
	 */
    public double getYeast() {
        return this.yeast;
    }
    
	/**
	 * Setter for yeast property.
	 * @param yeast The weight of yeast
	 */
    public void setYeast(double yeast) {
        this.yeast = yeast;
    }
    
	/**
	 * Getter for eggs property.
	 * @return eggs The number of eggs
	 */
    public double getEggs() {
        return this.eggs;
    }

	/**
	 * Setter for eggs property.
	 * @param eggs The number of eggs
	 */
    public void setEggs(double eggs) {
        this.eggs = eggs;
    }
    
	/**
	 * Getter for butter property.
	 * @return butter The weight of butter
	 */
    public double getButter() {
        return this.butter;
    }
    
	/**
	 * Setter for butter property.
	 * @param butter The weight of butter
	 */
    public void setButter(double butter) {
        this.butter = butter;
    }

	/**
	 * Getter for quantity property.
	 * @return quantity The quantity of recipe
	 */
    public int getQuantity() {
        return this.quantity;
    }

	/**
	 * Setter for quantity property.
	 * @param quantity The quantity of recipe
	 */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Adds a given amount to the current quantity of the recipe.
     * @param quantityToAdd The amount to add to the current quantity.
     */
    public void addQuantity(int quantityToAdd) {
    	if (this.quantity >= 0) {
            this.quantity += quantityToAdd;
    	} else {
    		System.out.println("Invalid total quantity.");
    	}
    }
    
}
