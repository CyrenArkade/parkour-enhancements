package com.cyren.mod;

import com.cyren.mod.config.Config;
import com.cyren.mod.gui.CoordsGUI;
import com.cyren.mod.offset.Offset;
import com.cyren.mod.proxy.CommonProxy;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = NGGlobal.MOD_ID, name = NGGlobal.MOD_NAME, version = NGGlobal.VERSION, guiFactory = NGGlobal.NG_GUI_FACTORY, clientSideOnly = true)
public class Main {
	
	@Instance(NGGlobal.MOD_ID)
	public static Main instance;
	
	@SidedProxy(clientSide = NGGlobal.NG_CLIENT_PROXY, serverSide = NGGlobal.NG_COMMON_PROXY)
	public static CommonProxy proxy;
	
	public static Configuration config;

	@EventHandler
	public void preInit (FMLPreInitializationEvent preEvent) {
		
		// Config.preInit();
		
		Config.init(preEvent.getSuggestedConfigurationFile());

        MinecraftForge.EVENT_BUS.register(new Config());
		
		this.proxy.preInit(preEvent);
		
	}
	
	@EventHandler
	public void init (FMLInitializationEvent Event) {
		
		//Vars.init();
		ClientCommandHandler.instance.registerCommand(new Offset.Help());
		
		this.proxy.init(Event);
		
		}
	
	@EventHandler
	public void postInit (FMLPostInitializationEvent postEvent) {
		
		MinecraftForge.EVENT_BUS.register(new CoordsGUI());
		this.proxy.postInit(postEvent);
		
	}
}
