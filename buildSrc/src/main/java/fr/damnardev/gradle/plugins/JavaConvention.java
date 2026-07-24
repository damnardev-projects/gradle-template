package fr.damnardev.gradle.plugins;

import org.gradle.api.Project;
import org.gradle.api.artifacts.VersionCatalog;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.jvm.toolchain.JavaLanguageVersion;

public class JavaConvention {

	public void apply(Project project, VersionCatalog libs) {
		String javaVersion = libs.findVersion("java")
								 .orElseThrow()
								 .getRequiredVersion();

		project.getExtensions()
			   .getByType(JavaPluginExtension.class)
			   .getToolchain()
			   .getLanguageVersion()
			   .set(JavaLanguageVersion.of(javaVersion));
	}

}
