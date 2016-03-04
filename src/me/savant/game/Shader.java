package me.savant.game;

import particles.ParticleShader;
import render.MasterRenderer;
import shader.NormalMappingShader;
import shader.StaticShader;
import shader.TerrainShader;
import skybox.SkyboxShader;
import water.WaterRenderer;
import water.WaterShader;
import engine.GameScript;
import font.FontShader;
import gui.GuiShader;

public class Shader extends GameScript
{
	public void start()
	{
		//Setup Shader Locations
		StaticShader.vertexFile = "src/shaders/vertexShader.txt";
		StaticShader.fragmentFile = "src/shaders/fragmentShader.txt";
		
		TerrainShader.vertexFile = "src/shaders/terrainVertexShader.txt";
		TerrainShader.fragmentFile = "src/shaders/terrainFragmentShader.txt";
		
		GuiShader.vertexFile = "src/shaders/guiVertexShader.txt";
		GuiShader.fragmentFile = "src/shaders/guiFragmentShader.txt";
		
		SkyboxShader.vertexShader = "src/shaders/skyboxVertexShader.txt";
		SkyboxShader.fragmentShader = "src/shaders/skyboxFragmentShader.txt";
		
		WaterShader.vertexShader = "src/shaders/waterVertex.txt";
		WaterShader.fragmentShader = "src/shaders/waterFragment.txt";
		
		NormalMappingShader.vertexFile = "src/shaders/vertexNormalShader.txt";
		NormalMappingShader.fragmentFile = "src/shaders/fragmentNormalShader.txt";
		
		FontShader.vertexShader = "src/shaders/fontVertex.txt";
		FontShader.fragmentShader = "src/shaders/fontFragment.txt";
		
		ParticleShader.vertexShader = "src/shaders/particleVertex.txt";
		ParticleShader.fragmentShader = "src/shaders/particleFragment.txt";
		
		//Setup Shader Textures
		WaterRenderer.DUDV_MAP = "res/dudvWaterMap.png";
		WaterRenderer.NORMAL_MAP = "res/normalWaterMap.png";
		
		//Setup Variables
		MasterRenderer.FOV = 90;
		MasterRenderer.NEAR_PLANE = 0.1f;
		MasterRenderer.FAR_PLANE = 1000f;
	}
	
	public void stop() {}
	public void update() {}
	public void lateUpdate() {}

}
