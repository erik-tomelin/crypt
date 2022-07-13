import java.math.BigInteger;

public class GetKeys {
	public static boolean goodkey; // true if last key was ok

	public static BigInteger geratePublicAndPrivateKey(BigInteger P, BigInteger Q, BigInteger E) {
		BigInteger one = new BigInteger("1");

		return getPrivateExp((P.subtract(one)).multiply(Q.subtract(one)), E);
	}

	public static BigInteger getPrivateExp(BigInteger Phi, BigInteger E) {
		BigInteger zero = new BigInteger("0");
		BigInteger one = new BigInteger("1");

		BigInteger r1 = E;
		BigInteger D = new BigInteger("1");
		BigInteger[] quotrem = Phi.divideAndRemainder(E);
		BigInteger quot = quotrem[0];
		BigInteger r2 = quotrem[1];
		BigInteger x = Phi.subtract(quot);

		while (r2.compareTo(zero) != 0) {
			quotrem = r1.divideAndRemainder(r2);
			quot = quotrem[0];

			BigInteger rem = quotrem[1];
			r1 = r2;
			r2 = rem;

			BigInteger temp = x;

			x = ((Phi.add(D)).subtract(quot.multiply(x).mod(Phi))).mod(Phi);
			D = temp;
		}

		goodkey = (r1.compareTo(one) == 0);

		return D;
	}
	
	public static void main(String[] args) {
		String a = "aaaaa";
		
		System.out.println(a.substring(0, 1));
		
		
		System.out.println(a.substring(1));
		
	}
}
