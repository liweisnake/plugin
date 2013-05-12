package org.common.plugin.container;

public class PluginConfigurationException extends RuntimeException {

	public PluginConfigurationException() {
		super();
	}

	public PluginConfigurationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PluginConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PluginConfigurationException(String message) {
		super(message);
	}

	public PluginConfigurationException(Throwable cause) {
		super(cause);
	}

}
