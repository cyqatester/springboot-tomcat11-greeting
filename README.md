# Greeting App (Spring Boot + Maven + Tomcat 11)

A small Spring MVC + Thymeleaf web app that accepts a first and last name, displays a greeting, and returns to a blank home form.

## Requirements

- JDK 17 or later
- Maven 3.9+
- Apache Tomcat 11 for external WAR deployment

This project uses Spring Boot 4.0.1, which targets the Jakarta EE 11 / Servlet 6.1 generation used by Tomcat 11. Verify your organization’s approved Spring Boot patch version before production use.

## Run tests

```bash
mvn clean verify
```

## Run locally

```bash
mvn spring-boot:run
```

Then open <http://localhost:8080/>.

## Build the WAR

```bash
mvn clean package
```

Output: `target/greeting-app.war`

## Deploy to Tomcat 11

1. Ensure Tomcat 11 is running on a supported JDK.
2. Configure a dedicated Tomcat Manager deployment user with the `manager-script` role.
3. Use a secured deployment process to upload `target/greeting-app.war` to Tomcat Manager or copy it into Tomcat's `webapps` directory where permitted.
4. The application context will be `/greeting-app` when deployed as `greeting-app.war`.
5. Open `http://localhost:8080/greeting-app/` (adjust host/port as needed).

For Tomcat Manager's text API, the deploy endpoint is typically:

```text
PUT /manager/text/deploy?path=/greeting-app&update=true
```

Use HTTPS, restrict access to the Manager application, and keep credentials out of source control.

## Project layout

```text
src/main/java/com/example/greeting/
  GreetingApplication.java
  web/
    GreetingController.java
    form/GreetingForm.java
src/main/resources/
  application.properties
  templates/
    home.html
    greeting.html
src/test/java/com/example/greeting/web/
  GreetingControllerTest.java
```

## Behavior

- `GET /` renders an empty form.
- `POST /greet` validates both names and renders the greeting.
- `POST /home` redirects to `GET /`, which creates a new empty form.
- Thymeleaf's `th:text` escapes the submitted names before rendering.

## Notes

- JUnit 5 tests are provided using Spring MVC's `MockMvc`.
- No database is needed for this sample.
- For production, add CI checks, dependency/security scanning, integration tests, and environment-specific configuration as appropriate.

test 3 to trigger CI/CD
