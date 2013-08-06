package semante.settings;

import java.util.Arrays;

@SuppressWarnings("serial")
public final class SettingsException extends RuntimeException {

	public SettingsException(String setting) {
		super(setting);
	}
	
	public SettingsException(Object[] setting) {
		this(Arrays.toString(setting));
	}
	
}
