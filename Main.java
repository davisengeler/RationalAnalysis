import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the numerator:");
		Integer numerator = input.nextInt();
		System.out.println("Enter the denominator:");
		Integer denominator = input.nextInt();
		RationalNumber number = new RationalNumber(numerator, denominator);
		number.printInfo();
	}

}
