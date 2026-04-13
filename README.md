# Automation Test Framework

## Tech Stack
- Java
- Selenium
- Rest Assured
- Cucumber
- Gradle
- GitHub Actions

## Features
- Web UI Automation using SauceDemo
- API Automation using Dummy API
- Cucumber BDD structure
- Page Object Model for web flow
- CI execution with GitHub Actions

## Project Structure

```text
automation-framework/
|-- build.gradle
|-- settings.gradle
|-- README.md
|-- .github/
|   `-- workflows/
|       `-- automation.yml
`-- src/
    `-- test/
        |-- java/
        |   |-- pages/
        |   |   `-- LoginPage.java
        |   |-- runners/
        |   |   |-- ApiTestRunner.java
        |   |   `-- WebTestRunner.java
        |   |-- stepdefinitions/
        |   |   |-- api/
        |   |   |   `-- UserSteps.java
        |   |   `-- web/
        |   |       `-- LoginSteps.java
        |   `-- utils/
        |       |-- BaseApi.java
        |       `-- DriverManager.java
        `-- resources/
            |-- config/
            |   `-- config.properties
            `-- features/
                |-- api/
                |   `-- user.feature
                `-- web/
                    `-- login.feature
```

## How to Run

Run all tests:

```bash
./gradlew test
```

Run API tests only:

```bash
./gradlew apiTest
```

Run web tests only:

```bash
./gradlew webTest
```

## Report

- HTML: `build/reports/cucumber.html`
- JSON: `build/reports/cucumber.json`
