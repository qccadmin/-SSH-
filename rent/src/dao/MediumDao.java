package dao;

import java.util.List;

import entity.Medium;

public interface MediumDao {
	//��ѯ����
	public List<Medium> queryAll();
	//����
	public boolean insertMedium(Medium m);
    public List<Medium> queryAllMedium(String hql,Object ...obj);
	public Medium queryByUid(int uid);
	public Medium queryByMid(int mid);
}
