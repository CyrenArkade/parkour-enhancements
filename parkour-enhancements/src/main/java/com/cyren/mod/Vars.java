package com.cyren.mod;

import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class Vars {
	public static Double condMinX = -1.0D;
	public static Double condMaxX = 1.0D;
	public static Double condMinZ = -1.0D;
	public static Double condMaxZ = 1.0D;

	public static Double lbMinX = 0.0D;
	public static Double lbMinY = 0.0D;
	public static Double lbMinZ = 0.0D;
	public static Double lbMaxX = 0.0D;
	public static Double lbMaxY = 0.0D;
	public static Double lbMaxZ = 0.0D;
	public static Double lbPb = -1.0D;
	public static int lbXZType = 0;

	public static Double lastLandingX = 0D;
	public static Double lastLandingY = 0D;
	public static Double lastLandingZ = 0D;
	public static Double tickRotationYaw = 0D;
	public static Double yawStock = 0D;

	public static boolean landingPosSameLine = false;

	public static class Category {
		public static String GUI = "gui";
	}

	public static String getXZFacing() {
		double yaw = MathHelper.wrapAngleTo180_float(Minecraft.getMinecraft().thePlayer.rotationYaw);
		int xz = (int) Math.floor(Math.abs(yaw / 45));
		return Arrays.asList(0, 3, 4).contains(xz) ? "Z" : "X";
	}
}
