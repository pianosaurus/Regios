package couk.Adamki11s.Regios.SpoutGUI;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.event.screen.ScreenListener;
import org.getspout.spoutapi.gui.Container;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.Label;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.gui.TextField;
import org.getspout.spoutapi.player.SpoutPlayer;
import couk.Adamki11s.Regios.Commands.HelpCommands;
import couk.Adamki11s.Regios.Data.MODE;
import couk.Adamki11s.Regios.Mutable.MutableEconomy;
import couk.Adamki11s.Regios.Mutable.MutableFun;
import couk.Adamki11s.Regios.Mutable.MutableMessages;
import couk.Adamki11s.Regios.Mutable.MutableMisc;
import couk.Adamki11s.Regios.Mutable.MutableMobs;
import couk.Adamki11s.Regios.Mutable.MutableModes;
import couk.Adamki11s.Regios.Mutable.MutableProtection;
import couk.Adamki11s.Regios.Mutable.MutableProtectionMisc;
import couk.Adamki11s.Regios.Regions.Region;
import couk.Adamki11s.Regios.SpoutGUI.RegionScreen4.ExToggle;

public class Screen_Listener extends ScreenListener {

	public static HashMap<SpoutPlayer, GenericLabel[]> oldWidgets = new HashMap<SpoutPlayer, GenericLabel[]>();

	public void onButtonClick(ButtonClickEvent evt) {
		if (evt.getScreenType() == ScreenType.CUSTOM_SCREEN) {
			ScreenHolder sh = ScreenHolder.getScreenHolder(evt.getPlayer());
			helpListener(evt, sh);
			if (RegionScreenManager.page.containsKey(evt.getPlayer())) {
				if (RegionScreenManager.page.get(evt.getPlayer()) == 1) {
					regionScreen1Listener(evt, sh);
				} else if (RegionScreenManager.page.get(evt.getPlayer()) == 2) {
					regionScreen2Listener(evt, sh);
				} else if (RegionScreenManager.page.get(evt.getPlayer()) == 3) {
					regionScreen3Listener(evt, sh);
				} else if (RegionScreenManager.page.get(evt.getPlayer()) == 4) {
					regionScreen4Listener(evt, sh);
				}
			}
			regionControlListener(evt, sh);
		}
	}

