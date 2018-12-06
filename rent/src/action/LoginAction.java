package action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Medium;
import entity.Power;
import entity.Region;
import entity.User;
import biz.MediumBiz;
import biz.PowerBiz;
import biz.RegionBiz;
import biz.UserBiz;

@Controller
public class LoginAction extends ActionSupport{
	
	@Resource
	private UserBiz userBiz;
	@Resource
	private PowerBiz powerBiz;
	@Resource
	private MediumBiz mediumBiz;
	@Resource
	private RegionBiz regionBiz;
	private int userid;
	private String username;
	private String password;
	private User user;
	private String phone;
	private String email; 
	private String msg;
	private List<User> list;
	private File myfile;  
	private String headimage;
	private String myfileContentType;  //�ļ����ͣ�ContentType�̶���ʽ
	private String myfileFileName;    // �ļ����ƣ�FileName�̶���ʽ
	private String savePath;   //ͨ��Struts_user.xml�����ļ�ע��ĵ�ַ
	private Power power;
	private List<Medium> mlist;
	private String quyu;
	private List<Region> regionlist;
	private String regionname;
	private String email2;//��ȡ����д��������֤��
	private String cunt;
	private String msg2;
	//��¼
	public String login(){
		String ph = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$";//�ж������������ʽ
	//	System.out.println("---"+username);
		if(username.matches(ph)){//�Ƿ������ֻ��ŵ�¼
			user = userBiz.queryByEmail(username, password);
		}else{
			user=userBiz.queryByLogin(username,password);
		}
		System.out.println("----"+user);
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpSession session=req.getSession();
		if(user!=null && user.getPowerid().getPowerid()==1){
			session.setAttribute("user",user);
			username=user.getUsername();
			return "houtai";
		}
		else if(user!=null){
			
			msg="��ӭ����"+username;
			session.setAttribute("user", user);
			System.out.println("user:"+user);
			return "login";
		}
		else{
			msg="�û�����������������µ�¼��";
			return "no";
		}
	}
	//ע��ʱ��֤�����Ƿ��Ѿ�ע���
	public String email(){
		user=userBiz.queryByemail(email);
		if(user==null){
			msg2="true";
		} else{
	    	msg2="false";
	    }
		return "ajaxemail";
	}
	//ע��
	public String register(){
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpSession session=req.getSession();
		cunt=(String) session.getAttribute("cunt");
			if (email2.equalsIgnoreCase(cunt)) {// �����д����֤��ͷ��͵���֤��һ��
				Power p = new Power();
				p.setPowerid(3);
				User u = new User();
				// u.setUsername(username);
				u.setPassword(password);
				// u.setPhone(phone);
				u.setEmail(email);
				u.setPowerid(p);
				boolean flag;
				flag = userBiz.register(u);
				// System.out.println("---"+flag);
				if (flag) {
					msg = "ע��ɹ���";
					return "ok2";
				} else {
					msg = "ע��ʧ�ܣ�";
					return "no";
				}
				
			} else {
				msg = "��֤�����";
				return "no";
			}
	}
	//��������
	public String forget(){
		user=userBiz.queryByphone(phone);
		System.out.println("user:"+user);
		if(user!=null){
		//	System.out.println(user.getPhone());
			if(user.getPhone()==null){
				System.out.println("--"+user.getPhone()==null);
			msg="�ֻ��Ų����ڡ�";
			return "no2";
			}
			HttpServletRequest req=ServletActionContext.getRequest();
			HttpSession session=req.getSession();
			session.setAttribute("phone", phone);
			return "ok3";
	  }else{
		  msg="�û������ڡ�";
		  return "no2";
	  }
	}
	//��������ʱ��֤�����Ƿ��Ѿ�ע���
		public String email2(){
			user=userBiz.queryByemail(email);
			if(user!=null){
				msg2="true";
			} else{
		    	msg2="false";
		    }
			return "ajaxemail2";
		}
	//��������:������֤
		public String forget2(){
			HttpServletRequest req=ServletActionContext.getRequest();
			HttpSession session=req.getSession();
			cunt=(String) session.getAttribute("cunt");
			user=userBiz.queryByemail(email);
			//System.out.println("user:"+user);
			if(user!=null){
			if (email2.equalsIgnoreCase(cunt)) {// �����д����֤��ͷ��͵���֤��һ��
				session.setAttribute("email", email);
				return "ok3";
				}else {
					msg = "��֤�����";
					return "no2";
					}
			}else {
			msg = "�û������ڡ�";
			return "no2";
			}
		}
	//�һ������е��޸�����
	public String updateupwd(){
		//int n=userBiz.updateByPhone(password, phone);
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpSession session=req.getSession();
		email=(String) session.getAttribute("email");
		int n=userBiz.updateByEmail(password, email);
		if(n>0){
			session.removeAttribute("user");
			user=userBiz.queryByemail(email);
			session.setAttribute("user", user);
			msg="�޸�����ɹ�";
			return "ok2";
		}else{
			msg="�޸�����ʧ��";
			return "no3";
		}
	}
	//�ϴ�ͷ��
	public String updateimage(){
		System.out.println("-----");
		System.out.println("�ļ����ͣ�"+myfileContentType);
		System.out.println("�ļ����ƣ�"+myfileFileName);
		ServletContext app=ServletActionContext.getServletContext();
		String path=app.getRealPath(savePath);
		System.out.println("ע���ַ��"+path);
		File filepath=new File(path);  //�ļ���ַ
		if(!filepath.exists()){     //����ļ���ַû��
			filepath.mkdir();      //�����ļ���ַ
		}
		if(myfile==null || myfile.length()<=0){
			msg="�ļ���������������ϴ���";
			return "no4";
		}
		//���ļ����ָ��ݵ�ǰʱ���������������
		myfileFileName=new Date().getTime()+"_"+myfileFileName; 
		try {
			//�ϴ��ļ�
			FileUtils.copyFile(myfile, new File(path, myfileFileName));
			msg="�ļ��ϴ��ɹ�";
			HttpServletRequest req=ServletActionContext.getRequest();
			HttpSession session=req.getSession();
			User u=(User) session.getAttribute("user");
			int n=userBiz.updateimg("upload/"+myfileFileName, u.getUserid());
			System.out.println("n======="+n);
			if(n>0){
		    	session.removeAttribute("user");
				user = userBiz.queryById(u.getUserid());
				System.out.println("===="+user);
				session.setAttribute("user", user);
				msg="�޸ĳɹ�";
			}else{
				msg="�޸�ʧ��";
			}
			return "ok4";
			
		} catch (Exception e) {
			e.printStackTrace();
			msg="�쳣��";
			return "no4";
		}

	}
	//�޸ĸ�����Ϣ
	public String updateById(){
		boolean flag;
		//System.out.println("�޸���Ϣ����������");
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpSession session=req.getSession();
		User u=(User) session.getAttribute("user");
		user.setUserid(u.getUserid());
		user.setPassword(u.getPassword());
		user.setHeadimage(u.getHeadimage());
		user.setPowerid(u.getPowerid());
		user.setEmail(u.getEmail());
		flag=userBiz.updateById(user);
		System.out.println("flag"+flag);
		if(flag){
			session.removeAttribute("user");
			user = userBiz.queryById(user.getUserid());
			System.out.println("===="+user);
			session.setAttribute("user", user);
			msg="�޸ĳɹ���";
			return "ziliao";
		}else{
			msg="�޸�ʧ�ܡ�";
			return "no5";
		}
		
	}
	//�˺���������
	public String updateupwd2(){
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpSession session=req.getSession();
		User u=(User) session.getAttribute("user");
		int n=userBiz.updateById(user.getPassword(), u.getUserid());
		if(n>0){
			session.removeAttribute("user");
			user = userBiz.queryById(u.getUserid());			
			session.setAttribute("user", user);
			msg= "�����޸ĳɹ�";
			return "ok5";
		}else{
			msg= "�����޸ĳɹ�";
			return "no6";
		}		
	}
	//��ȡ������Ϣ
	public String shenqing(){
		regionlist=regionBiz.queryAll();
		if(regionlist!=null){
			HttpServletRequest req=ServletActionContext.getRequest();
			HttpSession session=req.getSession();
			session.setAttribute("regionlist", regionlist);
		}
		return "ok6";
	}
	//�������ɾ�����
	public String shenqing2(){
		boolean flag;
		boolean n;
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpSession session=req.getSession();
		User u=(User) session.getAttribute("user");
		regionlist=(List<Region>) session.getAttribute("regionlist");//��ȡ������Ϣ
		/*int mid=0;
		String quyu = null;
		for(int i=0;i<mlist.size();i++){
		mid=mlist.get(i).getMediumid();
		quyu=mlist.get(i).getQuyu();
		}
		Medium m=new Medium();
		m.setMediumid(mid);
		m.setQuyu(quyu);
		m.setUserid(user);*/
		Medium m=new Medium();
		m.setQuyu(regionname);
		m.setUserid(user);
		user.setUserid(u.getUserid());
	    user.setPhone(u.getPhone());
		user.setFollow(u.getFollow());
		user.setPassword(u.getPassword());
		user.setHeadimage(u.getHeadimage());
		user.setProfession(u.getProfession());
		user.setEmail(u.getEmail());
		Power power=new Power();
		power.setPowerid(2);
		user.setPowerid(power);//���뾭����-�޸�Ȩ��IDΪ2
		
		flag=userBiz.updateById(user);
		n=mediumBiz.insertMedium(m);
		if(flag && n){
			session.removeAttribute("user");
			user = userBiz.queryById(u.getUserid());
			//System.out.println("===="+user);
			session.setAttribute("user", user);
			msg="����ɹ���";
			return "shenqing";
		}else{
			msg="����ʧ�ܡ�";
			return "shenqing";
		}
	}
	// ����һ������ı����ʼ�,����mail.jar��lib��
	public MimeMessage createSimpleMail(Session session, String email,
			String stringBuilder)  throws Exception {
			// �����ʼ�����
			MimeMessage message = new MimeMessage(session);
			// ����������
			message.setFrom(new InternetAddress("1637633817@qq.com"));
			// �ռ�������
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(
					email));
			// �ʼ�����
			message.setSubject("�ھӴ����˺�ע��");
			// �ʼ��ı�����
			message.setContent("��ӭ��ע���ھӴ���,�˺�ע����֤��Ϊ(һ������Ч):" + stringBuilder
					+ ",��Ǳ���,�뾡���޸�����,����ظ�������", "text/html;charset=UTF-8");
			return message;

		}

		
		
		// ֱ�ӷ����ַ������棬���������ȡjspҳ�档ȥ����404�����Ҳ���ҳ�档
		public String verifyEmail() { 
			System.out.println("email:"+email);
			Properties prop = new Properties();
			// ����debug����
			prop.setProperty("mail.debug", "true");
			// �����ʼ�������������
			prop.setProperty("mail.host", "smtp.qq.com");
			// ���ͷ�������Ҫ�������֤
			prop.setProperty("mail.smtp.auth", "true");
			// �����ʼ�Э��
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.put("mail.smtp.ssl.enable", "true");
			Session session = Session.getInstance(prop);
			Transport ts = null;
			MimeMessage message = null;
			try {
				ts = session.getTransport();
			} catch (NoSuchProviderException e1) {
				e1.printStackTrace();
			}

			try {
				ts.connect("smtp.qq.com", "1637633817", "wdfsnduxjaljbeii");
			} catch (MessagingException e) {
				e.printStackTrace();
			}

			String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			StringBuilder stringBuilder = new StringBuilder(6);
			for (int i = 0; i < 6; i++) {
				char ch = str.charAt(new Random().nextInt(str.length()));
				stringBuilder.append(ch);
			}
			try {
				message = createSimpleMail(session, email, stringBuilder.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			// �����ʼ�
			try {
				ts.sendMessage(message, message.getAllRecipients());
				session.setDebug(true);
				ts.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			cunt = stringBuilder.toString();
			HttpServletRequest req=ServletActionContext.getRequest();
			HttpSession session1=req.getSession();
			session1.setAttribute("cunt", cunt);
			msg=cunt;
			return "ajax";

		}
		//�˳���¼
		public String logout(){
			System.out.println("�˳�");
			ActionContext.getContext().getSession().remove("user");
			return "ok2";
		}
		
		
	public String getMsg2() {
			return msg2;
		}

		public void setMsg2(String msg2) {
			this.msg2 = msg2;
		}

	public String getEmail2() {
			return email2;
		}
		public void setEmail2(String email2) {
			this.email2 = email2;
		}
	public String getCunt() {
			return cunt;
		}
		public void setCunt(String cunt) {
			this.cunt = cunt;
		}
	public List<Region> getRegionlist() {
		return regionlist;
	}
	public void setRegionlist(List<Region> regionlist) {
		this.regionlist = regionlist;
	}
	public String getRegionname() {
		return regionname;
	}
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	public String getQuyu() {
		return quyu;
	}
	public void setQuyu(String quyu) {
		this.quyu = quyu;
	}
	public List<Medium> getMlist() {
		return mlist;
	}
	public void setMlist(List<Medium> mlist) {
		this.mlist = mlist;
	}
	public Power getPower() {
		return power;
	}
	public void setPower(Power power) {
		this.power = power;
	}
	public File getMyfile() {
		return myfile;
	}
	public void setMyfile(File myfile) {
		this.myfile = myfile;
	}
	public String getMyfileContentType() {
		return myfileContentType;
	}
	public void setMyfileContentType(String myfileContentType) {
		this.myfileContentType = myfileContentType;
	}
	public String getMyfileFileName() {
		return myfileFileName;
	}
	public void setMyfileFileName(String myfileFileName) {
		this.myfileFileName = myfileFileName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getHeadimage() {
		return headimage;
	}
	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}
	
	
 
}
