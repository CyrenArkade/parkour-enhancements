package com.cyren.mod.gui;

import java.util.Date;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;

// Code from parkour client. Adapted for parkour enhancements.

public class HHTimer{
	
	Minecraft mc = Minecraft.getMinecraft();
	  
	public boolean state_keyForeward;
	  
	public boolean lastStateForeward;
	  
	public boolean lastStateJump;
	  
	public boolean isLocked;
	  
	public boolean state_keyForeward_ticks;
	  
	public boolean lastStateForeward_ticks;
	  
	public boolean lastStateJump_ticks;
	  
	public boolean isLocked_ticks;
	  
	private long ms;
	  
	private long ticks;
	  
	public long finalTicks;
	
	public long finalms;
	  
	public Date date = new Date();

	public void onFrame() {
		if (Keyboard.isCreated()) {
	        if (isKey(mc.gameSettings.keyBindForward.getKeyCode())) {
	          this.state_keyForeward = true;
	          if (!this.lastStateForeward) {
	            this.date = new Date();
	            this.isLocked = false;
	          } 
	          this.lastStateForeward = true;
	        } else {
	          this.lastStateForeward = false;
	        } 
	        if (isKey(mc.gameSettings.keyBindJump.getKeyCode())) {
	          if (this.state_keyForeward)
	            if (!this.lastStateJump) {
	              if (!this.isLocked) {
	                long currentMillis = System.currentTimeMillis();
	                this.ms = currentMillis - this.date.getTime();
	                this.isLocked = true;
	              } 
	              this.state_keyForeward = false;
	              this.lastStateJump = true;
	            }  
	          return;
	        } 
	        this.lastStateJump = false;
	      } 
	}
	
	public void onTick() {
	    if ((mc.gameSettings.keyBindForward.isKeyDown())) {
	      this.state_keyForeward_ticks = true;
	      if (!this.lastStateForeward_ticks) {
	        this.ticks = 0L;
	        this.isLocked_ticks = false;
	      } 
	      this.lastStateForeward_ticks = true;
	    } else {
	      this.lastStateForeward_ticks = false;
	    } 
	    if ((mc.gameSettings.keyBindJump.isKeyDown())) {
	      if (this.state_keyForeward_ticks)
	        if (!this.lastStateJump_ticks) {
	          if (!this.isLocked_ticks) {
	            this.finalTicks = this.ticks;
	            this.finalms = this.ms;
	            this.isLocked_ticks = true;
	          } 
	          this.state_keyForeward_ticks = false;
	          this.lastStateJump_ticks = true;
	        }  
	    } else {
	      this.lastStateJump_ticks = false;
	    } 
	    this.ticks++;
	  }
	
	public long getMs() {
	    return this.finalms;
	}
	
	public long getTicks() {
	      return this.finalTicks;
	  }
	
	public boolean isKey(int id) {
		if (id < 0)
			return Mouse.isButtonDown(id + 100);
		else
			return Keyboard.isKeyDown(id);
	}

}
