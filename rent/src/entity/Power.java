package entity;

public class Power {
    private int powerid; //Ȩ��Id
    private String role; //�û���ɫ
    private String purl; //Ȩ��·��
	public int getPowerid() {
		return powerid;
	}
	public void setPowerid(int powerid) {
		this.powerid = powerid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPurl() {
		return purl;
	}
	public void setPurl(String purl) {
		this.purl = purl;
	}
	public Power() {
		super();
	}
	public Power(int powerid, String role, String purl) {
		super();
		this.powerid = powerid;
		this.role = role;
		this.purl = purl;
	}
	@Override
	public String toString() {
		return "Power [powerid=" + powerid + ", role=" + role + ", purl="
				+ purl + "]";
	}
	
}
