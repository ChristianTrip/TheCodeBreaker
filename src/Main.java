import java.util.*;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static final char[] myCustomAlphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
                                            'h', 'i', 'j', 'k', 'l', 'm', 'n',
                                            'o', 'p', 'q', 'r', 's', 't', 'u',
                                            'v', 'w', 'x', 'y', 'z', 'æ', 'ø',
                                            'å', ' ', ',', '.', ':', '!', '?',
                                            '\"', '\'', '(', ')', '/', '´', '`',
                                            '&', '@', '%', '#'};

// Menu methods----------------------------------------------------------------------------------------
    static void mainMenu(){
        boolean run = true;
        while (run) {

            System.out.println("==========================");
            System.out.println("    M A I N    M E N U    ");
            System.out.println("==========================");
            System.out.println();
            System.out.println("Welcome to the program");
            System.out.println("What would you like to do?");
            System.out.println();
            System.out.println("  1. Number Cipher Encoder");
            System.out.println("  2. Caesar Cipher Encoder");
            System.out.println("  3. Exit                 ");
            System.out.println();

            int choice = validateUserIntInput();
            switch (choice) {
                case 1:
                    numberCipherMenu();
                    break;
                case 2:
                    caesarMenu();
                    break;
                case 3:
                    run = false;
                    scan.close();
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println( "That number is not on the list\n" +
                                        "Try again:");
            }
        }
    }

    static void numberCipherMenu(){
        System.out.println("==========================");
        System.out.println("N U M B E R    C I P H E R");
        System.out.println("==========================");
        System.out.println();
        System.out.println( "The number cipher encodes a \n" +
                            "message by replacing each \n" +
                            "letter with a number that \n" +
                            "referes to the index of the \n" +
                            "letter in the alphabet it \n" +
                            "replaces.");
        System.out.println();

        boolean run = true;
        while (run) {

            System.out.println("What would you like to do?");
            System.out.println();
            System.out.println("  1. Encode               ");
            System.out.println("  2. Decode               ");
            System.out.println("  3. Back to main         ");
            System.out.println();


            int choice = validateUserIntInput();
            switch (choice) {
                case 1:
                    encodeTextSetup();
                    break;
                case 2:
                    decodeNumberSetup();
                    break;
                case 3:
                    run = false;
                    break;
                default:
                    System.out.println( "That number is not on the list\n" +
                                        "Try again:");
            }
        }
    }

    static void caesarMenu(){
        System.out.println("==========================");
        System.out.println("C A E S A R    C I P H E R");
        System.out.println("==========================");
        System.out.println();
        System.out.println( "The Caesar cipher encodes a \n" +
                "message by replacing each \n" +
                "letter with a letter some \n" +
                "fixed number of positions \n" +
                "down or up the alphabet");

        boolean run = true;
        while (run) {

            System.out.println();
            System.out.println("What would you like to do?");
            System.out.println();
            System.out.println("  1. Encode               ");
            System.out.println("  2. Decode              ");
            System.out.println("  3. Back to main         ");
            System.out.println();


            int choice = validateUserIntInput();
            switch (choice) {
                case 1:
                    System.out.println("Type in the number you want to shift the encoded numbers:");
                    int shiftEncode = validateUserIntInput();
                    String textToEncode = textToEncodeSetup();
                    String encoded = caesarCipherEncoder(textToEncode, shiftEncode);
                    System.out.println("Here is your text shifted by: " + shiftEncode);
                    System.out.println(encoded);
                    break;
                case 2:
                    System.out.println("Type in the number your letters have been shifted:");
                    int shiftDecode = validateUserIntInput();
                    String textToDecode = textToDecodeSetup();
                    String decoded = caesarCipherDecoder(textToDecode, shiftDecode);
                    System.out.println("Here is your encoded text shifted back by: " + shiftDecode);
                    System.out.println(decoded);
                    break;
                case 3:
                    run = false;
                    break;
                default:
                    System.out.println( "That number is not on the list\n" +
                            "Try again:");
            }
        }
    }

    public static int validateUserIntInput(){
        int returnNum = 0;
        boolean run = true;
        while (run) {
            try {
                returnNum = scan.nextInt();
                run = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! try a number");
                scan.next();
            }
        }
        return returnNum;
    }


