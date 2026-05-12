# VA ATI SRSE API Scripts

A comprehensive Java-based test automation framework for SRSE (Strategic Results, System Evolution) API testing. This project uses Selenium WebDriver and the VA ATI Platform Independent Core framework to create robust, maintainable automation scripts.

## Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Data Management](#data-management)
- [Logging and Reporting](#logging-and-reporting)
- [Development Guidelines](#development-guidelines)
- [Troubleshooting](#troubleshooting)
- [Dependencies](#dependencies)

## Overview

This project provides a scalable framework for automated testing of VA SRSE applications. It includes:

- **Selenium WebDriver Integration**: Support for EDGE, CHROME, and FIREFOX browsers
- **Flexible Configuration Management**: Environment-based property files for test configuration
- **Comprehensive Logging**: HTML and/or EXCEL format logs with optional log preservation
- **Data-Driven Testing**: Support for external dataset files
- **Windows Application Testing**: Optional support for Windows App Driver
- **Accessibility Testing**: Integration with Deque 508 Scan Tool for accessibility compliance
- **Vista Integration**: SSH connection support for Vista systems

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 11 or higher
- **Maven**: Version 3.6.0 or higher
- **Git**: For version control
- **Web Browsers**:
  - Google Chrome (latest stable version)
  - Mozilla Firefox (latest stable version)
  - Microsoft Edge (latest stable version)
- **Optional Tools**:
  - Reflection (for Vista SSH key retrieval)
  - WinAppDriver (for Windows application testing)
  - Deque 508 Scan Tool (for accessibility testing)

## Project Structure

```
va-ati-srse-api-scripts/
├── src/
│   ├── testScripts/          # Main test automation scripts
│   │   └── (Your test classes)
│   └── utilities/             # Utility classes and helper methods
│       └── (Common functionality)
├── resources/                 # Test resources (XML, JSON, etc.)
├── dataSets/                  # Test data files
│   └── local/                 # Local dataset directory
├── logs/                      # Generated test execution logs
├── config.properties          # User-level configuration (DO NOT COMMIT)
├── pom.xml                    # Maven project configuration
├── .gitignore                 # Git ignore rules
└── README.md                  # This file
```

## Setup and Installation

### 1. Clone the Repository

```bash
git clone https://github.com/jerilyn46/va-ati-srse-api-scripts.git
cd va-ati-srse-api-scripts
```

### 2. Build the Project

```bash
mvn clean install
```

This command will:
- Download all required dependencies
- Compile the Java source code
- Prepare the project for execution

### 3. Configure Your Environment

Copy the `config.properties` file to your local workspace (DO NOT commit changes back to the repository):

```bash
cp config.properties ~/.va-ati-config/config.properties  # On Linux/Mac
copy config.properties %USERPROFILE%\.va-ati-config\config.properties  # On Windows
```

## Configuration

### Browser Configuration

Edit `config.properties` and set your preferred browser:

```properties
BROWSER=EDGE
```

Supported browsers: `EDGE`, `CHROME`, `FIREFOX`

### Logging Configuration

Configure log output format and behavior:

```properties
# Log format: HTML, EXCEL, or both
LOG_TYPE=HTML

# Automatically display logs after test execution
#DISPLAY_LOG=true

# Preserve old logs when running new tests
PRESERVE_LOGS=true
```

### Dataset Configuration

Specify where test data files are stored:

```properties
# Default location: /dataSets/local
DATASETS_FOLDER=/dataSets/local
```

### Download Folder (for file-based testing)

If your tests involve downloading files:

```properties
DOWNLOAD_FOLDER=C:\\Users\\username\\Downloads
```

### Test Environment Logging

Log which environment tests are running against:

```properties
TEST_ENV=BFFS_URL
```

### Search Wait Time

Override default wait times for element searches (in seconds):

```properties
#SEARCH_WAIT_SECONDS=30
```

### Browser Close Configuration

For debugging page classes and element properties:

```properties
#CLOSE_ON_TERMINATE=TRUE
```

### Daily Logs

Enable a running summary of all test executions:

```properties
#DAILY_LOGS_FOLDER=F:\\LabLogs\\DailyLogs
```

### Vista Connection Settings

For Vista system integration (SSH):

```properties
#VISTA_A_HOST_NAME=vhaispdbsshr01.vha.med.va.gov
#VISTA_A_PORT=22
#VISTA_A_SSH_RSA_KEY=<your-rsa-key-here>
#VISTA_A_USERNAME=username
#VISTA_A_PASSWORD=password
```

**Note**: SSH RSA keys can be obtained from your Vista host administrator or retrieved from Reflection's known_hosts file if you've previously connected.

### Section 508 Accessibility Testing

Enable accessibility compliance scanning:

```properties
#SECTION_508_SCAN_ENABLED=TRUE
#SECTION_508_RESULTS_FOLDER_NAME=Results
#SECTION_508_DATASET_NAME=ScannedPages508.xlsx
#SECTION_508_SHEET_NAME=508Scans
#SECTION_508_TOOLS=C:\\Automation\\Tools\\Deque\\
```

### Windows Application Testing

Configure WinAppDriver for Windows application automation:

```properties
#WINDOWS_APP=C:\\Automation\\Calculator.lnk
#WINAPPDRIVER_URL=http://127.0.0.1:4723
#WINAPPDRIVER_REMOTE=FALSE
#WINAPPDRIVER_LOCATION=C:\\Automation\\Tools\\WinAppDriver.exe
```

### Xray Integration

Configure Xray reporting for test results:

```properties
#XRAY_REPORTING=TRUE
#XRAY_TEST_ENVIRONMENT=<environment>
#XRAY_PLAN_ID=<plan-id>
#XRAY_FIX_VERSION=<version>
```

## Running Tests

### Option 1: Using Maven

Run all tests:

```bash
mvn test
```

Run a specific test class:

```bash
mvn test -Dtest=YourTestClassName
```

Run a specific test method:

```bash
mvn test -Dtest=YourTestClassName#testMethodName
```

### Option 2: Using Your IDE

- **IntelliJ IDEA**: Right-click on the test class or method and select "Run"
- **Eclipse**: Right-click on the test class or method and select "Run As" → "JUnit Test"
- **Visual Studio Code**: Use the Test Runner extension

### Option 3: Execute JAR

After building with Maven:

```bash
java -cp target/classes:target/lib/* gov.va.ati.srse.YourTestClass
```

## Data Management

### Adding Test Datasets

1. Create a CSV, XLSX, or JSON file with your test data
2. Place it in the `dataSets/local/` directory (or configured `DATASETS_FOLDER`)
3. Reference the file in your test scripts:

```java
// Pseudocode example
List<TestData> data = DatasetReader.readDataset("testdata.xlsx");
for (TestData row : data) {
    runTestWithData(row);
}
```

### Dataset Best Practices

- Use descriptive filenames
- Include headers in your data files
- Use consistent naming conventions
- Document expected data format in your test class comments

## Logging and Reporting

### Log Output Locations

- **HTML Logs**: `logs/HTMLReports/`
- **Excel Logs**: `logs/ExcelReports/`
- **Daily Summary**: Configured via `DAILY_LOGS_FOLDER`

### Log Files Include

- Test execution timeline
- Pass/Fail status for each test case
- Screenshots on failures
- Browser console logs
- Network activity (when applicable)

### Interpreting Logs

- **Green indicators**: Passed test steps
- **Red indicators**: Failed test steps
- **Yellow indicators**: Warnings or skipped steps
- **Screenshots**: Attached to failures for debugging

## Development Guidelines

### Creating a New Test Script

1. Create a Java class in `src/testScripts/`
2. Extend the appropriate base test class from `gov.va.ati.platformindependentcore`
3. Structure your test:

```java
package gov.va.ati.srse.testScripts;

import gov.va.ati.platformindependentcore.BaseTest;
import org.junit.Test;

public class MyFirstTest extends BaseTest {
    
    @Test
    public void testSampleFunctionality() {
        // Setup
        // Action
        // Assertion
    }
}
```

### Code Organization

- **Page Classes**: Define UI elements and interactions for specific pages
- **Test Scripts**: Orchestrate page classes to test user workflows
- **Utilities**: Reusable helper methods, data readers, API clients

### Naming Conventions

- Test classes: `{Feature}Test.java` (e.g., `LoginTest.java`)
- Test methods: `test{Action}{Expected}()` (e.g., `testLoginWithValidCredentials()`)
- Page classes: `{PageName}Page.java` (e.g., `DashboardPage.java`)

### Code Quality

- Use meaningful variable and method names
- Add comments for complex logic
- Follow DRY (Don't Repeat Yourself) principle
- Keep methods focused and single-purpose
- Use proper exception handling

## Troubleshooting

### Common Issues

#### Issue: "Browser driver not found"

**Solution**: Ensure the browser is installed and paths are correct in `config.properties`.

#### Issue: "Element not found" exceptions

**Possible causes**:
- Element has not finished loading (increase `SEARCH_WAIT_SECONDS`)
- Selector is incorrect (verify in browser DevTools)
- Page has changed (update page class)

**Solution**:
```properties
SEARCH_WAIT_SECONDS=60  # Increase wait time
```

#### Issue: "Connection refused" for Vista

**Solution**: Verify Vista connection settings:
- Check hostname and port
- Confirm SSH RSA key is correct
- Verify username and password

#### Issue: Tests pass locally but fail in CI/CD

**Possible causes**:
- Different browser versions
- Different OS environment
- Missing dependencies
- Configuration not being read correctly

**Solution**:
- Use Docker for consistent environments
- Ensure all dependencies are in `pom.xml`
- Verify `config.properties` is properly configured in CI/CD pipeline

#### Issue: "Out of memory" errors

**Solution**: Increase JVM heap size:
```bash
export MAVEN_OPTS="-Xmx2048m"  # On Linux/Mac
set MAVEN_OPTS=-Xmx2048m        # On Windows
mvn test
```

### Getting Help

1. Check log files for detailed error messages
2. Enable debugging in your IDE
3. Review test screenshots for visual context
4. Check the official VA ATI documentation
5. Contact your project administrator

## Dependencies

### Core Dependencies

- **seleniumcore** (v3.2.1-SNAPSHOT): Selenium WebDriver wrapper
- **platformindependentcore** (v3.2.1-SNAPSHOT): VA ATI base testing framework

### Maven Build Plugin

- **maven-compiler-plugin** (v3.11.0): Compiles Java source code to Java 11 bytecode

### Optional Dependencies

To enable page, windows, or mobile testing, uncomment the appropriate dependency in `pom.xml`:

```xml
<!-- For web page testing -->
<dependency>
    <groupId>gov.va.ati.REPLACE_WITH_LOWER_CASE_PROJECT_NAME</groupId>
    <artifactId>REPLACE_WITH_LOWER_CASE_PROJECT_NAME.pages</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>

<!-- For Windows application testing -->
<dependency>
    <groupId>gov.va.ati.REPLACE_WITH_LOWER_CASE_PROJECT_NAME</groupId>
    <artifactId>REPLACE_WITH_LOWER_CASE_PROJECT_NAME.windows</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>

<!-- For mobile testing -->
<dependency>
    <groupId>gov.va.ati.REPLACE_WITH_LOWER_CASE_PROJECT_NAME</groupId>
    <artifactId>REPLACE_WITH_LOWER_CASE_PROJECT_NAME.mobile</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>

<!-- For Vista automation scripts -->
<dependency>
    <groupId>gov.va.ati.vista</groupId>
    <artifactId>vista.options</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

---

**Last Updated**: 2026-05-12

**Maintained By**: jerilyn46

For more information about VA ATI framework, visit the official documentation or contact your QA lead.
