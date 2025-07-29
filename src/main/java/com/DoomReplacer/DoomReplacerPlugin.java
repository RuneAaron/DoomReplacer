// package com.DoomReplacer;

// import com.google.inject.Provides;
// import javax.inject.Inject;
// import lombok.extern.slf4j.Slf4j;
// import net.runelite.api.ChatMessageType;
// import net.runelite.api.Client;
// import net.runelite.api.GameState;
// import net.runelite.api.events.GameStateChanged;
// import net.runelite.client.config.ConfigManager;
// import net.runelite.client.eventbus.Subscribe;
// import net.runelite.client.plugins.Plugin;
// import net.runelite.client.plugins.PluginDescriptor;

// @Slf4j
// @PluginDescriptor(name = "Doom Replacer")
// public class DoomReplacerPlugin extends Plugin {
// 	@Inject
// 	private Client client;

// 	@Inject
// 	private DoomReplacerConfig config;

// 	@Override
// 	protected void startUp() throws Exception {
// 		log.info("Example started!");
// 		log.info("config.themedRock() = {}", config.themedRock());
// 	}

// 	@Override
// 	protected void shutDown() throws Exception {
// 		log.info("Example stopped!");
// 	}

// 	@Subscribe
// 	public void onGameStateChanged(GameStateChanged gameStateChanged) {
// 		if (gameStateChanged.getGameState() == GameState.LOGGED_IN) {
// 			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Welcome to Doom Replacer!", null);
// 		}
// 	}

// 	@Provides
// 	DoomReplacerConfig provideConfig(ConfigManager configManager) {
// 		return configManager.getConfig(DoomReplacerConfig.class);
// 	}
// }

package com.DoomReplacer;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Projectile;
import net.runelite.api.events.ProjectileMoved;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(name = "Doom Replacer", description = "Alter the Projectiles for Dooms attacks for colourblind accessibility", tags = {
		"boss", "pvm", "doom", "osto-ayak" })
public class DoomReplacerPlugin extends Plugin {

	private static final int MAGIC_PROJECTILE = 3379;
	private static final int RANGE_PROJECTILE = 3380;
	private static final int MELEE_PROJECTILE = 3378;

	private static final int RANGE_ROCK_PROJECTILE = 3384;
	private static final int MAGE_ROCK_PROJECTILE = 3385;

	@Inject
	private Client client;

	@Inject
	private DoomReplacerConfig config;

	@Provides
	DoomReplacerConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(DoomReplacerConfig.class);
	}

	@Subscribe
	public void onProjectileMoved(ProjectileMoved projectileMoved) {
		Projectile projectile = projectileMoved.getProjectile();
		if (projectile.getId() == RANGE_PROJECTILE) {
			replaceProjectile(projectile, config.style().getRange());
		} else if (projectile.getId() == MAGIC_PROJECTILE) {
			replaceProjectile(projectile, config.style().getMagic());
		} else if (projectile.getId() == MELEE_PROJECTILE && config.style().getMelee() != projectile.getId()) {
			replaceProjectile(projectile, config.style().getMelee());
		}

		if (projectile.getId() == RANGE_ROCK_PROJECTILE && config.themedRock()) {
			replaceProjectile(projectile, config.style().getRangerock());
		} else if (projectile.getId() == MAGE_ROCK_PROJECTILE && config.themedRock()) {
			replaceProjectile(projectile, config.style().getMagerock());
		}

	}

	private void replaceProjectile(Projectile projectile, int projectileId) {
		Projectile p = client.createProjectile(projectileId,
				projectile.getSourcePoint(),
				projectile.getStartHeight(), projectile.getSourceActor(),
				projectile.getTargetPoint(),
				projectile.getEndHeight(), projectile.getTargetActor(),
				projectile.getStartCycle(), projectile.getEndCycle(),
				projectile.getSlope(), projectile.getStartPos());

		client.getProjectiles().addLast(p);
		projectile.setEndCycle(0);
	}
}