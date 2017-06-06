import java.math.BigInteger;
import java.util.Hashtable;

/**
 * A class for deciphering 
 * @author Harry
 *
 */
public class Decryption {
	private BigInteger [] cipherText;
	private BigInteger d;
	private BigInteger n;

	/**
	 * Empty constructor method
	 */
	public Decryption() {

	}


	/**
	 * 
	 * @param cipherText
	 * @param d
	 * @param e
	 * @param n
	 */
	public Decryption (BigInteger [] cipherText, BigInteger d, BigInteger e, BigInteger n)
	{
		this.cipherText = cipherText;
		this.d = d;
		this.n = n;


	}

	/**
	 *  M = C^d mod n = (M^e)^d mod n = M^e*d mod n.
	 * @return m
	 */
	public BigInteger [] decryptMessage()
	{
		BigInteger [] c = this.cipherText;
		BigInteger [] m = new BigInteger [c.length];
		for (int i = 0; i < c.length; i++)
		{
			m[i] = c[i].modPow(d, n);  
		}


		return m;

	}

	/**
	 * Converts the deciphered BigInteger array into an int []
	 * @param dMessage
	 * @return message
	 */
	public int [] decryptionToString(BigInteger [] dMessage) {
		int message [] = new  int [dMessage.length];
		for (int i = 0; i < dMessage.length; i++)
		{
			message[i] = dMessage[i].intValue();

		}
		return message;

	}

	/**
	 * Hashtable for translating from ascII characters to characters
	 * @return hash
	 */
	public Hashtable <Integer, Character> ascIICodings()
	{
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Hashtable <Integer, Character> hash = new Hashtable <Integer, Character>();
		for (int i = 0; i < 26; i++) //assigns alphabet letters to ascII characters
		{
			hash.put(i+65, alphabet.charAt(i));	
		}
		hash.put(32, ' '); //ascII Coding for space position
		return hash;
	}

	/**
	 * Translates the ascII message to plaintext
	 * @param letters
	 * @param message
	 * @return finalMessage
	 */
	public String ascIItoString (Hashtable <Integer, Character> letters, int [] message) {
		String decryption = "";
		for (int i = 0; i < message.length; i++)
		{
			String temp = Integer.toString(message[i]);
			decryption += letters.get(Integer.valueOf(temp.substring(0, 2)));
			decryption += letters.get(Integer.valueOf(temp.substring(2, 4)));
		}
		String finalMessage = "";

		for (int j = 0; j < decryption.length(); j++)
		{
			if (decryption.charAt(j) != ' ')
			{
				finalMessage += decryption.charAt(j);

			}


		}


		return finalMessage;
	}

	/**
	 * Caller method for the decryption toString()
	 * @return 
	 */

	public String finalDToString() {

		return ascIItoString(ascIICodings(), decryptionToString(decryptMessage()));

	}




}





