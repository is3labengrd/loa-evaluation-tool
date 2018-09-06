package eng.it.util;

public enum SARProperty {
	BASE_URL("base.url");
	

	private final String text;

	private SARProperty(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}