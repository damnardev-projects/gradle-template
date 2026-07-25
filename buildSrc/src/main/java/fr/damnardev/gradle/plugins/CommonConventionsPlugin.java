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

		new PluginConvention().apply(project, "java");
		new JavaConvention().apply(project, libs);
		new DependenciesConvention().apply(project, libs);
		new RepositoriesConvention().apply(project);
		new JUnitConvention().apply(project);
	}

}
