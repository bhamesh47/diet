package com.dietmaker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for the main JavaFX interface
 * Handles user interactions and diet selection logic
 */
public class MainController implements Initializable {
    
    @FXML private Button vegetarianBtn;
    @FXML private Button nonVegetarianBtn;
    @FXML private Button balancedBtn;
    
    @FXML private VBox selectedDietSection;
    @FXML private Label selectedDietTitle;
    @FXML private Label selectedDietDescription;
    
    @FXML private VBox mealCategoriesSection;
    @FXML private VBox breakfastMeals;
    @FXML private VBox lunchMeals;
    @FXML private VBox dinnerMeals;
    @FXML private VBox snackMeals;
    
    private DietPlan currentDietPlan;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the controller
        // Set up any default values or event handlers here if needed
        resetButtonStyles();
    }
    
    /**
     * Handle vegetarian diet selection
     */
    @FXML
    private void selectVegetarianDiet() {
        currentDietPlan = DietPlan.DietPlanFactory.createVegetarianDiet();
        displaySelectedDiet();
        updateButtonStyles(vegetarianBtn);
        populateMealCategories();
    }
    
    /**
     * Handle non-vegetarian diet selection
     */
    @FXML
    private void selectNonVegetarianDiet() {
        currentDietPlan = DietPlan.DietPlanFactory.createNonVegetarianDiet();
        displaySelectedDiet();
        updateButtonStyles(nonVegetarianBtn);
        populateMealCategories();
    }
    
    /**
     * Handle balanced diet selection
     */
    @FXML
    private void selectBalancedDiet() {
        currentDietPlan = DietPlan.DietPlanFactory.createBalancedDiet();
        displaySelectedDiet();
        updateButtonStyles(balancedBtn);
        populateMealCategories();
    }
    
    /**
     * Display the selected diet plan information
     */
    private void displaySelectedDiet() {
        if (currentDietPlan != null) {
            selectedDietTitle.setText("Selected Diet Plan: " + currentDietPlan.getName());
            selectedDietDescription.setText(currentDietPlan.getDescription());
            selectedDietSection.setVisible(true);
            mealCategoriesSection.setVisible(true);
        }
    }
    
    /**
     * Populate meal categories with the selected diet plan meals
     */
    private void populateMealCategories() {
        if (currentDietPlan == null) return;
        
        // Clear existing meals
        breakfastMeals.getChildren().clear();
        lunchMeals.getChildren().clear();
        dinnerMeals.getChildren().clear();
        snackMeals.getChildren().clear();
        
        // Populate breakfast meals
        populateMealSection(breakfastMeals, currentDietPlan.getMealsByType("Breakfast"));
        
        // Populate lunch meals
        populateMealSection(lunchMeals, currentDietPlan.getMealsByType("Lunch"));
        
        // Populate dinner meals
        populateMealSection(dinnerMeals, currentDietPlan.getMealsByType("Dinner"));
        
        // Populate snack meals
        populateMealSection(snackMeals, currentDietPlan.getMealsByType("Snack"));
    }
    
    /**
     * Populate a specific meal section with meals
     */
    private void populateMealSection(VBox mealContainer, List<Meal> meals) {
        for (Meal meal : meals) {
            VBox mealItem = createMealItem(meal);
            mealContainer.getChildren().add(mealItem);
        }
    }
    
    /**
     * Create a UI component for a meal item
     */
    private VBox createMealItem(Meal meal) {
        VBox mealBox = new VBox();
        mealBox.setSpacing(3);
        mealBox.getStyleClass().add("meal-item");
        
        // Meal name
        Label nameLabel = new Label(meal.getName());
        nameLabel.getStyleClass().add("meal-name");
        
        // Meal description
        Label descriptionLabel = new Label(meal.getDescription());
        descriptionLabel.getStyleClass().add("meal-description");
        descriptionLabel.setWrapText(true);
        
        // Nutritional information
        String nutritionInfo = String.format("Calories: %d | Protein: %.1fg | Carbs: %.1fg | Fats: %.1fg",
                meal.getCalories(), meal.getProtein(), meal.getCarbs(), meal.getFats());
        Label nutritionLabel = new Label(nutritionInfo);
        nutritionLabel.getStyleClass().add("meal-nutrition");
        
        mealBox.getChildren().addAll(nameLabel, descriptionLabel, nutritionLabel);
        
        // Add some margin
        VBox.setMargin(mealBox, new Insets(5, 0, 5, 0));
        
        return mealBox;
    }
    
    /**
     * Update button styles to show which diet is selected
     */
    private void updateButtonStyles(Button selectedButton) {
        resetButtonStyles();
        selectedButton.getStyleClass().add("selected-diet-btn");
    }
    
    /**
     * Reset all button styles to default
     */
    private void resetButtonStyles() {
        vegetarianBtn.getStyleClass().removeAll("selected-diet-btn");
        nonVegetarianBtn.getStyleClass().removeAll("selected-diet-btn");
        balancedBtn.getStyleClass().removeAll("selected-diet-btn");
    }
}
