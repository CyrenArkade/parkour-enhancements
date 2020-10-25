package com.cyren.mod.offset;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import org.apache.commons.lang3.math.NumberUtils;
import com.cyren.mod.Vars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Offset {
	public static class Help extends CommandBase implements ICommand {

		@Override
		public String getCommandName() {
			return "mpk";
		}

		@Override
		public String getCommandUsage(ICommandSender sender) {
			return "use it";
		}

		public static List<String> startsWith(String prefix, List<String> l) {
			ArrayList<String> list = new ArrayList<>(l);
			for (int i = list.size() - 1; i >= 0; i--) {
				if (!list.get(i).startsWith(prefix)) list.remove(i);
			}
			return list;
		}

		@Override
		public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
			List<String> argumentList = new ArrayList<>();
			for (String arg : args) {
				if (!arg.isEmpty() && !arg.equals(" ")) argumentList.add(arg);
			}
			String[] arguments = argumentList.toArray(new String[0]);

			System.out.println(Arrays.toString(arguments));
			System.out.println(args.length);
			for (int i = 0; i < args.length; i++) {
				System.out.println(args[i]);
			}
			if (args.length == 1) {
				List<String> all = Arrays.asList("clearlb", "clearpb", "setcond", "setlb");
				System.out.println(args[0].length());
				if (args[0].isEmpty()) return all;
				return startsWith(args[0], Arrays.asList("clearlb", "clearpb", "setcond", "setlb"));
			} else if (args.length == 2) {
				switch (args[0]) {
					case "clearlb":
					case "clearpb":
					case "colorlist":
					case "df":
					case "help":
					case "setcond":
						return Collections.emptyList();
					case "setlb":
						return Arrays.asList("x", "z", "~");
				}
			}
			return Collections.emptyList();
		}

		@Override
		public void processCommand(ICommandSender sender, String[] args) throws CommandException {
			EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
			if (args.length >= 1) {
				String cmd = args[0];
				String out = "No output";

				switch (cmd) {
					case "setlb":
						BlockPos blockpos = new BlockPos(player.posX, player.posY - 0.001D, player.posZ);
						IBlockState iblockstate = player.worldObj.getBlockState(blockpos);
						Block block = iblockstate.getBlock();
						if (block.isAir(player.worldObj, blockpos)) {
							blockpos = blockpos.down();
							iblockstate = player.worldObj.getBlockState(blockpos);
							block = iblockstate.getBlock();
						}

						if (block.getCollisionBoundingBox(player.worldObj, blockpos, iblockstate) != null) {
							AxisAlignedBB blockCollision = block.getCollisionBoundingBox(player.worldObj, blockpos, iblockstate);
							Vars.lbMinX = blockCollision.minX - 0.3D;
							Vars.lbMinY = blockCollision.minY;
							Vars.lbMinZ = blockCollision.minZ - 0.3D;
							Vars.lbMaxX = blockCollision.maxX + 0.3D;
							Vars.lbMaxY = blockCollision.maxY;
							if (block instanceof BlockFence)
								Vars.lbMaxY += 0.5;
							Vars.lbMaxZ = blockCollision.maxZ + 0.3D;
							Vars.lbXZType = 0;
							Vars.lbPb = -1.0D;
							Vars.condMinX = -3.0E8D;
							Vars.condMaxX = 3.0E8D;
							Vars.condMinZ = -3.0E8D;
							Vars.condMaxZ = 3.0E8D;
							out = "Successfully set landing block.";

							/*out += "\nmaxX: " + Vars.lbMaxX + ", minX: " + Vars.lbMinX;
							out += "\nmaxY: " + Vars.lbMaxY + ", minY: " + Vars.lbMinY;
							out += "\nmaxZ: " + Vars.lbMaxZ + ", minZ: " + Vars.lbMinZ;*/

							if (args.length >= 2) {
								if (args[1].equalsIgnoreCase("x")) {
									Vars.lbXZType = 1;
								} else if (args[1].equalsIgnoreCase("z")) {
									Vars.lbXZType = 2;
								} else if (args[1].equals("~")) {
									Vars.lbXZType = Vars.getXZFacing().equals("X") ? 1 : 2;
								}
							}
						} else {
							out = "Please stand on a tangible block";
						}
						break;
					case "clearlb":
						Vars.lbMinX = 0.0D;
						Vars.lbMinY = 0.0D;
						Vars.lbMinZ = 0.0D;
						Vars.lbMaxX = 0.0D;
						Vars.lbMaxY = 0.0D;
						Vars.lbMaxZ = 0.0D;
						Vars.lbPb = -1.0D;
						out = "Successfully cleared landing block.";
						break;
					case "clearpb":
						Vars.lbPb = -1.0D;
						out = "Successfully cleared pb.";
						break;
					case "setcond":
						if (args.length >= 5) {
							double d1 = NumberUtils.toDouble(args[1], -1.0D);
							double d2 = NumberUtils.toDouble(args[2], 1.0D);
							double d3 = NumberUtils.toDouble(args[3], -1.0D);
							double d0 = NumberUtils.toDouble(args[4], 1.0D);

							if (d1 <= d2 && d3 <= d0) {
								Vars.condMinX = d1;
								Vars.condMaxX = d2;
								Vars.condMinZ = d3;
								Vars.condMaxZ = d0;
								out = "Conditions successfully set";
								break;
							}
							out = "Invalid arguments";
							break;
						}
						out = "Please enter 4 arguments";
						break;
					default:
						out = "Invalid command. Try /pke help";
				}

				if (!out.isEmpty())
					player.addChatMessage(new ChatComponentText(out));
			}

		}

		@Override
		public int getRequiredPermissionLevel() {
			return 0;
		}
	}
}
