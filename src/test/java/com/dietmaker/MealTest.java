package com.dietmaker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Meal class
 */
public class MealTest {
    
    private Meal meal;
    
    @BeforeEach
    public void setUp() {
        meal = new Meal("Test Meal", "A delicious test meal", 350, 20.0, 40.0, 15.0, "Lunch");
    }
    
    @Test
    public void testMealCreation() {
        assertNotNull(meal);
        assertEquals("Test Meal", meal.getName());
        assertEquals("A delicious test meal", meal.getDescription());
        assertEquals(350, meal.getCalories());
        assertEquals(20.0, meal.getProtein(), 0.01);
        assertEquals(40.0, meal.getCarbs(), 0.01);
        assertEquals(15.0, meal.getFats(), 0.01);
        assertEquals("Lunch", meal.getMealType());
    }
    
    @Test
    public void testSettersAndGetters() {
        meal.setName("Updated Meal");
        assertEquals("Updated Meal", meal.getName());
        
        meal.setDescription("Updated description");
        assertEquals("Updated description", meal.getDescription());
        
        meal.setCalories(400);
        assertEquals(400, meal.getCalories());
        
        meal.setProtein(25.0);
        assertEquals(25.0, meal.getProtein(), 0.01);
        
        meal.setCarbs(50.0);
        assertEquals(50.0, meal.getCarbs(), 0.01);
        
        meal.setFats(18.0);
        assertEquals(18.0, meal.getFats(), 0.01);
        
        meal.setMealType("Dinner");
        assertEquals("Dinner", meal.getMealType());
    }
    
    @Test
    public void testToString() {
        String expected = "Test Meal (Lunch) - 350 cal, 20.0g protein, 40.0g carbs, 15.0g fats";
        assertEquals(expected, meal.toString());
    }
    
    @Test
    public void testEquals() {
        Meal sameMeal = new Meal("Test Meal", "Different description", 300, 15.0, 35.0, 12.0, "Lunch");
        Meal differentMeal = new Meal("Different Meal", "A delicious test meal", 350, 20.0, 40.0, 15.0, "Lunch");
        Meal differentMealType = new Meal("Test Meal", "A delicious test meal", 350, 20.0, 40.0, 15.0, "Dinner");
        
        assertEquals(meal, sameMeal); // Same name and meal type
        assertNotEquals(meal, differentMeal); // Different name
        assertNotEquals(meal, differentMealType); // Different meal type
        assertNotEquals(meal, null);
        assertNotEquals(meal, "Not a meal");
    }
    
    @Test
    public void testHashCode() {
        Meal sameMeal = new Meal("Test Meal", "Different description", 300, 15.0, 35.0, 12.0, "Lunch");
        assertEquals(meal.hashCode(), sameMeal.hashCode());
    }
    
    @Test
    public void testNegativeValues() {
        // Test that the meal can handle edge cases
        Meal edgeCaseMeal = new Meal("Edge Case", "Description", 0, 0.0, 0.0, 0.0, "Snack");
        assertEquals(0, edgeCaseMeal.getCalories());
        assertEquals(0.0, edgeCaseMeal.getProtein(), 0.01);
        assertEquals(0.0, edgeCaseMeal.getCarbs(), 0.01);
        assertEquals(0.0, edgeCaseMeal.getFats(), 0.01);
    }
}
