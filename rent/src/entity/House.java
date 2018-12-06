package entity;

import java.util.Set;

public class House {
   private int houseid;     //��Դid
   private String housename;//��Դ����
   private int rentmoney;   //���
   private Common renttype; //��������
   private int area;        //���
   private Medium mediumid;     //������id
   private Housetype typeid;     //����
   private Common orientation;//����comid
   private int floor;         //¥��
   private Common renovate;   //װ��comid
   private String haddress;   //��ַ
   private String releaseTime;//����ʱ��
   private String hstatus;    //״̬
   private String discreption;//����
   private Region regionid;   //�������
   private String imgurl;    //�����б���ʾͼƬ
   private Set<Image> imageid;
   
	public Set<Image> getImageid() {
		return imageid;
	}
	public void setImageid(Set<Image> imageid) {
		this.imageid = imageid;
	}
	public int getHouseid() {
		return houseid;
	}
	public void setHouseid(int houseid) {
		this.houseid = houseid;
	}
	public String getHousename() {
		return housename;
	}
	public void setHousename(String housename) {
		this.housename = housename;
	}
	public int getRentmoney() {
		return rentmoney;
	}
	public void setRentmoney(int rentmoney) {
		this.rentmoney = rentmoney;
	}
	public Common getRenttype() {
		return renttype;
	}
	public void setRenttype(Common renttype) {
		this.renttype = renttype;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	
	public Medium getMediumid() {
		return mediumid;
	}
	public void setMediumid(Medium mediumid) {
		this.mediumid = mediumid;
	}
	public Housetype getTypeid() {
		return typeid;
	}
	public void setTypeid(Housetype typeid) {
		this.typeid = typeid;
	}
	public Common getOrientation() {
		return orientation;
	}
	public void setOrientation(Common orientation) {
		this.orientation = orientation;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public Common getRenovate() {
		return renovate;
	}
	public void setRenovate(Common renovate) {
		this.renovate = renovate;
	}
	public String getHaddress() {
		return haddress;
	}
	public void setHaddress(String haddress) {
		this.haddress = haddress;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getHstatus() {
		return hstatus;
	}
	public void setHstatus(String hstatus) {
		this.hstatus = hstatus;
	}
	public String getDiscreption() {
		return discreption;
	}
	public void setDiscreption(String discreption) {
		this.discreption = discreption;
	}
	public Region getRegionid() {
		return regionid;
	}
	public void setRegionid(Region regionid) {
		this.regionid = regionid;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
	
	public House() {
		super();
	}
	
	public House(int houseid, String housename, int rentmoney, Common renttype,
			int area, Medium mediumid, Housetype typeid, Common orientation,
			int floor, Common renovate, String haddress, String releaseTime,
			String hstatus, String discreption, Region regionid, String imgurl,
			Set<Image> imageid) {
		super();
		this.houseid = houseid;
		this.housename = housename;
		this.rentmoney = rentmoney;
		this.renttype = renttype;
		this.area = area;
		this.mediumid = mediumid;
		this.typeid = typeid;
		this.orientation = orientation;
		this.floor = floor;
		this.renovate = renovate;
		this.haddress = haddress;
		this.releaseTime = releaseTime;
		this.hstatus = hstatus;
		this.discreption = discreption;
		this.regionid = regionid;
		this.imgurl = imgurl;
		this.imageid = imageid;
	}
	@Override
	public String toString() {
		return "House [houseid=" + houseid + ", housename=" + housename
				+ ", rentmoney=" + rentmoney + ", renttype=" + renttype
				+ ", area=" + area + ", mediumid=" + mediumid.getMediumid() + ", typeid="
				+ typeid + ", orientation=" + orientation + ", floor=" + floor
				+ ", renovate=" + renovate + ", haddress=" + haddress
				+ ", releaseTime=" + releaseTime + ", hstatus=" + hstatus
				+ ", discreption=" + discreption + ", regionid=" + regionid
				+ ", imgurl=" + imgurl + ", imageid=" + imageid + "]";
	}
	
	
}
