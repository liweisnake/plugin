package org.common.plugin.container;

import java.io.IOException;

import org.common.plugin.container.impl.Plugin;
import org.common.plugin.container.impl.PluginClassLoader;

public interface PluginManager {
	
	public Plugin getPlugin();
	
	public PluginClassLoader getPluginClassLoader() throws IOException;
	
	public PluginDescriptor getPluginDescriptor();
	
}
