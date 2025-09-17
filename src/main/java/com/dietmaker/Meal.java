package com.dietmaker;

/**
 * Represents a meal with nutritional information
 */
public class Meal {
    private String name;
    private String description;
    private int calories;
    private double protein;    // in grams
    private double carbs;      // in grams
    private double fats;       // in grams
    private String mealType;   // breakfast, lunch, dinner, snack

    public Meal(String name, String description, int calories, double protein, double carbs, double fats, String mealType) {
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.mealType = mealType;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFats() {
        return fats;
    }

    public String getMealType() {
        return mealType;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %d cal, %.1fg protein, %.1fg carbs, %.1fg fats", 
                           name, mealType, calories, protein, carbs, fats);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Meal meal = (Meal) obj;
        return name.equals(meal.name) && mealType.equals(meal.mealType);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + mealType.hashCode();
    }
}
