import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AccountFileStore {
    private String filePath;

    public AccountFileStore(String filePath) {
        this.filePath = filePath;
    }

    public boolean isFileEmpty() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        return br.readLine() == null;
    }

    public void save(List<Account> accounts) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        PrintWriter writer = new PrintWriter(bufferedWriter);

        for (Account account: accounts) {
            String entry = account.getNumber();

            if (!account.isIllegalNumber() && !account.isValidNumber()) {
                AccountIllegalSymbolValidator validator = new AccountIllegalSymbolValidator();
                AccountNumberParser parser = new AccountNumberParser();

                for (int i = 0; i < account.numberDigitsAsStringSymbols.size(); i++) {
                    Account newAccount = account;
                    newAccount.indexesOfInvalidCharacters.clear();
                    newAccount.indexesOfInvalidCharacters.add(i);

                    ArrayList<String> invalidNumberString = new ArrayList<>();
                    invalidNumberString.add(account.numberDigitsAsStringSymbols.get(i));

                    newAccount = validator.findAllPossibleAccountNumbersForIllegalAccount(newAccount, invalidNumberString, parser.numbersMap);

                    account.allValidAccountNumbers = newAccount.allValidAccountNumbers;
                }

                if (account.allValidAccountNumbers.size() == 1) {
                    entry = account.allValidAccountNumbers.get(0) + "\t\tOK";
                } else if (account.allValidAccountNumbers.size() > 1) {
                    entry += "\t\tAMB";
                } else {
                    entry += "\t\tERR";
                }

            } else if (account.isIllegalNumber()) {
                if (account.allValidAccountNumbers.size() == 1) {
                    entry = account.allValidAccountNumbers.get(0) + "\t\tOK";
                } else if (account.allValidAccountNumbers.size() > 1) {
                    entry += "\t\tAMB";
                } else {
                    entry += "\t\tILL";
                }
            }


            writer.println(entry);
        }

        writer.close();
    }
}