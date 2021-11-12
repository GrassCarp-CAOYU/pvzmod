package com.hungteen.pvz.common.impl.plant;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.model.entity.plant.arma.KernelPultModel;
import com.hungteen.pvz.client.model.entity.plant.defence.WaterGuardModel;
import com.hungteen.pvz.common.impl.EssenceType;
import com.hungteen.pvz.common.impl.CoolDowns;
import com.hungteen.pvz.common.impl.PlantType;
import com.hungteen.pvz.common.impl.Ranks;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.register.EntityRegister;

import java.util.ArrayList;
import java.util.List;

public final class CustomPlants extends PlantType {
	
	private static final List<IPlantType> LIST = new ArrayList<>();
	
	public static final IPlantType WATER_GUARD = new CustomPlants("water_guard", new PlantFeatures().isWaterPlant()
			.cost(50).maxLevel(20).requiredLevel(15)
			.cd(CoolDowns.LITTLE_SLOW).rank(Ranks.GRAY).essence(EssenceType.DEFENCE)
			.entityType(() -> EntityRegister.WATER_GUARD.get())
			.summonCard(() -> ItemRegister.WATER_GUARD_CARD.get())
			.enjoyCard(() -> ItemRegister.WATER_GUARD_ENJOY_CARD.get())
			.plantModel(() -> WaterGuardModel::new).scale(0.8F)
	);
	
	public static final IPlantType BUTTER_PULT = new CustomPlants("butter_pult", new PlantFeatures()
			.cost(275).maxLevel(20).requiredLevel(75)
			.cd(CoolDowns.NORMAL).rank(Ranks.BLACK).essence(EssenceType.ARMA)
			.entityType(() -> EntityRegister.BUTTER_PULT.get())
			.summonCard(() -> ItemRegister.BUTTER_PULT_CARD.get())
			.enjoyCard(() -> ItemRegister.BUTTER_PULT_ENJOY_CARD.get())
			.plantModel(() -> KernelPultModel::new).scale(0.9F)
	);
	
	public static void register() {
		PVZAPI.get().registerPlantTypes(LIST);
	}
	
	private CustomPlants(String name, PlantFeatures features) {
		super(name, features);
		LIST.add(this);
	}
	
    @Override
	public int getSortPriority() {
		return 60;
	}
    
	@Override
	public String getCategoryName() {
		return "custom";
	}

	@Override
	public String getModID() {
		return PVZMod.MOD_ID;
	}

}
