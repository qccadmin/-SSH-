package action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

import biz.HouseTypeBiz;
import entity.Housetype;
@Controller
public class HouseTypeAction implements ModelDriven<Housetype>{

	private Housetype ht;
	private String msg;
	private List<Housetype> housetypelist;
	private Housetype htm =new Housetype();
	private int result;
	@Resource
	private HouseTypeBiz htbiz;
	
	public String judge(){
		if(htbiz.queryByCondition(ht.getRoom(), ht.getHall(), ht.getToilet()).size()>0){
			msg="true";
		}else{
			msg="false";
		}
		 return "judged";
	}
    public String queryAllHouseType(){
    	housetypelist=htbiz.queryAll();
    	System.out.println(housetypelist);
		return "abc";
    }
    
    public String queryById(){
    	System.out.println("��");
    	int id=ht.getTid();
    	System.out.println(id);
    	ht=htbiz.queryById(id);
		return "ok";
    }
    public String updateHouseType(){
    	System.out.println("sdailsdhalsd"+htm);
    	boolean status1=htbiz.update(htm);
		return "update";   //ҳ�����ʾ�հ�  ؽ�����
    }
    public String deleteHouseType(){
    	int id=ht.getTid();
    	System.out.println("ɾ�������id"+id);
    	System.out.println(ht);
    	boolean date=htbiz.delete(ht);
    	System.out.println("ɾ�����"+date);
    	result=1;
		return "ajax";
    }
    public String addHouseType(){
    	System.out.println("��ӵĻ���"+htm);
    	boolean addResult=htbiz.save(htm);
		return "add";  //ҳ�����ʾ�հ� ؽ�����
    }
    public String a(){
    	return "aa";
    }
	public Housetype getHt() {
		return ht;
	}

	public void setHt(Housetype ht) {
		this.ht = ht;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Housetype> getHousetypelist() {
		return housetypelist;
	}
	public void setHousetypelist(List<Housetype> housetypelist) {
		this.housetypelist = housetypelist;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	@Override
	public Housetype getModel() {
		// TODO Auto-generated method stub
		return htm;
	}
}
