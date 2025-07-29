package com.DoomReplacer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DoomReplacerPluginTest {
	public static void main(String[] args) throws Exception {
		ExternalPluginManager.loadBuiltin(DoomReplacerPlugin.class);
		RuneLite.main(args);
	}
}