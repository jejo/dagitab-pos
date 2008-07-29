package domain;

public class ReturnReason {
	private Integer returnCode;
	private String name;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public String getName() {
		return name;
	}
	public Integer getReturnCode() {
		return returnCode;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}
	
}
