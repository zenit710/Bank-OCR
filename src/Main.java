import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            ArrayList<Account> accounts = AccountNumberParser.parseFile("entries/us4");

            AccountFileStore store = new AccountFileStore("results/us4");
            store.save(accounts);

            for (Account account: accounts) {
                System.out.println(account.getNumber());
            }
        } catch (IOException e) {
            System.out.println("Can't use file: " + e.getMessage());
        }
    }
}
