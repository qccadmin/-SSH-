package util;

import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtil {
	@Resource(name="sessionFactory")
	private  SessionFactory factory;
	

	//��ȡ�����澲̬���е�factory
	public  SessionFactory getFactory(){
		return factory;
	}
	//�����Ự
	public  Session getSession(){
		return factory.openSession();
	}
	//���
	public  boolean add(Object obj){
		boolean flag=false;
		Session session=getSession();
		try {
			// ��������
			Transaction tran=session.beginTransaction();
			//��������
			session.save(obj);
			//�ύ����
			tran.commit();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�رջỰ
			session.close();
		}
		return flag;
	}
	//�޸�
	public  boolean update(Object obj){
		boolean flag=false;
		Session session=getSession();
		try {
			Transaction tran=session.beginTransaction();
			session.update(obj);
			tran.commit();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return flag;
	}
	//�޸Ĳ���
	public  boolean updateImg(int hid,List<Integer> alist){
		boolean flag=false;
		Session session=getSession();
		try {
			Transaction tran=session.beginTransaction();
			Query query = session.createQuery("update Image set houseid.houseid ="+hid+" where imageid IN (:alist)"); 
			query.setParameterList("alist", alist);
			query.executeUpdate();
			tran.commit();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return flag;
	}
	//ɾ�������ݹ������Ƕ���
	public  boolean delete(Object obj){
		boolean flag=false;
		Session session=getSession();
		try {
			Transaction tran=session.beginTransaction();
			session.delete(obj);
			tran.commit();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return flag;
	}
	//����ID��ѯ������Ҫ����
	public  <T> T get(Class cla,int id){
		T obj=null;
		Session session=getSession();
		try {
			obj=(T) session.get(cla,id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return obj;
	}
	//��ѯ������¼��ȫ���ֶΣ�
	public  <T> List<T> queryHQL(String hql,Object ...obj){
		List<T> list=new ArrayList<T>();
		Session session=getSession();
		try {
			Query query=session.createQuery(hql);
			if(obj!=null){
				for(int i=0;i<obj.length;i++){
					query.setString(i, obj[i].toString());
				}
			}
			list =query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	//��ѯ������¼����ҳ
	public  <T> List<T> queryHQLPage(String hql,int page,int size,Object ...obj){
		List<T> list=new ArrayList<T>();
		Session session=getSession();
		try {
			Query query=session.createQuery(hql);
			if(obj!=null){
				for(int i=0;i<obj.length;i++){
					query.setString(i, obj[i].toString());
				}
			}
			query.setFirstResult((page-1)*size);//�ӵڼ�����ʼ��ƫ����
			query.setMaxResults(size);//ÿ����ʾ������¼
			list =query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}

	/*
	 * ��ѯ������¼
	 * ȫ���ֶ�:from HUser where huname=?
	 * ĳ�����ֶ�:select new HUser(huname,husex)from HUser where huname=?
	 */
	public  <T> T queryUnique(String hql,Object ...obj){
		T object=null;
		Session session=getSession();
		try {
			Query query=session.createQuery(hql);
			if(obj!=null){
				for(int i=0;i<obj.length;i++){
					query.setString(i, obj[i].toString());
				}
			}
			object=(T) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return object;
	}
	//��ȡ����ĳһ����¼
	public  int queryCount(String hql,Object ...obj){
		int count=0;
		Session session=getSession();
		try {
			Query query=session.createQuery(hql);
			if(obj!=null){
				for(int i=0;i<obj.length;i++){
					query.setString(i, obj[i].toString());
				}
			}
			count=Integer.valueOf(query.list().get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return count;
	}
	//��װ ɾ�� �޸�
		public int queryUpdate(String hql,Object ...obj){
			int flag =0;
			Session session = getSession();
			try{
				Transaction tran = session.beginTransaction();
				Query query = session.createQuery(hql);
				for (int i = 0; i < obj.length; i++) {
					query.setString(i, obj[i].toString());
				}
				flag =query.executeUpdate();
				tran.commit();
			}catch(Exception e){
				e.printStackTrace();			
			}finally{
				session.close();
			}
			return flag;	
		}

}
