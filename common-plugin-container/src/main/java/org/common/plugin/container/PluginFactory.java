package org.common.plugin.container;

import org.common.plugin.container.impl.Plugin;

public interface PluginFactory {

	public Plugin getPlugin(PluginDescriptor descriptor);
	
}
