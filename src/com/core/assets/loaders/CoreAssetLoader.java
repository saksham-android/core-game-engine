package com.core.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.utils.Array;

public class CoreAssetLoader extends AsynchronousAssetLoader<Texture, TextureLoader.TextureParameter> {

	public CoreAssetLoader(FileHandleResolver resolver) {
		super(resolver);
	}

	@Override
	public void loadAsync(AssetManager manager, String fileName,
			FileHandle file, TextureParameter parameter) {
		FileHandle handle = resolve(fileName);
		
		Pixmap pixmap = new Pixmap(handle);
		parameter.textureData = new FileTextureData(handle, pixmap, parameter.format, parameter.genMipMaps);
	}

	@Override
	public Texture loadSync(AssetManager manager, String fileName,
			FileHandle file, TextureParameter parameter) {
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Texture texture = parameter.texture;
		if (texture != null) {
			texture.load(parameter.textureData);
		} else {
			texture = new Texture(parameter.textureData);
		}
		if (parameter != null) {
			texture.setFilter(parameter.minFilter, parameter.magFilter);
			texture.setWrap(parameter.wrapU, parameter.wrapV);
		}
		return texture;
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String fileName,
			FileHandle file, TextureParameter parameter) {
		// TODO Auto-generated method stub
		return null;
	}

}
