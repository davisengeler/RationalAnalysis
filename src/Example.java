import java.util.Scanner;


public class Example {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the numerator:");
		Integer numerator = input.nextInt();
		System.out.println("Enter the denominator:");
		Integer denominator = input.nextInt();
		RationalAnalysis number = new RationalAnalysis(numerator, denominator);
		number.printInfo();
	}

}
