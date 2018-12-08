package net.coolsimulations.Lightsaber.proxy;

import net.coolsimulations.Lightsaber.init.LightsaberItems;

public class ClientProxy implements CommonProxy{
	@Override
	public void init(){
		LightsaberItems.registerRenders();
	}

}
