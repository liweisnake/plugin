package org.common.plugin.container.impl;

import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.JAXBException;

import org.common.plugin.container.PluginDescriptor;
import org.common.plugin.container.utils.JAXBUtils;
import org.junit.Test;

public class PluginClassLoaderTest {

	@Test
	public void testLoadClass() throws ClassNotFoundException {
		URL u = PluginClassLoaderTest.class.getClassLoader().getResource(
				"appcore.jar");
		PluginClassLoader classLoader = new PluginClassLoader(new URL[] { u },
				null);
		Class c = classLoader
				.loadClass("com.sap.cesp.creditinsight.web.app.entity.Product");
	}

	@Test
	public void testLoadPluginFile() throws JAXBException {
		InputStream is = PluginClassLoaderTest.class.getClassLoader()
				.getResourceAsStream("plugin.xml");
		Object obj = JAXBUtils.unmarshal(PluginDescriptor.class, is);
		System.out.println(obj);
	}
}
