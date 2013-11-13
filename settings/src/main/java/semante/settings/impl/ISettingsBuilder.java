package semante.settings.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.ScalarStyle;
import org.yaml.snakeyaml.Yaml;

import semante.settings.SettingsException;

public class ISettingsBuilder {
	private Map<String,Object> settings;
	
	@SuppressWarnings("unchecked")
	public ISettingsBuilder(File settingsFile) throws FileNotFoundException {
		final InputStream is = new FileInputStream(settingsFile);
	    final Yaml yaml = new Yaml();
	    settings = (Map<String,Object>) yaml.load(is);
	}
	
	public ISettingsBuilder() throws FileNotFoundException {
		this(ISettings.defaultSettingsFile());		
	}
	
	@SuppressWarnings("unchecked")
	public final void set(final String value, final Object... keys) throws SettingsException {
		
		// get the index for the last key.
		final int last = keys.length - 1;
		
		// copy over the keys, converting them to strings.
		final String[] pathTo = new String[keys.length];
		for (int i = 0; i <= last; i++) {
			pathTo[i] = keys[i].toString();
		}

		// traverse the settings tree.
		Map<String,Object> current = settings;
		for (int i = 0; i < last; i++) {
			if (!current.containsKey(pathTo[i])) {
				current.put(pathTo[i],new HashMap<String,Object>());
			}
			current = (Map<String, Object>) current.get(pathTo[i]);
		}
		current.put(pathTo[last],value);
	}

	public void dump(File targetFile) throws IOException {
	    DumperOptions options = new DumperOptions();
	    options.setDefaultScalarStyle(ScalarStyle.DOUBLE_QUOTED);
	    Yaml yaml = new Yaml(options);
		yaml.dump(settings,new FileWriter(targetFile));
	}

	public void dump() throws IOException {
		dump(ISettings.defaultSettingsFile());
	}
	
}
