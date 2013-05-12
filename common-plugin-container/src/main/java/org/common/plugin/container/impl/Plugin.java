package org.common.plugin.container.impl;

import org.common.plugin.container.PluginDescriptor;
import org.common.plugin.container.PluginManager;

public class Plugin {

	private boolean isPluginActivate = false;

	private PluginDescriptor descriptor;

	private PluginManager manager;

	public Plugin(PluginDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	public PluginDescriptor getDescriptor() {
		return descriptor;
	}

	public void start() {
		
	}

	public void end() {

	}
	
	

}
