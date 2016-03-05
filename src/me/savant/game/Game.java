package me.savant.game;

import models.ModelData;
import models.RawModel;
import models.TexturedModel;
import normalMappingObjConverter.NormalMappedObjLoader;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import render.Loader;
import render.OBJLoader;
import shader.Shader;
import terrain.Terrain;
import texture.TerrainTexture;
import texture.TerrainTexturePack;
import texture.Texture;
import water.WaterTile;
import engine.GameEngine;
import engine.World;
import engineui.GuiManager;
import engineui.TextureManager;
import entities.Camera;
import entities.Entity;
import entities.GameObject;
import entities.Light;
import entities.Player;
import file.FileManager;
import gui.GuiTexture;

public class Game extends GameEngine
{
	Loader loader;
	World world;
	
	static Game game;
	public static void main(String[] args)
	{
		game = new Game("Infiniteless - Demo");
		game.getDisplay().createDisplay();
		game.getUniverse().ready();
		System.out.println("[Loading] 10%");
		FileManager fileManager = new FileManager();
		TextureManager.fileManager = fileManager;
		game.getUniverse().setFileManager(fileManager);
		System.out.println("[Loading] 60%");
		game.getUniverse().syncData();
		System.out.println("[Loading] 100%");
		new PlayerUI();
		game.getUniverse().start();
	}
	
	public Game(String gameName)
	{
		super(gameName);
	}
	
	public void update()
	{
		world.update();
		
		if(Keyboard.isKeyDown(Keyboard.KEY_F1))
		{
			if(!TextureManager.isOpen())
			{
				TextureManager.open();
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_F2))
		{
			if(!GuiManager.isOpen())
			{
				GuiManager.open();
			}
		}
	}
	
	public void start()
	{
		//Getting 
		loader = getLoader();
		
		GuiTexture loadingTexture = new GuiTexture(loader, "res/loadingScreen_x2.png", new Vector2f(0f, 0f), new Vector2f(1f, 1f));
		loadingTexture.setEnabled(false);
		
		//Player
		Texture texture = new Texture(loader.loadTexture("res/blue.png"));
		texture.setSpecularDamper(10);
		texture.setSpecular(1);
		RawModel playerRawModel = ModelData.asRawModel(OBJLoader.loadOBJ("person"), loader);
		Texture playerTexture = new Texture(loader.loadTexture("res/rock.png"));
		playerTexture.setSpecularDamper(10);
		playerTexture.setSpecular(1);
		TexturedModel playerModel = new TexturedModel(playerRawModel, texture);
		Player player = new Player(playerModel, new Vector3f(20, 3, -20), 0, 90, 0, 0.75f);
		
		//Terrain
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("res/Nature/Textures/ground4.png"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("res/mud.png"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("res/Nature/Textures/ground15.png"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("res/path.png"));
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("res/blendMapDetail.png"));
		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
		Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap);
		terrain.createInfinite();
		
		//Normal Map Demo
		TexturedModel barrelModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("barrel", loader), new Texture(loader.loadTexture("res/barrel.png")));
		barrelModel.getTexture().setNormalMap(loader.loadTexture("res/barrelNormal.png"));
		barrelModel.getTexture().setSpecularDamper(10);
		barrelModel.getTexture().setSpecular(0.5f);
		Entity barrel = new GameObject(barrelModel, new Vector3f(35, 35, -35), 0, 0, 0, 1f);
		barrel.setShader(Shader.NORMAL);
		
		//World Stuff
		world = new World(getRenderer(), getGuiRenderer(), getWaterRenderer());
		Camera camera = new Camera(player);
		world.setCamera(camera);
		Light sun = new Light(new Vector3f(200, 200, 200), new Vector3f(1f, 1f, 1f));
		world.setLight(sun);
		WaterTile waterTile = new WaterTile(200, -200, 5, 200);
		world.setWater(waterTile);
		world.setPlayer(player);
		world.instantiate(player);
		world.instantiate(sun);
		world.instantiate(terrain);
		world.instantiate(waterTile);
		world.instantiate(barrel);
		world.instantiate(loadingTexture);
	}
	
	public void cleanUp()
	{
		loader.cleanUp();
	}
}
