import java.io.*;
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
            System.out.println("entry -> " + entry);

            if (!account.isIllegalNumber() && !account.isValidNumber()) {
                entry += "\t\tERR";
            } else if (account.isIllegalNumber()) {
                System.out.println(account.allPossibleAccountNumbers.size());
                if (account.allPossibleAccountNumbers.size() == 1) {
                    entry = account.allPossibleAccountNumbers.get(0) + "\t\tOK";
                } else if (account.allPossibleAccountNumbers.size() > 1) {
                    entry += "\t\tAMB";
                } else if (account.allPossibleAccountNumbers.size() < 1) {
                    entry += "\t\tILL";
                } else {
                    entry += "\t\tILL";
                }
            }


            writer.println(entry);
        }

        writer.close();
    }
}