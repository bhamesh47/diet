package com.dietmaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a diet plan with meals organized by categories and meal types
 */
public class DietPlan {
    private String name;
    private String description;
    private Map<String, List<Meal>> mealsByType; // breakfast, lunch, dinner, snack

    public DietPlan(String name, String description) {
        this.name = name;
        this.description = description;
        this.mealsByType = new HashMap<>();
        this.mealsByType.put("Breakfast", new ArrayList<>());
        this.mealsByType.put("Lunch", new ArrayList<>());
        this.mealsByType.put("Dinner", new ArrayList<>());
        this.mealsByType.put("Snack", new ArrayList<>());
    }

    public void addMeal(Meal meal) {
        String mealType = meal.getMealType();
        mealsByType.get(mealType).add(meal);
    }

    public List<Meal> getMealsByType(String mealType) {
        return mealsByType.getOrDefault(mealType, new ArrayList<>());
    }

    public List<Meal> getAllMeals() {
        List<Meal> allMeals = new ArrayList<>();
        for (List<Meal> meals : mealsByType.values()) {
            allMeals.addAll(meals);
        }
        return allMeals;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, List<Meal>> getMealsByType() {
        return mealsByType;
    }

    /**
     * Creates predefined diet plans with sample meals
     */
    public static class DietPlanFactory {
        
        public static DietPlan createVegetarianDiet() {
            DietPlan vegDiet = new DietPlan("Vegetarian Diet", "Plant-based meals rich in nutrients and fiber");
            
            // Breakfast meals
            vegDiet.addMeal(new Meal("Oatmeal with Berries", "Steel-cut oats topped with fresh blueberries and almonds", 
                                   320, 12.0, 58.0, 8.0, "Breakfast"));
            vegDiet.addMeal(new Meal("Avocado Toast", "Whole grain bread with mashed avocado, tomato, and seeds", 
                                   280, 8.0, 35.0, 15.0, "Breakfast"));
            vegDiet.addMeal(new Meal("Greek Yogurt Parfait", "Greek yogurt layered with granola and fresh fruits", 
                                   250, 15.0, 30.0, 8.0, "Breakfast"));
            
            // Lunch meals
            vegDiet.addMeal(new Meal("Quinoa Buddha Bowl", "Quinoa with roasted vegetables, chickpeas, and tahini dressing", 
                                   450, 18.0, 65.0, 14.0, "Lunch"));
            vegDiet.addMeal(new Meal("Caprese Salad", "Fresh mozzarella, tomatoes, and basil with balsamic glaze", 
                                   320, 16.0, 12.0, 24.0, "Lunch"));
            vegDiet.addMeal(new Meal("Vegetable Wrap", "Hummus wrap with fresh vegetables and sprouts", 
                                   380, 12.0, 48.0, 16.0, "Lunch"));
            
            // Dinner meals
            vegDiet.addMeal(new Meal("Lentil Curry", "Red lentils cooked in aromatic spices with rice", 
                                   420, 20.0, 68.0, 6.0, "Dinner"));
            vegDiet.addMeal(new Meal("Eggplant Parmesan", "Baked eggplant layers with marinara and cheese", 
                                   380, 18.0, 32.0, 22.0, "Dinner"));
            vegDiet.addMeal(new Meal("Stuffed Bell Peppers", "Bell peppers stuffed with rice, vegetables, and herbs", 
                                   310, 12.0, 52.0, 8.0, "Dinner"));
            
            // Snacks
            vegDiet.addMeal(new Meal("Mixed Nuts", "Almonds, walnuts, and cashews", 
                                   170, 6.0, 6.0, 15.0, "Snack"));
            vegDiet.addMeal(new Meal("Apple with Peanut Butter", "Fresh apple slices with natural peanut butter", 
                                   190, 7.0, 20.0, 12.0, "Snack"));
            
            return vegDiet;
        }
        
        public static DietPlan createNonVegetarianDiet() {
            DietPlan nonVegDiet = new DietPlan("Non-Vegetarian Diet", "Protein-rich meals including lean meats and fish");
            
            // Breakfast meals
            nonVegDiet.addMeal(new Meal("Scrambled Eggs with Toast", "Scrambled eggs with whole grain toast and avocado", 
                                      350, 20.0, 28.0, 18.0, "Breakfast"));
            nonVegDiet.addMeal(new Meal("Protein Smoothie", "Whey protein with banana, berries, and almond milk", 
                                      280, 25.0, 32.0, 5.0, "Breakfast"));
            nonVegDiet.addMeal(new Meal("Turkey Sausage Breakfast", "Turkey sausage with sweet potato hash", 
                                      320, 22.0, 25.0, 15.0, "Breakfast"));
            
            // Lunch meals
            nonVegDiet.addMeal(new Meal("Grilled Chicken Salad", "Mixed greens with grilled chicken breast and vinaigrette", 
                                      380, 35.0, 12.0, 22.0, "Lunch"));
            nonVegDiet.addMeal(new Meal("Salmon Bowl", "Grilled salmon with quinoa and steamed broccoli", 
                                      450, 32.0, 35.0, 20.0, "Lunch"));
            nonVegDiet.addMeal(new Meal("Turkey Club Sandwich", "Lean turkey with lettuce, tomato on whole grain bread", 
                                      420, 28.0, 42.0, 16.0, "Lunch"));
            
            // Dinner meals
            nonVegDiet.addMeal(new Meal("Beef Stir Fry", "Lean beef with mixed vegetables and brown rice", 
                                      480, 30.0, 45.0, 18.0, "Dinner"));
            nonVegDiet.addMeal(new Meal("Baked Cod", "Herb-crusted cod with roasted vegetables", 
                                      320, 28.0, 15.0, 12.0, "Dinner"));
            nonVegDiet.addMeal(new Meal("Chicken Curry", "Tender chicken in coconut curry sauce with rice", 
                                      420, 32.0, 38.0, 16.0, "Dinner"));
            
            // Snacks
            nonVegDiet.addMeal(new Meal("Protein Bar", "Whey protein bar with nuts and dried fruits", 
                                      200, 20.0, 18.0, 8.0, "Snack"));
            nonVegDiet.addMeal(new Meal("Hard-Boiled Eggs", "Two hard-boiled eggs with a pinch of salt", 
                                      140, 12.0, 1.0, 10.0, "Snack"));
            
            return nonVegDiet;
        }
        
        public static DietPlan createBalancedDiet() {
            DietPlan balancedDiet = new DietPlan("Balanced Diet", "Well-rounded meals with optimal macro-nutrient balance");
            
            // Breakfast meals
            balancedDiet.addMeal(new Meal("Whole Grain Pancakes", "Whole wheat pancakes with Greek yogurt and berries", 
                                        320, 14.0, 48.0, 10.0, "Breakfast"));
            balancedDiet.addMeal(new Meal("Egg and Veggie Omelet", "Two-egg omelet with spinach, mushrooms, and cheese", 
                                        290, 18.0, 8.0, 20.0, "Breakfast"));
            balancedDiet.addMeal(new Meal("Smoothie Bowl", "Acai smoothie with granola, nuts, and fresh fruits", 
                                        340, 12.0, 52.0, 12.0, "Breakfast"));
            
            // Lunch meals
            balancedDiet.addMeal(new Meal("Mediterranean Bowl", "Quinoa with grilled chicken, vegetables, and feta cheese", 
                                        430, 28.0, 42.0, 18.0, "Lunch"));
            balancedDiet.addMeal(new Meal("Tuna Salad Wrap", "Tuna salad with vegetables in a spinach wrap", 
                                        360, 24.0, 28.0, 16.0, "Lunch"));
            balancedDiet.addMeal(new Meal("Vegetable Soup with Bread", "Mixed vegetable soup with whole grain bread", 
                                        280, 12.0, 48.0, 8.0, "Lunch"));
            
            // Dinner meals
            balancedDiet.addMeal(new Meal("Grilled Fish with Quinoa", "Grilled tilapia with quinoa and roasted asparagus", 
                                        380, 30.0, 32.0, 14.0, "Dinner"));
            balancedDiet.addMeal(new Meal("Chicken and Rice Bowl", "Teriyaki chicken with brown rice and steamed vegetables", 
                                        410, 28.0, 48.0, 12.0, "Dinner"));
            balancedDiet.addMeal(new Meal("Pasta Primavera", "Whole wheat pasta with seasonal vegetables and olive oil", 
                                        350, 14.0, 58.0, 10.0, "Dinner"));
            
            // Snacks
            balancedDiet.addMeal(new Meal("Greek Yogurt with Honey", "Plain Greek yogurt drizzled with honey and nuts", 
                                        150, 12.0, 15.0, 6.0, "Snack"));
            balancedDiet.addMeal(new Meal("Vegetable Sticks with Hummus", "Carrot and celery sticks with hummus dip", 
                                        120, 5.0, 12.0, 6.0, "Snack"));
            
            return balancedDiet;
        }
    }
}
