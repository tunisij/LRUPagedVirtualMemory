import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

public class Processor {
	
	PCB pcb;
	
	public Processor(){
		pcb = new PCB();
	}
	
	public void execute(String[] args){
		String file = args[0];
		BufferedReader bufferedReader;
		ListMultimap<String, Integer> references = ArrayListMultimap.create();

		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			Set<Integer> uniqueProcesses = new HashSet<Integer>();
			Process process = new Process();
			String line;
			
			while((line = bufferedReader.readLine()) != null){
				String[] splitLine = line.split("\t");
				references.put(splitLine[0], binaryToDecimal(splitLine[1]));
				
				Integer processNbr = parsePID(splitLine[0]);
				uniqueProcesses.add(processNbr);
				
				if(!uniqueProcesses.contains(processNbr)){
					process.setPID(processNbr);
					pcb.insert(process);
				}
				
				if(process.getPID() == processNbr){
					process.insertPageReference(processNbr);
				}
			}
			
			
//			SetMultimap<String, Integer> referencesSet = LinkedHashMultimap.create(references);
//			
//			for (String	key : referencesSet.keySet()) {
//				System.out.println("Total size(in pages): " + key + " " + referencesSet.get(key).size());
//			}
//			
//			for (String	key : references.keySet()) {
//				System.out.println("Total references: " + key + " " + references.get(key).size());
//			}
			
			bufferedReader.close();
		} catch (IOException e) {
			System.exit(1);
		}
	}

	private static int binaryToDecimal(String binary){
		return Integer.parseInt(binary, 2); 
	}
	
	private static Integer parsePID(String process){
		String pid = process.substring(1, process.length()-1);
		return Integer.parseInt(pid);
	}
	
	public static void main(String[] args) {
		Processor processor = new Processor();
		processor.execute(args);
	}
}	
