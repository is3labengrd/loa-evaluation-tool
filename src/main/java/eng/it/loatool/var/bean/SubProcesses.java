package eng.it.loatool.var.bean;

import java.util.List;

public class SubProcesses {
	private String name;
	private String processSegmentId;
	private String level;
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
	public List<SubProcesses> getSubProcesses() {
		return subProcesses;
	}
	public void setSubProcess(List<SubProcesses> subProcesses) {
		this.subProcesses = subProcesses;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
}
