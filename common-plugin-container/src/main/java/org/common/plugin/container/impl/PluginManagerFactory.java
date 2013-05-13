package org.common.plugin.container.impl;

import org.common.plugin.container.PluginDescriptor;
import org.common.plugin.container.PluginManager;

public class PluginManagerFactory {

	public static PluginManager getPluginManager(PluginDescriptor descriptor)
	{
		return new StandardPluginManager(descriptor);
	}

}
