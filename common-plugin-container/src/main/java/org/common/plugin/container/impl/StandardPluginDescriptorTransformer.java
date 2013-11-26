package org.common.plugin.container.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.common.plugin.container.PluginConfigurationException;
import org.common.plugin.container.PluginDescriptor;
import org.common.plugin.container.PluginDescriptorTransformer;
import org.common.plugin.container.utils.JAXBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardPluginDescriptorTransformer implements PluginDescriptorTransformer{
	
	private static Logger logger = LoggerFactory.getLogger(StandardPluginDescriptorTransformer.class);

	public PluginDescriptor transform(File f) throws PluginConfigurationException {
		try {
			PluginDescriptor descriptor = (PluginDescriptor)JAXBUtils.unmarshal(PluginDescriptor.class, new FileInputStream(f));
			descriptor.setPluginHome(f.getParent());
			return descriptor;
		} catch (FileNotFoundException e) {
			throw new PluginConfigurationException("Can not find plugin.xml file", e);
		} catch (JAXBException e) {
			throw new PluginConfigurationException("plugin.xml file config error", e);
		}
	}

}
