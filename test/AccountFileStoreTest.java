import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

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

}