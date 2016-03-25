package com.google.common.hash;

import java.nio.charset.StandardCharsets;

public class HashExample {

	public static void main(String[] args){
		HashFunction h = Hashing.murmur3_128(423564178);
		HashCode v = h.hashString("myString", StandardCharsets.UTF_8);
		double distance = Math
				.acos(3/ (Math.sqrt(4) * Math.sqrt(7)));
		System.out.println(distance);
		System.out.println(v.asInt());
		
	}
	
}
