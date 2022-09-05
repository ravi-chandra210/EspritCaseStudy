ESPRIT CASE STUDY --

Esprit Case Study is based on BDD Automation Framework with the below tools & technologies.

Framework	            --  BDD Hybrid (Cucumber with Gherkin Language & Page Factory Model)
Tools	                --  Selenium WebDriver & Maven
Programming Languages   --	JAVA & JavaScript
Version Controlling Tool--	GitHUB.
Browsers	            --  Chrome, Firefox, Safari, IE, etc.

The Feature files consists of test cases which verifies the following behavior
    1. Login Credentials.
    2. Invalid login credentials.
    3. Add products to Cart.
    4. Verifying checkout page.
    5. Verifying Order Tables.
    6. Verify Confirmation Mail sent.
    7. Verify created order XML file.
    8. Verify Product Details in XML file.

Steps required to Execute the scenario --

Clone master branch from GIT Repository or download the ZIP file.
Clone the project from GIT to local machine with below dependencies -- JAVA Sdk, Apache Maven, Selenium Webdriver (chromedriver and geckodriver).
Currently the script supports Chrome and Firefox browsers only.

Open Excel file and change the Test Data if required. 
Save the changes and execute the script through the below methods by executing the test cases. 
Below are the different methods to execute the script --

Method 1: Eclipse IDE: Execute as JUnit Test.
Method 2: Use any IDE (Eclipse or IntelliJ)
Method 3: Command Line: Open cmd prompt in the system --> Navigate to the filepath where the project is stored --> mvn verify

Note: These tests can also be executed parallel by editing the pom file with maven surefire plugin and parallel configuration and updating the feature files accordingly.