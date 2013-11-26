package org.common.plugin.container;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="plugin")
public class PluginDescriptor {

	@XmlAttribute
	private String id;

	@XmlAttribute
	private String name;

	@XmlAttribute
	private String description;

	@XmlAttribute
	private String version;

	@XmlAttribute(name="class")
	private String clazz;

	private String pluginHome;

	@XmlElementWrapper(name="requires")
	@XmlElement(name="import")
	//@XmlAttribute(name="plugin")
	private List<String> requires = new ArrayList<String>();

	@XmlElementWrapper(name="runtime")
	@XmlElement(name="library")
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

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public void addRequire(String require) {
		this.requires.add(require);
	}

	public void addRuntimeLib(String lib) {
		this.runtimeLib.add(lib);
	}
	
	

	public void setRequires(List<String> requires) {
		this.requires = requires;
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

	@Override
	public String toString() {
		return "PluginDescriptor [id=" + id + ", name=" + name
				+ ", description=" + description + ", version=" + version
				+ ", clazz=" + clazz + ", pluginHome=" + pluginHome
				+ ", requires=" + requires + ", runtimeLib=" + runtimeLib + "]";
	}

	
}

