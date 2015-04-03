import java.util.ArrayList;
import java.util.List;


public class PCB {
	private List<Process> processes;
	
	public PCB(){
		processes = new ArrayList<Process>();
	}
	
	public void insert(Process process){
		processes.add(process);
	}
	
	public void remove(Process process){
		processes.remove(process);
	}
}
