# Diet Maker 🥗

**Your Personal Diet Planning Assistant**

A modern **Spring Boot web application** that helps users explore different diet plans and discover nutritious meal options. Access from any device through your web browser! Choose from three carefully curated diet categories and get detailed nutritional information for each meal.

🌐 **Live Demo**: `http://your-server:8080`

## 🌟 Features

- **Three Diet Categories**: 
  - 🥗 **Vegetarian Diet** - Plant-based meals rich in nutrients and fiber
  - 🥩 **Non-Vegetarian Diet** - Protein-rich meals including lean meats and fish  
  - ⚖️ **Balanced Diet** - Well-rounded meals with optimal macro-nutrient balance

- **Comprehensive Meal Information**: View detailed nutritional data including calories, protein, carbs, and fats for each meal

- **Organized by Meal Types**: Browse meals organized into Breakfast, Lunch, Dinner, and Snack categories

- **Responsive Web Interface**: Beautiful, mobile-friendly Bootstrap 5 UI that works on all devices

- **Web Accessible**: Access from anywhere via web browser - no installation required

- **Lightweight & Fast**: No external database required - all diet plans are built into the application

## 🏗️ Architecture

The application follows **Spring Boot MVC architecture** with clean separation of concerns:

- **Model Classes**: `Meal`, `DietPlan`, `User` - Core business logic and data structures
- **View Layer**: Thymeleaf HTML templates with Bootstrap 5 responsive styling  
- **Controller Layer**: Spring MVC `MainController` - Handles HTTP requests and responses
- **Factory Pattern**: `DietPlanFactory` for creating predefined diet plans
- **Web Framework**: Spring Boot with embedded Tomcat server

## 📋 Prerequisites

- **Java 17** or higher
- **Maven 3.6+** 
- **Web Browser** (Chrome, Firefox, Safari, Edge)
- **Port 8080** available (default Spring Boot port)

## 🚀 Getting Started

### Clone the Repository

```bash
git clone https://github.com/Akash05061/diet.git
cd diet
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

#### Option 1: Using Spring Boot Maven Plugin (Recommended)
```bash
mvn spring-boot:run
```

#### Option 2: Using the JAR file
```bash
# Build first
mvn clean package

# Run the JAR
java -jar target/dietmaker-1.0.jar
```

### Access the Application

Once running, open your web browser and navigate to:
- **Local**: `http://localhost:8080`
- **Network**: `http://your-ip-address:8080`

#### Available URLs:
- **Home**: `http://localhost:8080/`
- **Vegetarian Diet**: `http://localhost:8080/diet/vegetarian`
- **Non-Vegetarian Diet**: `http://localhost:8080/diet/non-vegetarian`
- **Balanced Diet**: `http://localhost:8080/diet/balanced`

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
│   │   │   ├── Main.java              # Spring Boot application entry point
│   │   │   ├── MainController.java    # Spring MVC web controller
│   │   │   ├── Meal.java              # Meal data model
│   │   │   ├── DietPlan.java          # Diet plan with factory
│   │   │   └── User.java              # User profile management
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── index.html         # Home page template
│   │       │   └── diet-plan.html     # Diet plan display template
│   │       └── static/                # Static web resources (CSS, JS, images)
│   └── test/java/com/dietmaker/
│       ├── MealTest.java              # Meal class tests
│       ├── DietPlanTest.java          # DietPlan class tests
│       └── UserTest.java              # User class tests
├── pom.xml                            # Spring Boot Maven configuration
├── WARP.md                            # WARP development guide
├── WEB_CONVERSION_GUIDE.md            # Web conversion documentation
└── README.md                          # This file
```

## 🎯 Usage Guide

1. **Launch the Application**: Run using one of the methods described above and open `http://localhost:8080` in your web browser

2. **Select a Diet Plan**: Click on one of the three responsive diet cards:
   - 🥗 Vegetarian Diet
   - 🥩 Non-Vegetarian Diet  
   - ⚖️ Balanced Diet

3. **Browse Meals**: After selecting a diet, scroll through the organized meal categories:
   - 🌅 Breakfast Options
   - 🌞 Lunch Options
   - 🌙 Dinner Options
   - 🍎 Snack Options

