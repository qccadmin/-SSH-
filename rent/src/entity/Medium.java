package entity;

import java.util.Set;

public class Medium {
     private int mediumid;
     private User userid;     //�û������
     private String quyu;     //����
     private Set<House> houseid;   //��Դ���
	public int getMediumid() {
		return mediumid;
	}
	public void setMediumid(int mediumid) {
		this.mediumid = mediumid;
	}
	public User getUserid() {
		return userid;
	}
	public void setUserid(User userid) {
		this.userid = userid;
	}
	
	public String getQuyu() {
		return quyu;
	}
	public void setQuyu(String quyu) {
		this.quyu = quyu;
	}

	public Set<House> getHouseid() {
		return houseid;
	}
	public void setHouseid(Set<House> houseid) {
		this.houseid = houseid;
	}
	
	
	public Medium() {
		super();
	}
	

	public Medium(int mediumid, User userid, String quyu, Set<House> houseid) {
		super();
		this.mediumid = mediumid;
		this.userid = userid;
		this.quyu = quyu;
		this.houseid = houseid;
	}
/*	@Override
	public String toString() {
		return "Medium [mediumid=" + mediumid + ", userid=" + userid.getUserid()
				+ ", quyu=" + quyu + ", houseid=" + houseid + "]";
	}*/
	public Medium(int mediumid) {
		super();
		this.mediumid = mediumid;
	}
	
	
}
