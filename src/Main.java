import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            ArrayList<Account> accounts = AccountNumberParser.parseFile("entries/test");

            AccountFileStore store = new AccountFileStore("results/test");
            store.save(accounts);

            System.out.println();
            System.out.println();
            for (Account account: accounts) {
                System.out.println("Main: " + account.getNumber());
            }
        } catch (IOException e) {
            System.out.println("Can't use file: " + e.getMessage());
        }
    }
}
