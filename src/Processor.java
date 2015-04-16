import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Processor {
	
	PCB pcb;
	PhysicalMemory physicalMemory;
	
	public Processor(){
		pcb = new PCB();
		physicalMemory = new PhysicalMemory();
	}
	
	public void execute(String[] args){
		String file = args[0];
		BufferedReader bufferedReader;

		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String line;
			
			while((line = bufferedReader.readLine()) != null){
				String[] splitLine = line.split("\t");
				physicalMemory.insertFrame(parsePID(splitLine[0]), binaryToDecimal(splitLine[1]));
				if(pcb.getProcess(parsePID(splitLine[0])) != null){
					pcb.getProcess(parsePID(splitLine[0])).insertPageReference(binaryToDecimal(splitLine[1]));
				}
				else{
					Process process = new Process();
					process.setPID(parsePID(splitLine[0]));
					process.setPageTable(new ArrayList<Integer>());
					process.insertPageReference(binaryToDecimal(splitLine[1]));
					pcb.insert(process);
				}
				physicalMemory.printMemoryTable();
//				printPageTable();
			}
			bufferedReader.close();
		} catch (IOException e) {
			System.exit(1);
		}
		printStats();
	}
	
	public void printPageTable(){
		for (Process process : pcb.getProcesses()) {
			System.out.println("P" + process.getPID() + ": page table");
			System.out.println("--------|--------------------------");
			System.out.println("Page:\t|\tFrame:");
			System.out.println("--------|--------------------------");
			printPageTableEntry(process, physicalMemory.findFrameNbrs(process.getPID()));
			System.out.println();
		}
	}
	
	private void printPageTableEntry(Process process, List<Integer> frameNbrs) {
		for (Integer pid : process.getPageTable()) {
			System.out.println(pid + "\t|\t" + 0);
			System.out.println("--------|--------------------------");
		}
	}

	private static int binaryToDecimal(String binary){
		return Integer.parseInt(binary, 2); 
	}
	
	private static Integer parsePID(String process){
		String pid = process.substring(1, process.length()-1);
		return Integer.parseInt(pid);
	}
	
	private void printStats(){
		for (Process process : pcb.getProcesses()) {
			System.out.println("PID: " + process.getPID());
			System.out.println("Size: " + process.getSizeOfLogicalAddressSpace() + " pages");
			System.out.println("References: " + process.getNbrOfMemoryReferences());
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Processor processor = new Processor();
		processor.execute(args);
	}
}	
