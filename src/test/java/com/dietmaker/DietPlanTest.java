package com.dietmaker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Unit tests for the DietPlan class
 */
public class DietPlanTest {
    
    private DietPlan dietPlan;
    private Meal breakfastMeal;
    private Meal lunchMeal;
    private Meal dinnerMeal;
    private Meal snackMeal;
    
    @BeforeEach
    public void setUp() {
        dietPlan = new DietPlan("Test Diet", "A test diet plan");
        breakfastMeal = new Meal("Test Breakfast", "Breakfast description", 300, 15.0, 35.0, 10.0, "Breakfast");
        lunchMeal = new Meal("Test Lunch", "Lunch description", 450, 25.0, 50.0, 18.0, "Lunch");
        dinnerMeal = new Meal("Test Dinner", "Dinner description", 400, 22.0, 45.0, 16.0, "Dinner");
        snackMeal = new Meal("Test Snack", "Snack description", 150, 5.0, 20.0, 6.0, "Snack");
    }
    
    @Test
    public void testDietPlanCreation() {
        assertNotNull(dietPlan);
        assertEquals("Test Diet", dietPlan.getName());
        assertEquals("A test diet plan", dietPlan.getDescription());
        assertNotNull(dietPlan.getMealsByType());
        
        // Check that all meal type containers are initialized
        assertTrue(dietPlan.getMealsByType().containsKey("Breakfast"));
        assertTrue(dietPlan.getMealsByType().containsKey("Lunch"));
        assertTrue(dietPlan.getMealsByType().containsKey("Dinner"));
        assertTrue(dietPlan.getMealsByType().containsKey("Snack"));
    }
    
    @Test
    public void testAddMeal() {
        dietPlan.addMeal(breakfastMeal);
        List<Meal> breakfastMeals = dietPlan.getMealsByType("Breakfast");
        assertEquals(1, breakfastMeals.size());
        assertEquals(breakfastMeal, breakfastMeals.get(0));
        
        dietPlan.addMeal(lunchMeal);
        List<Meal> lunchMeals = dietPlan.getMealsByType("Lunch");
        assertEquals(1, lunchMeals.size());
        assertEquals(lunchMeal, lunchMeals.get(0));
    }
    
    @Test
    public void testGetMealsByType() {
        dietPlan.addMeal(breakfastMeal);
        dietPlan.addMeal(lunchMeal);
        dietPlan.addMeal(dinnerMeal);
        dietPlan.addMeal(snackMeal);
        
        List<Meal> breakfastMeals = dietPlan.getMealsByType("Breakfast");
        List<Meal> lunchMeals = dietPlan.getMealsByType("Lunch");
        List<Meal> dinnerMeals = dietPlan.getMealsByType("Dinner");
        List<Meal> snackMeals = dietPlan.getMealsByType("Snack");
        
        assertEquals(1, breakfastMeals.size());
        assertEquals(1, lunchMeals.size());
        assertEquals(1, dinnerMeals.size());
        assertEquals(1, snackMeals.size());
        
        assertEquals(breakfastMeal, breakfastMeals.get(0));
        assertEquals(lunchMeal, lunchMeals.get(0));
        assertEquals(dinnerMeal, dinnerMeals.get(0));
        assertEquals(snackMeal, snackMeals.get(0));
    }
    
    @Test
    public void testGetMealsByTypeEmpty() {
        List<Meal> emptyMeals = dietPlan.getMealsByType("Breakfast");
        assertNotNull(emptyMeals);
        assertEquals(0, emptyMeals.size());
        
        // Test with non-existent meal type
        List<Meal> nonExistentMeals = dietPlan.getMealsByType("NonExistent");
        assertNotNull(nonExistentMeals);
        assertEquals(0, nonExistentMeals.size());
    }
    
