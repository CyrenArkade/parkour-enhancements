package com.cyren.mod.config;

import java.io.File;

import com.cyren.mod.NGGlobal;
import com.cyren.mod.gui.CoordsGUI;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Config {
	
	CoordsGUI coordsGUI = new CoordsGUI();
	
	static Configuration config = null;
	
	public static boolean enabledKeystrokes;
	public static boolean enabledBarrierWarning;
	public static boolean enabledBlocksPerSecond;
	public static String hhFormat;
	public static int coordPrecision;

	public static String textBackground;
	public static String textTextColor;
	public static String textNumberColor;
	
	public static int locationHHTextX;
	public static int locationHHTextY;
	public static int locationFacingTextX;
	public static int locationFacingTextY;
	public static int locationVelocityTextX;
	public static int locationVelocityTextY;
	public static int locationXTextX;
	public static int locationXTextY;
	public static int locationYTextX;
	public static int locationYTextY;
	public static int locationZTextX;
	public static int locationZTextY;
	public static int locationFPSTextX;
	public static int locationFPSTextY;

	public static String keyTextColor;
	public static String keyBackgroundColor;
	public static String keyPressedTextColor;
	public static String keyPressedBackgroundColor;

	public static int locationForwardX;
	public static int locationForwardY;
	public static int locationLeftX;
	public static int locationLeftY;
	public static int locationBackX;
	public static int locationBackY;
	public static int locationRightX;
	public static int locationRightY;
	public static int locationSprintX;
	public static int locationSprintY;
	public static int locationSneakX;
	public static int locationSneakY;
	public static int locationJumpX;
	public static int locationJumpY;
	
	public static int sizeForwardX;
	public static int sizeForwardY;
	public static int sizeLeftX;
	public static int sizeLeftY;
	public static int sizeBackX;
	public static int sizeBackY;
	public static int sizeRightX;
	public static int sizeRightY;
	public static int sizeSprintX;
	public static int sizeSprintY;
	public static int sizeSneakX;
	public static int sizeSneakY;
	public static int sizeJumpX;
	public static int sizeJumpY;
	
	public static void init(File configFile)
    {
        if (config == null)
        {
            config = new Configuration(configFile);
            loadConfig();
        }
    }
	
	private static void loadConfig() {
		String category;
		
		category = "General";
		config.addCustomCategoryComment(category, "General settings");
		enabledKeystrokes = config.getBoolean("enabledKeystrokes", category, true, "Toggles keystrokes");
		enabledBarrierWarning = config.getBoolean("enabledBarrierWarning", category, true, "Toggles the barrier warning");
		enabledBlocksPerSecond = config.getBoolean("enabledBlocksPerSecond", category, false, "Toggles between b/t and b/s");
		hhFormat = config.getString("hhFormat", category, "HH-Timing: {hhms} ({hhTicks} ticks)", "Changes the text in the hh timing line");
		coordPrecision = config.getInt("coordPrecision", category, 6, 1, 16, "How many decimals of precision to show in player variables");
		
		category = "Text Color";
		config.addCustomCategoryComment(category, "Change the color of the GUI text");
		textBackground = config.getString("textBackground", category, "#40404000", "Changes the background drawn behind the text");
		textTextColor = config.getString("textTextColor", category, "7", "Changes the background drawn behind the text\\nSee \\\"Formatting codes\\\" on the Minecraft wiki");
		textNumberColor = config.getString("textNumberColor", category, "e", "Changes the background drawn behind the text\nSee \"Formatting codes\" on the Minecraft wiki");
		
		category = "Text Location";
		config.addCustomCategoryComment(category, "Change the location of each key\nThe origin is the bottom right corner");
		locationHHTextX = config.getInt("locationHHTextX", category, 2, -100, 10000, "X distance from the top left");
		locationHHTextY = config.getInt("locationHHTextY", category, 2, -100, 10000, "Y distance from the top left");
		locationFacingTextX = config.getInt("locationFacingTextX", category, 2, -100, 10000, "X distance from the top Facing");
		locationFacingTextY = config.getInt("locationFacingTextY", category, 13, -100, 10000, "Y distance from the top Facing");
		locationVelocityTextX = config.getInt("locationVelocityTextX", category, 2, -100, 10000, "X distance from the top left");
		locationVelocityTextY = config.getInt("locationVelocityTextY", category, 24, -100, 10000, "Y distance from the top left");
		locationXTextX = config.getInt("locationXTextX", category, 2, -100, 10000, "X distance from the top left");
		locationXTextY = config.getInt("locationXTextY", category, 35, -100, 10000, "Y distance from the top left");
		locationYTextX = config.getInt("locationYTextX", category, 2, -100, 10000, "X distance from the top left");
		locationYTextY = config.getInt("locationYTextY", category, 46, -100, 10000, "Y distance from the top left");
		locationZTextX = config.getInt("locationZTextX", category, 2, -100, 10000, "X distance from the top left");
		locationZTextY = config.getInt("locationZTextY", category, 57, -100, 10000, "Y distance from the top left");
		locationFPSTextX = config.getInt("locationFPSTextX", category, 2, -100, 10000, "X distance from the top left");
		locationFPSTextY = config.getInt("locationFPSTextY", category, 68, -100, 10000, "Y distance from the top left");
		
		category = "Keystrokes Color";
		config.addCustomCategoryComment(category, "Change the color of the keystrokes");
		keyTextColor = config.getString("keyTextColor", category, "#ffffffff", "Changes the text drawn on the background");
		keyBackgroundColor = config.getString("keyBackgroundColor", category, "#1f1f1f2f", "Changes the background drawn behind the text");
		keyPressedTextColor = config.getString("keyPressedTextColor", category, "#00000000", "Changes the text drawn on the background");
		keyPressedBackgroundColor = config.getString("keyPressedBackgroundColor", category, "#ffffff5f", "Changes the background drawn behind the text");
		
		category = "Keystrokes Location";
		config.addCustomCategoryComment(category, "Change the location of each key\nThe origin is the bottom right corner");
		locationForwardX = config.getInt("locationForwardX", category, 50, -100, 10000, "X distance from the bottom left");
		locationForwardY = config.getInt("locationForwardY", category, 83, -100, 10000, "Y distance from the bottom left");
		locationLeftX = config.getInt("locationLeftX", category, 75, -100, 10000, "X distance from the bottom left");
		locationLeftY = config.getInt("locationLeftY", category, 58, -100, 10000, "Y distance from the bottom left");
		locationBackX = config.getInt("locationBackX", category, 50, -100, 10000, "X distance from the bottom left");
		locationBackY = config.getInt("locationBackY", category, 58, -100, 10000, "Y distance from the bottom left");
		locationRightX = config.getInt("locationRightX", category, 25, -100, 10000, "X distance from the bottom left");
		locationRightY = config.getInt("locationRightY", category, 58, -100, 10000, "Y distance from the bottom left");
		locationSprintX = config.getInt("locationSprintX", category, 75, -100, 10000, "X distance from the bottom left");
		locationSprintY = config.getInt("locationSprintY", category, 33, -100, 10000, "Y distance from the bottom left");
		locationSneakX = config.getInt("locationSneakX", category, 37, -100, 10000, "X distance from the bottom left");
		locationSneakY = config.getInt("locationSneakY", category, 33, -100, 10000, "Y distance from the bottom left");
		locationJumpX = config.getInt("locationJumpX", category, 75, -100, 10000, "X distance from the bottom left");
		locationJumpY = config.getInt("locationJumpY", category, 17, -100, 10000, "Y distance from the bottom left");
		
		category = "Keystrokes Size";
		config.addCustomCategoryComment(category, "Change the size of each key");
		sizeForwardX = config.getInt("sizeForwardX", category, 23, -100, 10000, "X distance from the bottom left");
		sizeForwardY = config.getInt("sizeForwardY", category, 23, -100, 10000, "Y distance from the bottom left");
		sizeLeftX = config.getInt("sizeLeftX", category, 23, -100, 10000, "X distance from the bottom left");
		sizeLeftY = config.getInt("sizeLeftY", category, 23, -100, 10000, "Y distance from the bottom left");
		sizeBackX = config.getInt("sizeBackX", category, 23, -100, 10000, "X distance from the bottom left");
		sizeBackY = config.getInt("sizeBackY", category, 23, -100, 10000, "Y distance from the bottom left");
		sizeRightX = config.getInt("sizeRightX", category, 23, -100, 10000, "X distance from the bottom left");
		sizeRightY = config.getInt("sizeRightY", category, 23, -100, 10000, "Y distance from the bottom left");
		sizeSprintX = config.getInt("sizeSprintX", category, 35, -100, 10000, "X distance from the bottom left");
		sizeSprintY = config.getInt("sizeSprintY", category, 14, -100, 10000, "Y distance from the bottom left");
		sizeSneakX = config.getInt("sizeSneakX", category, 35, -100, 10000, "X distance from the bottom left");
		sizeSneakY = config.getInt("sizeSneakY", category, 14, -100, 10000, "Y distance from the bottom left");
		sizeJumpX = config.getInt("sizeJumpX", category, 73, -100, 10000, "X distance from the bottom left");
		sizeJumpY = config.getInt("sizeJumpY", category, 14, -100, 10000, "Y distance from the bottom left");
				
        config.save();
	}
	
	@SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(NGGlobal.MOD_ID))
        {
            loadConfig();
        }
    }
}
