package org.common.plugin.container.impl;

import java.net.URL;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.common.plugin.container.PluginManager;

public class PluginClassLoader extends URLClassLoader {

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
		Class<?> clazz = super.findClass(name);
		try {
			if (clazz == null) {// load class from dependency plugin
				for (String require : dependency) {
					PluginManager manager = PluginRegistry
							.getPluginManagerById(require);
					clazz = manager.getPluginClassLoader().findClass(name);
					if (clazz != null) {
						return clazz;
					}
				}
			}
			if (clazz != null)
				return clazz;
		} catch (Exception e) {

		}
		throw new ClassNotFoundException(
				MessageFormat
						.format("Can not find class {0} both in current plugin and require plugins",
								name));
	}

}
