# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Development Commands

### Prerequisites
```powershell
# Ensure Java 17+ and Maven are installed
java -version
mvn -version

# Install Maven if not available:
# Download from https://maven.apache.org/download.cgi
# Or use package manager like Chocolatey: choco install maven
```

### Building the Project
```powershell
# Clean and build the Spring Boot web application
mvn clean install

# Quick build without tests
mvn clean compile

# Package as executable JAR
mvn clean package
```

### Running the Web Application
```powershell
# Primary method - using Spring Boot Maven plugin
mvn spring-boot:run

# Alternative - run from JAR file
java -jar target/dietmaker-1.0.jar

# Application will be available at:
# http://localhost:8080
```

### Testing
```powershell
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=MealTest

# Run tests with specific method
mvn test -Dtest=MealTest#testMealCreation

# Run tests in a specific package
mvn test -Dtest="com.dietmaker.*Test"
```

### Development Workflow
```powershell
# Compile and run in one step for development
mvn compile && mvn javafx:run

# Watch for changes and rebuild (requires external tool)
# Alternative: use IDE integration with auto-reload
```

## Code Architecture

### Application Structure
This is a **Spring Boot web application** following **MVC (Model-View-Controller)** architecture with a **Factory pattern** for data creation.

**Key Architecture Components:**

1. **Entry Point**: `Main.java` - Spring Boot application launcher with embedded Tomcat server

2. **Model Layer** (Business Logic):
   - `Meal.java` - Core data model representing individual meals with nutritional information
   - `DietPlan.java` - Container for organizing meals by type (Breakfast, Lunch, Dinner, Snack) 
   - `User.java` - User profile with BMI/calorie calculations and preferences (future-ready)

3. **View Layer** (Web UI):
   - `templates/index.html` - Thymeleaf template for diet selection homepage
   - `templates/diet-plan.html` - Thymeleaf template for displaying diet plans and meals
   - Bootstrap 5 for responsive, modern web interface styling

4. **Controller Layer**:
   - `MainController.java` - Spring MVC controller handling HTTP requests and rendering templates

5. **Factory Pattern**:
   - `DietPlan.DietPlanFactory` - Static factory methods creating pre-configured diet plans with sample meals

### Data Flow Pattern
```
HTTP Request → Spring Controller → DietPlanFactory → DietPlan → Thymeleaf Template → HTML Response
```

1. User clicks diet link → HTTP GET request sent
2. Controller method triggered and calls factory to create diet plan with meals
3. Controller adds data to Model and returns template name
4. Thymeleaf processes template with meal data and renders HTML

### Key Design Patterns Used
- **Factory Pattern**: `DietPlanFactory` for creating predefined diet plans
- **MVC Pattern**: Separation of concerns between Model, View, Controller
- **Observer Pattern**: JavaFX property binding for UI updates
- **Builder Pattern**: Meal creation with comprehensive nutritional data

### UI Architecture
- **Bootstrap 5** responsive grid system with mobile-first design
- **Thymeleaf templates** for server-side rendering with model binding
- **Card-based layouts** for diet selection and meal displays
- **CSS3 animations** and hover effects for modern user experience

## Development Notes

### Technology Stack
- **Java 17** (minimum required version)
- **Spring Boot 3.1.5** for web framework and embedded server
- **Thymeleaf** for template engine and server-side rendering
- **Bootstrap 5** for responsive CSS framework
- **Maven** for dependency management and build lifecycle
- **JUnit 5** for comprehensive unit testing
- **Embedded Tomcat** server (default port 8080)

### Important File Locations
- **Main source**: `src/main/java/com/dietmaker/`
- **Web templates**: `src/main/resources/templates/` (Thymeleaf HTML templates)
- **Static resources**: `src/main/resources/static/` (CSS, JS, images if needed)
- **Tests**: `src/test/java/com/dietmaker/`
- **Build configuration**: `pom.xml` (Spring Boot parent POM)
- **Application properties**: `src/main/resources/application.properties` (Spring Boot config)

### Extension Points for Future Development
- **User Profile Integration**: `User.java` is prepared for BMI calculations and personalized recommendations
- **Database Integration**: Current hardcoded meals can be moved to database
- **Additional Diet Types**: Factory pattern makes adding new diet plans straightforward
- **Meal Filtering**: Architecture supports filtering by allergies/preferences
- **Internationalization**: UI structure supports multiple language resources

### Testing Strategy
- **Model Testing**: Comprehensive unit tests for `Meal`, `DietPlan`, and `User` classes
- **Business Logic**: Factory methods and calculation algorithms thoroughly tested
- **Edge Cases**: Boundary conditions and error scenarios covered
- **UI Testing**: Controller logic tested, but UI components require manual verification

### Common Development Patterns
- Use **Factory methods** in `DietPlan.DietPlanFactory` when adding new diet types
- Follow **Spring MVC** patterns with `@GetMapping` for new endpoints
- Use **Thymeleaf expressions** `${...}` for data binding in templates
- Maintain **separation of concerns** - keep business logic in model classes
- Use **Bootstrap classes** for responsive styling and component consistency
- Add **Controller methods** that return template names and populate Model objects
