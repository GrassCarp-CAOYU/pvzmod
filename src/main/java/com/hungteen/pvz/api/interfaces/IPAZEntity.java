package com.hungteen.pvz.api.interfaces;

import com.hungteen.pvz.api.types.base.IPAZType;
import com.mojang.datafixers.util.Pair;

import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 16:04
 *
 * an abstract interface above on both plants and zombies entity.<br>
 * plants and zombies have something in common.<br>
 **/
public interface IPAZEntity extends ICanBeEnergetic {


    IPAZType getPAZType();

    /**
     * display in almanac.
     */
    void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list);

}
