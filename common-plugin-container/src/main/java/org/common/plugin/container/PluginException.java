package org.common.plugin.container;

public class PluginException extends RuntimeException {

	public PluginException() {
		super();
	}

	public PluginException(String message, Throwable cause) {
		super(message, cause);
	}

	public PluginException(String message) {
		super(message);
	}

	public PluginException(Throwable cause) {
		super(cause);
	}

}
