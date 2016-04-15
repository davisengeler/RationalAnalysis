RationalAnalysis
-------------

This Java class allows you to generate a mathematical analysis for a given rational
number in any valid base `b`.

#### Sample Output:

```
Enter the numerator:
15
Enter the denominator:
66

15/66 is reducible. Considering it to be 5/22

We must first calculate the p-adic order of 22 (the denominator) for all prime factors p of the base.
(So we will calculate 2-adic and 5-adic order of 22 since we are in base 10):

The 2-adic order of 22 is 1
The 5-adic order of 22 is 0

The maximum of these two values (1) is then equal to the number of non-repeating digits in the decimal expansion of 5/22.

The prime factorization of 22 (the denominator) contains some primes that do not divide the base. It will not terminate.
Now consider a new number, say u, that is built from only the prime factors of the denominator that do not divide the base.
Let u = 22/(2^1*5^0) <-- This 'removes' the largest powers of primes dividing both the denominator and the base.
The period is then equal to the order of the base 10 modulo u.

To summarize facts about the decimal expansion of 5/22:
It is non-terminating. There are 1 non-periodic decimal digits before its 2 digit period.
Check it out: 0.22727272727272727 (this might have rounding or rollover errors – check wolframalpha)
```

#### Current features:

| Feature | Return | Description |
|---------|--------|-------------|
| `terminates()` | `boolean` | The radix `b` expansion terminates. |
| `periodLength()` | `int` | The length of the repeating period in the radix `b` expansion. |
| `nonperiodicLength()` | `int` | The length of the non-repeating portion in the radix `b` expansion. |


#### TODO:


| Feature | Return | Description |
|---------|--------|-------------|
| `accuracy()` | `double` | Percent accuracy of of the number when stored in `storageSize` for the given `base`. |
