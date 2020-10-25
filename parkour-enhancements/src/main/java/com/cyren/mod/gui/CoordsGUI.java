package com.cyren.mod.gui;

import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.cyren.mod.NGGlobal;
import com.cyren.mod.Vars;
import com.cyren.mod.config.Config;
import com.cyren.mod.offset.Offset;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class CoordsGUI extends Gui {
	
	Minecraft mc = Minecraft.getMinecraft();
	FontRenderer renderer = mc.fontRendererObj;
	HHTimer hhTimer = new HHTimer();
	ScaledResolution sr;
	int width;
	int height;
	int rawFacing;
	String direction;
	EnumFacing enumfacing;
	boolean lookingAtBarrier;
	String textTextColor = "\u00a7" + Config.textTextColor;
	String textNumberColor = "\u00a7" + Config.textNumberColor;
	DecimalFormat df = new DecimalFormat("0." + new String(new char[Config.coordPrecision]).replace("\0", "0"));
	int backgroundColor = hexToInt(Config.textBackground);
	int keyTextColor = hexToInt(Config.keyTextColor);
	int keyTextPressedColor = hexToInt(Config.keyPressedTextColor);
	int keyBackgroundColor = hexToInt(Config.keyBackgroundColor);
	int keyBackgroundPressedColor = hexToInt(Config.keyPressedBackgroundColor);
	
	String posX;
	String posY;
	String posZ;
	double v_xz;
	String facing;
	
	boolean forwardPressed;
	boolean leftPressed;
	boolean backPressed;
	boolean rightPressed;
	boolean sprintPressed;
	boolean sneakPressed;
	boolean jumpPressed;
	
	long hhms;
	long hhTicks;
	
	@SubscribeEvent
	public void clientTick(ClientTickEvent event) {
		
		if(event.phase == Phase.START)
			return;
		hhms = hhTimer.getMs();
		hhTicks = hhTimer.getTicks();
		
		hhTimer.onTick();
		EntityPlayerSP player = mc.thePlayer;
		if (player == null) return;
		
		lookingAtBarrier = false;
		if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.mc.objectMouseOver.getBlockPos() != null) {
			if (mc.theWorld.getBlockState(mc.objectMouseOver.getBlockPos()).getBlock().getRegistryName().equals("minecraft:barrier")) {
				lookingAtBarrier = true;
			}
		}
		
		// Finds player values for that tick
		df.setRoundingMode(RoundingMode.HALF_UP);
		posX = df.format(player.posX);
		posY = df.format(player.posY);
		posZ = df.format(player.posZ);
		v_xz = Math.sqrt(Math.pow((player.posX - player.lastTickPosX), 2) + Math.pow((player.posZ - player.lastTickPosZ), 2));
		
		// Handles player offset and showing it in chat
		if (player.posY <= Vars.lbMaxY && player.lastTickPosY > Vars.lbMaxY && Vars.condMinX < player.lastTickPosX && player.lastTickPosX < Vars.condMaxX && Vars.condMinZ < player.lastTickPosZ && player.lastTickPosZ < Vars.condMaxZ && player.lastTickPosX >= Vars.lbMinX - 1.0D && player.lastTickPosX <= Vars.lbMaxX + 1.0D && player.lastTickPosZ >= Vars.lbMinZ - 1.0D && player.lastTickPosZ <= Vars.lbMaxZ + 1.0D) {

			double lbXOff = Math.min(Vars.lbMaxX - player.lastTickPosX, player.lastTickPosX - Vars.lbMinX);
			double lbZOff = Math.min(Vars.lbMaxZ - player.lastTickPosZ, player.lastTickPosZ - Vars.lbMinZ);
			double lbXZOff = Math.sqrt(Math.pow(lbXOff, 2.0D) + Math.pow(lbZOff, 2.0D));
			double pb;

			if (Vars.lbXZType == 0) {
				if (lbXOff <= 0.0D && lbZOff <= 0.0D) pb = -lbXZOff;
				else if (lbXOff > 0.0D && lbZOff > 0.0D) pb = lbXZOff;
				else pb = Math.min(lbZOff, lbXOff);
			} else if (Vars.lbXZType == 1) {
				pb = lbXOff;
			} else {
				pb = lbZOff;
			}

			if (pb > Vars.lbPb) {
				player.addChatMessage(new ChatComponentText("New pb ! " + pb));
				Vars.lbPb = pb;
			}

			String str = "\u00a7lX offset\u00a7r: " + df.format(lbXOff);
			if (Vars.lbXZType == 2) str = "\u00a7lZ offset\u00a7r: " + df.format(lbZOff);
			else if (Vars.lbXZType == 0) str += "\n\u00a7lZ offset\u00a7r: " + df.format(lbZOff);

			player.addChatMessage(new ChatComponentText(str));
		}
		
		// Checks if keys are pressed that tick
		forwardPressed = mc.gameSettings.keyBindForward.isKeyDown();
		leftPressed = mc.gameSettings.keyBindLeft.isKeyDown();
		backPressed = mc.gameSettings.keyBindBack.isKeyDown();
		rightPressed = mc.gameSettings.keyBindRight.isKeyDown();
		sprintPressed = mc.gameSettings.keyBindSprint.isKeyDown();
		sneakPressed = mc.gameSettings.keyBindSneak.isKeyDown();
		jumpPressed = mc.gameSettings.keyBindJump.isKeyDown();
	}
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
        	hhTimer.onFrame();
        	EntityPlayerSP player = mc.thePlayer;
        	if (player == null) return;
        	ScaledResolution sr = new ScaledResolution(mc);
        	width = sr.getScaledWidth();
        	height = sr.getScaledHeight();
        	
        	facing = df.format(((player.rotationYaw + 180) % 360 + 360) % 360 - 180);
        	
        	// Checks which direction the player is facing
    		enumfacing = player.getHorizontalFacing();
        	switch (enumfacing) {
                case NORTH:
                    direction = "-Z";
                    break;
                case SOUTH:
                	direction = "+Z";
                    break;
                case WEST:
                	direction = "-X";
                    break;
                case EAST:
                	direction = "+X";
            }
        	
        	// Draws the coords/hh timing GUI
        	if (!mc.gameSettings.showDebugInfo) {
        		drawCoordElement(Config.locationHHTextX, Config.locationHHTextY, textTextColor + Config.hhFormat.replace("{hhms}", textNumberColor + hhms + textTextColor).replace("{hhTicks}", textNumberColor + hhTicks + textTextColor), 2);
        		drawCoordElement(Config.locationFacingTextX, Config.locationFacingTextY, textTextColor + "Facing: " + textNumberColor + facing + textTextColor + " (" + textNumberColor + direction + textTextColor + ")", 2);
        		drawCoordElement(Config.locationVelocityTextX, Config.locationVelocityTextY, textTextColor + "Velocity: " + textNumberColor + (Config.enabledBlocksPerSecond ? (df.format(v_xz * 20) + textTextColor + " b/s") : (df.format(v_xz)) + textTextColor + " b/t"), 2);
        		drawCoordElement(Config.locationXTextX, Config.locationXTextY, textTextColor + "X: " + textNumberColor + posX, 2);
        		drawCoordElement(Config.locationYTextX, Config.locationYTextY, textTextColor + "Y: " + textNumberColor + posY, 2);
        		drawCoordElement(Config.locationZTextX, Config.locationZTextY, textTextColor + "Z: " + textNumberColor + posZ, 2);
        		drawCoordElement(Config.locationFPSTextX, Config.locationFPSTextY, textTextColor + "FPS: " + textNumberColor + mc.getDebugFPS(), 2);
        		
        		if (lookingAtBarrier & Config.enabledBarrierWarning) {
    				GlStateManager.scale(2, 2, 2);
        			renderer.drawString("\u00a7c!", width / 2 - 3, 2, 1);
    				GlStateManager.scale(0.5, 0.5, 0.5);
        		}
        	}
			
			// Draws all the keystrokes
        	if (Config.enabledKeystrokes) {
				drawKey(Config.locationForwardX, Config.locationForwardY, Config.sizeForwardX, Config.sizeForwardY, forwardPressed, "W");
				drawKey(Config.locationLeftX, Config.locationLeftY, Config.sizeLeftX, Config.sizeLeftY, leftPressed, "A");
				drawKey(Config.locationBackX, Config.locationBackY, Config.sizeBackX, Config.sizeBackY, backPressed, "S");
				drawKey(Config.locationRightX, Config.locationRightY, Config.sizeRightX, Config.sizeRightY, rightPressed, "D");
				drawKey(Config.locationSprintX, Config.locationSprintY, Config.sizeSprintX, Config.sizeSprintY, sprintPressed, "Sprint");
				drawKey(Config.locationSneakX, Config.locationSneakY, Config.sizeSneakX, Config.sizeSneakY, sneakPressed, "Sneak");
				drawKey(Config.locationJumpX, Config.locationJumpY, Config.sizeJumpX, Config.sizeJumpY, jumpPressed, "");
			}
		}
	}
	
	@SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(NGGlobal.MOD_ID))
        {
    		textTextColor = "\u00a7" + Config.textTextColor;
        	textNumberColor = "\u00a7" + Config.textNumberColor;
        	df = new DecimalFormat("0." + new String(new char[Config.coordPrecision]).replace("\0", "0"));
        	backgroundColor = hexToInt(Config.textBackground);
        	keyTextColor = hexToInt(Config.keyTextColor);
        	keyTextPressedColor = hexToInt(Config.keyPressedTextColor);
        	keyBackgroundColor = hexToInt(Config.keyBackgroundColor);
        	keyBackgroundPressedColor = hexToInt(Config.keyPressedBackgroundColor);
        }
    }
	
	public void drawCoordElement(int x, int y, String text, int border) {
		drawRect(x, y, x + renderer.getStringWidth(text) + 1 * border, y + 7 + 2 * border, backgroundColor);
		renderer.drawString(text, x + border, y + border, 1, true);
	}
	
	public void drawKey(int x, int y, int keyX, int keyY, boolean pressed, String text) {
    	if (pressed) {
			drawRect(width - x, height - y, width + keyX - x, height + keyY - y, keyBackgroundPressedColor);
			renderer.drawString(text, width + (keyX - renderer.getStringWidth(text)) / 2 + 1 - x, height + keyY / 2 - 3 - y, keyTextPressedColor, false);
		}
		else {
			drawRect(width - x, height - y, width + keyX - x, height + keyY - y, keyBackgroundColor);
			renderer.drawString(text, width + (keyX - renderer.getStringWidth(text)) / 2 + 1 - x, height + keyY / 2 - 3 - y, keyTextColor, false);
		}
    	
	}
	
	public static int hexToInt(String hex) {
		  int r = Integer.parseInt(hex.substring(1, 3), 16);
		  int g = Integer.parseInt(hex.substring(3, 5), 16);
		  int b = Integer.parseInt(hex.substring(5, 7), 16);
		  int a = Integer.parseInt(hex.substring(7, 9), 16);
		  return new Color(r,g,b,a).getRGB();
	  }

}
