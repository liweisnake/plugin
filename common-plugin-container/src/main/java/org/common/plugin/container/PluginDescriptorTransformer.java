package org.common.plugin.container;

public interface PluginDescriptorTransformer {
	
	public PluginDescriptor transform(String pluginXml) throws PluginConfigurationException;

}
