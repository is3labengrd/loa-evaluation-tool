package eng.it.loatool.var.bean;

import java.util.List;

public class SubProcesses {
	private String name;
	private String processSegmentId;
	private int level;
	private List<SubProcesses> subProcesses;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProcessSegmentId() {
		return processSegmentId;
	}
	public void setProcessSegmentId(String processSegmentId) {
		this.processSegmentId = processSegmentId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public List<SubProcesses> getSubProcesses() {
		return subProcesses;
	}
	public void setSubProcesses(List<SubProcesses> subProcesses) {
		this.subProcesses = subProcesses;
	}
	
	
	
	
}
