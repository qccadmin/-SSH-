package biz;

import java.util.List;
import entity.Region;

public interface RegionBiz {
	public List<Region> queryAll();
	public boolean save(Region r);  //���������Ϣ
	public boolean update(Region r);//�޸�������Ϣ
	public boolean delete(Region r);//ɾ��������Ϣ  ������ɾ��  ��������������з���ɾ��
	public Region queryById(int id);
}
