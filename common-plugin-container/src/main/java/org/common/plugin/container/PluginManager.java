package org.common.plugin.container;

import java.io.IOException;

import org.common.plugin.container.impl.PluginLoader;
import org.common.plugin.container.impl.PluginProxy;

public interface PluginManager {
	
	public PluginProxy getPlugin();
	
	public PluginLoader getPluginLoader() throws IOException;
	
	public PluginDescriptor getPluginDescriptor();
	
}
