package org.spoutcraft.client.gui.minimap;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map.Entry;

import net.minecraft.src.Entity;

import org.spoutcraft.client.SpoutClient;
import org.spoutcraft.client.gui.GuiSpoutScreen;
import org.spoutcraft.spoutcraftapi.Spoutcraft;
import org.spoutcraft.spoutcraftapi.addon.Addon;
import org.spoutcraft.spoutcraftapi.gui.Button;
import org.spoutcraft.spoutcraftapi.gui.GenericButton;
import org.spoutcraft.spoutcraftapi.gui.GenericLabel;
import org.spoutcraft.spoutcraftapi.gui.GenericScrollArea;

public class GuiAdvancedEntitySettings extends GuiSpoutScreen {
	GuiMinimapMenu parent = null;
	GenericScrollArea scroll;
	GenericButton buttonDone;
	GenericLabel title;
	LinkedList<EntityVisibilityCheckbox> checks = new LinkedList<EntityVisibilityCheckbox>();

	public GuiAdvancedEntitySettings(GuiMinimapMenu guiMinimapMenu) {
		parent = guiMinimapMenu;
	}

	@Override
	protected void createInstances() {
		title = new GenericLabel("Filter Mobs");
		buttonDone = new GenericButton("Done");
		scroll = new GenericScrollArea();

		Addon spoutcraft = Spoutcraft.getAddonManager().getAddon("Spoutcraft");

		for (Entry<Class<? extends Entity>, String> e : WatchedEntity.mobFaceTextures.entrySet()) {
			EntityVisibilityCheckbox ch = new EntityVisibilityCheckbox(e.getKey(), e.getValue());
			scroll.attachWidget(spoutcraft, ch);
			checks.add(ch);
		}
		Collections.sort(checks, new Comparator<EntityVisibilityCheckbox>() {
			@Override
			public int compare(EntityVisibilityCheckbox o1, EntityVisibilityCheckbox o2) {
				return o1.getText().compareTo(o2.getText());
			}
		});

		getScreen().attachWidgets(spoutcraft, buttonDone, title, scroll);
	}

	@Override
	protected void layoutWidgets() {
		title.setX(width / 2 - SpoutClient.getHandle().fontRenderer.getStringWidth(title.getText()) / 2);
		title.setY(10);

		scroll.setGeometry(0, 25, width, height - 25 - 30);

		int needed = 315;
		int top = 5;
		int i = 0;
		int left = width / 2 - needed / 2;
		int center = left + 100 + 5;
		int right = center + 100 + 5;
		for (EntityVisibilityCheckbox ch : checks) {
			ch.setGeometry(0, top, 100, 20);
			switch(i%3) {
				case 0:
					ch.setX(left);
					break;
				case 1:
					ch.setX(center);
					break;
				case 2:
					ch.setX(right);
					break;
			}
			i++;
			if (i%3==0) {
				top += 22;
			}
		}
		scroll.updateInnerSize(); 

		buttonDone.setGeometry(width / 2 + 5, height - 25, 150, 20);
	}

	@Override
	protected void buttonClicked(Button btn) {
		if (btn == buttonDone) {
			mc.displayGuiScreen(parent);
		}
	}
}
