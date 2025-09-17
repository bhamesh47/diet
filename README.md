# Diet Maker 🥗

**Your Personal Diet Planning Assistant**

A modern JavaFX desktop application that helps users explore different diet plans and discover nutritious meal options. Choose from three carefully curated diet categories and get detailed nutritional information for each meal.

## 🌟 Features

- **Three Diet Categories**: 
  - 🥗 **Vegetarian Diet** - Plant-based meals rich in nutrients and fiber
  - 🥩 **Non-Vegetarian Diet** - Protein-rich meals including lean meats and fish  
  - ⚖️ **Balanced Diet** - Well-rounded meals with optimal macro-nutrient balance

- **Comprehensive Meal Information**: View detailed nutritional data including calories, protein, carbs, and fats for each meal

- **Organized by Meal Types**: Browse meals organized into Breakfast, Lunch, Dinner, and Snack categories

- **Modern User Interface**: Beautiful, responsive JavaFX UI with custom CSS styling

- **Lightweight & Fast**: No external database required - all diet plans are built into the application

## 🏗️ Architecture

The application follows object-oriented design principles with clean separation of concerns:

- **Model Classes**: `Meal`, `DietPlan`, `User` - Core business logic and data structures
- **View Layer**: FXML files and CSS stylesheets for the user interface  
- **Controller Layer**: `MainController` - Handles user interactions and UI updates
- **Factory Pattern**: `DietPlanFactory` for creating predefined diet plans

## 📋 Prerequisites

- **Java 17** or higher
- **Maven 3.6+** 
- **JavaFX 19** (included as dependency)

## 🚀 Getting Started

### Clone the Repository

```bash
git clone <your-repository-url>
cd diet-maker
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

You have several options to run the application:

#### Option 1: Using JavaFX Maven Plugin (Recommended)
```bash
mvn javafx:run
```

#### Option 2: Using the JAR file
```bash
java -jar target/dietmaker-1.0.jar
```

#### Option 3: Direct execution with JavaFX modules
```bash
java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -cp target/classes com.dietmaker.Main
```

## 🧪 Running Tests

Execute the comprehensive unit test suite:

```bash
mvn test
```

The test suite covers:
- ✅ Core model classes (`Meal`, `DietPlan`, `User`)
- ✅ Factory pattern implementations
- ✅ Business logic validation
- ✅ Edge cases and error conditions

## 📁 Project Structure

```
diet-maker/
├── src/
│   ├── main/
│   │   ├── java/com/dietmaker/
│   │   │   ├── Main.java              # Application entry point
│   │   │   ├── MainController.java    # UI event handling
│   │   │   ├── Meal.java              # Meal data model
│   │   │   ├── DietPlan.java          # Diet plan with factory
│   │   │   └── User.java              # User profile management
│   │   └── resources/
│   │       ├── fxml/
│   │       │   └── main.fxml          # Main UI layout
│   │       └── css/
│   │           └── styles.css         # Application styling
│   └── test/java/com/dietmaker/
│       ├── MealTest.java              # Meal class tests
│       ├── DietPlanTest.java          # DietPlan class tests
│       └── UserTest.java              # User class tests
├── pom.xml                            # Maven configuration
└── README.md                          # This file
```

## 🎯 Usage Guide

1. **Launch the Application**: Run using one of the methods described above

2. **Select a Diet Plan**: Click on one of the three diet cards:
   - Vegetarian Diet
   - Non-Vegetarian Diet  
   - Balanced Diet

3. **Browse Meals**: After selecting a diet, scroll through the meal categories:
   - 🌅 Breakfast Options
   - 🌞 Lunch Options
   - 🌙 Dinner Options
   - 🍎 Snack Options

4. **View Nutritional Information**: Each meal displays:
   - Meal name and description
   - Calories, protein, carbs, and fats content

## 🛠️ Development

### Adding New Meals

To add new meals to a diet plan, modify the factory methods in `DietPlan.java`:

```java
public static DietPlan createVegetarianDiet() {
    DietPlan vegDiet = new DietPlan("Vegetarian Diet", "Description...");
    
    // Add new meal
    vegDiet.addMeal(new Meal("New Meal", "Description", 
                           calories, protein, carbs, fats, "MealType"));
    
    return vegDiet;
}
```

### Customizing the UI

- **Layout Changes**: Edit `src/main/resources/fxml/main.fxml`
- **Styling Updates**: Modify `src/main/resources/css/styles.css`
- **New UI Logic**: Update `MainController.java`

### Running in Development Mode

```bash
mvn compile
mvn javafx:run
```

## 🔧 Configuration

The application can be configured by modifying:

- **JavaFX Version**: Update `javafx.version` in `pom.xml`
- **Java Version**: Change `maven.compiler.source` and `maven.compiler.target` in `pom.xml`
- **Application Metadata**: Edit the `<name>`, `<description>` sections in `pom.xml`

## 🤝 Contributing

We welcome contributions! Here's how you can help:

### Getting Started
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass (`mvn test`)
6. Commit your changes (`git commit -m 'Add amazing feature'`)
7. Push to the branch (`git push origin feature/amazing-feature`)
8. Open a Pull Request

### Contribution Guidelines
- Follow existing code style and conventions
- Add unit tests for new functionality
- Update documentation for API changes
- Ensure the UI remains responsive and accessible
- Test on multiple screen sizes

### Areas for Contribution
- 🍽️ **New Diet Plans**: Add more specialized diets (keto, Mediterranean, etc.)
- 🧮 **Enhanced Calculations**: Implement more sophisticated BMI and calorie calculations
- 🎨 **UI Improvements**: Enhanced animations, themes, or responsive design
- 📊 **Analytics**: Add meal planning and nutrition tracking features
- 🌐 **Internationalization**: Support for multiple languages

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Authors

- **Development Team** - Initial work and ongoing maintenance

## 🙏 Acknowledgments

- JavaFX community for excellent documentation and examples
- Nutrition data sourced from various reputable health organizations
- Modern UI design inspired by contemporary health and wellness applications

## 📞 Support

If you encounter any issues or have questions:

1. Check the [Issues](../../issues) section for existing solutions
2. Create a new issue with detailed information about your problem
3. Include your Java version, operating system, and steps to reproduce

---

**Eat Healthy, Live Healthy!** 🌱✨
