package fr.damnardev.gradle.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class LibraryConventionsPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		new PluginConvention().apply(project, "java-library", "common-conventions");
	}

}
