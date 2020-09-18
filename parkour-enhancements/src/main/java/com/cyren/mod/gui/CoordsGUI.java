package com.cyren.mod.gui;

import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.cyren.mod.config.Config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;


public class CoordsGUI extends Gui {
	
	Minecraft mc = Minecraft.getMinecraft();
	FontRenderer renderer = mc.fontRendererObj;
	HHTimer hhTimer = new HHTimer();
	DecimalFormat df = new DecimalFormat("0." + new String(new char[Config.coordPrecision]).replace("\0", "0"));
	ScaledResolution sr;
	int width;
	int height;
	int rawFacing;
	String direction;
	EnumFacing enumfacing;
	boolean lookingAtBarrier;
	String convertedhhFormat = Config.hhFormat.replace("hhTime", "%1$s").replace("hhTicks", "%2$s");
	int backgroundColor = new Color (Config.backgroundR, Config.backgroundG, Config.backgroundB, Config.backgroundA).getRGB();
	int keyTextColor = new Color (Config.keyTextR, Config.keyTextG, Config.keyTextB, Config.keyTextA).getRGB();
	int keyTextPressedColor = new Color (Config.keyTextPressedR, Config.keyTextPressedG, Config.keyTextPressedB, Config.keyTextPressedA).getRGB();
	int keyBackgroundColor = new Color (Config.keyBackgroundR, Config.keyBackgroundG, Config.keyBackgroundB, Config.keyBackgroundA).getRGB();
	int keyBackgroundPressedColor = new Color (Config.keyBackgroundPressedR, Config.keyBackgroundPressedG, Config.keyBackgroundPressedB, Config.keyBackgroundPressedA).getRGB();
	
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
	boolean shiftPressed;
	boolean jumpPressed;
	
	boolean frameForwardPressed;
	boolean frameJumpPressed;
	long ms;
	long wTime;
	long framewTime;
	long hhms;
	long hhTicks;
	boolean waiting;
	boolean wasJumpPressed;
	boolean wasForwardPressed;
	boolean frameWasForwardPressed;
	boolean frameWasJumpPressed;
	boolean frameWaiting;
	
	@SubscribeEvent
	public void clientTick(ClientTickEvent event) {
		
		if(event.phase == Phase.END)
			return;
		
		hhTimer.onTick();
		EntityPlayerSP player = mc.thePlayer;
		if (player == null) return;
		
		// Finds player values for that tick
		df.setRoundingMode(RoundingMode.HALF_UP);
		posX = df.format(player.posX);
		posY = df.format(player.posY);
		posZ = df.format(player.posZ);
		v_xz = Math.sqrt(Math.pow((player.posX - player.lastTickPosX), 2) + Math.pow((player.posZ - player.lastTickPosZ), 2));
		
		forwardPressed = false;
		leftPressed = false;
		backPressed = false;
		rightPressed = false;
		sprintPressed = false;
		shiftPressed = false;
		jumpPressed = false;
		
		lookingAtBarrier = false;
		if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.mc.objectMouseOver.getBlockPos() != null) {
			if (mc.theWorld.getBlockState(mc.objectMouseOver.getBlockPos()).getBlock().getRegistryName().equals("minecraft:barrier")) {
				lookingAtBarrier = true;
			}
		}
		
		// Checks if keys are pressed that tick
		if (mc.gameSettings.keyBindForward.isKeyDown())
			forwardPressed = true;
		if (mc.gameSettings.keyBindLeft.isKeyDown())
			leftPressed = true;
		if (mc.gameSettings.keyBindBack.isKeyDown())
			backPressed = true;
		if (mc.gameSettings.keyBindRight.isKeyDown())
			rightPressed = true;
		if (mc.gameSettings.keyBindSprint.isKeyDown())
			sprintPressed = true;
		if (mc.gameSettings.keyBindSneak.isKeyDown())
			shiftPressed = true;
		if (mc.gameSettings.keyBindJump.isKeyDown())
			jumpPressed = true;
		
		hhms = hhTimer.getMs();
		hhTicks = hhTimer.getTicks();
		
	}
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
        	hhTimer.onFrame();
        	EntityPlayerSP player = mc.thePlayer;
        	if (player == null) return;
        	ScaledResolution sr = new ScaledResolution(mc);
        	width = sr.getScaledWidth() / 2;
        	height = sr.getScaledHeight() / 2;
        	
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
        		drawCoordElement(2, 2, String.format(convertedhhFormat, hhms, hhTicks), 2);
        		drawCoordElement(2, 13, "��7Facing: ��e" + facing + "��7 (��e" + direction + "��7)", 2);
        		drawCoordElement(2, 24, "��7Velocity: ��e" + (Config.enabledBlocksPerSecond ? df.format(v_xz * 20) + "��7 b/s" : df.format(v_xz)) + "��7 b/t", 2);
        		drawCoordElement(2, 35, "��7X: ��e" + posX, 2);
        		drawCoordElement(2, 46, "��7Y: ��e" + posY, 2);
        		drawCoordElement(2, 57, "��7Z: ��e" + posZ, 2);
        		
        		if (lookingAtBarrier & Config.enabledBarrierWarning) {
    				GlStateManager.scale(2, 2, 2);
        			renderer.drawString("��4!", width - 3, 2, 1);
    				GlStateManager.scale(0.5, 0.5, 0.5);
        		}
        	}
			
			// Draws all the keystrokes
			if (Config.enabledKeystrokes) {
				GlStateManager.scale(2, 2, 2);
				drawKey(Config.locationForwardX, Config.locationForwardY, Config.sizeForwardX, Config.sizeForwardY, forwardPressed, "W");
				drawKey(Config.locationLeftX, Config.locationLeftY, Config.sizeLeftX, Config.sizeLeftY, leftPressed, "A");
				drawKey(Config.locationBackX, Config.locationBackY, Config.sizeBackX, Config.sizeBackY, backPressed, "S");
				drawKey(Config.locationRightX, Config.locationRightY, Config.sizeRightX, Config.sizeRightY, rightPressed, "D");
				drawKey(Config.locationSprintX, Config.locationSprintY, Config.sizeSprintX, Config.sizeSprintY, sprintPressed, ">>");
				drawKey(Config.locationShiftX, Config.locationShiftY, Config.sizeShiftX, Config.sizeShiftY, shiftPressed, "v");
				drawKey(Config.locationJumpX, Config.locationJumpY, Config.sizeJumpX, Config.sizeJumpY, jumpPressed, "");
				GlStateManager.scale(0.5, 0.5, 0.5);
			}
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
	
}