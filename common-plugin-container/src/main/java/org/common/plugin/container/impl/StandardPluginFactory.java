package org.common.plugin.container.impl;

import org.common.plugin.container.PluginDescriptor;
import org.common.plugin.container.PluginManager;

public class StandardPluginFactory {

	public Plugin getPlugin(PluginDescriptor descriptor)
	{
		PluginManager manager = new StandardPluginManager();
		manager
		return new Plugin(descriptor);
	}

}
