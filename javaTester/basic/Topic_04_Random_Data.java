package basic;

import java.util.Random;

public class Topic_04_Random_Data {

	public static void main(String[] args) {
		Random rand = new Random();
		System.out.println(rand.nextInt());
		
		// 0 -> 999 = 1000 số
		// 1 -> 1000
		System.out.println(rand.nextInt(999));
		System.out.println(rand.nextDouble());
		System.out.println(rand.nextLong());
		System.out.println(rand.nextFloat());
		
		System.out.println("HuyNguyen" + rand.nextInt(999) + "@hotmail.com");
		System.out.println("HuyNguyen" + rand.nextInt(999) + "@hotmail.com");
		System.out.println("HuyNguyen" + rand.nextInt(999) + "@hotmail.com");
		System.out.println("HuyNguyen" + rand.nextInt(999) + "@hotmail.com");
		System.out.println("HuyNguyen" + rand.nextInt(999) + "@hotmail.com");

	}

}
