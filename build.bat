@echo off
echo Diet Maker - Build Script
echo ========================

echo Checking Java installation...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 17 or higher from: https://adoptium.net/
    pause
    exit /b 1
)

echo Java found!

echo.
echo Checking Maven installation...
mvn --version >nul 2>&1
if %errorlevel% neq 0 (
    echo WARNING: Maven is not installed or not in PATH
    echo.
    echo Please install Maven from: https://maven.apache.org/download.cgi
    echo After installation, add Maven's bin directory to your PATH environment variable.
    echo.
    echo Then run: mvn clean install
    echo To build the project.
    echo.
    pause
    exit /b 1
)

echo Maven found!
echo.
echo Building the project...
mvn clean install

if %errorlevel% eq 0 (
    echo.
    echo ========================================
    echo BUILD SUCCESSFUL!
    echo ========================================
    echo.
    echo To run the application:
    echo   mvn javafx:run
    echo.
    echo Or:
    echo   java -jar target/dietmaker-1.0.jar
    echo.
) else (
    echo.
    echo BUILD FAILED!
    echo Please check the error messages above.
)

pause
