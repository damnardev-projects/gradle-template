package fr.damnardev.gradle.plugins;

import org.gradle.api.Project;

public class RepositoriesConvention {

	public void apply(Project project) {
		project.getRepositories()
			   .mavenCentral();
	}

}
