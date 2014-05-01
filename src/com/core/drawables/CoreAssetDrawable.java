package com.core.drawables;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.core.actors.CoreAssetActor;
import com.core.assets.CoreAsset;

/**
 * Base drawables for all assets
 * 
 * @author saksham
 *
 */
public class CoreAssetDrawable extends TextureRegionDrawable {
	protected CoreAsset asset;
	protected Layout layout;
	
	protected boolean refreshRegion = true;
	
	public CoreAssetDrawable(CoreAsset asset) {
		this.asset = asset;
	}
	
	public void setActor(Layout layout) {
		this.layout = layout;
	}
	
	public void onAssetLoad() {
		this.setRegion(asset.getTextureRegion());
		
		if (this.layout != null) {
			this.layout.invalidateHierarchy();
		}
	}
	
	protected boolean checkAsset() {
		boolean assetLoaded = asset.isLoaded();
		
		if (assetLoaded) {
			if (refreshRegion) {
				this.onAssetLoad();
				refreshRegion = false;
			}
		} else {
			refreshRegion = true;
		}
		
		return assetLoaded;
	}
	
	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		if (checkAsset()) {
			super.draw(batch, x, y, width, height);
		}
	}
}
