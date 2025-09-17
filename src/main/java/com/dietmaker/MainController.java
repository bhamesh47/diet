package com.dietmaker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Spring MVC Controller for the Diet Maker web application
 * Handles HTTP requests and renders Thymeleaf templates
 */
@Controller
public class MainController {

    /**
     * Home page - displays diet selection options
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    /**
     * Display specific diet plan with meals
     */
    @GetMapping("/diet/{type}")
    public String getDiet(@PathVariable String type, Model model) {
        DietPlan dietPlan;
        
        switch(type.toLowerCase()) {
            case "vegetarian":
                dietPlan = DietPlan.DietPlanFactory.createVegetarianDiet();
                break;
            case "non-vegetarian":
                dietPlan = DietPlan.DietPlanFactory.createNonVegetarianDiet();
                break;
            case "balanced":
                dietPlan = DietPlan.DietPlanFactory.createBalancedDiet();
                break;
            default:
                return "redirect:/";
        }
        
        // Add diet plan and meals to model for Thymeleaf template
        model.addAttribute("dietPlan", dietPlan);
        model.addAttribute("dietType", type);
        model.addAttribute("breakfastMeals", dietPlan.getMealsByType("Breakfast"));
        model.addAttribute("lunchMeals", dietPlan.getMealsByType("Lunch"));
        model.addAttribute("dinnerMeals", dietPlan.getMealsByType("Dinner"));
        model.addAttribute("snackMeals", dietPlan.getMealsByType("Snack"));
        
        return "diet-plan";
    }
}
