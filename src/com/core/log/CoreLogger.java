package com.core.log;

import com.badlogic.gdx.Gdx;

/**
 * Core logging class
 * 
 * @author saksham
 *
 */
public class CoreLogger {
	private static final String DELIMITER = "|";

	private static String getTagString(ILoggingTag tag) {
		String tagString = "";

		if (tag.getParent() != null) {
			tagString = getTagString(tag.getParent()) + tagString;
		}

		if (!tagString.equals("")) {
			return tagString + DELIMITER + tag.getTag();
		}

		return tagString + tag.getTag();
	}

	public static void log(ILoggingTag tag, String log) {
		String tagString = getTagString(tag);
		
		if (Gdx.app != null) {
			Gdx.app.log(tagString, log);
		}			
	}

	public static void debug(ILoggingTag tag, String log) {
		String tagString = getTagString(tag);

		if (Gdx.app != null) {
			Gdx.app.debug(tagString, log);
		}
	}

	public static void error(ILoggingTag tag, String log) {
		String tagString = getTagString(tag);

		if (Gdx.app != null) {
			Gdx.app.error(tagString, log);
		}
	}
}
