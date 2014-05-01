package com.core.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.core.game.CoreGame;

public class CoreUIStage extends Stage {
	public CoreUIStage() {
		super(CoreGame.getUIStageViewport());
	}
}
