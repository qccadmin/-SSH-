package dao;

import java.util.List;

import entity.User;

public interface UserDao {
	//��¼���������ֺ�����
	public User queryByLogin(String name,String upwd);
	//ע��
	public boolean register(User user);
	//��¼�������ֻ��ź�����
	public User queryByPhone(String phone,String upwd);
	//�����ֻ��Ų�ѯ
	public User queryByphone(String phone);
	//�����ֻ��޸�����
	public int updateByPhone(String upwd,String phone);
	//��������
	public List<User> queryAll();
	//����ͷ��
	public int updateimg(String headimage,int userid);
	//����ID�޸�
	public boolean updateById(User u);
	//����ID��ѯ
	public User queryById(int uid);
	//����id�޸�����
	public int updateById(String upwd,int uid);
	//��¼���������������
	public User queryByEmail(String email,String upwd);
	//�ж������Ƿ����
	public User queryByemail(String email);
	//���������޸�����
	public int updateByEmail(String upwd,String email);
}
