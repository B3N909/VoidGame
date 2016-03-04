package me.savant.game;

import java.io.File;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.GameScript;
import font.FontType;
import font.GuiText;
import gui.GuiTexture;

public class PlayerUI extends GameScript
{
	public void start()
	{
		GuiTexture gui = new GuiTexture(getLoader().loadTexture("res/logo.png"), new Vector2f(0.95f, 0.82f), new Vector2f(0.25f, 0.25f));
	
		FontType font = new FontType(getLoader().loadTexture("res/candara.png"), new File("res/candara.fnt"));
		GuiText text = new GuiText("Savant's Game Engine", 3f, font, new Vector2f(0, 0), 1f, true);
		text.setColor(new Vector3f(1, 1, 1));
		text.hasDropshadow(true);
		
	}
	
	public void stop() {}
	public void update() {}
	public void lateUpdate() {}
}
