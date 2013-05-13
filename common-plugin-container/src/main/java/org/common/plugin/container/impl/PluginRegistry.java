package org.common.plugin.container.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.common.plugin.container.PluginManager;


public class PluginRegistry {
	
	private static Map<String, PluginManager> pluginManagers = new ConcurrentHashMap<String, PluginManager>();
	
	public synchronized static void regist(PluginManager manager){
		if(!pluginManagers.containsKey(manager.getPluginDescriptor().getId())){
			pluginManagers.put(manager.getPluginDescriptor().getId(), manager);
		}else{
			//log
		}
	}
	
	public static PluginManager getPluginManagerById(String id){
		return pluginManagers.get(id);
	}

}
