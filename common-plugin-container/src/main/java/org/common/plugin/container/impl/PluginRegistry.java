package org.common.plugin.container.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class PluginRegistry {
	
	private static Map<String, Plugin> plugins = new ConcurrentHashMap<String, Plugin>();
	
	public synchronized static void regist(Plugin plugin){
		if(!plugins.containsKey(plugin.getDescriptor().getId())){
			plugins.put(plugin.getDescriptor().getId(), plugin);
		}else{
			//log
		}
	}
	
	public static Plugin getPluginById(String id){
		return plugins.get(id);
	}

}
