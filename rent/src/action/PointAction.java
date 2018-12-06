package action;

import java.util.List;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import biz.HouseBiz;
import biz.HouseTypeBiz;
import biz.InformBiz;
import biz.MediumBiz;
import biz.UserBiz;
import entity.House;
import entity.Housetype;
import entity.Inform;
import entity.Medium;
import entity.User;

public class PointAction {
	private int regionid;
	private int cid;
	private Housetype ht;
	private String flag;
	@Resource
	private HouseTypeBiz htbiz;
	@Resource
	private HouseBiz hbiz;
	@Resource
	private InformBiz ibiz;
	@Resource
	private MediumBiz mbiz;
	@Resource
	private UserBiz ubiz;
	public String infoMedium(){
		int tid=0;
		List<Housetype> list = htbiz.queryByCondition(ht.getRoom(), ht.getHall(), ht.getToilet());
		for(Housetype htype :list){
			tid=htype.getTid();
		}
		
		List<Object> hlist=hbiz.queryPoint(regionid, cid, tid);
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpSession session=req.getSession();
		User u=(User) session.getAttribute("user");
		if(hlist.size()>0){
			for(Object i:hlist){
				Inform info= new Inform();
				Medium m =mbiz.queryByMid((Integer) i);
				String em = ubiz.queryById(m.getUserid().getUserid()).getEmail();
				boolean f = verifyEmail(em);
				System.out.println(f);
				info.setMediumid(new Medium((Integer) i));
				info.setUserid(u);
				info.setStatus("δ��");
				ibiz.addInform(info);
				
			}
			flag="ok";
			return "ok";
		}
		return "ok";
	}
	// ����һ������ı����ʼ�,����mail.jar��lib��
		public MimeMessage createSimpleMail(Session session, String email)  throws Exception {
				// �����ʼ�����
				MimeMessage message = new MimeMessage(session);
				// ����������
				message.setFrom(new InternetAddress("1637633817@qq.com"));
				// �ռ�������
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(
						email));
				// �ʼ�����
				message.setSubject("�ھӴ���ָ������");
				// �ʼ��ı�����
				message.setContent("���û����������˹�������", "text/html;charset=UTF-8");
				return message;

			}

			
			
			// ֱ�ӷ����ַ������棬���������ȡjspҳ�档ȥ����404�����Ҳ���ҳ�档
			public boolean verifyEmail(String email) { 
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

				try {
					message = createSimpleMail(session, email);
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
				return true;

			}
	public int getRegionid() {
		return regionid;
	}

	public void setRegionid(int regionid) {
		this.regionid = regionid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public Housetype getHt() {
		return ht;
	}

	public void setHt(Housetype ht) {
		this.ht = ht;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	
	

}
