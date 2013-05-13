package org.common.plugin.container.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;

import org.apache.commons.io.filefilter.NameFileFilter;
import org.common.plugin.container.PluginDescriptor;
import org.common.plugin.container.PluginException;

public class PluginLoader {

	private final String PLUGIN_LIB_DIR = "lib";

	private PluginClassLoader classLoader;

	private PluginDescriptor descriptor;

	public PluginLoader(PluginDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	public Plugin loadPlugin() {
		String className = descriptor.getClazz();
		try {
			Class<?> pluginClazz = getPluginClassLoader().loadClass(className);
			if (pluginClazz != null) {
				Constructor con = pluginClazz
						.getConstructor(new Class[] { PluginDescriptor.class });
				Plugin plugin = (Plugin) con.newInstance(descriptor);
				if (plugin != null) {
					return plugin;
				}
			}
		} catch (Exception e) {
			throw new PluginException(e);
		}
		throw new PluginException("Can not find load plugin.");
	}

	public PluginClassLoader getPluginClassLoader() throws IOException {
		if (classLoader == null) {
			File pluginWorkDir = new File(descriptor.getPluginHome());
			File[] pluginLibDir = pluginWorkDir
					.listFiles((FileFilter) new NameFileFilter(PLUGIN_LIB_DIR));
			if (pluginLibDir.length > 0 && pluginLibDir[0].exists()
					&& pluginLibDir[0].isDirectory()
					&& pluginLibDir[0].canRead()) {
				URL url = new URL(pluginLibDir[0].getCanonicalPath());
				classLoader = new PluginClassLoader(new URL[] { url },
						descriptor.getRequires());
			}
		}
		return classLoader;
	}

}
