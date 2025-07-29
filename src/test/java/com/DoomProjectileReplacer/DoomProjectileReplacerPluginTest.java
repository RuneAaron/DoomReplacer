package com.DoomProjectileReplacer;

import com.DoomProjectileReplacer.DoomProjectileReplacerPlugin;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DoomProjectileReplacerPluginTest {
	public static void main(String[] args) throws Exception {
		ExternalPluginManager.loadBuiltin(DoomProjectileReplacerPlugin.class);
		RuneLite.main(args);
	}
}