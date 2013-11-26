package org.common.plugin.container.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;

import org.apache.commons.io.filefilter.NameFileFilter;
import org.common.plugin.container.PluginDescriptor;
import org.common.plugin.container.PluginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PluginLoader {
	
	private static Logger logger = LoggerFactory.getLogger(PluginLoader.class);

	private final String PLUGIN_LIB_DIR = "lib";

	private PluginClassLoader classLoader;

	private PluginDescriptor descriptor;

	public PluginLoader(PluginDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	public PluginProxy loadPlugin() {
		String className = descriptor.getClazz();
		try {
			Class<?> pluginClazz = getPluginClassLoader().loadClass(className);
			if (pluginClazz != null) {
				Constructor<?> con = pluginClazz
						.getConstructor(new Class[] { PluginDescriptor.class });
				PluginProxy plugin = (PluginProxy) con.newInstance(descriptor);
				if (plugin != null) {
					return plugin;
				}
			}
		} catch (Exception e) {
			throw new PluginException(e);
		}
		throw new PluginException("Can not find plugin for " + className);
	}

	public PluginClassLoader getPluginClassLoader() throws IOException {
		if (classLoader == null) {
			File pluginWorkDir = new File(descriptor.getPluginHome());
			File[] pluginLibDir = pluginWorkDir
					.listFiles((FileFilter) new NameFileFilter(PLUGIN_LIB_DIR));
			if (pluginLibDir.length > 0 && pluginLibDir[0].exists()
					&& pluginLibDir[0].isDirectory()
					&& pluginLibDir[0].canRead()) {
				File [] jarFiles = pluginLibDir[0].listFiles();
				URL[] urls = new URL[jarFiles.length];
				for (int j = 0; j < jarFiles.length; j++) {
					urls[j] = jarFiles[j].toURI().toURL();
				}
				classLoader = new PluginClassLoader(urls,
						descriptor.getRequires());
			}
		}
		return classLoader;
	}

}
