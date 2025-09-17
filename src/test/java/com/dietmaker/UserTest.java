package com.dietmaker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the User class
 */
public class UserTest {
    
    private User user;
    private Meal favoriteMeal;
    
    @BeforeEach
    public void setUp() {
        user = new User("John Doe", 30, 70.0, 175.0, "moderately active");
        favoriteMeal = new Meal("Favorite Meal", "A delicious meal", 400, 25.0, 45.0, 18.0, "Lunch");
    }
    
    @Test
    public void testUserCreation() {
        assertNotNull(user);
        assertEquals("John Doe", user.getName());
        assertEquals(30, user.getAge());
        assertEquals(70.0, user.getWeight(), 0.01);
        assertEquals(175.0, user.getHeight(), 0.01);
        assertEquals("moderately active", user.getActivityLevel());
        assertNotNull(user.getAllergies());
        assertNotNull(user.getFavoriteMeals());
        assertTrue(user.getDailyCalorieGoal() > 0);
    }
    
    @Test
    public void testSettersAndGetters() {
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName());
        
        user.setAge(25);
        assertEquals(25, user.getAge());
        
        user.setWeight(65.0);
        assertEquals(65.0, user.getWeight(), 0.01);
        
        user.setHeight(165.0);
        assertEquals(165.0, user.getHeight(), 0.01);
        
        user.setActivityLevel("very active");
        assertEquals("very active", user.getActivityLevel());
        
