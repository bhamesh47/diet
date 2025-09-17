# Converting Diet Maker to Web Application

## Option 2A: Spring Boot + Thymeleaf Web App

### 1. Add Spring Boot Dependencies to pom.xml:
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.1.5</version>
    <relativePath/>
</parent>

<dependencies>
    <!-- Spring Boot Web Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Thymeleaf Template Engine -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    
    <!-- Keep existing dependencies -->
    <!-- Remove JavaFX dependencies -->
</dependencies>
```

### 2. Create Web Controller:
```java
@Controller
public class DietController {
    
    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }
    
    @GetMapping("/diet/{type}")
    public String getDiet(@PathVariable String type, Model model) {
        DietPlan plan;
        switch(type) {
            case "vegetarian":
                plan = DietPlan.DietPlanFactory.createVegetarianDiet();
                break;
            case "non-vegetarian":
                plan = DietPlan.DietPlanFactory.createNonVegetarianDiet();
                break;
            case "balanced":
                plan = DietPlan.DietPlanFactory.createBalancedDiet();
                break;
            default:
                plan = DietPlan.DietPlanFactory.createBalancedDiet();
        }
        
        model.addAttribute("dietPlan", plan);
        model.addAttribute("breakfastMeals", plan.getMealsByType("Breakfast"));
        model.addAttribute("lunchMeals", plan.getMealsByType("Lunch"));
        model.addAttribute("dinnerMeals", plan.getMealsByType("Dinner"));
        model.addAttribute("snackMeals", plan.getMealsByType("Snack"));
        
        return "diet-plan";
    }
}
```

### 3. Create Spring Boot Main Class:
```java
@SpringBootApplication
public class DietMakerWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(DietMakerWebApplication.class, args);
    }
}
```

### 4. Create Thymeleaf Templates:

**src/main/resources/templates/index.html:**
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Diet Maker</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-5">Diet Maker - Your Personal Diet Planner</h1>
        
        <div class="row justify-content-center">
            <div class="col-md-4 mb-3">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">🥗 Vegetarian Diet</h5>
                        <p class="card-text">Plant-based meals rich in nutrients and fiber</p>
                        <a href="/diet/vegetarian" class="btn btn-success">Select Vegetarian</a>
                    </div>
                </div>
            </div>
            
            <div class="col-md-4 mb-3">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">🥩 Non-Vegetarian Diet</h5>
                        <p class="card-text">Protein-rich meals including lean meats and fish</p>
                        <a href="/diet/non-vegetarian" class="btn btn-danger">Select Non-Vegetarian</a>
                    </div>
                </div>
            </div>
            
            <div class="col-md-4 mb-3">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <h5 class="card-title">⚖️ Balanced Diet</h5>
                        <p class="card-text">Well-rounded meals with optimal macro-nutrient balance</p>
                        <a href="/diet/balanced" class="btn btn-primary">Select Balanced</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
```

## Option 2B: REST API + React Frontend

### 1. Create REST API Controller:
```java
@RestController
@RequestMapping("/api")
public class DietApiController {
    
    @GetMapping("/diets/{type}")
    public ResponseEntity<DietPlan> getDiet(@PathVariable String type) {
        DietPlan plan;
        switch(type.toLowerCase()) {
            case "vegetarian":
                plan = DietPlan.DietPlanFactory.createVegetarianDiet();
                break;
            case "non-vegetarian":
                plan = DietPlan.DietPlanFactory.createNonVegetarianDiet();
                break;
            case "balanced":
                plan = DietPlan.DietPlanFactory.createBalancedDiet();
                break;
            default:
                return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(plan);
    }
    
    @GetMapping("/diets")
    public ResponseEntity<Map<String, String>> getAllDietTypes() {
        Map<String, String> dietTypes = Map.of(
            "vegetarian", "Plant-based meals rich in nutrients and fiber",
            "non-vegetarian", "Protein-rich meals including lean meats and fish",
            "balanced", "Well-rounded meals with optimal macro-nutrient balance"
        );
        return ResponseEntity.ok(dietTypes);
    }
}
```

## Ubuntu EC2 Deployment Steps:

### 1. Prepare EC2 Instance:
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

### 2. Deploy Application:
```bash
# Clone repository
git clone https://github.com/your-username/diet-maker.git
cd diet-maker

# Build application
mvn clean package -DskipTests

# Run Spring Boot application
java -jar target/dietmaker-1.0.jar
```

### 3. Configure Security Group:
- Open port 8080 (default Spring Boot port) in EC2 security group
- Add inbound rule: Type: HTTP, Port: 8080, Source: 0.0.0.0/0

### 4. Access Application:
- Web URL: `http://your-ec2-public-ip:8080`
- API endpoint: `http://your-ec2-public-ip:8080/api/diets/vegetarian`

### 5. Run as Service (Optional):
```bash
# Create systemd service file
sudo nano /etc/systemd/system/diet-maker.service

# Add content:
[Unit]
Description=Diet Maker Web Application
After=network.target

[Service]
Type=forking
User=ubuntu
ExecStart=/usr/bin/java -jar /home/ubuntu/diet-maker/target/dietmaker-1.0.jar
Restart=always

[Install]
WantedBy=multi-user.target

# Enable and start service
sudo systemctl enable diet-maker
sudo systemctl start diet-maker
sudo systemctl status diet-maker
```
