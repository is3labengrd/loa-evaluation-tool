package eng.it.loatool.var.bean;

import java.util.List;

public class Individual {
	private String name;
	private List<Attrs> attr;
	private String className;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Attrs> getAttr() {
		return attr;
	}
	public void setAttr(List<Attrs> attr) {
		this.attr = attr;
	}

    public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	@Override
    public String toString() {
        return "Individual [name=" + name + ", attr=" + attr + "]";
    }

}
