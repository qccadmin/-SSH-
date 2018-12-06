package biz;

import java.util.List;
import entity.Housetype;

public interface HouseTypeBiz {
	public List<Housetype> queryByCondition(int room,int hall,int toilet);
	public List<Housetype> queryAll();
	public boolean save(Housetype h);    //��ӻ���   �����ݿ��д�����ͬ����  ����ʾ���ʧ�� ����ĳ��ҳ��
	public boolean update(Housetype h);
	public boolean delete(Housetype h);
	public Housetype queryById(int id); //ͨ��id��ѯ����
}
