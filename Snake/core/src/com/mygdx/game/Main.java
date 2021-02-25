package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.managers.StateManage;
import com.mygdx.game.managers.Jukebox;

public class Main extends ApplicationAdapter {

	public static float WIDTH;
	public static float HEIGHT;

	public static OrthographicCamera orgCamera;
	private static Vector3 oriPoCamera; //Vector to save the original position of the camera

	private static StateManage stateManage;


	@Override
	public void create() {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();

		orgCamera = new OrthographicCamera(WIDTH, HEIGHT);
		orgCamera.translate(WIDTH / 2, HEIGHT / 2);
		oriPoCamera = orgCamera.position.cpy();
		orgCamera.update();

		loadSounds();
		stateManage = new StateManage();
	}

	/**
	 * Sets the original position of the camera.
	 */
	public static void setCameraPosition() {
		orgCamera.position.set(oriPoCamera);
		orgCamera.update();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.1f, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stateManage.update(Gdx.graphics.getDeltaTime());
		stateManage.draw();
	}

	@Override
	public void dispose() {
		stateManage.batch.dispose();
		stateManage.renderer.dispose();
		stateManage.titleFont.dispose();
		stateManage.font.dispose();
	}

	private void loadSounds(){
		Jukebox.MANAGER.load("sounds/itemback.wav", "select");
		Jukebox.MANAGER.load("sounds/itempick.wav", "accept");
		Jukebox.MANAGER.load("sounds/level-up.wav", "levelup");
		Jukebox.MANAGER.load("sounds/tick.wav", "bonus");
		Jukebox.MANAGER.load("sounds/Slide01.wav","slide1");
		Jukebox.MANAGER.load("sounds/Slide02.wav", "slide2");
		Jukebox.MANAGER.load("sounds/hiss2.wav", "hiss");

	}
}