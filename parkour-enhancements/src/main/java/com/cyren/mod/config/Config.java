package com.cyren.mod.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;

public class Config {
	
	private static Configuration config = null;
	
	public static final String CATEGORY_NAME_TEXT = "text";
	
	public static String textValueColor;
	public static String textSecondaryColor;
	
	public static int backgroundR;
	public static int backgroundG;
	public static int backgroundB;
	public static int backgroundA;
	
	public static int keyTextR;
	public static int keyTextG;
	public static int keyTextB;
	public static int keyTextA;
	
	public static int keyTextPressedR;
	public static int keyTextPressedG;
	public static int keyTextPressedB;
	public static int keyTextPressedA;
	
	public static int keyBackgroundR;
	public static int keyBackgroundG;
	public static int keyBackgroundB;
	public static int keyBackgroundA;
	
	public static int keyBackgroundPressedR;
	public static int keyBackgroundPressedG;
	public static int keyBackgroundPressedB;
	public static int keyBackgroundPressedA;
	
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
	public static int locationShiftX;
	public static int locationShiftY;
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
	public static int sizeShiftX;
	public static int sizeShiftY;
	public static int sizeJumpX;
	public static int sizeJumpY;
	
	public static boolean enabledKeystrokes;
	public static boolean enabledBarrierWarning;
	public static int coordPrecision;
	public static String hhFormat;
	public static boolean enabledBlocksPerSecond;

	
	public static void preInit() {
		File configFile = new File(Loader.instance().getConfigDir(), "ParkourEnhancements.cfg");
		config = new Configuration(configFile);
		syncFromFiles();
	}

	
	public static void clientPreInit( ) {
	}
	
	public static void syncFromFiles() {
		syncConfig(true, true);
	}
	
