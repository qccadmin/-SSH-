package dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import util.HibernateUtil;
import dao.PowerDao;
import entity.Power;
@Repository
public class PowerDaoImpl implements PowerDao{
	@Resource
	private HibernateUtil util;
	//��ѯ����Ȩ����Ϣ
	@Override
	public Power queryAll(int pid) {
		String hql="from Power where powerid=��";
		Power p=util.get(Power.class, pid);
		return p;
	}

}
