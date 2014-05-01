package com.core.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.core.game.CoreGame;
import static com.core.log.CoreLogTag.*;

import com.core.log.CoreLogger;
import com.core.config.CoreAssetConfig;


/**
 * Base asset class for all texture region drawables
 * @author saksham
 *
 */
public class CoreAsset {
	protected static ObjectMap<String, CoreAsset> coreAssetMap =
			new ObjectMap<String, CoreAsset>();
	
	public enum AssetState {
		INITIALIZED,
		ADDED,
		LOADED,
		FAILED
	}
	
	protected String fileName;
	protected AssetState state;
	
	protected TextureRegion textureRegion;
	
	protected CoreAssetManager assetManager;
	
	public static CoreAsset get(String fileName) {
		CoreAsset coreAsset = checkExistingAsset(CoreAsset.getAssetClass(), fileName);
		
		if (coreAsset == null) {
			coreAsset = new CoreAsset(fileName);
			addToAssetCache(coreAsset);
		}
		
		return coreAsset;
	}
	
	protected static void addToAssetCache(CoreAsset coreAsset) {
		coreAssetMap.put(coreAsset.fileName, coreAsset);
	}
	
	/**
	 * Take care of state changes when an asset fails
	 * @param fileName
	 */
	public static void failAsset(String fileName, Throwable throwable) {
		CoreLogger.log(ASSETS, "Failed to load asset: " + fileName + " error: " + throwable.getMessage());
		
		CoreAsset coreAsset = coreAssetMap.get(fileName);
		
		if (coreAsset == null) {
			throw new GdxRuntimeException("Failed asset not cached: " + fileName);
		}
		
		coreAsset.setState(AssetState.FAILED);
	}

	/**
	 * Checks for the asset's presence and creates the
	 * object map entry if that's not the case
	 * @param clazz
	 * @param fileName
	 * @return
	 */
	protected static CoreAsset checkExistingAsset(Class clazz, String fileName) {
		return coreAssetMap.get(fileName);
	}
	
	public CoreAsset(String fileName) {
		this.assetManager = CoreGame.getAssetManager();
		this.fileName = fileName;
		
		this.setState(AssetState.INITIALIZED);
	}
	
	protected void setState(AssetState state) {
		this.state = state;
		CoreLogger.log(ASSETS, "Setting Asset State: " + state + " for Asset: " + fileName);
	}
	
	/**
	 * Handles state changes for this asset
	 * @return Whether the asset is loaded or not
	 */
	public boolean isLoaded() {
		switch (state) {
		case LOADED:
			return true;
		case INITIALIZED:
			loadInAssetManager();
			break;
		case ADDED:
			checkLoaded();
			break;
		default:
			break;
		}
		
		return false;
	}
	
	/**
	 * Puts the asset in the asset manager loading queue
	 */
	protected void loadInAssetManager() {
		TextureParameter tm = new TextureParameter();
		tm.minFilter = CoreAssetConfig.DEFAULT_MIN_TEXTURE_FILTER;
		tm.magFilter = CoreAssetConfig.DEFAULT_MIN_TEXTURE_FILTER;
		tm.genMipMaps = false;
		assetManager.load(fileName, Texture.class, tm);
		
		this.setState(AssetState.ADDED);
	}
	
	/**
	 * Checks the status of this asset in asset manager
	 * and changes the state if necessary
	 */
	protected void checkLoaded() {
		assetManager.update();
		
		if (assetManager.isLoaded(fileName)) {
			this.reset();
			this.setState(AssetState.LOADED);
		}
	}
	
	protected void reset() {
		Texture texture = assetManager.get(fileName, Texture.class);
		
		this.textureRegion = new TextureRegion(texture);
	}
	
	public TextureRegion getTextureRegion() {
		return textureRegion;
	}
	
	public static Class getAssetClass() {
		return CoreAsset.class;
	}
	
	/**
	 * Loads this asset by calling into
	 * {@link AssetManager#finishLoading()}
	 */
	public void load() {
		while (!isLoaded()) {
			assetManager.finishLoading();
		}
	}
}
