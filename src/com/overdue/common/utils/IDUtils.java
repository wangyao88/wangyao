package com.overdue.common.utils;

import java.util.UUID;

public class IDUtils {

	public static String getUUID() {
		return UUID.randomUUID().toString().substring(0,32);
	}

}
