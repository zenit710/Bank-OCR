import java.util.ArrayList;
import java.util.HashMap;

public class AccountIllegalSymbolValidator {

    public static Account findAllPossibleAccountNumbersForIllegalAccount(
            Account account, ArrayList<String> invalidNumberString, HashMap<String, Integer> numbersMap) {

        ArrayList<String> allPossibleAccountNumbers = new ArrayList<>();

        int allOptions = 1;
        int digitChangeIndexExp = 1;

        for (int i = 0; i < account.indexesOfInvalidCharacters.size(); i++) {
            int indexToReplace = account.indexesOfInvalidCharacters.get(i);
            ArrayList<Integer> allCorrectDigits = findCorrectNumbers(invalidNumberString.get(i), numbersMap);
            allOptions *= allCorrectDigits.size();

            if (i == 0) {
                for (int k = 0; k < allCorrectDigits.size(); k++) {
                    Account acc = new Account(account.getNumber());
                    acc.replaceCharAt(indexToReplace, allCorrectDigits.get(k));
                    allPossibleAccountNumbers.add(acc.getNumber());
                }
            } else {
                if (allPossibleAccountNumbers.size() < allOptions) {
                    int ind = 0;
                    while (allPossibleAccountNumbers.size() < allOptions) {
                        String num = allPossibleAccountNumbers.get(ind);
                        allPossibleAccountNumbers.add(num);
                        ind++;
                    }
                }

                if (allCorrectDigits.size() == 1) digitChangeIndexExp = 0;
                int digitChangeIndex = (int)Math.pow(2, digitChangeIndexExp++);
                int digitIndex = 0;

                for (int numberIndex = 0; numberIndex < allPossibleAccountNumbers.size(); numberIndex++) {
                    if (numberIndex % digitChangeIndex == 0) {
                        digitIndex++;

                        if (digitIndex >= allCorrectDigits.size()) {
                            digitIndex = 0;
                        }
                    }

                    Account acc = new Account(allPossibleAccountNumbers.get(numberIndex));
                    acc.replaceCharAt(indexToReplace, allCorrectDigits.get(digitIndex));
                    allPossibleAccountNumbers.set(numberIndex, acc.getNumber());
                }
            }
        }

        account.allPossibleAccountNumbers = allPossibleAccountNumbers;
        return account;
    }

    private static ArrayList<Integer> findCorrectNumbers(String numberString, HashMap<String, Integer> numbersMap) {
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
}
