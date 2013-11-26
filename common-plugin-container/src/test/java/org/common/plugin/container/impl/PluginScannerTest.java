package org.common.plugin.container.impl;


import java.util.Collection;

import org.common.plugin.container.PluginManager;
import org.common.plugin.container.Scanner;
import org.junit.Test;

public class PluginScannerTest {

	@Test
	public void testScan() throws ClassNotFoundException {
		Scanner scanner = new StandardScanner();
		scanner.scan();
		Collection<PluginManager> cols = PluginRegistry.getAllPluginManagers();
		for(PluginManager pm : cols){
			System.out.println(pm.getPluginDescriptor());
			System.out.println(pm.getPlugin());
		}
		
	}
}
