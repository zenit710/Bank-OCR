import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AccountFileStoreTest {

    @Test
    public void isNonEmptyFileEmpty() throws IOException {
        AccountFileStore store = new AccountFileStore("test/entries/numbers");

        assertFalse(store.isFileEmpty());
    }

    @Test
    public void isEmptyFileEmpty() throws IOException {
        AccountFileStore store = new AccountFileStore("test/entries/empty");

        assertTrue(store.isFileEmpty());
    }

    @Test
    public void saveInExistingDirectory() throws IOException {
        AccountFileStore store = new AccountFileStore("test/results/numbers");
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account("123456789"));

        store.save(accounts);

        assertFalse(store.isFileEmpty());
    }

    @Test
    public void saveInNonExistingDirectory() {
        boolean exceptionThrew = false;

        try {
            AccountFileStore store = new AccountFileStore("test/res/numbers");
            ArrayList<Account> accounts = new ArrayList<>();
            accounts.add(new Account("123456789"));

            store.save(accounts);
        } catch (IOException exception) {
            exceptionThrew = true;
        }

        assertTrue(exceptionThrew);
    }

    @Test
    public void saveIllegalAccount() throws IOException {
        String resultPath = "test/results/numbers";
        AccountFileStore store = new AccountFileStore(resultPath);
        ArrayList<Account> accounts = new ArrayList<>();
        Account account = new Account("?");
        accounts.add(account);

        store.save(accounts);

        Scanner scanner = new Scanner(new File(resultPath));
        assertTrue(scanner.nextLine().contains("\tILL"));
    }

    @Test
    public void saveInvalidAccount() throws IOException {
        String resultPath = "test/results/numbers";
        AccountFileStore store = new AccountFileStore(resultPath);
        ArrayList<Account> accounts = new ArrayList<>();
        Account account = new Account("444444444");
        accounts.add(account);

        store.save(accounts);

        Scanner scanner = new Scanner(new File(resultPath));
        assertTrue(scanner.nextLine().contains("\tERR"));
    }

    @Test
    public void saveValidAccount() throws IOException {
        String resultPath = "test/results/numbers";
        AccountFileStore store = new AccountFileStore(resultPath);
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account("000000051"));

        store.save(accounts);

        Scanner scanner = new Scanner(new File(resultPath));
        String line = scanner.nextLine();

        assertFalse(line.contains("\tILL"));
        assertFalse(line.contains("\tERR"));
    }

}