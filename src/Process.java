import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Process {
	private Integer PID;
	private List<Integer> pageTable;
	private Set<Integer> uniquePages;
	private Integer nbrOfMemoryReferences;
	private Integer nbrOfPageFaults;
	
	public Process(){
		PID = null;
		pageTable = new ArrayList<Integer>();
		uniquePages = new HashSet<Integer>();
		nbrOfMemoryReferences = 0;
		nbrOfPageFaults = 0;
	}
	
	public Process(Integer PID){
		this.PID = PID;
		pageTable = new ArrayList<Integer>();
		uniquePages = new HashSet<Integer>();
		nbrOfMemoryReferences = 0;
		nbrOfPageFaults = 0;
	}

	public Integer getPID() {
		return PID;
	}

	public void setPID(Integer pID) {
		PID = pID;
	}

	public List<Integer> getPageTable() {
		return pageTable;
	}
	
	public void setPageTable(List<Integer> pageTable){
		this.pageTable = pageTable;
	}
	
	public void insertPageReference(Integer reference){
		if(pageTable.contains(reference)){
			pageTable.remove(reference);
		}
		else{
			nbrOfPageFaults++;
		}
		pageTable.add(reference);
		uniquePages.add(reference);
		nbrOfMemoryReferences++;
	}
	
	public Integer getSizeOfLogicalAddressSpace(){
		return uniquePages.size();
	}
	
	public Integer getNbrOfMemoryReferences(){
		return nbrOfMemoryReferences;
	}
	
	public void printPageTable(Integer frame){
		System.out.println("P" + PID + ": page table");
		System.out.println("--------|--------------------------");
		System.out.println("Page:\t|\tFrame:");
		System.out.println("--------|--------------------------");
		for (Integer page : pageTable) {
			System.out.println(page + "\t|\t");
			System.out.println("--------|--------------------------");
//			System.out.println();
		}
		System.out.println();
	}
}
