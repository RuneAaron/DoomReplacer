package com.DoomReplacer;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("example")
public interface DoomReplacerConfig extends Config {

	String GROUP_NAME = "doomreplacer";

	@ConfigSection(name = "General", description = "General settings for the plugin", position = 1, closedByDefault = true)
	String GENERAL_SECTION = "generalSection";

	@ConfigItem(keyName = "doomProjectileTheme", name = "Projectile Theme", description = "Modifies the Projectile to appear in a specific theme to assist with colourblind users", position = 1, section = GENERAL_SECTION)
	default DoomProjectileTheme doomProjectileTheme() {
		return DoomProjectileTheme.DEFAULT;
	}

	@ConfigItem(name = "Themed Rock", keyName = "themedRock", description = "Enables the themed rock for the Doom of Mokhaiotl", position = 2, section = GENERAL_SECTION)
	default boolean themedRock() {
		return true;
	}

}

enum DoomProjectileTheme {
	DEFAULT("Default"),
	RED("Red"),
	GREEN("Green"),
	BLUE("Blue");

	private final String name;

	DoomProjectileTheme(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}