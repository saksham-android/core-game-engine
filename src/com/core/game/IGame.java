package com.core.game;

import com.core.assets.CoreAssetManager;
import com.core.stages.CoreUIStage;

/**
 * Bridge for game specific core data structures
 * 
 * @author saksham
 */
public interface IGame {
	/**
	 * @return NULL if default {@link CoreAssetManager}
	 * is to be used
	 */
	public CoreAssetManager getAssetManager();

	/**
	 * @return An optional game specific UI stage,
	 * default used if NULL is returned
	 */
	public CoreUIStage getUIStage();

	/**
	 * Initialize all configs for game specific code
	 */
	public void initializeConfigs();
}
