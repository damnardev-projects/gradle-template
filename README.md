# Gradle Template

Ce projet Java peut servir de modèle pour un nouveau projet. Il utilise Gradle pour gérer le cycle de vie du projet et les dépendances.

## Structure du projet

Ci-dessous, vous trouverez des informations sur les fichiers et répertoires importants du projet.

Le fichier `settings.gradle` déclare les modules du projet.

Le fichier `build.gradle` configure SonarQube et l'agrégation des rapports JaCoCo.

Le fichier `gradle/libs.versions.toml` centralise les versions des dépendances et des plugins.

Le répertoire `buildSrc/` contient les plugins de convention partagés par les modules.

- `CommonConventionsPlugin` (alias `common-conventions`) : applique les conventions communes à tous les modules.
    - `PluginConventions` : configure les plugins communs à tous les modules.
    - `JavaConventions` : configure la version de Java à utiliser pour le projet. Cette version est récupérée dans l'entrée `java` de la section `[versions]` du fichier `gradle/libs.versions.toml`.
    - `DependenciesConventions` : configure les contraintes de dépendances et les versions centralisées. Les versions des dépendances sont récupérées dans le fichier `gradle/libs.versions.toml`.
    - `RepositoriesConventions` : configure le dépôt à utiliser pour résoudre les dépendances.
    - `JUnitConventions` : configure la plateforme de test JUnit.
- `LibraryConventionsPlugin` (alias `library-conventions`) : applique les conventions spécifiques aux modules de bibliothèque.
    - `java-library` : applique le plugin `java-library`. Cette convention permet d'avoir un module de type bibliothèque.
    - `common-conventions` : applique les conventions communes à tous les modules.
- `ApplicationConventionsPlugin` (alias `application-conventions`) : applique les conventions spécifiques aux modules exécutables.
    - `application` : applique le plugin `application`. Cette convention permet d'avoir un module exécutable.
    - `common-conventions` : applique les conventions communes à tous les modules.

Les modules `projects/common` et `projects/cli` sont des exemples du template. Le module `projects/common` est une bibliothèque partagée, et le module `projects/cli` est une application en ligne de commande qui dépend de `projects/common`.

## Installation

Une fois le projet cloné, exécutez la commande `./gradlew build` pour télécharger les dépendances, compiler le projet et lancer les vérifications.

## Commandes utiles

- `./gradlew clean` : supprime les fichiers générés
- `./gradlew build` : compile le projet, exécute les tests et assemble les artefacts
- `./gradlew test` : exécute les tests unitaires
- `./gradlew aggregate` : génère le rapport de couverture agrégé JaCoCo dans `build/reports/jacoco/aggregate/`
- `./gradlew :projects:cli:run` : exécute l'application en ligne de commande
- `./gradlew :projects:cli:installDist` : installe la distribution de l'application dans `projects/cli/build/install/`
- `./gradlew sonar` : envoie les données d'analyse et de couverture à SonarQube (nécessite les variables d'environnement `SONAR_URI` et `SONAR_TOKEN`)

## Ajouter une dépendance

Vous devez éditer le fichier `gradle/libs.versions.toml` et ajouter la nouvelle dépendance dans la section `[libraries]`, puis modifier le fichier `buildSrc/src/main/java/fr/damnardev/gradle/plugins/DependenciesConventions.java` pour l'ajouter aux conventions de dépendances.

## Ajouter un module

Pour ajouter un nouveau module dans le projet, vous devez suivre les étapes ci-dessous.

1. Créez le module dans le répertoire `projects`.
2. Créez le fichier `projects/<module>/build.gradle` et, dans la section `plugins`, ajoutez `id 'library-conventions'` pour appliquer les conventions de bibliothèque ou `id 'application-conventions'` pour appliquer les conventions d'application.
3. Déclarez le module dans le fichier `settings.gradle` racine avec `include 'projects:new-module'`.
4. Déclarez le module dans la section `dependencies` du fichier `build.gradle` racine avec `jacocoAggregation project(':projects:new-module')`.

## GitHub

Par défaut, le projet est configuré pour lancer Dependabot à 21 h afin de vérifier les mises à jour des dépendances Gradle et des GitHub Actions.

De plus, deux GitHub Actions sont configurées dans ce projet. La première, `compile-verify`, se lance à la création d'une pull request et lors des push sur la branche `develop`. La seconde, `sonar`, se lance après un push sur la branche `develop` afin d'analyser la qualité du code.

## SonarQube

Pour configurer SonarQube sur votre projet, vous devez éditer le fichier `build.gradle` et modifier la valeur de `sonar.projectKey`. Vous devez également ajouter dans GitHub une variable `SONAR_URI` contenant l'URL de votre serveur, ainsi qu'un secret `SONAR_TOKEN` contenant votre token.
