package com.course2go.config.utils;

import java.security.SecureRandom;
import java.util.Random;

public class RandomSaltGenerator {
	private static final Random RANDOM = new SecureRandom();
	
	public static byte[] getNextSalt() {
	    byte[] salt = new byte[16];
	    RANDOM.nextBytes(salt);
	    return salt;
	  }
	
}
