# 🧪 SauceDemo Selenium Automation Testing

## 📌 Project Overview

This project is a **Selenium WebDriver automation testing framework** developed using **Java** to automate and validate key functionalities of the SauceDemo web application.

The project was developed as part of my hands-on learning in **Selenium Automation Testing** during a **14-day GUVI training program** conducted as part of the HCLTech hiring process and Hackathon preparation.

The main objective was to understand how manual test scenarios can be converted into **reusable and maintainable automated test scripts**.

---

## 🎯 Objective

To automate important e-commerce workflows in SauceDemo and verify that the application behaves as expected through automated test execution.

### Application Under Test

**SauceDemo:** https://www.saucedemo.com/

---

## 🛠️ Tools & Technologies

| Technology             | Purpose                                       |
| ---------------------- | --------------------------------------------- |
| **Java**               | Programming language for automation scripts   |
| **Selenium WebDriver** | Browser and web-element automation            |
| **TestNG**             | Test execution and test validation            |
| **Maven**              | Project and dependency management             |
| **XPath**              | Identifying dynamic web elements              |
| **CSS Selector**       | Locating web elements                         |
| **WebDriverWait**      | Synchronizing automation with the application |
| **Git**                | Version control                               |
| **GitHub**             | Source code management and repository hosting |
| **IntelliJ IDEA**      | Development and debugging                     |

---

## 🧪 Test Scenarios

The following key user workflows were automated:

### 🔐 Login

* Launch the SauceDemo application
* Enter valid username and password
* Verify successful login
* Validate the products page

### 🛒 Add to Cart

* Select products
* Add products to the shopping cart
* Verify the selected products are displayed in the cart

### ❌ Remove from Cart

* Open the shopping cart
* Remove selected products
* Verify that the product is removed successfully

### 🔄 Product Sorting

* Select different sorting options
* Verify that products are displayed according to the selected sorting criteria

### 💳 Checkout

* Enter customer information
* Continue through the checkout process
* Verify the order summary
* Complete the checkout workflow

### 🚪 Logout

* Navigate to the menu
* Click Logout
* Verify that the user is redirected to the login page

---

## 📚 Selenium Concepts Implemented

This project provided hands-on practice with:

* Selenium WebDriver
* WebDriver methods
* Web element interactions
* Locators
* XPath
* CSS Selectors
* Explicit Waits
* Expected Conditions
* Browser automation
* Assertions
* TestNG annotations
* Test case organization
* Maven dependency management

---

## 🏗️ Project Structure

```text
SauceDemo-Automation/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/
│   │           └── ...
│   │
│   └── test/
│       └── java/
│           ├── tests/
│           │   └── ...
│           └── ...
│
├── screenshots/
│
├── pom.xml
├── testng.xml
└── README.md
```

> Update the package and class names above according to your actual GitHub project structure.

---

## ⚙️ Framework Approach

The automation project follows a structured approach to make the test scripts easier to maintain and reuse.

### Page Object Model (POM)

Page-related locators and actions are separated from the test classes wherever POM is implemented.

This helps to:

* Reduce duplicate code
* Improve code readability
* Make maintenance easier
* Improve reusability
* Separate test logic from page interactions

---

## ⏳ Wait Strategy

The framework uses Selenium wait mechanisms to handle synchronization between the automation script and the web application.

**WebDriverWait** and **ExpectedConditions** can be used to wait for elements before performing actions instead of relying on unnecessary fixed delays.

---

## 📊 Test Execution

Tests can be executed using Maven:

```bash
mvn test
```

Or through the configured TestNG test suite.

---

## 📸 Test Execution Output

The project includes test execution evidence demonstrating the execution of the automated test scenarios.

Screenshots/results can be added here:

```text
📸 Add your test execution screenshot here
```

---

## 💡 Key Learning Outcomes

Through this project, I gained practical experience in:

* Automating web applications using Selenium WebDriver
* Writing test cases using Java
* Identifying web elements using different locators
* Working with XPath and CSS Selectors
* Handling synchronization using waits
* Organizing test cases using TestNG
* Managing dependencies using Maven
* Understanding the Page Object Model approach
* Using Git and GitHub for version control

---

## 🚀 Future Enhancements

Planned improvements include:

* Implementing Cucumber BDD
* Adding reusable utility classes
* Improving test reporting
* Adding screenshot capture on test failure
* Implementing data-driven testing
* Adding cross-browser testing
* Integrating the framework with CI/CD pipelines

---

## 👨‍💻 Author

**Srinivas J G**

Computer Science & Engineering Graduate
Aspiring Automation Test Engineer

---

## ⭐ Acknowledgement

This project was developed as part of my hands-on learning journey during the **GUVI 14-day training program** associated with the HCLTech hiring process and Hackathon preparation.

The project helped strengthen my foundation in **Selenium Automation Testing, Java, TestNG, Maven, and software testing practices**.

---

## 🔗 Repository

GitHub Repository:
https://github.com/Srinivas1408/SauceDemo

⭐ If you find this project useful, feel free to explore the repository and provide feedback.
