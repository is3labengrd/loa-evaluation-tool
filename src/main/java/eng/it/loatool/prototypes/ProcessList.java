package eng.it.loatool.prototypes;

import java.util.ArrayList;

public class ProcessList {

	private static ProcessList example;
	static {

		ProcessList.example = new ProcessList();

		Process process1A = new Process();
		process1A.setName("Process 1");
		process1A.setSublevels(3);
		ProcessList.example.processList.add(process1A);

		Process process1B = new Process();
		process1B.setName("Process 1");
		process1B.setSublevels(3);
		process1B.setSub1("P1.1");
		process1B.setSub2("P1.1.1");
		process1B.setSub2("P1.2.1");
		ProcessList.example.processList.add(process1B);

		Process process1C = new Process();
		process1C.setName("Process 1");
		process1C.setSublevels(3);
		process1C.setSub1("P1.2");
		process1C.setSub2("P1.2.1");
		process1C.setSub2("P1.2.2");
		ProcessList.example.processList.add(process1C);

		Process process2A = new Process();
		process2A.setName("Process 2");
		process2A.setSublevels(3);
		ProcessList.example.processList.add(process1C);

	}
	
	public static ProcessList getExample() {
		return ProcessList.example;
	}

	public ArrayList<Process> getProcesses() {
		return this.processList;
	}

	public ProcessList() {
		this.processList = new ArrayList<Process>();
	}

	private ArrayList<Process> processList;

}
