import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AccountNumberParser {
    private final static String NUMBER_0 = " _ | ||_|";
    private final static String NUMBER_1 = "     |  |";
    private final static String NUMBER_2 = " _  _||_ ";
    private final static String NUMBER_3 = " _  _| _|";
    private final static String NUMBER_4 = "   |_|  |";
    private final static String NUMBER_5 = " _ |_  _|";
    private final static String NUMBER_6 = " _ |_ |_|";
    private final static String NUMBER_7 = " _   |  |";
    private final static String NUMBER_8 = " _ |_||_|";
    private final static String NUMBER_9 = " _ |_| _|";
    private final static int numberSize = 3;
    private final static int numberLines = 4;
    private final static int accountNumberLength = 9;

    private static HashMap<String, Integer> numbersMap = createNumbersMap();

    public static ArrayList<Account> parseFile(String filePath) throws IOException {
        ArrayList<Account> accounts = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filePath));
        String[] entry = initEntry();
        int currentLine = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (currentLine % numberLines == 3) { ;
                accounts.add(createAccountFromEntry(entry));
                entry = initEntry();
            } else {
                int parts = line.length() / numberSize;

                for (int i = 0; i < parts; i++) {
                    int start = numberSize * i;
                    entry[i] += line.substring(start, Math.min(line.length(), start + numberSize));
                }
            }

            currentLine++;
        }

        scanner.close();

        return accounts;
    }

    private static Account createAccountFromEntry(String[] entry) {
        String numberString = "";
        ArrayList<Integer> indexesOfInvalidCharacters = new ArrayList<>();
        ArrayList<String> invalidNumberString = new ArrayList<>();
        int index = 0;

        for (String number : entry) {
            if (numbersMap.containsKey(number)) {
                numberString += numbersMap.get(number);
            } else {
                indexesOfInvalidCharacters.add(index);
                invalidNumberString.add(number);
                numberString += Account.INVALID_CHARACTER_MARK;
            }

            index++;
        }

        Account account = new Account(numberString);
        account.indexesOfInvalidCharacters = indexesOfInvalidCharacters;

        AccountIllegalSymbolValidator validator = new AccountIllegalSymbolValidator();
        account = validator.findAllPossibleAccountNumbersForIllegalAccount(account, invalidNumberString, numbersMap);

        return account;
    }


    private static String[] initEntry() {
        String[] entry = new String[accountNumberLength];

        for (int i = 0; i < accountNumberLength; i++) entry[i] = "";

        return entry;
    }

    private static HashMap<String, Integer> createNumbersMap() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put(NUMBER_0, 0);
        map.put(NUMBER_1, 1);
        map.put(NUMBER_2, 2);
        map.put(NUMBER_3, 3);
        map.put(NUMBER_4, 4);
        map.put(NUMBER_5, 5);
        map.put(NUMBER_6, 6);
        map.put(NUMBER_7, 7);
        map.put(NUMBER_8, 8);
        map.put(NUMBER_9, 9);

        return map;
    }
}