    @Test
    public void testGetAllMeals() {
        dietPlan.addMeal(breakfastMeal);
        dietPlan.addMeal(lunchMeal);
        dietPlan.addMeal(dinnerMeal);
        dietPlan.addMeal(snackMeal);
        
        List<Meal> allMeals = dietPlan.getAllMeals();
        assertEquals(4, allMeals.size());
        
        assertTrue(allMeals.contains(breakfastMeal));
        assertTrue(allMeals.contains(lunchMeal));
        assertTrue(allMeals.contains(dinnerMeal));
        assertTrue(allMeals.contains(snackMeal));
    }
    
    @Test
    public void testGetAllMealsEmpty() {
        List<Meal> allMeals = dietPlan.getAllMeals();
        assertNotNull(allMeals);
        assertEquals(0, allMeals.size());
    }
    
    @Test
    public void testVegetarianDietFactory() {
        DietPlan vegDiet = DietPlan.DietPlanFactory.createVegetarianDiet();
        
        assertNotNull(vegDiet);
        assertEquals("Vegetarian Diet", vegDiet.getName());
        assertEquals("Plant-based meals rich in nutrients and fiber", vegDiet.getDescription());
        
        // Check that each meal type has meals
        assertTrue(vegDiet.getMealsByType("Breakfast").size() > 0);
        assertTrue(vegDiet.getMealsByType("Lunch").size() > 0);
        assertTrue(vegDiet.getMealsByType("Dinner").size() > 0);
        assertTrue(vegDiet.getMealsByType("Snack").size() > 0);
        
        // Check that all meals are properly categorized
        List<Meal> breakfastMeals = vegDiet.getMealsByType("Breakfast");
        for (Meal meal : breakfastMeals) {
            assertEquals("Breakfast", meal.getMealType());
        }
    }
    
    @Test
    public void testNonVegetarianDietFactory() {
        DietPlan nonVegDiet = DietPlan.DietPlanFactory.createNonVegetarianDiet();
        
        assertNotNull(nonVegDiet);
        assertEquals("Non-Vegetarian Diet", nonVegDiet.getName());
        assertEquals("Protein-rich meals including lean meats and fish", nonVegDiet.getDescription());
        
        // Check that each meal type has meals
        assertTrue(nonVegDiet.getMealsByType("Breakfast").size() > 0);
        assertTrue(nonVegDiet.getMealsByType("Lunch").size() > 0);
        assertTrue(nonVegDiet.getMealsByType("Dinner").size() > 0);
        assertTrue(nonVegDiet.getMealsByType("Snack").size() > 0);
    }
    
    @Test
    public void testBalancedDietFactory() {
        DietPlan balancedDiet = DietPlan.DietPlanFactory.createBalancedDiet();
        
        assertNotNull(balancedDiet);
        assertEquals("Balanced Diet", balancedDiet.getName());
        assertEquals("Well-rounded meals with optimal macro-nutrient balance", balancedDiet.getDescription());
        
        // Check that each meal type has meals
        assertTrue(balancedDiet.getMealsByType("Breakfast").size() > 0);
        assertTrue(balancedDiet.getMealsByType("Lunch").size() > 0);
        assertTrue(balancedDiet.getMealsByType("Dinner").size() > 0);
        assertTrue(balancedDiet.getMealsByType("Snack").size() > 0);
    }
    
    @Test
    public void testMultipleMealsSameType() {
        Meal breakfast1 = new Meal("Breakfast 1", "First breakfast", 300, 15.0, 35.0, 10.0, "Breakfast");
        Meal breakfast2 = new Meal("Breakfast 2", "Second breakfast", 350, 18.0, 40.0, 12.0, "Breakfast");
        
        dietPlan.addMeal(breakfast1);
        dietPlan.addMeal(breakfast2);
        
        List<Meal> breakfastMeals = dietPlan.getMealsByType("Breakfast");
        assertEquals(2, breakfastMeals.size());
        assertTrue(breakfastMeals.contains(breakfast1));
        assertTrue(breakfastMeals.contains(breakfast2));
    }
}
