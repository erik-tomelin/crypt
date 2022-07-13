import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class NewEncrypt {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("/home/erik/ExternalProjects/crypt/source.txt"));
		String sourceTxt = "";

		try {
			StringBuilder builder = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				builder.append(line);
				builder.append(System.lineSeparator());
				line = br.readLine();
			}

			sourceTxt = builder.toString();
		} finally {
			br.close();
		}

		String encSourceTxt = "";

		String encSourceTxtAll = "";

		int lengthNow = sourceTxt.length();

		System.out.println(sourceTxt);

		while (lengthNow > 1) {

			Random random = new Random();

			int generatedNumber = random.nextInt(lengthNow);

			encSourceTxt = sourceTxt.substring(0, generatedNumber);

			sourceTxt = sourceTxt.substring(generatedNumber);

			System.out.println(sourceTxt);

			lengthNow = sourceTxt.length();

			encSourceTxtAll += encSourceTxt;
		}
	}

	private int getRandomInt(int length) {
		Random random = new Random();

		return random.nextInt(length);
	}
}