4. **View Nutritional Information**: Each meal displays:
   - Meal name and description
   - Color-coded nutritional badges: Calories, Protein, Carbs, Fats
   - Responsive cards that work on mobile and desktop

5. **Navigate Between Diets**: Use the diet switcher buttons or back button to explore other options

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

### Customizing the Web UI

- **Layout Changes**: Edit Thymeleaf templates in `src/main/resources/templates/`
- **Styling Updates**: Modify Bootstrap classes or add custom CSS in templates
- **New Web Logic**: Update `MainController.java` with new `@GetMapping` endpoints
- **Static Resources**: Add CSS/JS files to `src/main/resources/static/`

### Running in Development Mode

```bash
# Development with auto-restart on changes
mvn spring-boot:run

# Or use Spring Boot DevTools for hot reload
# Add spring-boot-devtools dependency for automatic restarts
```

## 🔧 Configuration

The application can be configured by modifying:

- **Server Port**: Create `src/main/resources/application.properties` and set `server.port=8080`
- **Java Version**: Change `maven.compiler.source` and `maven.compiler.target` in `pom.xml`
- **Spring Boot Version**: Update parent version in `pom.xml`
- **Application Metadata**: Edit the `<name>`, `<description>` sections in `pom.xml`

### Example application.properties:
```properties
# Server configuration
server.port=8080
server.servlet.context-path=/

# Thymeleaf configuration
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
```

## 🚀 Deployment

### Deploy to Ubuntu EC2

1. **Prepare EC2 Instance**:
```bash
# Update system
sudo apt update && sudo apt upgrade -y

# Install Java 17
sudo apt install openjdk-17-jdk -y

# Install Maven
sudo apt install maven -y

# Verify installations
java -version
mvn -version
```

2. **Deploy Application**:
```bash
# Clone and build
git clone https://github.com/Akash05061/diet.git
cd diet
mvn clean package -DskipTests

# Run the application
java -jar target/dietmaker-1.0.jar
```

3. **Configure Security Group**:
   - Add inbound rule: **Type: HTTP, Port: 8080, Source: 0.0.0.0/0**

4. **Access Application**:
   - Web URL: `http://your-ec2-public-ip:8080`

### Run as Service (Optional):
```bash
# Create systemd service
sudo nano /etc/systemd/system/diet-maker.service

# Add service configuration:
[Unit]
Description=Diet Maker Web Application
After=network.target

[Service]
Type=simple
User=ubuntu
ExecStart=/usr/bin/java -jar /home/ubuntu/diet/target/dietmaker-1.0.jar
Restart=always

[Install]
WantedBy=multi-user.target

# Enable and start service
sudo systemctl enable diet-maker
sudo systemctl start diet-maker
sudo systemctl status diet-maker
```

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
- 🎨 **UI Improvements**: Enhanced web animations, themes, or mobile responsiveness
- 📊 **Analytics**: Add meal planning and nutrition tracking features
- 🌐 **API Development**: Create REST APIs for mobile app integration
- 📱 **PWA Features**: Make it a Progressive Web App for offline access
- 🔐 **User Authentication**: Add user accounts and personalized meal plans
- 🌍 **Internationalization**: Support for multiple languages

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Authors

- **Development Team** - Initial work and ongoing maintenance

## 🙏 Acknowledgments

- Spring Boot community for excellent documentation and framework
- Bootstrap team for the responsive CSS framework
- Thymeleaf community for the powerful template engine
- Nutrition data sourced from various reputable health organizations
- Modern UI design inspired by contemporary health and wellness applications

## 📦 Support

If you encounter any issues or have questions:

1. Check the [Issues](../../issues) section for existing solutions
2. Create a new issue with detailed information about your problem
3. Include your Java version, browser, operating system, and steps to reproduce
4. For deployment issues, include server logs and configuration details

### Common Issues:
- **Port 8080 already in use**: Change port in `application.properties` or stop conflicting service
- **Application won't start**: Check Java 17+ is installed and JAVA_HOME is set
- **Browser can't connect**: Verify firewall settings and security group configuration

---

**Eat Healthy, Live Healthy!** 🌱✨
