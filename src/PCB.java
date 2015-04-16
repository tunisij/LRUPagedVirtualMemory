import java.util.HashSet;
import java.util.Set;


public class PCB {
	private Set<Process> processes;
	
	public PCB(){
		processes = new HashSet<Process>();
	}
	
	public void insert(Process process){
		processes.add(process);
	}
	
	public void remove(Process process){
		processes.remove(process);
	}
	
	public Process getProcess(Integer PID){
		for (Process process : processes) {
			if(process.getPID() == PID){
				return process;
			}
		}
		return null;
	}
	
	public Set<Process> getProcesses(){
		return processes;
	}
}
