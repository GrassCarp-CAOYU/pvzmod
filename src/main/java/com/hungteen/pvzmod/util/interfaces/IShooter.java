package com.hungteen.pvzmod.util.interfaces;

public interface IShooter {

	/**
	 * ����ʵ�巢���ӵ�
	 */
	void shootBullet();
	
	/**
	 * ��ȡ����CD һ��Ϊ30
	 * 
	 */
	int getShootSpeed();
	
	/**
	 * ��ʼ����
	 */
	void startShootAttack();
}
