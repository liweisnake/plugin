package org.common.plugin.container.impl;

import java.net.URL;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.common.plugin.container.PluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PluginClassLoader extends URLClassLoader {

	private static Logger logger = LoggerFactory
			.getLogger(PluginClassLoader.class);

	private List<String> dependency;

	public PluginClassLoader(URL[] urls, List<String> dependency) {
		super(urls);
		if (dependency == null) {
			this.dependency = new ArrayList<String>();
		} else {
			this.dependency = dependency;
		}
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		logger.trace("find plugin for: " + name);
		Class<?> clazz = super.findClass(name);
		try {
			if (clazz == null) {// load class from dependency plugin
				for (String require : dependency) {
					PluginManager manager = PluginRegistry
							.getPluginManagerById(require);
					logger.trace("try to find plugin from dependency: "
							+ require);
					clazz = manager.getPluginLoader().getPluginClassLoader().findClass(name);
					if (clazz != null) {
						return clazz;
					}
				}
			}
			if (clazz != null)
				return clazz;
		} catch (Exception e) {
			logger.error("Can not find class, detail is:" + e.getMessage());
		}
		throw new ClassNotFoundException(
				MessageFormat
						.format("Can not find class {0} both in current plugin and require plugins",
								name));
	}

}