	private static void syncConfig(boolean loadFromConfigFile, boolean readFieldsFromConfig) {
		if(loadFromConfigFile)
			config.load();
		
		String category = "gui_text";
		Property propertyTextValueColor = config.get(category, "r", 255);
		Property propertyTextSecondaryColor = config.get(category, "g", 255);
		
		category = "gui_background";
		Property propertyBackgroundR = config.get(category, "r", 64);
		Property propertyBackgroundG = config.get(category, "g", 64);
		Property propertyBackgroundB = config.get(category, "b", 64);
		Property propertyBackgroundA = config.get(category, "a", 0);
		
		category = "key_text";
		Property propertyKeyTextR = config.get(category, "r", 255);
		Property propertyKeyTextG = config.get(category, "g", 255);
		Property propertyKeyTextB = config.get(category, "b", 255);
		Property propertyKeyTextA = config.get(category, "a", 255);
		
		category = "key_text_pressed";
		Property propertyKeyTextPressedR = config.get(category, "r", 64);
		Property propertyKeyTextPressedG = config.get(category, "g", 64);
		Property propertyKeyTextPressedB = config.get(category, "b", 64);
		Property propertyKeyTextPressedA = config.get(category, "a", 64);
		
		category = "key_background";
		Property propertyKeyBackgroundR = config.get(category, "r", 64);
		Property propertyKeyBackgroundG = config.get(category, "g", 64);
		Property propertyKeyBackgroundB = config.get(category, "b", 64);
		Property propertyKeyBackgroundA = config.get(category, "a", 64);
		
		category = "key_background_pressed";
		Property propertyKeyBackgroundPressedR = config.get(category, "r", 255);
		Property propertyKeyBackgroundPressedG = config.get(category, "g", 255);
		Property propertyKeyBackgroundPressedB = config.get(category, "b", 255);
		Property propertyKeyBackgroundPressedA = config.get(category, "a", 255);
		
		category = "key_locations";
		Property propertyLocationForwardX = config.get(category, "forwardX", 20);
		Property propertyLocationForwardY = config.get(category, "forwardY", 38);
		Property propertyLocationLeftX = config.get(category, "leftX", 30);
		Property propertyLocationLeftY = config.get(category, "leftY", 28);
		Property propertyLocationBackX = config.get(category, "backX", 20);
		Property propertyLocationBackY = config.get(category, "backY", 28);
		Property propertyLocationRightX = config.get(category, "rightX", 10);
		Property propertyLocationRightY = config.get(category, "rightY", 28);
		Property propertyLocationSprintX = config.get(category, "sprintX", 30);
		Property propertyLocationSprintY = config.get(category, "sprintY", 18);
		Property propertyLocationShiftX = config.get(category, "shiftX", 14);
		Property propertyLocationShiftY = config.get(category, "shiftY", 18);
		Property propertyLocationJumpX = config.get(category, "jumpX", 30);
		Property propertyLocationJumpY = config.get(category, "jumpY", 8);
		
		category = "key_sizes";
		Property propertySizeForwardX = config.get(category, "forwardX", 9);
		Property propertySizeForwardY = config.get(category, "forwardY", 9);
		Property propertySizeLeftX = config.get(category, "leftX", 9);
		Property propertySizeLeftY = config.get(category, "leftY", 9);
		Property propertySizeBackX = config.get(category, "leftX", 9);
		Property propertySizeBackY = config.get(category, "leftY", 9);
		Property propertySizeRightX = config.get(category, "rightX", 9);
		Property propertySizeRightY = config.get(category, "rightY", 9);
		Property propertySizeSprintX = config.get(category, "sprintX", 13);
		Property propertySizeSprintY = config.get(category, "sprintY", 9);
		Property propertySizeShiftX = config.get(category, "shiftX", 13);
		Property propertySizeShiftY = config.get(category, "shiftY", 9);
		Property propertySizeJumpX = config.get(category, "jumpX", 29);
		Property propertySizeJumpY = config.get(category, "jumpY", 7);
		
		category = "settings";
		Property propertyEnabledKeystrokes = config.get(category, "enabledKeystrokes", true);
		Property propertyCoordFormat = config.get(category, "coordPrecision", 6);
		Property propertyhhFormat = config.get(category, "hhFormat", "\u00a77HH-Timing: \u00a7ehhTime\u00a77 ms (\u00a7ehhTicks\u00a77 ticks)");
		Property propertyEnabledBarrierWarning = config.get(category, "enabledBarrierWarning", true);
		Property propertyEnabledBlocksPerSecond = config.get(category, "enabledBlocksPerSecond", false);
		
		
		if(readFieldsFromConfig) {
			textValueColor = propertyTextValueColor.getString();
			textSecondaryColor = propertyTextSecondaryColor.getString();
			backgroundR = propertyBackgroundR.getInt();
			backgroundG = propertyBackgroundG.getInt();
			backgroundB = propertyBackgroundB.getInt();
			backgroundA = propertyBackgroundA.getInt();
			keyTextR = propertyKeyTextR.getInt();
			keyTextG = propertyKeyTextG.getInt();
			keyTextB = propertyKeyTextB.getInt();
			keyTextA = propertyKeyTextA.getInt();
			keyTextPressedR = propertyKeyTextPressedR.getInt();
			keyTextPressedG = propertyKeyTextPressedG.getInt();
			keyTextPressedB = propertyKeyTextPressedB.getInt();
			keyTextPressedA = propertyKeyTextPressedA.getInt();
			keyBackgroundR = propertyKeyBackgroundR.getInt();
			keyBackgroundG = propertyKeyBackgroundG.getInt();
			keyBackgroundB = propertyKeyBackgroundB.getInt();
			keyBackgroundA = propertyKeyBackgroundA.getInt();
			keyBackgroundPressedR = propertyKeyBackgroundPressedR.getInt();
			keyBackgroundPressedG = propertyKeyBackgroundPressedG.getInt();
			keyBackgroundPressedB = propertyKeyBackgroundPressedB.getInt();
			keyBackgroundPressedA = propertyKeyBackgroundPressedA.getInt();
			locationForwardX = propertyLocationForwardX.getInt();
			locationForwardY = propertyLocationForwardY.getInt();
			locationLeftX = propertyLocationLeftX.getInt();
			locationLeftY = propertyLocationLeftY.getInt();
			locationBackX = propertyLocationBackX.getInt();
			locationBackY = propertyLocationBackY.getInt();
			locationRightX = propertyLocationRightX.getInt();
			locationRightY = propertyLocationRightY.getInt();
			locationSprintX = propertyLocationSprintX.getInt();
			locationSprintY = propertyLocationSprintY.getInt();
			locationShiftX = propertyLocationShiftX.getInt();
			locationShiftY = propertyLocationShiftY.getInt();
			locationJumpX = propertyLocationJumpX.getInt();
			locationJumpY = propertyLocationJumpY.getInt();
			sizeForwardX = propertySizeForwardX.getInt();
			sizeForwardY = propertySizeForwardY.getInt();
			sizeLeftX = propertySizeLeftX.getInt();
			sizeLeftY = propertySizeLeftY.getInt();
			sizeBackX = propertySizeBackX.getInt();
			sizeBackY = propertySizeBackY.getInt();
			sizeRightX = propertySizeRightX.getInt();
			sizeRightY = propertySizeRightY.getInt();
			sizeSprintX = propertySizeSprintX.getInt();
			sizeSprintY = propertySizeSprintY.getInt();
			sizeShiftX = propertySizeShiftX.getInt();
			sizeShiftY = propertySizeShiftY.getInt();
			sizeJumpX = propertySizeJumpX.getInt();
			sizeJumpY = propertySizeJumpY.getInt();
			enabledKeystrokes = propertyEnabledKeystrokes.getBoolean();
			enabledBarrierWarning = propertyEnabledBarrierWarning.getBoolean();
			coordPrecision = propertyCoordFormat.getInt();
			hhFormat = propertyhhFormat.getString();
			enabledBlocksPerSecond = propertyEnabledBlocksPerSecond.getBoolean();
		}
		
		if(config.hasChanged())
			config.save();
		
	}
	
}
