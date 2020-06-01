package com.hungteen.pvzmod.util.interfaces;

import com.hungteen.pvzmod.util.enums.Plants;

public interface IPlant {
	
	/**
	 * ֲ��ö�ٵ�����
	 */
	Plants getPlantEnumName();
	
	/**
	 * ֲ�����ʱ��
	 */
	int getSuperTimeLength();
	
	/**
	 * ֲ�￨��ȴ
	 */
	int getCoolDownTime();
	
	/**
	 * ��������
	 */
	int getSunCost();
	
	/**
	 * Ѫ��
	 */
	float getLife();
}
