package com.core.ui.core;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.core.drawables.CoreAssetDrawable;
import com.core.drawables.CoreUIDrawable;

public class CoreTable extends Table {
	public CoreTable() {
		super();
	}
	
	public void setBackground(CoreUIDrawable background) {
		background.setTable(this);
		
		super.setBackground(background);
	}
}
