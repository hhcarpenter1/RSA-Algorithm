import java.util.Scanner;
import java.math.BigInteger;


/**
 * The class in charge of inputing and user interaction
 * @author Harry
 *
 */
public class User {
	private static Scanner scan = new Scanner(System.in); 
	//Got prime numbers from https://primes.utm.edu/lists/small/small2.html
	private BigInteger p = new BigInteger("35201546659608842026088328007565866231962578784643756647773109869245232364730066609837018108561065242031153677"); 
	private BigInteger q = new BigInteger("10513733234846849736873637829838635104309714688896631127438692162131857778044158273164093838937083421380041997");
	private BigInteger e;
	private BigInteger n;
	private BigInteger d;
	private KeyGeneration key;




	public User() {

	}

	public void run() {
		User use = new User();
		BigInteger [] cipherText = use.encipher();
		use.decipher(cipherText);
	}




	/**
	 * A class for enciphering messages
	 * @throws IOException
	 */
	public BigInteger [] encipher() 
	{
		key= new KeyGeneration(p, q);
		Encryption myEncryption = new Encryption();
		e = key.findNumberE();
		n = key.computeModulus();
		d = key.computeInverseD(e);
		System.out.println("Please enter the message that you want to encrypt.");
		String message = scan.nextLine();
		myEncryption = new Encryption(n, e, message);
		BigInteger [] cipherText = myEncryption.runEncryption();
		String encipheredMessage = myEncryption.encryptionToString();
		System.out.println("Your encrypted message:");
		System.out.println(encipheredMessage);
		return cipherText;
	}


	/**
	 * A class for deciphering messages 
	 * @throws IOException
	 */
	public void decipher(BigInteger [] cipherText) 
	{
		Decryption myDecryption = new Decryption();
		System.out.println("Decryption:");
		myDecryption = new Decryption(cipherText, d, e, n);
		String decipheredMessage = myDecryption.finalDToString();
		System.out.println(decipheredMessage);

	}




}