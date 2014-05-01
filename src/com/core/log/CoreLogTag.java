package com.core.log;

public class CoreLogTag implements ILoggingTag {
	public static CoreLogTag GAME_STATE = new CoreLogTag("GAME_STATE", null);
	public static CoreLogTag ASSETS = new CoreLogTag("ASSETS", null);
	
	protected String tag;
	protected ILoggingTag parent;
	
	public CoreLogTag(String tag, ILoggingTag parent) {
		this.tag = tag;
		this.parent = parent;
	}
	
	public CoreLogTag(String tag) {
		this(tag, null);
	}
	
	@Override
	public String getTag() {
		return tag;
	}

	@Override
	public ILoggingTag getParent() {
		return parent;
	}

}
