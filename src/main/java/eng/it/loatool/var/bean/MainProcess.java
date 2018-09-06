package eng.it.loatool.var.bean;

import java.util.List;

public class MainProcess {
	
	private String name;
	private String processSegmentId;
	private String subProcLowerLevel;
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
	public void setSubProcesses(List<SubProcesses> subProcesses) {
		this.subProcesses = subProcesses;
	}
	public String getSubProcLowerLevel() {
		return subProcLowerLevel;
	}
	public void setSubProcLowerLevel(String subProcLowerLevel) {
		this.subProcLowerLevel = subProcLowerLevel;
	}
	

}
