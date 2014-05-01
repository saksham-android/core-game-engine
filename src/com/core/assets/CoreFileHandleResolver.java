package com.core.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

public class CoreFileHandleResolver implements FileHandleResolver {
	@Override
	public FileHandle resolve(String fileName) {
		return Gdx.files.internal(fileName);
	}

}
