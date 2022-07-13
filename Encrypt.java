import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class Encrypt extends GetKeys {

	public static void main() throws IOException {
		BigInteger P = new BigInteger(String.valueOf(1521289103));
		BigInteger Q = new BigInteger(String.valueOf(1521289589));

		// Módulo
		BigInteger N = P.multiply(Q);

		// Chave Publica
		BigInteger E = new BigInteger(String.valueOf(1521289639));

		// Chave Privada
		BigInteger D = geratePublicAndPrivateKey(P, Q, E);

		System.out.println("\nPublic   : " + E);
		System.out.println("Modulo   : " + N);

		System.out.println("\nPrivate  : " + D);
		System.out.println("Modulo   : " + N);

		StringBuilder externalizePublic = new StringBuilder();
		externalizePublic.append(String.valueOf(E));
		externalizePublic.append("\n");
		externalizePublic.append(String.valueOf(N));

		StringBuilder externalizePrivate = new StringBuilder();
		externalizePrivate.append(String.valueOf(D));
		externalizePrivate.append("\n");
		externalizePrivate.append(String.valueOf(N));

		Path pathPrivate = Paths.get("/home/erik/ExternalProjects/crypt/private.txt");
		Path pathPublic = Paths.get("/home/erik/ExternalProjects/crypt/public.txt");

		try {
			Files.writeString(pathPublic, externalizePublic.toString(), StandardCharsets.UTF_8);
			Files.writeString(pathPrivate, externalizePrivate.toString(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Read key_file_name (public key)
		// Read source_file_name (plain)
		// Read dest_file_name (arrayCrypted)
		//

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

		System.out.println("\n" + sourceTxt);

		BigInteger chunkSize = new TextChunk(sourceTxt).bigIntValue();
		String encodedChunk = Base64.getEncoder().encodeToString(chunkSize.toString().getBytes());

		System.out.println("\nbiginteger value of \n" + chunkSize);
		
		// for chunk in codedText.split(by chunk_size) do
		// originalChunk = convertToBigInt(chunk)
		// encodedChunk = originalChunk.modPow(key, modulus)
		// save(encodedChunk, dest_file_name)
		// done

		StringBuilder sb = new StringBuilder(); 
		String line;
		while ((line = encodedChunk) != null) {
			sb.append(line);
		
			// if enough content is read, extract the chunk
			while (sb.length() >= chunkSize.intValue()) {
		
				String c = sb.substring(0, chunkSize.intValue());
				// do something with the string
		
				// add the remaining content to the next chunk
				sb = new StringBuilder(sb.substring(chunkSize.intValue()));
			}
		}
		// thats the last chunk
		String c = sb.toString();

		System.out.println(c);
	}
}