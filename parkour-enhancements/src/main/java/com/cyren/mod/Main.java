package com.cyren.mod;

import com.cyren.mod.config.Config;
import com.cyren.mod.gui.CoordsGUI;
import com.cyren.mod.proxy.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = NGGlobal.MOD_ID, name = NGGlobal.MOD_NAME, version = NGGlobal.VERSION)
public class Main {
	
	@Instance(NGGlobal.MOD_ID)
	public static Main instance;
	
	@SidedProxy(clientSide = NGGlobal.NG_CLIENT_PROXY, serverSide = NGGlobal.NG_COMMON_PROXY)
	public static CommonProxy proxy;
	
	public static Configuration config;

	@EventHandler
	public void preInit (FMLPreInitializationEvent preEvent) {
		
		Config.preInit();
		
		this.proxy.preInit(preEvent);
		
	}
	
	@EventHandler
	public void init (FMLInitializationEvent Event) {
		
		this.proxy.init(Event);
		
		}
	
	@EventHandler
	public void postInit (FMLPostInitializationEvent postEvent) {
	
		MinecraftForge.EVENT_BUS.register(new CoordsGUI());
		this.proxy.postInit(postEvent);
		
	}
}
