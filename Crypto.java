import java.util.*;

public class Crypto {
    
    public static Scanner input = new Scanner(System.in);
   // public static String text = ("This is some \"really\" great. (Text)!?");
    public static int shift;
    public static int number;
    
    public static void main (String [] args ){
        String text = ("This is some \"really\" great. (Text)!?");
        text = normalizeText(text);
        System.out.println(text);
        System.out.println("Enter shift position: ");
        shift = input.nextInt();
        String textC= caesarify(text,shift);
        System.out.println("String after caesarify: ");
        System.out.println(textC);
        System.out.println("Enter number per group: ");
        number = input.nextInt();
        String textG = groupify(textC,number);
        System.out.println("String after groupify: ");
        System.out.println(textG);

        System.out.println("String after encrypt: ");
        String cyphertext = encryptString("Who will in the election?",shift,number);
        System.out.println(cyphertext);

        ungroupify(cyphertext);
        System.out.println(ungroupify(cyphertext));

        String plaintext = decryptString(cyphertext,shift);
        System.out.println(plaintext);

    }
    public static String normalizeText(String result){
        result = result.replaceAll(" ","");
        result = result.replaceAll("[.,:;'\"!?()]","");
        result = result.toUpperCase();
        return result;
    }
    public static String caesarify(String enterText, int shift){
        String result = "";
        char currChar;
        for (int i = 0; i<enterText.length();i++){
            currChar = enterText.charAt(i);
            if ((int)(currChar+shift)>=(int)'A' &&(int) (currChar+shift)<=(int)'Z'){
                currChar = (char)(shift+(int)currChar);
            } else if ((int)(currChar+shift) > (int)'Z') {
                currChar = (char)((int)currChar-26+shift);
            }
            result = result + currChar;
        }
        return result;
    }

    public static String groupify(String enterText, int number) {
        String result = "";
        int k= enterText.length()/ number;
        int q= enterText.length()-k* number;
        int startindex=0;
        int lastindex = number;
        while (lastindex<=enterText.length()){
            String sub = enterText.substring(startindex,lastindex);
            startindex = startindex + number;
            lastindex  = lastindex + number;
            result = result + sub + " ";
        }
        result = result + enterText.substring(number *k);
        String X = "";
        for (int i = 0; i< number -q; i++){
            X = X + "x";
        }
        result = result + X;
        return result;
    }

    public static String encryptString(String text,int shift, int number){
        String result;
        result = normalizeText(text);
        result = caesarify(result,shift);
        result = groupify(result,number);
        return result;
    }

    public static String ungroupify(String text){
        text = text.replaceAll(" ","");
        text = text.replaceAll("x","");
        return text;
    }

    public static String unceasarify(String text, int shift){
            String result = "";
            char currChar;
            for (int i = 0; i<text.length();i++){
                currChar = text.charAt(i);
                if ((int)(currChar-shift)>=(int)'A' &&(int) (currChar-shift)<=(int)'Z'){
                    currChar = (char)((int)currChar-shift);
                } else if ((int)(currChar-shift) < (int)'A') {
                    currChar = (char)((int)currChar+26-shift);
                }
                result = result + currChar;
            }
            return result;
    }

    public static String decryptString (String text, int shift){
        String result1;
         result1 = ungroupify(text);
         result1=unceasarify(result1,shift);
         return  result1;
    }
}