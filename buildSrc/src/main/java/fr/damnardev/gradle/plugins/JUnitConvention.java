package fr.damnardev.gradle.plugins;

import org.gradle.api.Project;
import org.gradle.api.tasks.testing.Test;

public class JUnitConvention {

	public void apply(Project project) {
		project.getTasks()
			   .named("test", Test.class, Test::useJUnitPlatform);
	}

}
