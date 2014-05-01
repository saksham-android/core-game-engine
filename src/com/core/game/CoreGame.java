package com.core.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.core.actors.CoreAssetActor;
import com.core.assets.CoreAsset;
import com.core.assets.CoreAssetManager;
import com.core.config.CoreDisplayConfig;
import com.core.drawables.CoreAssetDrawable;
import com.core.drawables.CoreUIDrawable;
import com.core.game.IGame;
import com.core.log.CoreLogger;
import com.core.log.CoreLogger.*;
import com.core.stages.CoreUIStage;
import com.core.ui.core.CoreTable;

import static com.core.log.CoreLogTag.*;

public class CoreGame extends ApplicationAdapter {
	public enum GameState {
		INITIALIZING, 
		LOADING_SCREEN, 
		INITIALIZING_CORE_MODULES, 
		INITIALIZE_STAGES,
		LOADED
	}

	protected IGame game;
	protected static CoreGame gameInstance;

	protected GameState state;
	protected CoreUIStage uiStage; 

	/**
	 * Core Modules
	 */
	protected CoreAssetManager assetManager;

	public CoreGame(Application app, IGame game) {
		this.game = game;
		this.setApp(app);
		this.game.initializeConfigs();

		gameInstance = this;
		this.setState(GameState.INITIALIZING);
	}

	private void setState(GameState state) {
		this.state = state;
		
		CoreLogger.log(GAME_STATE, "Reached state: " + state);
	}

	protected void initializeCoreModules() {
		/*
		 * Initialize Asset Manager 
		 */
		assetManager = game.getAssetManager();

		if (game.getAssetManager() == null) {
			assetManager = new CoreAssetManager();
		}

		this.setState(GameState.INITIALIZE_STAGES);
	}
	
	protected void initializeStages() {
		uiStage = game.getUIStage();
		
		if (uiStage == null) {
			uiStage = new CoreUIStage();
			
			CoreTable table = new CoreTable();
			CoreUIDrawable drawable = new CoreUIDrawable(CoreAsset.get("badlogic.jpg"));
			table.setBackground(drawable);
			table.debug();
			uiStage.addActor(table);
		}
		
		this.setState(GameState.LOADED);
	}

	@Override
	public void create() {
		
	}
	
	protected void renderStages() {
		uiStage.act(Gdx.graphics.getDeltaTime());
		uiStage.draw();
		Table.drawDebug(uiStage);
	}

	@Override
	public void render() {
		clearScreen();

		switch (state) {
		case INITIALIZING:
			// break;
		case LOADING_SCREEN:
			// break;
		case INITIALIZING_CORE_MODULES:
			initializeCoreModules();
			break;
		case INITIALIZE_STAGES:
			initializeStages();
			break;
		case LOADED:
			renderStages();
			break;
		default:
			break;
		}
	}

	protected void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public static CoreAssetManager getAssetManager() {
		return gameInstance.assetManager;
	}
	
	public static Viewport getUIStageViewport() {
		StretchViewport viewport = new StretchViewport(CoreDisplayConfig.UI_STAGE_VIEWPORT_WIDTH, 
				CoreDisplayConfig.UI_STAGE_VIEWPORT_HEIGHT);
		return viewport;
	}

	public void setApp(Application app) {
		Gdx.app = app;
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
}
