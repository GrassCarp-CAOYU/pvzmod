package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.item.PVZItemBase;
import com.hungteen.pvz.item.PVZSpawnEggItem;
import com.hungteen.pvz.utils.enums.Colors;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegister {

    public static final DeferredRegister<Item> ITEMS =new DeferredRegister<>(ForgeRegistries.ITEMS, PVZMod.MOD_ID);
	
    //ingot essence
    public static final RegistryObject<Item> ORIGIN_ESSENCE = ITEMS.register("origin_essence", PVZItemBase::new);
    public static final RegistryObject<Item> APPEASE_ESSENCE = ITEMS.register("appease_essence", PVZItemBase::new);
    public static final RegistryObject<Item> LIGHT_ESSENCE = ITEMS.register("light_essence", PVZItemBase::new);
    public static final RegistryObject<Item> EXPLOSION_ESSENCE = ITEMS.register("explosion_essence", PVZItemBase::new);
    public static final RegistryObject<Item> DEFENCE_ESSENCE = ITEMS.register("defence_essence", PVZItemBase::new);
    public static final RegistryObject<Item> ICE_ESSENCE = ITEMS.register("ice_essence", PVZItemBase::new);
    public static final RegistryObject<Item> ENFORCE_ESSENCE = ITEMS.register("enforce_essence", PVZItemBase::new);
    public static final RegistryObject<Item> TOXIC_ESSENCE = ITEMS.register("toxic_essence", PVZItemBase::new);
    public static final RegistryObject<Item> ASSIST_ESSENCE = ITEMS.register("assist_essence", PVZItemBase::new);
    public static final RegistryObject<Item> MAGIC_ESSENCE = ITEMS.register("magic_essence", PVZItemBase::new);
    public static final RegistryObject<Item> FLAME_ESSENCE = ITEMS.register("flame_essence", PVZItemBase::new);
    public static final RegistryObject<Item> SPEAR_ESSENCE = ITEMS.register("spear_essence", PVZItemBase::new);
    public static final RegistryObject<Item> ARMA_ESSENCE = ITEMS.register("arma_essence", PVZItemBase::new);
    public static final RegistryObject<Item> ELECTRIC_ESSENCE = ITEMS.register("electric_essence", PVZItemBase::new);
    public static final RegistryObject<Item> SHADOW_ESSENCE = ITEMS.register("shadow_essence", PVZItemBase::new);
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", PVZItemBase::new);
    public static final RegistryObject<Item> AMETHYST_INGOT = ITEMS.register("amethyst_ingot", PVZItemBase::new);
    
    //card model
    public static final RegistryObject<Item> GRAY_CARD = ITEMS.register("gray_card", PVZItemBase::new);
    public static final RegistryObject<Item> WHITE_CARD = ITEMS.register("white_card", PVZItemBase::new);
    public static final RegistryObject<Item> GREEN_CARD = ITEMS.register("green_card", PVZItemBase::new);
    public static final RegistryObject<Item> BLUE_CARD = ITEMS.register("blue_card", PVZItemBase::new);
    public static final RegistryObject<Item> PURPLE_CARD = ITEMS.register("purple_card", PVZItemBase::new);
    public static final RegistryObject<Item> GOLD_CARD = ITEMS.register("gold_card", PVZItemBase::new);
    public static final RegistryObject<Item> RED_CARD = ITEMS.register("red_card", PVZItemBase::new);
    
    //plant card
    
    //plants
	public static final RegistryObject<Item> PEA = ITEMS.register("pea", PVZItemBase::new);
	
    //spawn egg
	public static final RegistryObject<PVZSpawnEggItem> SUN_SPAWN_EGG = registerSpawnEgg("sun", EntityRegister.SUN, Colors.YELLOW, Colors.WHITE);
	public static final RegistryObject<PVZSpawnEggItem> COIN_SPAWN_EGG = registerSpawnEgg("coin", EntityRegister.COIN, Colors.ORANGE, Colors.GOLD);
	public static final RegistryObject<PVZSpawnEggItem> ENERGY_SPAWN_EGG = registerSpawnEgg("energy", EntityRegister.ENERGY, Colors.GREEN, Colors.DARK_GREEN);
	
	/**
	 * register spawn eggs
	 */
    private static RegistryObject<PVZSpawnEggItem> registerSpawnEgg(String name,RegistryObject<? extends EntityType<?>> entityType,int color1,int color2)
    {
    	return ITEMS.register(name+"_spawn_egg", () -> new PVZSpawnEggItem(entityType, color1, color2, new Item.Properties().group(GroupRegister.PVZ_GROUP)));
    }
}
