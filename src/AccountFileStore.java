import java.io.*;
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
            String entry = account.getNumber() + "\t\t" + account.getState();

            writer.println(entry);
        }

        writer.close();
    }
}