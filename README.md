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
- Dedicated hooks for API and web lifecycle
- Allure result output for richer reporting
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
        |   |   |   `-- ApiHooks.java
        |   |   |   `-- UserSteps.java
        |   |   `-- web/
        |   |       `-- WebHooks.java
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
- Failure screenshots: `build/reports/screenshots`
- Allure results: `build/allure-results`

Generate an Allure report locally if the Allure CLI is available:

```bash
allure serve build/allure-results
```

## Portfolio Summary

This project demonstrates a compact hybrid automation framework that covers both UI and API validation in one Gradle repository. It uses Cucumber for readable BDD scenarios, Selenium 4 with Page Object Model for web automation, Rest Assured for API checks, and GitHub Actions for CI execution.

Current implemented coverage includes successful and failed login validation on SauceDemo, user-list and tag retrieval from Dummy API, negative API validation for unknown resources, screenshot capture on web failures, and report generation in Cucumber plus Allure-compatible output.
