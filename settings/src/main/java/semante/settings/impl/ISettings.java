package semante.settings.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import org.yaml.snakeyaml.Yaml;

import semante.settings.Settings;
import semante.settings.SettingsException;

@ToString
@RequiredArgsConstructor
public final class ISettings implements Settings {
	
	private final Map<String,Object> settings;
	
	@SuppressWarnings("unchecked")
	public final String get(final Object... keys) throws SettingsException {
		
		// get the index for the last key.
		val last = keys.length - 1;
		
		// copy over the keys, converting them to strings.
		final String[] pathTo = new String[keys.length];
		for (int i = 0; i <= last; i++) {
			pathTo[i] = keys[i].toString();
		}
			
		// traverse the settings tree.
		try {
			Map<String,Object> current = settings;
			for (int i = 0; i < last; i++) {
				current = (Map<String, Object>) current.get(pathTo[i]);
			}
			return (String) current.get(pathTo[last]);
		}
		
		// apparently the path is incorrect.
		catch (ClassCastException e) {
			throw new SettingsException(pathTo);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ISettings(File settingsFile) throws FileNotFoundException {
		final InputStream is = new FileInputStream(settingsFile);
	    final Yaml yaml = new Yaml();
	    settings = (Map<String,Object>) yaml.load(is);
	}
	
	public ISettings() throws FileNotFoundException {
		this(defaultSettingsFile());
	}
	
	public static File defaultSettingsFile() {
		return new File(new File(System.getProperty("user.home"), ".semante"), "settings.yml");
	}
}
