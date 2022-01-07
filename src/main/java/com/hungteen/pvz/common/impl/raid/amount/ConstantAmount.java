package com.hungteen.pvz.common.impl.raid.amount;

import com.google.gson.JsonElement;
import com.hungteen.pvz.api.raid.IAmountComponent;
import net.minecraft.util.JSONUtils;

public class ConstantAmount implements IAmountComponent {
	
	public static final String NAME = "count";
	private int cnt;
	
	public ConstantAmount() {
	}
	
	@Override
	public int getSpawnAmount() {
		return this.cnt;
	}

	@Override
	public void readJson(JsonElement json) {
		this.cnt = JSONUtils.convertToInt(json, NAME);
	}

}
