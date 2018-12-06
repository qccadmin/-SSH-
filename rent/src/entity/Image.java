package entity;

public class Image {
    private int imageid;      //ͼƬid
    private String urladdress;//ͼƬ��ַ
    private House  houseid;   //��Դid
	public int getImageid() {
		return imageid;
	}
	public void setImageid(int imageid) {
		this.imageid = imageid;
	}
	public String getUrladdress() {
		return urladdress;
	}
	public void setUrladdress(String urladdress) {
		this.urladdress = urladdress;
	}
	public House getHouseid() {
		return houseid;
	}
	public void setHouseid(House houseid) {
		this.houseid = houseid;
	}
	public Image() {
		super();
	}
	public Image(int imageid, String urladdress, House houseid) {
		super();
		this.imageid = imageid;
		this.urladdress = urladdress;
		this.houseid = houseid;
	}
	@Override
	public String toString() {
		return "Image [imageid=" + imageid + ", urladdress=" + urladdress
				+ ", houseid=" + houseid.getHouseid() + "]";
	}
	public Image(String urladdress) {
		super();
		this.urladdress = urladdress;
	}
	
}
