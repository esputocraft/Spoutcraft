/*
 * This file is part of Spoutcraft (http://www.spout.org/).
 *
 * Spoutcraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spoutcraft is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.spoutcraft.client.gui.settings;

import java.util.UUID;

import org.spoutcraft.client.config.ConfigReader;
import org.spoutcraft.spoutcraftapi.event.screen.ButtonClickEvent;

public class FancyParticlesButton extends AutomatedCheckBox {
	UUID fancyGraphics;
	public FancyParticlesButton(UUID fancyGraphics) {
		super("Fancy Particles");
		this.fancyGraphics = fancyGraphics;
		setChecked(ConfigReader.fancyParticles);
		setTooltip("Fancy Light\nFast - lower quality, faster\nFancy - higher quality, slower\nFast Particles renders fewer particles, and only those nearyby.\nFancy particles renders all particles.");
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		ConfigReader.fancyParticles = !ConfigReader.fancyParticles;
		ConfigReader.write();
		((FancyGraphicsButton)getScreen().getWidget(fancyGraphics)).custom = true;
	}
}
