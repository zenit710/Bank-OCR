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

        ArrayList<String> allCorrectAccounts = findAllPossibleAccountNumbersForIllegalAccount(account, indexesOfInvalidCharacters, invalidNumberString);
        account = validateAndSetStateOfAccount(account, allCorrectAccounts);

        return account;
    }

    private static ArrayList<String> findAllPossibleAccountNumbersForIllegalAccount(
            Account account, ArrayList<Integer> indexesOfInvalidCharacters, ArrayList<String> invalidNumberString) {

        ArrayList<String> allCorrectAccounts = new ArrayList<>();

        int allOptions = 1;
        int digitChangeIndexExp = 1;

        for (int i = 0; i < indexesOfInvalidCharacters.size(); i++) {
            int indexToReplace = indexesOfInvalidCharacters.get(i);
            ArrayList<Integer> allCorrectDigits = findCorrectNumbers(invalidNumberString.get(i));
            allOptions *= allCorrectDigits.size();

            if (i == 0) {
                for (int k = 0; k < allCorrectDigits.size(); k++) {
                    Account acc = new Account(account.getNumber());
                    acc.replaceCharAt(indexToReplace, allCorrectDigits.get(k));
                    allCorrectAccounts.add(acc.getNumber());
                }
            } else {
                if (allCorrectAccounts.size() < allOptions) {
                    int ind = 0;
                    while (allCorrectAccounts.size() < allOptions) {
                        String num = allCorrectAccounts.get(ind);
                        allCorrectAccounts.add(num);
                        ind++;
                    }
                }

                if (allCorrectDigits.size() == 1) digitChangeIndexExp = 0;
                int digitChangeIndex = (int)Math.pow(2, digitChangeIndexExp++);
                int digitIndex = 0;

                for (int numberIndex = 0; numberIndex < allCorrectAccounts.size(); numberIndex++) {
                    if (numberIndex % digitChangeIndex == 0) {
                        digitIndex++;

                        if (digitIndex >= allCorrectDigits.size()) {
                            digitIndex = 0;
                        }
                    }

                    Account acc = new Account(allCorrectAccounts.get(numberIndex));
                    acc.replaceCharAt(indexToReplace, allCorrectDigits.get(digitIndex));
                    allCorrectAccounts.set(numberIndex, acc.getNumber());
                }
            }
        }

        return allCorrectAccounts;
    }

    private static Account validateAndSetStateOfAccount(Account account, ArrayList<String> allCorrectAccounts) {
        ArrayList<String> validNumbers = new ArrayList<>();

        for (String number : allCorrectAccounts) {
            Account acc = new Account(number);

            if (acc.isValidNumber()) {
                validNumbers.add(acc.getNumber());
            }
        }

        if (validNumbers.size() == 1) {
            account = new Account(validNumbers.get(0));
            account.setState("OK");
        } else if (validNumbers.size() > 1) {
            account.setState("AMB");
        } else if (validNumbers.size() < 1) {
            account.setState("ILL");
        }

        return account;
    }

    private static ArrayList<Integer> findCorrectNumbers(String numberString) {
        ArrayList<Integer> correctNumbers = new ArrayList<>();

        for (int i = 0; i < numberString.length(); i++) {
            char[] numberChars = numberString.toCharArray();

            if (numberChars[i] == '|' || numberChars[i] == '_') {
                numberChars[i] = ' ';
            } else if (numberChars[i] == ' ') {
                if (i == 1 || i == 4 || i == 7) {
                    numberChars[i] = '_';
                } else if (i == 3 || i == 5 || i == 6 || i == 8) {
                    numberChars[i] = '|';
                }
            }

            if (numbersMap.containsKey(String.valueOf(numberChars))) {
                correctNumbers.add(numbersMap.get(String.valueOf(numberChars)));
            }
        }

        return correctNumbers;
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
