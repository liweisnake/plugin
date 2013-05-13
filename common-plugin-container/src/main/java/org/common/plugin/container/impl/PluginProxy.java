package org.common.plugin.container.impl;

import org.common.plugin.container.Plugin;

public class PluginProxy implements Plugin{

	private boolean isPluginActivate = false;

	private Plugin pluginInstance;

	public PluginProxy() {
	}

	public void start() {
		if(pluginInstance != null && !isPluginActivate){
			pluginInstance.start();
			isPluginActivate = true;
		}
	}

	public void end() {
		if(pluginInstance != null && isPluginActivate){
			pluginInstance.end();
			isPluginActivate = false;
		}
	}
	

}