// Number Cipher methods--------------------------------------------------------------------------------

    static void encodeTextSetup(){
        boolean run = true;
        while (run) {
            System.out.println("Type in the number you want to shift the encoded numbers:");
            int shift = validateUserIntInput();
            String text = textToEncodeSetup();
            boolean isValidText = validedTextToEncode(text);
            if (isValidText) {
                ArrayList<Integer> encodedNumberText = numberCipherEncoder(text, shift);
                System.out.println("Here is your text encoded into a list of numbers:");
                System.out.println(encodedNumberText);
                System.out.println();
                run = false;
                break;
            } else {
                System.out.println("Your text is invalid.\n" +
                        "You can only use these letters:");
                System.out.println(Arrays.toString(myCustomAlphabet));
                System.out.println("Try again!");
            }
        }
    }

    static void decodeNumberSetup(){
        boolean run = true;
        while (run) {
            System.out.println("Type in the number your numbers have been shifted with:");
            int shift = validateUserIntInput();
            ArrayList<Integer> arrayOfNumbersToDecode = createIntArrayToDecode();

            System.out.println("Here is your number list decoded into a string of letters:");
            System.out.println(numberCipherDecoder(arrayOfNumbersToDecode, shift));
            System.out.println();
            run = false;
            break;

        }
    }

    static ArrayList<Integer> numberCipherEncoder(String toEncode, int shift){

        ArrayList<Integer> encodedCharsToInts = new ArrayList<>();
        char[] stringToChars = toEncode.toLowerCase().toCharArray();

        for (int i = 0; i < toEncode.length(); i++) {
            for (int j = 0; j < myCustomAlphabet.length; j++) {
                if (stringToChars[i] == myCustomAlphabet[j]){
                    encodedCharsToInts.add(j + shift);
                }
            }
        }
        return encodedCharsToInts;
    }

    static String numberCipherDecoder(ArrayList<Integer> toDecode, int shift){
        String decoded = new String();
        for (int i = 0; i < toDecode.size(); i++) {
            for (int j = 0; j < myCustomAlphabet.length; j++) {
                if (toDecode.get(i) > myCustomAlphabet.length){
                    decoded += myCustomAlphabet[toDecode.get(i) - shift];
                    break;
                }else if (toDecode.get(i).equals(j)){
                    decoded += myCustomAlphabet[j - shift];
                }
            }
        }
        return decoded;
    }

    static ArrayList<Integer> createIntArrayToDecode(){
        ArrayList<Integer> toDecode = new ArrayList<Integer>();

        System.out.println("Insert numbers to decode:" +
                "\nPress anything else then a number to end your encoded number.");

        while (scan.hasNextInt()){
            toDecode.add(scan.nextInt());
        }

        return toDecode;
    }


// Caesar Cipher methods--------------------------------------------------------------------------------

    static String caesarCipherEncoder(String textToEncode, int shift){

    int shiftOutOfBounds = shift;
    String encodedText = "";

    for (int i = 0; i < textToEncode.length(); i++) {
        for (int j = 0; j < myCustomAlphabet.length; j++) {
            if (textToEncode.charAt(i) == myCustomAlphabet[j] && (j + shift) > myCustomAlphabet.length - 1){
                shiftOutOfBounds -= (myCustomAlphabet.length - j);
                while (shiftOutOfBounds >= myCustomAlphabet.length - 1){
                    shiftOutOfBounds -= (myCustomAlphabet.length - 1);
                }
                encodedText += myCustomAlphabet[shiftOutOfBounds];
                shiftOutOfBounds = shift;
            } else if (textToEncode.charAt(i) == myCustomAlphabet[j]){
                encodedText += myCustomAlphabet[j + shift];
            }
        }
    }
    return encodedText;
}

    static String caesarCipherDecoder(String textToDecode, int shift){

        int shiftOutOfBounds = shift;
        String decodedText = "";

        for (int i = 0; i < textToDecode.length(); i++) {
            for (int j = 0; j < myCustomAlphabet.length; j++) {
                char a = textToDecode.charAt(i);
                char b = myCustomAlphabet[j];
                int c = j - shift;
                if (textToDecode.charAt(i) == myCustomAlphabet[j] && (j - shift) < 0){

                    shiftOutOfBounds -=  j;
                    while (shiftOutOfBounds >= myCustomAlphabet.length - 1){
                        shiftOutOfBounds -= (myCustomAlphabet.length );
                    }
                    decodedText += myCustomAlphabet[myCustomAlphabet.length - shiftOutOfBounds];
                    shiftOutOfBounds = shift;

                }else if (textToDecode.charAt(i) == myCustomAlphabet[j]){
                    decodedText += myCustomAlphabet[j - shift];

                }
            }
        }
        return decodedText;
    }


// Shared Cipher methods--------------------------------------------------------------------------------

    static String textToEncodeSetup(){
        System.out.println("Write a message you want to be encoded.");
        System.out.println("When you are done hit enter");
        System.out.println();
        String textToReturn = scan.next();
        textToReturn += scan.nextLine();

        return textToReturn;
    }

    static String textToDecodeSetup(){
        System.out.println("Write a message you want to be decoded.");
        System.out.println("When you are done hit enter");
        System.out.println();
        String textToReturn = scan.next();
        textToReturn += scan.nextLine();

        return textToReturn;
    }

    static boolean validedTextToEncode(String textToValidate){

        textToValidate = textToValidate.toLowerCase();


        int count = 0;

        for (int i = 0; i < textToValidate.length(); i++) {
            for (int j = 0; j < myCustomAlphabet.length; j++) {
                if (textToValidate.charAt(i) == myCustomAlphabet[j]){
                    count ++;
                    break;
                }
            }
        }
        if (count == textToValidate.length()){
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {

        mainMenu();

    }
}
