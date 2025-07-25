package com.example;

import com.DoomReplacer.DoomReplacerPlugin;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ExamplePluginTest {
	public static void main(String[] args) throws Exception {
		ExternalPluginManager.loadBuiltin(DoomReplacerPlugin.class);
		RuneLite.main(args);
	}
}