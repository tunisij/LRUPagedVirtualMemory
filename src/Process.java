import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;


public class Process {
	private Integer PID;
	private Integer sizeOfLogicalAddressSpace;
	private Stack<Integer> pageReferences;
	private Set<Integer> uniquePages;
	private PageTable pageTable;
	
	public Process(){
		PID = null;
		sizeOfLogicalAddressSpace = null;
		pageReferences = new Stack<Integer>();
		uniquePages = new HashSet<Integer>();
		pageTable = new PageTable();
	}
	
	public Process(Integer PID){
		this.PID = PID;
		sizeOfLogicalAddressSpace = null;
		pageReferences = new Stack<Integer>();
		uniquePages = new HashSet<Integer>();
		pageTable = new PageTable();
	}
	
	public Process(Integer PID, Integer sizeOfLogicalAddressSpace){
		this.PID = PID;
		this.sizeOfLogicalAddressSpace = sizeOfLogicalAddressSpace;
		pageReferences = new Stack<Integer>();
		uniquePages = new HashSet<Integer>();
		pageTable = new PageTable();
	}

	public Integer getPID() {
		return PID;
	}

	public void setPID(Integer pID) {
		PID = pID;
	}

	public void insertPageReference(Integer pageReference){
		pageReferences.push(pageReference);
		uniquePages.add(pageReference);
	}
	
	public void insertPageReferences(List<Integer> pageReferences){
		pageReferences.addAll(pageReferences);
		uniquePages.addAll(pageReferences);
	}
	
	public Integer getNbrPageReferences(){
		return pageReferences.size();
	}
	
	public Integer getSizeOfLogicalAddressSpace() {
		return sizeOfLogicalAddressSpace;
	}

	public void setSizeOfLogicalAddressSpace(Integer sizeOfLogicalAddressSpace) {
		this.sizeOfLogicalAddressSpace = sizeOfLogicalAddressSpace;
	}

	public PageTable getPageTable() {
		return pageTable;
	}

	public Set<Integer> getUniquePages() {
		return uniquePages;
	}
	
	public Integer getLeastRecentReferencePage(){
		Integer leastRecent = -1;
		for (Integer integer : uniquePages) {
			if(pageReferences.search(integer) > leastRecent){
				leastRecent = pageReferences.search(integer);
			}
		}
		return leastRecent;
	}
	
	public Integer getMostRecentReferencePage(){
		return pageReferences.peek();
	}
}
