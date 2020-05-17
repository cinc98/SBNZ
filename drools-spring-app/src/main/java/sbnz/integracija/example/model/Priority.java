package sbnz.integracija.example.model;

public class Priority {
	private int maxNum;
	private int priority;
	private boolean allSatisfied;
	
	public Priority() {
		
	}
	
	public Priority(int maxNum) {
		this.maxNum = maxNum;
		this.priority = 0;
		this.allSatisfied = false;
	}
	
	public void addModel() {
		if (allSatisfied) {
			this.priority += 5;
		}
		else {
			this.priority++;
			if(this.priority == this.maxNum) {
				this.allSatisfied = true;
			}
		}
	}
	
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isAllSatisfied() {
		return allSatisfied;
	}
	public void setAllSatisfied(boolean allSatisfied) {
		this.allSatisfied = allSatisfied;
	}
}
