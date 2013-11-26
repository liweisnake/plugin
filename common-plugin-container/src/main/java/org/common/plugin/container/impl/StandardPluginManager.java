package org.common.plugin.container.impl;

import java.io.IOException;

import org.common.plugin.container.PluginDescriptor;
import org.common.plugin.container.PluginManager;

public class StandardPluginManager implements PluginManager {

	private PluginDescriptor descriptor;
	
	private PluginLoader loader;
	
	private PluginProxy plugin;

	public StandardPluginManager(PluginDescriptor descriptor) {
		// classLoader = new PluginClassLoader(urls);
		this.descriptor = descriptor;
		loader = new PluginLoader(descriptor);
	}
	
	public void load() {
		plugin = loader.loadPlugin();
	}
	
	public void reload(){
		loader = new PluginLoader(descriptor);
		load();
	}
	
	public void activate(){
		if(plugin == null){
			load();
		}
		plugin.start();
	}

	public PluginLoader getPluginLoader() throws IOException {
		return loader;
	}

	public PluginDescriptor getPluginDescriptor() {
		return descriptor;
	}

	public PluginProxy getPlugin(){
		activate();
		return plugin;
	}
}
