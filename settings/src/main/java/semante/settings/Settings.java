package semante.settings;

public interface Settings {
	String get(Object... key) throws SettingsException;
}