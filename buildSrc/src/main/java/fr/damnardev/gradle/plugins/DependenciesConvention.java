package fr.damnardev.gradle.plugins;

import org.gradle.api.Project;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.api.artifacts.VersionCatalog;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.provider.Provider;
import org.jspecify.annotations.NonNull;

import static org.gradle.api.plugins.JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME;
import static org.gradle.api.plugins.JavaPlugin.RUNTIME_ONLY_CONFIGURATION_NAME;
import static org.gradle.api.plugins.JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME;

public class DependenciesConvention {

	private static @NonNull Provider<MinimalExternalModuleDependency> get(VersionCatalog libs, String alias) {
		return libs.findLibrary(alias)
				   .orElseThrow();
	}

	public void apply(Project project, VersionCatalog libs) {
		Provider<MinimalExternalModuleDependency> slf4j = get(libs, "slf4j-api");
		Provider<MinimalExternalModuleDependency> logback = get(libs, "logback-classic");
		Provider<MinimalExternalModuleDependency> assertj = get(libs, "assertj-core");
		Provider<MinimalExternalModuleDependency> junit = get(libs, "junit-bom");

		DependencyHandler dependencies = project.getDependencies();
		dependencies.constraints(c -> c.add(IMPLEMENTATION_CONFIGURATION_NAME, slf4j));
		dependencies.constraints(c -> c.add(RUNTIME_ONLY_CONFIGURATION_NAME, logback));
		dependencies.constraints(c -> c.add(TEST_IMPLEMENTATION_CONFIGURATION_NAME, assertj));
		dependencies.add(TEST_IMPLEMENTATION_CONFIGURATION_NAME, dependencies.platform(junit));

		// To add a new dependency:
		// 1. Add version to gradle/libs.versions.toml: [libraries] section
		// 2. Add library definition to libs.versions.toml
		// 3. Add constraint below (following the pattern of slf4j, logback, junit, assertj)
		// 4. Reference in module build.gradle: implementation libs.your.new.dependency
	}

}
