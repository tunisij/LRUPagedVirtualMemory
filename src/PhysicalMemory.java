import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.google.common.collect.LinkedHashMultimap;


public class PhysicalMemory {
	private static final Integer FRAME_SIZE = 16;
	private LinkedHashMultimap<Integer, Integer> physicalMemory;
	private Integer nbrOfPageFaults;

	public PhysicalMemory(){
		physicalMemory = LinkedHashMultimap.create();
		nbrOfPageFaults = 0;
	}
	
	public void insertFrame(Integer PID, Integer process) {
		if (physicalMemory.containsEntry(PID, process)) {
			if (physicalMemory.size() < FRAME_SIZE) {
				physicalMemory.remove(PID, process);
				physicalMemory.put(PID, process);
			} else {
				physicalMemory.remove(PID, process);
				physicalMemory.put(PID, process);
				nbrOfPageFaults++;
			}
		} else {
			if (physicalMemory.size() < FRAME_SIZE) {
				physicalMemory.put(PID, process);
			} else {
				physicalMemory.remove(physicalMemory.keys().toArray()[0], physicalMemory.values().toArray()[0]);
				physicalMemory.put(PID, process);
			}
			nbrOfPageFaults++;
		}
	}

	public Integer getNbrOfPageFaults() {
		return nbrOfPageFaults;
	}
	
	public LinkedHashMultimap<Integer, Integer> getFrames(){
		return physicalMemory;
	}
	
	public List<Integer> findFrameNbrs(Integer pid){
		List<Integer> frameNbrs = new ArrayList<Integer>();
		for (Integer key : physicalMemory.keys()) {
			frameNbrs.add(key);
		}
		return frameNbrs;
	}
	
	public void printMemoryTable(){
			System.out.println("Frame:\t|\tPID:\t|\tPage:");
			System.out.println("----------------------------------------");
			int i = 0;
			for (Entry<Integer, Integer> entry : physicalMemory.entries()) {
				System.out.println(i + "\t|\t " + entry.getKey() + "\t|\t" + entry.getValue());
				System.out.println("----------------------------------------");
				i++;
			}
			System.out.println();
	}
}
