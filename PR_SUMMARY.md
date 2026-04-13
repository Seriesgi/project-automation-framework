# PR Summary

## What Changed
- upgraded the framework baseline to Java 17 compatible build settings and Selenium 4
- separated API and web hooks from step definition classes to keep test flow cleaner
- added Allure-compatible result generation alongside existing Cucumber HTML and JSON reports
- expanded API coverage with a negative unknown-user scenario
- added automatic screenshot capture for failed web scenarios

## Why
- cleaner lifecycle handling reduces coupling inside step definition classes
- richer reports make the framework easier to demo and troubleshoot
- negative-path coverage improves confidence beyond happy-path testing

## Verification
- `gradlew.bat apiTest`
- `gradlew.bat webTest`

Local verification in this workspace used the IntelliJ JBR because the machine default `JAVA_HOME` still points to Java 8. The project itself targets Java 17+ and CI runs on Java 17.
