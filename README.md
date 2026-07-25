# Gradle Template

Multi-module Gradle project with Gradle wrapper, configured for Java 21 and JUnit 6. This repository provides a small, opinionated template for CLI and common modules, with aggregated JaCoCo coverage reports and SonarQube integration.

## Structure

- **root**:
    - `build.gradle` — SonarQube and JaCoCo aggregation configuration.
    - `settings.gradle` — declares the project modules.
- **gradle/libs.versions.toml** — version catalog for dependencies and plugins.
- **buildSrc/** — shared build logic implemented as convention plugins:
    - `common-conventions` — applies the `java` plugin, sets the Java 21 toolchain, configures JUnit Platform, and adds dependency constraints (slf4j, logback, junit-bom, assertj).
    - `library-conventions` — extends `common-conventions` for library modules.
    - `application-conventions` — extends `common-conventions` and applies the `application` plugin for runnable modules.
- **projects/** — application modules:
    - `projects/common` — shared code used by other modules.
    - `projects/cli` — CLI application (uses `application-conventions`, main class: `fr.damnardev.template.gradle.cli.Startup`).
- **build/** — compiled outputs and generated reports.

## Useful Commands

Use the included Gradle wrapper (`gradlew` / `gradlew.bat`) to ensure all developers use the same Gradle version.

- `./gradlew clean` : removes generated files
- `./gradlew build` : compiles, tests, and assembles all artifacts
- `./gradlew test` : runs unit tests
- `./gradlew aggregate` : generates the aggregated JaCoCo XML/HTML report in `build/reports/jacoco/aggregate/`
- `./gradlew :projects:cli:run` : runs the CLI application
- `./gradlew :projects:cli:installDist` : installs the CLI distribution under `projects/cli/build/install/`
- `./gradlew sonar` : sends coverage and analysis data to SonarQube (requires env vars below)

### SonarQube

SonarQube analysis is only enabled when the `SONAR_TOKEN` (authentication token) and `SONAR_URI` (server URL) environment variables are both set.

## Adding a Module

Edit `settings.gradle` to include the new module (e.g. `include 'projects:new-module'`), then add it to the `jacocoAggregation` dependencies in the root `build.gradle` to include it in the aggregated coverage report.

## Adding a Dependency

Add the dependency and its version to `gradle/libs.versions.toml`, then edit `CommonConventionsPlugin` in `buildSrc/` to register it as a dependency constraint under the appropriate configuration (`implementation`, `testImplementation`,
`runtimeOnly`, etc.). Finally, reference the dependency in the module's `build.gradle` where it is needed (e.g. `implementation libs.some.dependency`).
