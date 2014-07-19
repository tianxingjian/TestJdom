package nc.ui.pub.file;

public class ImpDataVO {
	String key;
	String[][] array;

	public ImpDataVO(String key, String[][] array) {
		this.key = key;
		this.array = array;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String[][] getArray() {
		return array;
	}

	public void setArray(String[][] array) {
		this.array = array;
	}
}
