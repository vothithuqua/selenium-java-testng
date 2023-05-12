package javaTester;

import java.util.Random;

public class Topic_05_Random {

	public static void main(String[] args) {
		// utilities = tiện ích 
		// Data type: Class/ Interface/ Collection/ String/ Float/ ...
		Random rand = new Random(); 
		System.out.println(rand.nextFloat());
		System.out.println(rand.nextDouble());
		System.out.println(rand.nextInt(99999));
		System.out.println("Automation"+ rand.nextInt(999) + "@gmail.com");
		System.out.println(rand.nextLong());

		System.out.println("thuqua" + getRandomNumber() + "@gmail.net");
		System.out.println("thuqua" + getRandomNumber() + "@gmail.net");
		System.out.println("thuqua" + getRandomNumber() + "@gmail.net");

	}
	
	public static int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
