package org.common.plugin.container.impl;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;

import org.apache.commons.io.filefilter.NameFileFilter;
import org.common.plugin.container.PluginDescriptor;
import org.common.plugin.container.PluginManager;

public class StandardPluginManager implements PluginManager {

	private final String PLUGIN_LIB_DIR = "lib";

	private PluginDescriptor descriptor;

	private PluginClassLoader classLoader;

	public StandardPluginManager(PluginDescriptor descriptor) {
		// classLoader = new PluginClassLoader(urls);
		this.descriptor = descriptor;
	}

	public void load() {

	}

	private void load(String clazzName) {
		if (classLoader == null) {
			File pluginWorkDir = new File(descriptor.getPluginHome());
			File[] pluginLibDir = pluginWorkDir
					.listFiles((FileFilter) new NameFileFilter(PLUGIN_LIB_DIR));
			if (pluginLibDir.length > 0 && pluginLibDir[0].exists()
					&& pluginLibDir[0].isDirectory()
					&& pluginLibDir[0].canRead()) {
				URL url = new URL(pluginLibDir[0].getCanonicalPath());
				classLoader = new PluginClassLoader(new URL[] { url });
			}
		}
	}

}
