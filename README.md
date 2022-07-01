# Automation Test Examples

A set of demo projects with different automation approaches and frameworks.

---

## Subprojects

- [Core](core) - General code, used in all subprojects
- [Core Playwright](core-playwright) - Playwright-specific utils
- [Core Selenium](core-selenium) - Selenium-specific utils
- JUnit 5 projects:
    - [JUnit 5 - Core](junit-core)
    - [JUnit 5 - API Tests with Apache HttpClient](junit-api-apache)
    - [JUnit 5 - API Tests with OkHttp](junit-api-okhttp)
    - [JUnit 5 - API Tests with RestAssured](junit-api-restassured)
    - [JUnit 5 - UI Tests with Selenium](junit-ui-selenium)

## Setup

- Install **Java 17** or higher (any distribution)
    - [jenv](https://github.com/jenv/jenv) or [SDKMAN](https://github.com/sdkman)

- Configure **Git** to only use **LN** for EOL
```shell
git config core.autocrlf false
git config pull.ff only
```
