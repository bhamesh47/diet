package com.dietmaker;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user with personal information and diet preferences
 */
public class User {
    private String name;
    private int age;
    private double weight; // in kg
    private double height; // in cm
    private String activityLevel; // sedentary, lightly active, moderately active, very active
    private String dietPreference; // vegetarian, non-vegetarian, balanced
    private List<String> allergies;
    private List<Meal> favoriteMeals;
    private double dailyCalorieGoal;

    public User(String name, int age, double weight, double height, String activityLevel) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.activityLevel = activityLevel;
        this.allergies = new ArrayList<>();
        this.favoriteMeals = new ArrayList<>();
        this.dailyCalorieGoal = calculateDailyCalorieGoal();
    }

    /**
     * Calculate daily calorie goal using basic BMR formula
     * BMR (Basal Metabolic Rate) calculation using Mifflin-St Jeor Equation
     */
    private double calculateDailyCalorieGoal() {
        // Basic BMR calculation (this is a simplified version)
        double bmr = 10 * weight + 6.25 * height - 5 * age + 5; // for males (simplified)
        
        // Activity level multipliers
        double activityMultiplier = switch (activityLevel.toLowerCase()) {
            case "sedentary" -> 1.2;
            case "lightly active" -> 1.375;
            case "moderately active" -> 1.55;
            case "very active" -> 1.725;
            default -> 1.4;
        };
        
        return bmr * activityMultiplier;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public String getDietPreference() {
        return dietPreference;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public List<Meal> getFavoriteMeals() {
        return favoriteMeals;
    }

    public double getDailyCalorieGoal() {
        return dailyCalorieGoal;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
        this.dailyCalorieGoal = calculateDailyCalorieGoal(); // recalculate when age changes
    }

    public void setWeight(double weight) {
        this.weight = weight;
        this.dailyCalorieGoal = calculateDailyCalorieGoal(); // recalculate when weight changes
    }

    public void setHeight(double height) {
        this.height = height;
        this.dailyCalorieGoal = calculateDailyCalorieGoal(); // recalculate when height changes
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
        this.dailyCalorieGoal = calculateDailyCalorieGoal(); // recalculate when activity level changes
    }

    public void setDietPreference(String dietPreference) {
        this.dietPreference = dietPreference;
    }

    public void setDailyCalorieGoal(double dailyCalorieGoal) {
        this.dailyCalorieGoal = dailyCalorieGoal;
    }

    // Utility methods
    public void addAllergy(String allergy) {
        if (!allergies.contains(allergy)) {
            allergies.add(allergy);
        }
    }

    public void removeAllergy(String allergy) {
        allergies.remove(allergy);
    }

    public void addFavoriteMeal(Meal meal) {
        if (!favoriteMeals.contains(meal)) {
            favoriteMeals.add(meal);
        }
    }

    public void removeFavoriteMeal(Meal meal) {
        favoriteMeals.remove(meal);
    }

    /**
     * Calculate BMI (Body Mass Index)
     */
    public double calculateBMI() {
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);
    }

    /**
     * Get BMI category
     */
    public String getBMICategory() {
        double bmi = calculateBMI();
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    @Override
    public String toString() {
        return String.format("User: %s, Age: %d, Weight: %.1f kg, Height: %.1f cm, Activity: %s, BMI: %.1f (%s), Daily Calorie Goal: %.0f",
                           name, age, weight, height, activityLevel, calculateBMI(), getBMICategory(), dailyCalorieGoal);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
