import java.util.ArrayList;
import java.util.List;


public class Process {
	private Integer PID;
	private Integer sizeOfLogicalAddressSpace;
	private List<Integer> pageReferences;
	private PageTable pageTable;
	
	public Process(){
		PID = null;
		sizeOfLogicalAddressSpace = null;
		pageReferences = new ArrayList<Integer>();
		pageTable = new PageTable();
	}
	
	public Process(Integer PID){
		this.PID = PID;
		sizeOfLogicalAddressSpace = null;
		pageReferences = new ArrayList<Integer>();
		pageTable = new PageTable();
	}

	public Integer getPID() {
		return PID;
	}

	public void setPID(Integer pID) {
		PID = pID;
	}

	public void insertPageReference(Integer pageReference){
		pageReferences.add(pageReference);
	}
	
	public void insertPageReferences(List<Integer> pageReferences){
		this.pageReferences.addAll(pageReferences);
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
}
