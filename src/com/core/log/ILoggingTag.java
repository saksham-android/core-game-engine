package com.core.log;

/**
 * Logging tags
 * 
 * @author saksham
 *
 */
public interface ILoggingTag {
	/**
	 * Main tag
	 * @return
	 */
	public String getTag();
	
	/**
	 * Dependencies are assumed to be in the reverse
	 * order
	 * @return
	 */
	public ILoggingTag getParent();
}
