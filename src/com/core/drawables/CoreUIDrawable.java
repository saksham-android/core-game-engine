package com.core.drawables;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.core.assets.CoreAsset;
import com.core.ui.core.CoreTable;

public class CoreUIDrawable extends CoreAssetDrawable {
	protected CoreTable table;
	
	public CoreUIDrawable(CoreAsset asset) {
		super(asset);
	}

	public void setTable(CoreTable table) {
		this.table = table;
		super.setActor(table);
	}
	
	@Override
	public void onAssetLoad() {
		super.onAssetLoad();

		table.pack();
	}
}
