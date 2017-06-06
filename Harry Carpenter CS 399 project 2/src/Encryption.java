import java.math.BigInteger;
import java.util.Hashtable;

/**
 * A class for enciphering messages
 * @author Harry
 *
 */
public class Encryption {
	private BigInteger n;
	private BigInteger e;
	private String message;


	/**
	 * Empty constructor method
	 */
	public Encryption() {

	}

	/**
	 * 
	 * @param n
	 * @param e
	 * @param message
	 */
	public  Encryption (BigInteger n, BigInteger e, String message) 
	{
		this.n = n;
		this.e = e; 
		this.message = message;
	}


	/**
	 * Encryption is C = M^e mod n, where M is the message
	 * @return c
	 */
	public BigInteger [] runEncryption() //Returns C 
	{
		BigInteger [] m = this.getM();
		BigInteger [] c = new BigInteger [m.length];
		String temp = "";
		BigInteger store;
		for (int i = 0; i < m.length; i++)
		{
			temp += m [i];
			store = new BigInteger(temp);

			c[i] = store.modPow(e, n);  
			temp = "";
		}
		return c;
	}

	/**
	 * prints encryption array to string
	 * @return encryption
	 */
	public String encryptionToString()
	{
		BigInteger [] encrypt = this.runEncryption();
		String encryption = "";
		for (int i = 0; i < encrypt.length; i++)
		{
			encryption += encrypt[i];
		}

		return encryption;

	}

	/**
	 * ASCII codings for each character in the message
	 * @return m
	 */
	public BigInteger [] getM() {
		BigInteger [] m = this.putMessageInASCII(this.ascIICodings());
		return m;
	}

	/**
	 * A Method for setting characters in the alphabet to ascII values by using a Hashtable
	 * @return Hashtable
	 */
	public Hashtable <Character, Integer> ascIICodings()
	{
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Hashtable <Character, Integer> hash = new Hashtable <Character, Integer>();
		for (int i = 0; i < 26; i++) //assigns alphabet letters to ascII characters
		{
			hash.put(alphabet.charAt(i), i+65);		
		}
		hash.put(' ', 32); //ascII Coding for space position
		return hash;
	}


	/**
	 * A method that translates the plaintext message into ascII values
	 * @param letters
	 * @return m
	 */
	public BigInteger [] putMessageInASCII(Hashtable <Character, Integer> letters) {
		BigInteger m2;
		String newMessage = "";
		String message = this.putMessageInPairs();
		BigInteger [] m = new BigInteger [message.length()/2];
		int count = 0;
		for (int i = 0; i < message.length()-1; i+=2) //Translates the paired message to ascII characters
		{
			newMessage += letters.get(message.charAt(i));
			newMessage += letters.get(message.charAt(i+1));
			m2 = new BigInteger(newMessage);
			m [count] = m2;
			newMessage = "";
			count++;
		}


		return m;
	}






	/**
	 * Divides the message into pairs of two
	 * @return finalMessage
	 */
	public String putMessageInPairs() {
		String newMessage = "";
		message = message.toUpperCase().replaceAll("[[(){},.;!?<>%]]", ""); //Capitalizes message and gets rid of punctuation
		for (int i = 0; i < message.length(); i++)	
		{

			newMessage += message.charAt(i);
		}
		String finalMessage = "";
		for (int j = 0; j < newMessage.length(); j++)
		{
			if (j % 2 == 0 && j != 0)
			{
				finalMessage += ' ';
				finalMessage += newMessage.charAt(j);
			}
			else 
			{
				finalMessage += newMessage.charAt(j);
			}


		}

		return finalMessage;
	}



}