	private void regionControlListener(ButtonClickEvent evt, ScreenHolder sh) {

		if (!RegionScreenManager.popup.containsKey(evt.getPlayer())) {
			return;
		}

		SpoutPlayer sp = evt.getPlayer();

		UUID esc = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.escButton.getId()).getId();
		UUID next = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.pageForward.getId()).getId();
		UUID prev = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.pageBackwards.getId()).getId();

		UUID buttonID = evt.getButton().getId();

		if (buttonID == esc) {
			sp.getMainScreen().closePopup();
			return;
		}

		if (buttonID == next) {
			RegionScreenManager.nextPage(sp, sh);
			return;
		}

		if (buttonID == prev) {
			RegionScreenManager.previousPage(sp, sh);
			return;
		}
	}

	private void helpListener(ButtonClickEvent evt, ScreenHolder sh) {

		if (!HelpCommands.helps.containsKey(evt.getPlayer())) {
			return;
		}

		System.out.println(HelpCommands.helps.containsKey(evt.getPlayer()));
		System.out.println(sh);
		System.out.println(sh.escButton);

		UUID esc = ((GenericPopup) HelpCommands.helps.get(evt.getPlayer())).getWidget(sh.escButton.getId()).getId();
		UUID general = ((GenericPopup) HelpCommands.helps.get(evt.getPlayer())).getWidget(sh.generalData.getId()).getId();
		UUID protection = ((GenericPopup) HelpCommands.helps.get(evt.getPlayer())).getWidget(sh.protection.getId()).getId();
		UUID data = ((GenericPopup) HelpCommands.helps.get(evt.getPlayer())).getWidget(sh.data.getId()).getId();
		UUID exceptions = ((GenericPopup) HelpCommands.helps.get(evt.getPlayer())).getWidget(sh.exceptions.getId()).getId();
		UUID fun = ((GenericPopup) HelpCommands.helps.get(evt.getPlayer())).getWidget(sh.fun.getId()).getId();
		UUID invent = ((GenericPopup) HelpCommands.helps.get(evt.getPlayer())).getWidget(sh.inventory.getId()).getId();
		UUID message = ((GenericPopup) HelpCommands.helps.get(evt.getPlayer())).getWidget(sh.messages.getId()).getId();
		UUID modes = ((GenericPopup) HelpCommands.helps.get(evt.getPlayer())).getWidget(sh.modes.getId()).getId();
		UUID modify = ((GenericPopup) HelpCommands.helps.get(evt.getPlayer())).getWidget(sh.modify.getId()).getId();
		UUID other = ((GenericPopup) HelpCommands.helps.get(evt.getPlayer())).getWidget(sh.other.getId()).getId();
		UUID perms = ((GenericPopup) HelpCommands.helps.get(evt.getPlayer())).getWidget(sh.perms.getId()).getId();
		UUID spout = ((GenericPopup) HelpCommands.helps.get(evt.getPlayer())).getWidget(sh.spout.getId()).getId();

		SpoutPlayer sp = evt.getPlayer();

		UUID buttonID = evt.getButton().getId();

		if (buttonID == esc) {
			evt.getPlayer().getMainScreen().closePopup();
		}

		if (buttonID == general) {
			if (oldWidgets.containsKey(sp)) {
				HelpCommands.pinLabels(sp, sh.generalDataText, oldWidgets.get(sp), sh);
			} else {
				HelpCommands.pinLabels(sp, sh.generalDataText, null, sh);
			}
			oldWidgets.put(sp, sh.generalDataText);
		}

		if (buttonID == protection) {
			if (oldWidgets.containsKey(sp)) {
				HelpCommands.pinLabels(sp, sh.protectionText, oldWidgets.get(sp), sh);
			} else {
				HelpCommands.pinLabels(sp, sh.protectionText, null, sh);
			}
			oldWidgets.put(sp, sh.protectionText);
		}

		if (buttonID == data) {
			if (oldWidgets.containsKey(sp)) {
				HelpCommands.pinLabels(sp, sh.dataText, oldWidgets.get(sp), sh);
			} else {
				HelpCommands.pinLabels(sp, sh.dataText, null, sh);
			}
			oldWidgets.put(sp, sh.dataText);
		}

		if (buttonID == exceptions) {
			if (oldWidgets.containsKey(sp)) {
				HelpCommands.pinLabels(sp, sh.exceptionText, oldWidgets.get(sp), sh);
			} else {
				HelpCommands.pinLabels(sp, sh.exceptionText, null, sh);
			}
			oldWidgets.put(sp, sh.exceptionText);
		}

		if (buttonID == fun) {
			if (oldWidgets.containsKey(sp)) {
				HelpCommands.pinLabels(sp, sh.funText, oldWidgets.get(sp), sh);
			} else {
				HelpCommands.pinLabels(sp, sh.funText, null, sh);
			}
			oldWidgets.put(sp, sh.funText);
		}

		if (buttonID == invent) {
			if (oldWidgets.containsKey(sp)) {
				HelpCommands.pinLabels(sp, sh.inventText, oldWidgets.get(sp), sh);
			} else {
				HelpCommands.pinLabels(sp, sh.inventText, null, sh);
			}
			oldWidgets.put(sp, sh.inventText);
		}

		if (buttonID == message) {
			if (oldWidgets.containsKey(sp)) {
				HelpCommands.pinLabels(sp, sh.messagesText, oldWidgets.get(sp), sh);
			} else {
				HelpCommands.pinLabels(sp, sh.messagesText, null, sh);
			}
			oldWidgets.put(sp, sh.messagesText);
		}

		if (buttonID == modes) {
			if (oldWidgets.containsKey(sp)) {
				HelpCommands.pinLabels(sp, sh.modeText, oldWidgets.get(sp), sh);
			} else {
				HelpCommands.pinLabels(sp, sh.modeText, null, sh);
			}
			oldWidgets.put(sp, sh.modeText);
		}

		if (buttonID == modify) {
			if (oldWidgets.containsKey(sp)) {
				HelpCommands.pinLabels(sp, sh.modifyText, oldWidgets.get(sp), sh);
			} else {
				HelpCommands.pinLabels(sp, sh.modifyText, null, sh);
			}
			oldWidgets.put(sp, sh.modifyText);
		}

		if (buttonID == other) {
			if (oldWidgets.containsKey(sp)) {
				HelpCommands.pinLabels(sp, sh.otherText, oldWidgets.get(sp), sh);
			} else {
				HelpCommands.pinLabels(sp, sh.otherText, null, sh);
			}
			oldWidgets.put(sp, sh.otherText);
		}

		if (buttonID == perms) {
			if (oldWidgets.containsKey(sp)) {
				HelpCommands.pinLabels(sp, sh.permissionsText, oldWidgets.get(sp), sh);
			} else {
				HelpCommands.pinLabels(sp, sh.permissionsText, null, sh);
			}
			oldWidgets.put(sp, sh.permissionsText);
		}

		if (buttonID == spout) {
			if (oldWidgets.containsKey(sp)) {
				HelpCommands.pinLabels(sp, sh.spoutText, oldWidgets.get(sp), sh);
			} else {
				HelpCommands.pinLabels(sp, sh.spoutText, null, sh);
			}
			oldWidgets.put(sp, sh.spoutText);
		}
	}

	static final MutableProtection protection = new MutableProtection();
	static final MutableProtectionMisc miscProtection = new MutableProtectionMisc();
	static final MutableMessages msg = new MutableMessages();
	static final MutableEconomy eco = new MutableEconomy();
	static final MutableFun fun = new MutableFun();
	static final MutableMobs mob = new MutableMobs();
	static final MutableMisc misc = new MutableMisc();
	static final MutableModes modes = new MutableModes();

	private void regionScreen1Listener(ButtonClickEvent evt, ScreenHolder sh) {
		if (!RegionScreenManager.popup.containsKey(evt.getPlayer())) {
			return;
		}

		Region r = RegionScreenManager.editing.get(evt.getPlayer());

		UUID protect = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[0].getId()).getId();
		UUID prevententry = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[1].getId()).getId();
		UUID preventexit = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[2].getId()).getId();
		UUID preventinteraction = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[3].getId()).getId();
		UUID doorslocked = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[4].getId()).getId();
		UUID chestslocked = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[5].getId()).getId();
		UUID fireprotection = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[6].getId()).getId();
		UUID blockform = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[7].getId()).getId();
		UUID mobspawn = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[8].getId()).getId();
		UUID monsterspawn = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[9].getId()).getId();
		UUID showwelcome = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[10].getId()).getId();
		UUID showleave = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[11].getId()).getId();
		UUID showprevententry = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[12].getId()).getId();
		UUID showpreventexit = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[13].getId()).getId();
		UUID showprotection = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[14].getId()).getId();
		UUID showpvp = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[15].getId()).getId();
		UUID pvp = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[16].getId()).getId();
		UUID healthenabled = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[17].getId()).getId();
		UUID protectmode = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[18].getId()).getId();
		UUID prevententrymode = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[19].getId()).getId();
		UUID preventexitmode = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[20].getId()).getId();
		UUID itemmode = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page1Widgets[21].getId()).getId();

		SpoutPlayer sp = evt.getPlayer();

		UUID buttonID = evt.getButton().getId();

		if (buttonID == protect) {
			if (r.is_protection()) {
				protection.editUnprotect(r);
				sp.sendNotification("Protection", ChatColor.RED + "Protection Disabled", Material.DIAMOND_BLOCK);
			} else {
				protection.editProtect(r);
				sp.sendNotification("Protection", ChatColor.GREEN + "Protection Enabled", Material.DIAMOND_BLOCK);
			}
			((GenericButton) (sh.page1Widgets[0])).setTooltip(RegionScreenManager.getStatus(r.is_protection()));
			((GenericButton) (sh.page1Widgets[0])).setTextColor(RegionScreenManager.getColourToken(r.is_protection()));
			((GenericButton) (sh.page1Widgets[0])).setDirty(true);
			return;
		}

		if (buttonID == prevententry) {
			if (r.isPreventEntry()) {
				protection.editAllowEntry(r);
				sp.sendNotification("Prevent Entry", ChatColor.RED + "Prevent Entry Disabled", Material.CHAINMAIL_CHESTPLATE);
			} else {

				protection.editPreventEntry(r);
				sp.sendNotification("Prevent Entry", ChatColor.GREEN + "Prevent Entry Enabled", Material.CHAINMAIL_CHESTPLATE);
			}
			((GenericButton) (sh.page1Widgets[1])).setTooltip(RegionScreenManager.getStatus(r.isPreventEntry()));
			((GenericButton) (sh.page1Widgets[1])).setTextColor(RegionScreenManager.getColourToken(r.isPreventEntry()));
			((GenericButton) (sh.page1Widgets[1])).setDirty(true);
			return;
		}

		if (buttonID == preventexit) {
			if (r.isPreventExit()) {
				protection.editAllowExit(r);
				sp.sendNotification("Prevent Exit", ChatColor.RED + "Prevent Exit Disabled", Material.CHAINMAIL_CHESTPLATE);
			} else {

				protection.editPreventExit(r);
				sp.sendNotification("Prevent Exit", ChatColor.GREEN + "Prevent Exit Enabled", Material.CHAINMAIL_CHESTPLATE);
			}
			((GenericButton) (sh.page1Widgets[2])).setTooltip(RegionScreenManager.getStatus(r.isPreventExit()));
			((GenericButton) (sh.page1Widgets[2])).setTextColor(RegionScreenManager.getColourToken(r.isPreventExit()));
			((GenericButton) (sh.page1Widgets[2])).setDirty(true);
			return;
		}

		if (buttonID == preventinteraction) {
			if (r.isPreventingInteraction()) {
				miscProtection.editInteraction(r, false);
				sp.sendNotification("Prevent Interaction", ChatColor.RED + "Interaction Disabled", Material.SHEARS);
			} else {
				miscProtection.editInteraction(r, true);
				sp.sendNotification("Prevent Interaction", ChatColor.GREEN + "Interaction Enabled", Material.SHEARS);
			}
			((GenericButton) (sh.page1Widgets[3])).setTooltip(RegionScreenManager.getStatus(r.isPreventingInteraction()));
			((GenericButton) (sh.page1Widgets[3])).setTextColor(RegionScreenManager.getColourToken(r.isPreventingInteraction()));
			((GenericButton) (sh.page1Widgets[3])).setDirty(true);
			return;
		}

		if (buttonID == doorslocked) {
			if (r.areDoorsLocked()) {
				miscProtection.editDoorsLocked(r, false);
				sp.sendNotification("Doors Locked", ChatColor.RED + "Door Locking Disabled", Material.IRON_DOOR);
			} else {
				miscProtection.editDoorsLocked(r, true);
				sp.sendNotification("Doors Locked", ChatColor.GREEN + "Door Locking Enabled", Material.IRON_DOOR);
			}
			((GenericButton) (sh.page1Widgets[4])).setTooltip(RegionScreenManager.getStatus(r.areDoorsLocked()));
			((GenericButton) (sh.page1Widgets[4])).setTextColor(RegionScreenManager.getColourToken(r.areDoorsLocked()));
			((GenericButton) (sh.page1Widgets[4])).setDirty(true);
			return;
		}

		if (buttonID == chestslocked) {
			if (r.isChestsLocked()) {
				miscProtection.editChestsLocked(r, false);
				sp.sendNotification("Chests Locked", ChatColor.RED + "Chest Locking Disabled", Material.CHEST);
			} else {
				miscProtection.editChestsLocked(r, true);
				sp.sendNotification("Chests Locked", ChatColor.GREEN + "Chest Locking Enabled", Material.CHEST);
			}
			((GenericButton) (sh.page1Widgets[5])).setTooltip(RegionScreenManager.getStatus(r.isChestsLocked()));
			((GenericButton) (sh.page1Widgets[5])).setTextColor(RegionScreenManager.getColourToken(r.isChestsLocked()));
			((GenericButton) (sh.page1Widgets[5])).setDirty(true);
			return;
		}

		if (buttonID == fireprotection) {
			if (r.isFireProtection()) {
				miscProtection.editFireProtection(r, false);
				sp.sendNotification("Fire Protection", ChatColor.RED + "Fire Protection Disabled", Material.FIRE);
			} else {
				miscProtection.editFireProtection(r, true);
				sp.sendNotification("Fire Protection", ChatColor.GREEN + "Fire Protection Enabled", Material.FIRE);
			}
			((GenericButton) (sh.page1Widgets[6])).setTooltip(RegionScreenManager.getStatus(r.isFireProtection()));
			((GenericButton) (sh.page1Widgets[6])).setTextColor(RegionScreenManager.getColourToken(r.isFireProtection()));
			((GenericButton) (sh.page1Widgets[6])).setDirty(true);
			return;
		}

		if (buttonID == blockform) {
			if (r.isBlockForm()) {
				miscProtection.editBlockForm(r, false);
				sp.sendNotification("Block Form", ChatColor.RED + "Block Form Disabled", Material.SNOW);
			} else {
				miscProtection.editBlockForm(r, true);
				sp.sendNotification("Block Form", ChatColor.GREEN + "Block Form Enabled", Material.SNOW);
			}
			((GenericButton) (sh.page1Widgets[7])).setTooltip(RegionScreenManager.getStatus(r.isBlockForm()));
			((GenericButton) (sh.page1Widgets[7])).setTextColor(RegionScreenManager.getColourToken(r.isBlockForm()));
			((GenericButton) (sh.page1Widgets[7])).setDirty(true);
			return;
		}

		if (buttonID == mobspawn) {
			if (r.isMobSpawns()) {
				mob.editMobSpawn(r, false);
				sp.sendNotification("Mob Spawns", ChatColor.RED + "Mob Spawns Disabled", Material.RAW_FISH);
			} else {
				mob.editMobSpawn(r, true);
				sp.sendNotification("Mob Spawns", ChatColor.GREEN + "Mob Spawns Enabled", Material.RAW_FISH);
			}
			((GenericButton) (sh.page1Widgets[8])).setTooltip(RegionScreenManager.getStatus(r.isMobSpawns()));
			((GenericButton) (sh.page1Widgets[8])).setTextColor(RegionScreenManager.getColourToken(r.isMobSpawns()));
			((GenericButton) (sh.page1Widgets[8])).setDirty(true);
			return;
		}

		if (buttonID == monsterspawn) {
			if (r.isMonsterSpawns()) {
				mob.editMonsterSpawn(r, false);
				sp.sendNotification("Monster Spawns", ChatColor.RED + "Monster Spawns Disabled", Material.RAW_FISH);
			} else {
				mob.editMonsterSpawn(r, true);
				sp.sendNotification("Monster Spawns", ChatColor.GREEN + "Monster Spawns Enabled", Material.RAW_FISH);
			}
			((GenericButton) (sh.page1Widgets[9])).setTooltip(RegionScreenManager.getStatus(r.isMonsterSpawns()));
			((GenericButton) (sh.page1Widgets[9])).setTextColor(RegionScreenManager.getColourToken(r.isMonsterSpawns()));
			((GenericButton) (sh.page1Widgets[9])).setDirty(true);
			return;
		}

		if (buttonID == showwelcome) {
			if (r.isShowWelcomeMessage()) {
				msg.editShowWelcomeMessage(r, false);
				sp.sendNotification("Welcome Message", ChatColor.RED + "Welcome Msg Disabled", Material.BOOK);
			} else {
				msg.editShowWelcomeMessage(r, true);
				sp.sendNotification("Welcome Message", ChatColor.GREEN + "Welcome Msg Enabled", Material.BOOK);
			}
			((GenericButton) (sh.page1Widgets[10])).setTooltip(RegionScreenManager.getStatus(r.isShowWelcomeMessage()));
			((GenericButton) (sh.page1Widgets[10])).setTextColor(RegionScreenManager.getColourToken(r.isShowWelcomeMessage()));
			((GenericButton) (sh.page1Widgets[10])).setDirty(true);
			return;
		}

		if (buttonID == showleave) {
			if (r.isShowLeaveMessage()) {
				msg.editShowLeaveMessage(r, false);
				sp.sendNotification("Leave Message", ChatColor.RED + "Leave Msg Disabled", Material.BOOK);
			} else {
				msg.editShowLeaveMessage(r, true);
				sp.sendNotification("Leave Message", ChatColor.GREEN + "Leave Msg Enabled", Material.BOOK);
			}
			((GenericButton) (sh.page1Widgets[11])).setTooltip(RegionScreenManager.getStatus(r.isShowLeaveMessage()));
			((GenericButton) (sh.page1Widgets[11])).setTextColor(RegionScreenManager.getColourToken(r.isShowLeaveMessage()));
			((GenericButton) (sh.page1Widgets[11])).setDirty(true);
			return;
		}

		if (buttonID == showprevententry) {
			if (r.isShowPreventEntryMessage()) {
				msg.editShowPreventEntryMessage(r, false);
				sp.sendNotification("Prev Entry Message", ChatColor.RED + "Prev Entry Msg Disabled", Material.BOOK);
			} else {
				msg.editShowPreventEntryMessage(r, true);
				sp.sendNotification("Prev Entry Message", ChatColor.GREEN + "Prev Entry Msg Enabled", Material.BOOK);
			}
			((GenericButton) (sh.page1Widgets[12])).setTooltip(RegionScreenManager.getStatus(r.isShowPreventEntryMessage()));
			((GenericButton) (sh.page1Widgets[12])).setTextColor(RegionScreenManager.getColourToken(r.isShowPreventEntryMessage()));
			((GenericButton) (sh.page1Widgets[12])).setDirty(true);
			return;
		}

		if (buttonID == showpreventexit) {
			if (r.isShowPreventExitMessage()) {
				msg.editShowPreventExitMessage(r, false);
				sp.sendNotification("Prev Exit Message", ChatColor.RED + "Prev Exit Msg Disabled", Material.BOOK);
			} else {
				msg.editShowPreventExitMessage(r, true);
				sp.sendNotification("Prev Exit Message", ChatColor.GREEN + "Prev Exit Msg Enabled", Material.BOOK);
			}
			((GenericButton) (sh.page1Widgets[13])).setTooltip(RegionScreenManager.getStatus(r.isShowPreventExitMessage()));
			((GenericButton) (sh.page1Widgets[13])).setTextColor(RegionScreenManager.getColourToken(r.isShowPreventExitMessage()));
			((GenericButton) (sh.page1Widgets[13])).setDirty(true);
			return;
		}

		if (buttonID == showprotection) {
			if (r.isShowProtectionMessage()) {
				msg.editShowProtectionMessage(r, false);
				sp.sendNotification("Protection Message", ChatColor.RED + "Protection Msg Disabled", Material.BOOK);
			} else {
				msg.editShowProtectionMessage(r, true);
				sp.sendNotification("Protection Message", ChatColor.GREEN + "Protection Msg Enabled", Material.BOOK);
			}
			((GenericButton) (sh.page1Widgets[14])).setTooltip(RegionScreenManager.getStatus(r.isShowProtectionMessage()));
			((GenericButton) (sh.page1Widgets[14])).setTextColor(RegionScreenManager.getColourToken(r.isShowProtectionMessage()));
			((GenericButton) (sh.page1Widgets[14])).setDirty(true);
			return;
		}

		if (buttonID == showpvp) {
			if (r.isShowPvpWarning()) {
				msg.editShowPvpWarningMessage(r, false);
				sp.sendNotification("PvP Message", ChatColor.RED + "PvP Msg Disabled", Material.BOOK);
			} else {
				msg.editShowPvpWarningMessage(r, true);
				sp.sendNotification("PvP Message", ChatColor.GREEN + "PvP Msg Enabled", Material.BOOK);
			}
			((GenericButton) (sh.page1Widgets[15])).setTooltip(RegionScreenManager.getStatus(r.isShowPvpWarning()));
			((GenericButton) (sh.page1Widgets[15])).setTextColor(RegionScreenManager.getColourToken(r.isShowPvpWarning()));
			((GenericButton) (sh.page1Widgets[15])).setDirty(true);
			return;
		}

		if (buttonID == pvp) {
			if (r.isPvp()) {
				fun.editPvPEnabled(r, false);
				sp.sendNotification("PvP", ChatColor.RED + "PvP Disabled", Material.DIAMOND_SWORD);
			} else {
				fun.editPvPEnabled(r, true);
				sp.sendNotification("PvP", ChatColor.GREEN + "PvP Enabled", Material.DIAMOND_SWORD);
			}
			((GenericButton) (sh.page1Widgets[16])).setTooltip(RegionScreenManager.getStatus(r.isPvp()));
			((GenericButton) (sh.page1Widgets[16])).setTextColor(RegionScreenManager.getColourToken(r.isPvp()));
			((GenericButton) (sh.page1Widgets[16])).setDirty(true);
			return;
		}

		if (buttonID == healthenabled) {
			if (r.isHealthEnabled()) {
				fun.editHealthEnabled(r, false);
				sp.sendNotification("Health", ChatColor.RED + "Health Disabled", Material.GOLDEN_APPLE);
			} else {
				fun.editHealthEnabled(r, true);
				sp.sendNotification("Health", ChatColor.GREEN + "Health Enabled", Material.GOLDEN_APPLE);
			}
			((GenericButton) (sh.page1Widgets[17])).setTooltip(RegionScreenManager.getStatus(r.isHealthEnabled()));
			((GenericButton) (sh.page1Widgets[17])).setTextColor(RegionScreenManager.getColourToken(r.isHealthEnabled()));
			((GenericButton) (sh.page1Widgets[17])).setDirty(true);
			return;
		}

		if (buttonID == protectmode) {
			if (r.getProtectionMode() == MODE.Whitelist) {
				modes.editProtectionMode(r, MODE.Blacklist);
				sp.sendNotification("Protection Mode", ChatColor.BLACK + "Mode : Blacklist", Material.OBSIDIAN);
			} else if (r.getProtectionMode() == MODE.Blacklist) {
				modes.editProtectionMode(r, MODE.Whitelist);
				sp.sendNotification("Protection Mode", "Mode : Whitelist", Material.OBSIDIAN);
			}
			((GenericButton) (sh.page1Widgets[18])).setTooltip(RegionScreenManager.getStatus(r.getProtectionMode()));
			((GenericButton) (sh.page1Widgets[18])).setTextColor(RegionScreenManager.getColourToken(r.getProtectionMode()));
			((GenericButton) (sh.page1Widgets[18])).setDirty(true);
			return;
		}

		if (buttonID == prevententrymode) {
			if (r.getPreventEntryMode() == MODE.Whitelist) {
				modes.editPreventEntryMode(r, MODE.Blacklist);
				sp.sendNotification("Prevent Entry Mode", ChatColor.BLACK + "Mode : Blacklist", Material.OBSIDIAN);
			} else if (r.getPreventEntryMode() == MODE.Blacklist) {
				modes.editPreventEntryMode(r, MODE.Whitelist);
				sp.sendNotification("Prevent Entry Mode", "Mode : Whitelist", Material.OBSIDIAN);
			}
			((GenericButton) (sh.page1Widgets[19])).setTooltip(RegionScreenManager.getStatus(r.getPreventEntryMode()));
			((GenericButton) (sh.page1Widgets[19])).setTextColor(RegionScreenManager.getColourToken(r.getPreventEntryMode()));
			((GenericButton) (sh.page1Widgets[19])).setDirty(true);
			return;
		}

		if (buttonID == preventexitmode) {
			if (r.getPreventExitMode() == MODE.Whitelist) {
				modes.editPreventExitMode(r, MODE.Blacklist);
				sp.sendNotification("Prevent Exit Mode", ChatColor.BLACK + "Mode : Blacklist", Material.OBSIDIAN);
			} else if (r.getPreventExitMode() == MODE.Blacklist) {
				modes.editPreventExitMode(r, MODE.Whitelist);
				sp.sendNotification("Prevent Exit Mode", "Mode : Whitelist", Material.OBSIDIAN);
			}
			((GenericButton) (sh.page1Widgets[20])).setTooltip(RegionScreenManager.getStatus(r.getPreventExitMode()));
			((GenericButton) (sh.page1Widgets[20])).setTextColor(RegionScreenManager.getColourToken(r.getPreventExitMode()));
			((GenericButton) (sh.page1Widgets[20])).setDirty(true);
			return;
		}

		if (buttonID == itemmode) {
			if (r.getItemMode() == MODE.Whitelist) {
				modes.editItemControlMode(r, MODE.Blacklist);
				sp.sendNotification("Item Control Mode", ChatColor.BLACK + "Mode : Blacklist", Material.OBSIDIAN);
			} else if (r.getItemMode() == MODE.Blacklist) {
				modes.editItemControlMode(r, MODE.Whitelist);
				sp.sendNotification("Item Control Mode", "Mode : Whitelist", Material.OBSIDIAN);
			}
			((GenericButton) (sh.page1Widgets[21])).setTooltip(RegionScreenManager.getStatus(r.getItemMode()));
			((GenericButton) (sh.page1Widgets[21])).setTextColor(RegionScreenManager.getColourToken(r.getItemMode()));
			((GenericButton) (sh.page1Widgets[21])).setDirty(true);
			return;
		}

	}

	private void regionScreen2Listener(ButtonClickEvent evt, ScreenHolder sh) {

		Region r = RegionScreenManager.editing.get(evt.getPlayer());

		SpoutPlayer sp = evt.getPlayer();

		TextField welcometxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[0].getId());
		TextField leavetxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[1].getId());
		TextField prevententrytxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[2].getId());
		TextField preventexittxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[3].getId());
		TextField protecttxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[4].getId());

		// 5 - 9 are labels
		UUID update = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[10].getId()).getId();

		UUID resetwelcome = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[11].getId()).getId();
		UUID resetleave = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[12].getId()).getId();
		UUID resetprevententry = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[13].getId()).getId();
		UUID resetpreventexit = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[14].getId()).getId();
		UUID resetprotection = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[15].getId()).getId();

		UUID cw = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[16].getId()).getId();
		UUID rl = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[17].getId()).getId();
		UUID rpent = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[18].getId()).getId();
		UUID rpex = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[19].getId()).getId();
		UUID rprot = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page2Widgets[20].getId()).getId();

		UUID button = evt.getButton().getId();

		if (button == update) {
			msg.editWelcomeMessage(r, welcometxt.getText());
			msg.editLeaveMessage(r, leavetxt.getText());
			msg.editPreventEntryMessage(r, prevententrytxt.getText());
			msg.editPreventExitMessage(r, preventexittxt.getText());
			msg.editProtectionMessage(r, protecttxt.getText());
			sp.sendNotification("Region Messages", ChatColor.GREEN + "All Messages Updated!", Material.PAPER);
			return;
		}

		if (button == cw) {
			welcometxt.setText("");
			welcometxt.setDirty(true);
			sp.sendNotification("Welcome Message", ChatColor.GREEN + "Welcome Msg Wiped", Material.PAPER);
			return;
		}

		if (button == rl) {
			leavetxt.setText("");
			leavetxt.setDirty(true);
			sp.sendNotification("Leave Message", ChatColor.GREEN + "Leave Msg Wiped", Material.PAPER);
			return;
		}

		if (button == rpent) {
			prevententrytxt.setText("");
			prevententrytxt.setDirty(true);
			sp.sendNotification("Prevent Entry Message", ChatColor.GREEN + "Prevent Entry Msg Wiped", Material.PAPER);
			return;
		}

		if (button == rpex) {
			preventexittxt.setText("");
			preventexittxt.setDirty(true);
			sp.sendNotification("Prevent Exit Message", ChatColor.GREEN + "Prevent Exit Msg Wiped", Material.PAPER);
			return;
		}

		if (button == rprot) {
			protecttxt.setText("");
			protecttxt.setDirty(true);
			sp.sendNotification("Protection Message", ChatColor.GREEN + "Protection Msg Wiped", Material.PAPER);
			return;
		}

		if (button == resetwelcome) {
			welcometxt.setText(r.getWelcomeMessage());
			welcometxt.setDirty(true);
			sp.sendNotification("Welcome Message", ChatColor.GREEN + "Welcome Msg Reset", Material.PAPER);
			return;
		}

		if (button == resetleave) {
			leavetxt.setText(r.getLeaveMessage());
			leavetxt.setDirty(true);
			sp.sendNotification("Leave Message", ChatColor.GREEN + "Leave Msg Reset", Material.PAPER);
			return;
		}

		if (button == resetprevententry) {
			prevententrytxt.setText(r.getPreventEntryMessage());
			prevententrytxt.setDirty(true);
			sp.sendNotification("Prevent Entry Message", ChatColor.GREEN + "Prevent Entry Msg Reset", Material.PAPER);
			return;
		}

		if (button == resetpreventexit) {
			preventexittxt.setText(r.getPreventExitMessage());
			preventexittxt.setDirty(true);
			sp.sendNotification("Prevent Exit Message", ChatColor.GREEN + "Prevent Exit Msg Reset", Material.PAPER);
			return;
		}

		if (button == resetprotection) {
			protecttxt.setText(r.getProtectionMessage());
			protecttxt.setDirty(true);
			sp.sendNotification("Protection Message", ChatColor.GREEN + "Protection Msg Reset", Material.PAPER);
			return;
		}
	}

	private void regionScreen3Listener(ButtonClickEvent evt, ScreenHolder sh) {
		Region r = RegionScreenManager.editing.get(evt.getPlayer());

		SpoutPlayer sp = evt.getPlayer();

		TextField lspstxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[1].getId());
		TextField healthregentxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[4].getId());
		TextField velwarptxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[7].getId());
		TextField pricetxt = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[11].getId());

		UUID togglesale = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[9].getId()).getId();
		UUID confirmlsps = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[2].getId()).getId();
		UUID confirmhealthregen = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[5].getId()).getId();
		UUID confirmvelwarp = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[8].getId()).getId();
		UUID confirmprice = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page3Widgets[12].getId()).getId();

		UUID button = evt.getButton().getId();

		if (button == togglesale) {
			if (r.isForSale()) {
				eco.editForSale(r, false);
				sp.sendNotification("Sale", ChatColor.RED + "Not for sale", Material.SIGN);
			} else {
				eco.editForSale(r, true);
				sp.sendNotification("Sale", ChatColor.GREEN + "For sale", Material.SIGN);
			}
			((GenericButton) (sh.page3Widgets[9])).setTooltip(RegionScreenManager.getStatus(r.isForSale()));
			((GenericButton) (sh.page3Widgets[9])).setTextColor(RegionScreenManager.getColourToken(r.isForSale()));
			((GenericButton) (sh.page3Widgets[9])).setDirty(true);
			return;
		}

		if (button == confirmlsps) {
			String lsps = lspstxt.getText();
			int val;
			try {
				val = Integer.parseInt(lsps);
			} catch (NumberFormatException ex) {
				sp.sendNotification(ChatColor.RED + "Error!", "The value must be an int!", Material.FIRE);
				return;
			}
			if (val < 0) {
				sp.sendNotification(ChatColor.RED + "Error!", "The value must be > 0!", Material.FIRE);
				return;
			}
			fun.editLSPS(r, val);
			sp.sendNotification("LSPS", ChatColor.GREEN + "Set to : " + val, Material.FIRE);
		}

		if (button == confirmhealthregen) {
			String hr = healthregentxt.getText();
			int val;
			try {
				val = Integer.parseInt(hr);
			} catch (NumberFormatException ex) {
				sp.sendNotification(ChatColor.RED + "Error!", "The value must be an int!", Material.FIRE);
				return;
			}
			if (val < 0) {
				sp.sendNotification(ChatColor.RED + "Error!", "The value must be > 0!", Material.FIRE);
				return;
			}
			fun.editHealthRegen(r, val);
			sp.sendNotification("Health Regen", ChatColor.GREEN + "Set to : " + val, Material.PORK);
		}

		if (button == confirmvelwarp) {
			String vel = velwarptxt.getText();
			double val;
			try {
				val = Double.parseDouble(vel);
			} catch (NumberFormatException ex) {
				sp.sendNotification(ChatColor.RED + "Error!", "Value must be a double!", Material.FIRE);
				return;
			}
			if (val < 0) {
				sp.sendNotification(ChatColor.RED + "Error!", "The value must be > 0!", Material.FIRE);
				return;
			}
			fun.editVelocityWarp(r, val);
			sp.sendNotification("Velocity Warp", ChatColor.GREEN + "Set to : " + val, Material.CAKE);
		}

		if (button == confirmprice) {
			String price = pricetxt.getText();
			int val;
			try {
				val = Integer.parseInt(price);
			} catch (NumberFormatException ex) {
				sp.sendNotification(ChatColor.RED + "Error!", "The value must be an int!", Material.FIRE);
				return;
			}
			if (val < 0) {
				sp.sendNotification(ChatColor.RED + "Error!", "The value must be > 0!", Material.FIRE);
				return;
			}
			eco.editSalePrice(r, val);
			sp.sendNotification("Region Price", ChatColor.GREEN + "Set to : " + val, Material.SIGN);
		}
	}
	
	private void regionScreen4Listener(ButtonClickEvent evt, ScreenHolder sh) {
		
		Region r = RegionScreenManager.editing.get(evt.getPlayer());

		SpoutPlayer sp = evt.getPlayer();
		
		TextField excepField = (TextField) ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[5].getId());

		UUID togglePlayer = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[1].getId()).getId();
		UUID toggleNode = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[2].getId()).getId();
		UUID toggleSubOwner = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[3].getId()).getId();
		UUID toggleItems = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[4].getId()).getId();
		
		UUID addEx = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[6].getId()).getId();
		UUID remEx = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[7].getId()).getId();
		UUID eraseEx = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[8].getId()).getId();
		UUID back = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[10].getId()).getId();
		UUID forward = ((GenericPopup) RegionScreenManager.popup.get(evt.getPlayer())).getWidget(sh.page4Widgets[11].getId()).getId();
	
		UUID button = evt.getButton().getId();
		
		if(button == addEx){
			if(excepField.getText().length() < 1){
				sp.sendNotification(ChatColor.RED + "Error!", "No Text Entered!", Material.FIRE);
				return;
			}
			RegionScreen4.addException(RegionScreen4.toggle.get(sp), sp, r, excepField.getText(), excepField);
		}
		
		if(button == remEx){
			if(excepField.getText().length() < 1){
				sp.sendNotification(ChatColor.RED + "Error!", "No Text Entered!", Material.FIRE);
				return;
			}
			RegionScreen4.removeException(RegionScreen4.toggle.get(sp), sp, r, excepField.getText(), excepField);
		}
		
		if(button == eraseEx){
			RegionScreen4.eraseExceptions(RegionScreen4.toggle.get(sp), sp, r);
		}
		
		if(button == back){
			RegionScreen4.prevPage(sp, r, sh);
		}
		
		if(button == forward){
			RegionScreen4.nextPage(sp, r, sh);
		}
		
		if(button == togglePlayer){
			RegionScreen4.switchToggle(sp, ExToggle.PLAYER, sh, r, evt.getButton());
		}
		
		if(button == toggleNode){
			RegionScreen4.switchToggle(sp, ExToggle.NODE, sh, r, evt.getButton());
		}
		
		if(button == toggleSubOwner){
			RegionScreen4.switchToggle(sp, ExToggle.SUB_OWNER, sh, r, evt.getButton());
		}
		
		if(button == toggleItems){
			RegionScreen4.switchToggle(sp, ExToggle.ITEM, sh, r, evt.getButton());
		}
	}

}