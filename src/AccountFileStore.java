import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AccountFileStore {
    private String filePath;

    public AccountFileStore(String filePath) {
        this.filePath = filePath;
    }

    public void save(List<Account> accounts) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
        PrintWriter writer = new PrintWriter(bufferedWriter);

        for (Account account: accounts) {
            String entry = account.getNumber();



            if (account.isIllegalNumber()) {
                entry += "\tILL";
            } else if (!account.isValidNumber()) {
                entry += "\tERR";
            }

            writer.println(entry);
        }

        writer.close();
    }
}