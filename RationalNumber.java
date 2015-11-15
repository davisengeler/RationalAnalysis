import java.math.BigInteger;


public class RationalNumber {
	
	private BigInteger n, d;
	private boolean terminates;
	private int nonperiod = 0, period = 0;
	
	public RationalNumber(Integer numerator, Integer denominator) {
		n = BigInteger.valueOf(numerator.intValue());
		d = BigInteger.valueOf(denominator.intValue());
		
		// Get n/d in lowest terms.
		BigInteger gcd = n.gcd(d);
		System.out.println(); // Separate input junk from the information produced.
		if (gcd.compareTo(BigInteger.valueOf(1)) != 0) {
			System.out.print(this + " is reducible. Considering it to be ");
			n = n.divide(gcd);
			d = d.divide(gcd);
			System.out.println(this);
		} else {
			System.out.println("It looks like " + this + " is in lowest terms. Great.");
		}
		
		// Find the denominator's 2-adic and 5-adic order (prime factors of the base [10]).
		// The max of these two will be the number of digits in the non-period.
		System.out.println("\nWe must first calculate the p-adic order of " + d + " (the denominator) for all prime factors p of the base.\n(So we will calculate 2-adic and 5-adic order of " + d + " since we are in base 10):\n");
		int twoAdic = padicOrder(2, d.intValue());
		int fiveAdic = padicOrder(5, d.intValue());
		nonperiod = Math.max(twoAdic, fiveAdic);
		System.out.println("\nThe maximum of these two values (" + nonperiod + ") is then equal to the number of non-repeating digits in the decimal expansion of " + this + ".");
		
		// Figure out if it terminates. If not, find how many digits make up its period.
		if (d.gcd(BigInteger.valueOf(10)).compareTo(BigInteger.valueOf(1)) == 0) {
			// If d is relatively prime to the base [10], check to see if it has only the prime factors of the base [10].
				terminates = false;
				period = order(10, d.intValue());
				System.out.println("Since " + d + " (the denominator) is relatively prime to the base, its period is equal to the order of the base modulo " + d + " (the denominator)");
		} else {
			// This d contains prime factors that divide the base. 
			// Consider it in terms its unique factors.
			if (Math.pow(2, twoAdic) * Math.pow(5, fiveAdic) != d.intValue()) {
				terminates = false;
				int modulo = d.intValue();
				modulo = (int)(modulo / (Math.pow(2, twoAdic) * Math.pow(5, fiveAdic)));
				period = order(10, modulo);
				System.out.println("\nThe prime factorization of " + d + " (the denominator) contains some primes that do not divide the base. It will not terminate.");
				System.out.println("Now consider a new number, say u, that is built from only the prime factors of the denominator that do not divide the base.");
				System.out.println("Let u = " + d + "/(2^" + twoAdic + "*5^" + fiveAdic + ") <-- This 'removes' the largest powers of primes dividing both the denominator and the base.");
				System.out.println("The period is then equal to the order of the base 10 modulo u.");
			} else {
				System.out.println("Since the denominator, " + d + ", can be expressed in terms of the base's prime factors ( " + d + " = 2^" + twoAdic + " * 5^" + fiveAdic + "), the expansion of " + this + " terminates in " + nonperiod + " digits of its decimal expansion.");
				terminates = true;
			}
		}
	}
	
	private int padicOrder(int p, int n) {
		// Simple idea to get the p-adic order:
		// Grab the largest exponent v such that p^v divides n.
		int v = 0;
		while (n % Math.pow(p, v) == 0) {
			v++;
		}
		System.out.println("The " + p + "-adic order of " + n + " is " + (v-1));
		return v - 1;
	}
	
	private int order(int num, int m) {
		// Using BigInteger here. Things can get pretty large...
		BigInteger base = BigInteger.valueOf(num);
		BigInteger modulo = BigInteger.valueOf(m);
		for (int i = 1; true; i++) {
			// Increase powers of the base until the value is congruent to 1 mod m.
			if (base.pow(i).mod(modulo).compareTo(BigInteger.valueOf(1)) == 0)
				return i;
		}
	}
	
	public String toString() {
		return n + "/" + d;
	}
	
	public void printInfo() {
		System.out.println("\nTo summarize facts about the decimal expansion of " + this + ":");
		if (terminates) System.out.println("It terminates in " + nonperiod + " decimal digits.");
		else System.out.println("It is non-terminating. There are " + nonperiod + " non-periodic decimal digits before its " + period + " digit period.");
		System.out.println("Check it out: " + ((double)n.intValue() / (double)d.intValue()) + " (this might have rounding or rollover errors – check wolframalpha)");
	}
}
