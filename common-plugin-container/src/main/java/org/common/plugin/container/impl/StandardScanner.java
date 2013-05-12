package org.common.plugin.container.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.common.plugin.container.PluginDescriptor;
import org.common.plugin.container.PluginDescriptorTransformer;
import org.common.plugin.container.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardScanner implements Scanner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String PLUGIN_DIR = "plugin";

	private final String PLUGIN_CONFIG = "plugin.xml";

	public void scan() {

		URL url = this.getClass().getClassLoader().getResource("");
		String pluginPath = url.getPath() + "../" + PLUGIN_DIR;
		File pluginDir = new File(pluginPath);
		scan(pluginDir);
	}

	private void scan(File pluginDir) {
		if (pluginDir.exists()) {
			Set<String> sets = new HashSet<String>();
			File[] pluginFiles = pluginDir
					.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY);
			for (File f : pluginFiles) {
				try {
					if (scanDirectory(f)) {
						String filePathStr = f.getAbsolutePath().endsWith("/") ? f
								.getAbsolutePath().substring(0,
										f.getAbsolutePath().length() - 1) : f
								.getAbsolutePath();
						sets.add(FilenameUtils.getBaseName(filePathStr));
					}
				} catch (Exception e) {
					logger.error("Plugin not installed correctly for :"
							+ e.getMessage());
				}
			}
			pluginFiles = pluginDir.listFiles((FileFilter) FileFileFilter.FILE);
			for (File f : pluginFiles) {
				try {
					scanFile(sets, f);
				} catch (Exception e) {
					logger.error("Plugin not installed correctly:"
							+ e.getMessage());
				}
			}
		}
	}

	private boolean scanFile(Set<String> sets, File pluginZipFile)
			throws IOException, ZipException {
		ZipFile zip = new ZipFile(pluginZipFile);
		if (zip.isValidZipFile()) {
			String baseName = FilenameUtils
					.getBaseName(pluginZipFile.getName());
			if (!sets.contains(baseName)) {
				String parent = pluginZipFile.getParent();
				File dest = new File(parent, baseName);
				if (dest.isFile() || !dest.canRead())
					FileUtils.deleteQuietly(dest);
				if (!dest.exists())
					FileUtils.forceMkdir(dest);

				zip.extractAll(dest.getCanonicalPath());
				scanDirectory(dest);
				return true;
			}
		}
		return false;
	}

	private boolean scanDirectory(File pluginWorkDir) throws IOException {
		if (pluginWorkDir.isDirectory()) {
			File[] pluginFile = pluginWorkDir
					.listFiles((FileFilter) new NameFileFilter(PLUGIN_CONFIG));
			if (pluginFile.length > 0) {
				File xmlFile = pluginFile[0];
				
				if (xmlFile.isFile() && xmlFile.canRead()) {
					PluginDescriptorTransformer transformer = new StandardPluginDescriptorTransformer();
					PluginDescriptor descriptor = transformer
							.transform(FileUtils.readFileToString(xmlFile));
					Plugin plugin = new Plugin(descriptor);
					PluginRegistry.regist(plugin);
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		StandardScanner scanner = new StandardScanner();
		scanner.scan();
	}

}
