package com.core.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.core.assets.CoreAsset;
import com.core.drawables.CoreAssetDrawable;

/**
 * Base class for all actors build using the
 * {@link CoreAsset} infra
 * 
 * @author saksham
 *
 */
public class CoreAssetActor extends Image {
	protected CoreAssetDrawable drawable;
	
	public CoreAssetActor(CoreAssetDrawable drawable) {
		this(drawable, Scaling.none);
	}
	
	public CoreAssetActor(CoreAssetDrawable drawable, Scaling scaling) {
		super(drawable, scaling, Align.left | Align.bottom);
		
		this.drawable = drawable;
		this.drawable.setActor(this);
	}
}
