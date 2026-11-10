package fr.damnardev.gradle.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.VersionCatalog;
import org.gradle.api.artifacts.VersionCatalogsExtension;

public class CommonConventionsPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		VersionCatalog libs = project.getExtensions()
									 .getByType(VersionCatalogsExtension.class)
									 .named("libs");

		new PluginConventions().apply(project, "java");
		new JavaConventions().apply(project, libs);
		new DependenciesConventions().apply(project, libs);
		new RepositoriesConventions().apply(project);
		new JUnitConventions().apply(project);
	}

}
