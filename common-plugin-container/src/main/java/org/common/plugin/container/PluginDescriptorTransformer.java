package org.common.plugin.container;

import java.io.File;

public interface PluginDescriptorTransformer {
	
	public PluginDescriptor transform(File file) throws PluginConfigurationException;

}
