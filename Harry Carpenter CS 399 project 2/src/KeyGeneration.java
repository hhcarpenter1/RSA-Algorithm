import java.math.BigInteger;
import java.util.Random;
/**
 * A class for key generation
 * @author Harry
 *
 */
public class KeyGeneration {
	private BigInteger p;
	private BigInteger q;

	/**
	 * 
	 * @param p
	 * @param q
	 */
	public KeyGeneration(BigInteger p, BigInteger q) {
		this.p = p;
		this.q = q;
	}

	/**
	 * Computes the modulus by multiplying p and q
	 * @return n
	 */
	public BigInteger computeModulus() {
		BigInteger n = p.multiply(q); //n = p*q
		return n;
	}

	/**
	 * A method that computes the eulerTotient
	 * n 𝜙(n) = (p – 1) * (q – 1)
	 * @return eulerTotient
	 */
	public BigInteger computeEulerTotient()
	{
		BigInteger one = new BigInteger("1");	
		BigInteger firstPart = p.subtract(one); //(p-1)
		BigInteger secondPart = q.subtract(one); //(q-1)
		BigInteger eulerTotient = firstPart.multiply(secondPart);
		return eulerTotient; //returns 𝜙(n)
	}

	/**
	 * A method that computes the max (a,b)
	 * @return max
	 */
	public BigInteger findMax()
	{
		BigInteger max = p.max(q); //max (p,q)
		return max;
	}



	/**
	 * We need to find a number e <= n such that gcd(e, 𝜙(n)) = 1 
	 * This guarantees that e will have an inverse. 
	 * Remember that e should be greater than max(p, q).
	 * @return e
	 */
	public BigInteger findNumberE()
	{
		BigInteger n = this.computeModulus();
		BigInteger eulerTotient = this.computeEulerTotient();
		BigInteger max = this.findMax();
		Random rnd = new Random();
		BigInteger e;
		do {
			int number = n.bitLength() - max.bitLength()+1;
			int i = rnd.nextInt() % number;
			int randomNum = max.bitLength() + i;
			e= new BigInteger(randomNum, rnd);
		}	
		while (!(e.gcd(eulerTotient).intValue() == 1 && e.compareTo(max) == 1 && e.compareTo(n) == -1));


		return e;

	}


	/**
	 * Computes the inverse d <= n such that e * d = 1 mod 𝜙(n).
	 * @param e
	 * @return d
	 */
	public BigInteger computeInverseD(BigInteger e)	
	{
		BigInteger eulerTotient = this.computeEulerTotient();
		BigInteger d = e.modInverse(eulerTotient);
		return d;
	}


}
