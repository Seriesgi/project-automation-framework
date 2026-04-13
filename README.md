# Automation Test Framework

## Tech Stack
- Java 17+
- Selenium 4
- Rest Assured
- Cucumber
- Gradle
- GitHub Actions

## Features
- Web UI Automation using SauceDemo
- API Automation using Dummy API
- Cucumber BDD structure
- Page Object Model for web flow
- Config-driven test setup
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
        |       |-- ConfigManager.java
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

Override browser mode:

```bash
./gradlew webTest -Dheadless=false
```

The project targets Java 17 or newer for local runs and CI.

## Report

- API HTML: `build/reports/cucumber/api/cucumber.html`
- API JSON: `build/reports/cucumber/api/cucumber.json`
- Web HTML: `build/reports/cucumber/web/cucumber.html`
- Web JSON: `build/reports/cucumber/web/cucumber.json`
