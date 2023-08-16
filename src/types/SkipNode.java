
package types;

public class SkipNode {
	SkipNode next, prev, upper, lower;

	String activity;
	Integer dateTime;

	//default constructor
	public SkipNode(String a, Integer dt){
		activity = a;
		dateTime = dt;
		next = prev = upper = lower = null;
	}

	public void setNext(SkipNode n){
		this.next = n;
	}
	public void setPrev (SkipNode n){
		this.prev = n;
	}
	public void setUpper (SkipNode n){
		this.upper = n;
	}
	public void setLower (SkipNode n){
		this.lower = n;
	}

	public SkipNode getNext(){
		return this.next;
	}
	public SkipNode getPrev(){
		return this.prev;
	}
	public SkipNode getUpper (){
		return this.upper;
	}
	public SkipNode getLower (){
		return this.lower;
	}

	public String getActivity (){
		return this.activity;
	}

	public Integer getDateTime (){
		return this.dateTime;
	}


}
