package biz;

import java.util.List;

import entity.Medium;

public interface MediumBiz {
	//��ѯ����
	public List<Medium> queryAll();
	//����
	public boolean insertMedium(Medium m);
	public List<Medium> queryAllMedium();
	
	public Medium queryByUid(int uid);
	public Medium queryByMid(int mid);
}
