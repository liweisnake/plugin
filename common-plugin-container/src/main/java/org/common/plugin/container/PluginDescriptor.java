package org.common.plugin.container;

import java.util.ArrayList;
import java.util.List;


public class PluginDescriptor {
	
	private String id;
	
	private String name;
	
	private String description;
	
	private String version;
	
	private String pluginHome;
	
	private List<String> requires = new ArrayList<String>();
	
	private List<String> runtimeLib = new ArrayList<String>(); 
	
	public PluginDescriptor() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public void addRequire(String require){
		this.requires.add(require);
	}

	public void addRuntimeLib(String lib){
		this.runtimeLib.add(lib);
	}

	public List<String> getRequires() {
		return requires;
	}

	public List<String> getRuntimeLib() {
		return runtimeLib;
	}
	
	public String getPluginHome() {
		return pluginHome;
	}

	public void setPluginHome(String pluginHome) {
		this.pluginHome = pluginHome;
	}
	
}
