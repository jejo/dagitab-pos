package domain;

public class Clerk {
	
	private Long clerkCode;
	private String firstName;
	private String lastName;
	private String nickName;
	private String birthDate;
	private String address;
	private String contactNo;
	private String position;
	private String title;
	private String other;
	private String password;
	private Integer userLevel;
	private String username;
	
	public String getAddress() {
		return address;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public Long getClerkCode() {
		return clerkCode;
	}
	public String getContactNo() {
		return contactNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getNickName() {
		return nickName;
	}
	public String getOther() {
		return other;
	}
	public String getPassword() {
		return password;
	}
	public String getPosition() {
		return position;
	}
	public String getTitle() {
		return title;
	}
	public Integer getUserLevel() {
		return userLevel;
	}
	public String getUsername() {
		return username;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public void setClerkCode(Long clerkCode) {
		this.clerkCode = clerkCode;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
