package couk.Adamki11s.Regios.Economy;

import org.bukkit.entity.Player;

import couk.Adamki11s.Regios.Regions.Region;

public class iConomyManager extends Buying {
	
	@SuppressWarnings("static-access")
	private double getBalance(Player p){
		return EconomyCore.iconomy.getBank().getAccount(p.getName()).getBalance();
	}
	
	public boolean canAffordRegion(Player p, int price){
		return getBalance(p) >= price;
	}
	
	@SuppressWarnings("static-access")
	public void buyRegion(Region r, String buyer, String seller, int price){
		EconomyCore.iconomy.getBank().getAccount(seller).add(price);
		EconomyCore.iconomy.getBank().getAccount(buyer).subtract(price);
		super.buy(seller, buyer, r, price);
	}


}
