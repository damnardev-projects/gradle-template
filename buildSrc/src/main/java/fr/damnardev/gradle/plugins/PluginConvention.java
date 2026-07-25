package fr.damnardev.gradle.plugins;

import org.gradle.api.Project;

public class PluginConvention {

	public void apply(Project project, String... plugins) {
		for (String plugin : plugins) {
			project.getPluginManager()
				   .apply(plugin);
		}
	}

}
