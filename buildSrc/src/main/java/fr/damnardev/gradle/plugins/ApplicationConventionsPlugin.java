package fr.damnardev.gradle.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class ApplicationConventionsPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		new PluginConvention().apply(project, "application", "common-conventions");
	}

}
