package action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import entity.Common;
import entity.House;
import entity.Housetype;
import entity.Region;
import biz.CommonBiz;
import biz.HouseBiz;
import biz.RegionBiz;
@Controller
public class AboutAction {
    private List<Common> clist;
 	private List<Region> rlist;
 	private List<House>  houselist;
 	private House h;
 	private int result;
    @Resource
    private RegionBiz rbiz;
    @Resource
 	private CommonBiz cbiz;
    @Resource
    private HouseBiz hbiz;
 	public String queryHouse() {
		System.out.println("��ѯȫ���ķ�����Ϣ11111");
		houselist = hbiz.queryAllHouse();
		System.out.println(houselist);
		System.out.println("��ѯȫ���ķ�����Ϣ222222");
		return "good";
	}
 	public String deleteHouse(){
 		int id=h.getHouseid();  
 		System.out.println("ɾ�������id"+id);
    	boolean date=hbiz.delete(h);
    	System.out.println("ɾ�����"+date);
    	result=1;
		return "ajax";
 	}
    public String queryInfo(){ 
 		rlist=rbiz.queryAll();
		clist=cbiz.queryAll();
		return "about";       //��������
    }
    public String queryInformation(){
 		rlist=rbiz.queryAll();
		clist=cbiz.queryAll();
		return "contact";     //��ϵ����
    }
	public List<Common> getClist() {
		return clist;
	}
	public void setClist(List<Common> clist) {
		this.clist = clist;
	}
	public List<Region> getRlist() {
		return rlist;
	}
	public void setRlist(List<Region> rlist) {
		this.rlist = rlist;
	}
	public List<House> getHouselist() {
		return houselist;
	}
	public void setHouselist(List<House> houselist) {
		this.houselist = houselist;
	}
	public House getH() {
		return h;
	}
	public void setH(House h) {
		this.h = h;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
}