        user.setDietPreference("vegetarian");
        assertEquals("vegetarian", user.getDietPreference());
    }
    
    @Test
    public void testCalculateBMI() {
        // BMI = weight(kg) / height(m)^2
        // Expected BMI = 70 / (1.75)^2 = 70 / 3.0625 = 22.86
        double expectedBMI = 70.0 / ((175.0 / 100.0) * (175.0 / 100.0));
        assertEquals(expectedBMI, user.calculateBMI(), 0.01);
    }
    
    @Test
    public void testGetBMICategory() {
        // Test normal weight
        User normalUser = new User("Normal", 30, 70.0, 175.0, "moderately active");
        assertEquals("Normal weight", normalUser.getBMICategory());
        
        // Test underweight
        User underweightUser = new User("Under", 30, 50.0, 175.0, "moderately active");
        assertEquals("Underweight", underweightUser.getBMICategory());
        
        // Test overweight
        User overweightUser = new User("Over", 30, 85.0, 175.0, "moderately active");
        assertEquals("Overweight", overweightUser.getBMICategory());
        
        // Test obese
        User obeseUser = new User("Obese", 30, 100.0, 175.0, "moderately active");
        assertEquals("Obese", obeseUser.getBMICategory());
    }
    
    @Test
    public void testDailyCalorieGoalCalculation() {
        // Test different activity levels
        User sedentaryUser = new User("Sedentary", 30, 70.0, 175.0, "sedentary");
        User lightlyActiveUser = new User("Lightly Active", 30, 70.0, 175.0, "lightly active");
        User moderatelyActiveUser = new User("Moderately Active", 30, 70.0, 175.0, "moderately active");
        User veryActiveUser = new User("Very Active", 30, 70.0, 175.0, "very active");
        
        // Very active should have higher calorie goal than sedentary
        assertTrue(veryActiveUser.getDailyCalorieGoal() > sedentaryUser.getDailyCalorieGoal());
        assertTrue(moderatelyActiveUser.getDailyCalorieGoal() > lightlyActiveUser.getDailyCalorieGoal());
        assertTrue(lightlyActiveUser.getDailyCalorieGoal() > sedentaryUser.getDailyCalorieGoal());
    }
    
    @Test
    public void testDailyCalorieGoalRecalculation() {
        double initialGoal = user.getDailyCalorieGoal();
        
        // Change weight and check if calorie goal is recalculated
        user.setWeight(80.0);
        assertTrue(user.getDailyCalorieGoal() > initialGoal);
        
        // Change activity level and check if calorie goal is recalculated
        user.setActivityLevel("very active");
        double newGoal = user.getDailyCalorieGoal();
        assertTrue(newGoal > initialGoal);
        
        // Change age and check if calorie goal is recalculated
        user.setAge(40);
        assertTrue(user.getDailyCalorieGoal() < newGoal); // Older age should decrease calorie needs
    }
    
    @Test
    public void testAllergyManagement() {
        assertEquals(0, user.getAllergies().size());
        
        user.addAllergy("nuts");
        assertEquals(1, user.getAllergies().size());
        assertTrue(user.getAllergies().contains("nuts"));
        
        user.addAllergy("dairy");
        assertEquals(2, user.getAllergies().size());
        assertTrue(user.getAllergies().contains("dairy"));
        
        // Test adding duplicate allergy
        user.addAllergy("nuts");
        assertEquals(2, user.getAllergies().size()); // Should not add duplicate
        
        user.removeAllergy("nuts");
        assertEquals(1, user.getAllergies().size());
        assertFalse(user.getAllergies().contains("nuts"));
        assertTrue(user.getAllergies().contains("dairy"));
    }
    
    @Test
    public void testFavoriteMealManagement() {
        assertEquals(0, user.getFavoriteMeals().size());
        
        user.addFavoriteMeal(favoriteMeal);
        assertEquals(1, user.getFavoriteMeals().size());
        assertTrue(user.getFavoriteMeals().contains(favoriteMeal));
        
        Meal anotherMeal = new Meal("Another Meal", "Another description", 300, 20.0, 35.0, 12.0, "Dinner");
        user.addFavoriteMeal(anotherMeal);
        assertEquals(2, user.getFavoriteMeals().size());
        
        // Test adding duplicate favorite meal
        user.addFavoriteMeal(favoriteMeal);
        assertEquals(2, user.getFavoriteMeals().size()); // Should not add duplicate
        
        user.removeFavoriteMeal(favoriteMeal);
        assertEquals(1, user.getFavoriteMeals().size());
        assertFalse(user.getFavoriteMeals().contains(favoriteMeal));
        assertTrue(user.getFavoriteMeals().contains(anotherMeal));
    }
    
    @Test
    public void testToString() {
        String userString = user.toString();
        assertTrue(userString.contains("John Doe"));
        assertTrue(userString.contains("30"));
        assertTrue(userString.contains("70.0"));
        assertTrue(userString.contains("175.0"));
        assertTrue(userString.contains("moderately active"));
        assertTrue(userString.contains("BMI"));
        assertTrue(userString.contains("Daily Calorie Goal"));
    }
    
    @Test
    public void testEquals() {
        User sameNameUser = new User("John Doe", 35, 75.0, 180.0, "sedentary");
        User differentNameUser = new User("Jane Doe", 30, 70.0, 175.0, "moderately active");
        
        assertEquals(user, sameNameUser); // Same name
        assertNotEquals(user, differentNameUser); // Different name
        assertNotEquals(user, null);
        assertNotEquals(user, "Not a user");
    }
    
    @Test
    public void testHashCode() {
        User sameNameUser = new User("John Doe", 35, 75.0, 180.0, "sedentary");
        assertEquals(user.hashCode(), sameNameUser.hashCode());
    }
    
    @Test
    public void testDailyCalorieGoalSetter() {
        user.setDailyCalorieGoal(2500.0);
        assertEquals(2500.0, user.getDailyCalorieGoal(), 0.01);
    }
    
    @Test
    public void testActivityLevelCaseInsensitive() {
        User upperCaseUser = new User("Test", 30, 70.0, 175.0, "MODERATELY ACTIVE");
        User lowerCaseUser = new User("Test", 30, 70.0, 175.0, "moderately active");
        
        // Both should calculate the same calorie goal
        assertEquals(upperCaseUser.getDailyCalorieGoal(), lowerCaseUser.getDailyCalorieGoal(), 1.0);
    }
}
