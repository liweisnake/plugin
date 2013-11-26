package org.common.plugin.container.impl;

import org.common.plugin.container.PluginDescriptor;
import org.common.plugin.container.PluginDescriptorTransformer;
import org.common.plugin.container.PluginManager;

public class PluginFactory {

	public static PluginManager getPluginManager(PluginDescriptor descriptor){
		return new StandardPluginManager(descriptor);
	}

	public static PluginDescriptorTransformer getPluginDescriptorTransformer(){
		return new StandardPluginDescriptorTransformer();
	}
}
