package com.cyren.mod.config;

import java.util.ArrayList;
import java.util.List;

import com.cyren.mod.NGGlobal;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ConfigGui extends GuiConfig {

    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(parentScreen), NGGlobal.MOD_ID, false, false, "Change shit here.");
    }

    private static List<IConfigElement> getConfigElements(GuiScreen parent) {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        // adds sections declared in ConfigHandler. toLowerCase() is used because the configuration class automatically does this, so must we.
        list.add(new ConfigElement(Config.config.getCategory("General".toLowerCase())));
        list.add(new ConfigElement(Config.config.getCategory("Text Color".toLowerCase())));
        list.add(new ConfigElement(Config.config.getCategory("Text Location".toLowerCase())));
        list.add(new ConfigElement(Config.config.getCategory("Keystrokes Color".toLowerCase())));
        list.add(new ConfigElement(Config.config.getCategory("Keystrokes Location".toLowerCase())));
        list.add(new ConfigElement(Config.config.getCategory("Keystrokes Size".toLowerCase())));

        return list;
    }
}