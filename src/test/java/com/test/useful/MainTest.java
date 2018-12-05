package com.test.useful;

import java.math.BigDecimal;

public class MainTest {
	public static void main(String[] args) {

	}
	
	
	
	
	// 生产随机数
	public void random() {
		StringBuffer buffer = new StringBuffer();
		String scid = String.valueOf(1234);
		buffer.append(scid.length()).append(scid);
		int i = (int) (Math.random() * 900) + 100;
		buffer.append(i);
		buffer.append(formatAmount("688.00", i));
	}
	public static String formatAmount(String amount, int i) {
		BigDecimal decimal = new BigDecimal(amount);
		decimal = decimal.multiply(new BigDecimal(100)).add(new BigDecimal(i * i));
		String newStr = decimal.setScale(0).toString();
		return newStr;
	}

}
