package org.common.plugin.container;

public class PluginScannerException extends RuntimeException {

	public PluginScannerException() {
		super();
	}

	public PluginScannerException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PluginScannerException(String message, Throwable cause) {
		super(message, cause);
	}

	public PluginScannerException(String message) {
		super(message);
	}

	public PluginScannerException(Throwable cause) {
		super(cause);
	}

}
