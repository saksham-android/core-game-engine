package com.core.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.core.assets.loaders.CoreAssetLoader;
import com.core.log.CoreLogger;
import static com.core.log.CoreLogTag.*;

/**
 * Manager to manage all types of core assets
 * 
 * @author saksham
 *
 */
public class CoreAssetManager extends AssetManager implements AssetErrorListener {
	protected static FileHandleResolver resolver = new CoreFileHandleResolver();
	
	public CoreAssetManager() {
		super(resolver);
		
		this.setLoaders();
		this.setErrorListener(this);
	}
	
	protected void setLoaders() {
		this.setLoader(Texture.class, new CoreAssetLoader(resolver));
	}
	
	public static void disposeOnExit() {
		resolver = null;
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		CoreAsset.failAsset(asset.fileName, throwable);
	}
}
