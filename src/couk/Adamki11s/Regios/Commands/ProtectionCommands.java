package couk.Adamki11s.Regios.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Mutable.MutableProtection;
import couk.Adamki11s.Regios.Regions.Region;

public class ProtectionCommands {
	
	MutableProtection mutable = new MutableProtection();
	
	public void setProtected(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			p.sendMessage(ChatColor.GREEN + "[Regios] Protection message updated for region " + ChatColor.BLUE + region);
		}
		mutable.editProtect(r);
	}
	
	public void setUnProtected(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			p.sendMessage(ChatColor.GREEN + "[Regios] Protection message updated for region " + ChatColor.BLUE + region);
		}
		mutable.editUnprotect(r);
	}
	
	public void setPreventEntry(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			p.sendMessage(ChatColor.GREEN + "[Regios] Protection message updated for region " + ChatColor.BLUE + region);
		}
		mutable.editPreventEntry(r);
	}
	
	public void setAllowEntry(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			p.sendMessage(ChatColor.GREEN + "[Regios] Protection message updated for region " + ChatColor.BLUE + region);
		}
		mutable.editAllowEntry(r);
	}
	
	public void setPreventExit(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			p.sendMessage(ChatColor.GREEN + "[Regios] Protection message updated for region " + ChatColor.BLUE + region);
		}
		mutable.editPreventExit(r);
	}
	
	public void setAllowExit(Region r, String region, Player p){
		if(r == null){ p.sendMessage(ChatColor.RED + "[Regios] The region " + ChatColor.BLUE + region + ChatColor.RED + " doesn't exist!"); return; } else {
			p.sendMessage(ChatColor.GREEN + "[Regios] Protection message updated for region " + ChatColor.BLUE + region);
		}
		mutable.editAllowExit(r);
	}

}
