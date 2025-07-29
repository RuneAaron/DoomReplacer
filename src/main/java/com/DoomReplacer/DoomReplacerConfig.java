package com.DoomReplacer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(DoomReplacerConfig.GROUP)
public interface DoomReplacerConfig extends Config {

	// Default
	static final int DEFAULT_MELEE = 3378;

	static final int ZEBAK_RANGE_PROJECTILE = 2178;
	static final int ZEBAK_MAGE_PROJECTILE = 2176;

	// Inferno
	static final int INFERNO_RANGE = 1380;
	static final int INFERNO_MAGE = 1380;
	static final int INFERNO_MELEE = DEFAULT_MELEE;

	// CoX
	static final int COX_RANGE = 1343;
	static final int COX_MAGE = 1341;
	static final int COX_MELEE = 1345;

	// ToB
	static final int TOB_RANGE = 1607;
	static final int TOB_MAGE = 1606;
	static final int TOB_MELEE = DEFAULT_MELEE;

	// ToA
	static final int TOA_RANGE = 2241;
	static final int TOA_MAGE = 2224;
	static final int TOA_MELEE = 2204;

	String GROUP = "doom-projectiles";

	@Getter
	@AllArgsConstructor
	enum Style {
		Inferno(INFERNO_RANGE, INFERNO_MAGE, INFERNO_MELEE, ZEBAK_RANGE_PROJECTILE, ZEBAK_MAGE_PROJECTILE),
		CoX(COX_RANGE, COX_MAGE, COX_MELEE, ZEBAK_RANGE_PROJECTILE, ZEBAK_MAGE_PROJECTILE),
		ToB(TOB_RANGE, TOB_MAGE, TOB_MELEE, ZEBAK_RANGE_PROJECTILE, ZEBAK_MAGE_PROJECTILE),
		ToA(TOA_RANGE, TOA_MAGE, TOA_MELEE, ZEBAK_RANGE_PROJECTILE, ZEBAK_MAGE_PROJECTILE);

		private final int range;
		private final int magic;
		private final int melee;
		private final int rangerock;
		private final int magerock;
	}

	@ConfigItem(keyName = "style", name = "Projectile Style", description = "The type of projectile to replace Dooms projectiles with", position = 1)
	default Style style() {
		return Style.ToA;
	}

	@ConfigItem(name = "Themed Rock", keyName = "themedRock", description = "Replace boulders with Zebak alternatives", position = 2)
	default boolean themedRock() {
		return true;
	}
}