# Navigate to the project directory
Set-Location ./FoodManagementSystem

# Run Maven package command
mvn clean package

# Check if the build was successful
if ($LASTEXITCODE -eq 0) {
    Set-Location ./Application
    Write-Host "Build successful."
    # Run the JAR file
    java -jar ./build/Application-jar-with-dependencies.jar
} else {
    Write-Host "Build failed."
}
