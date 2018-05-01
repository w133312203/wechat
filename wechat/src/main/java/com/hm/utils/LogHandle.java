package com.hm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHandle {
	
	Logger log = LoggerFactory.getLogger(LogHandle.class);

	public void recordLog(Throwable e) {
		log.error("",e);
	}
	
}
