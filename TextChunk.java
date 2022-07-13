import java.math.BigInteger;

public class TextChunk {
    String stringVal;

    public TextChunk(String s) {
        stringVal = s;
    }

    public String toString() {
        return stringVal;
    }

    public TextChunk(BigInteger n) {
        BigInteger big256 = new BigInteger("256");
        BigInteger big0 = new BigInteger("0");

        if (n.compareTo(big0) == 0) {
            stringVal = "0";
        } else {
            stringVal = "";
            while (n.compareTo(big0) > 0) {
                BigInteger[] ans = n.divideAndRemainder(big256);
                int charNum = ans[1].intValue();
                stringVal = stringVal + (char) charNum;
                n = ans[0];
            }
        }
    }

    public BigInteger bigIntValue() {
        BigInteger big256 = new BigInteger("256");
        BigInteger result = new BigInteger("0");

        for (int i = stringVal.length() - 1; i >= 0; i--) {
            result = result.multiply(big256);
            result = result.add(BigInteger.valueOf((int) stringVal.charAt(i)));
        }
        return result;
    }

    public static int blockSize(BigInteger N) {
        BigInteger big1 = new BigInteger("1");
        BigInteger big2 = new BigInteger("2");

        int blockSize = 0;
        BigInteger temp = N.subtract(big1);
        while (temp.compareTo(big1) > 0) {
            temp = temp.divide(big2);
            blockSize++;
        }
        return blockSize / 8;
    }

    public static void main(String[] args) {
        String test = "asdfasdf232435@#%@";

        TextChunk chunk1 = new TextChunk(test);
        BigInteger n = chunk1.bigIntValue();

        System.out.println("biginteger value of " + test + " = " + n);
        System.out.println("blocksize for that is " + blockSize(n));

        TextChunk chunk2 = new TextChunk(n);
        String s = chunk2.toString();

        System.out.println("converted back to string = " + s);
        if (s.compareTo(test) == 0) {
            System.out.println("success");
        } else {
            System.out.println("FAIL");
        }
    }
}
