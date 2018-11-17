import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AccountNumberParserTest {

    @Test
    public void parseExistingFile() {
        try {
            ArrayList<Account> expectedAccounts = new ArrayList<>();
            expectedAccounts.add(new Account("0"));
            expectedAccounts.add(new Account("1"));
            expectedAccounts.add(new Account("2"));
            expectedAccounts.add(new Account("3"));
            expectedAccounts.add(new Account("4"));
            expectedAccounts.add(new Account("5"));
            expectedAccounts.add(new Account("6"));
            expectedAccounts.add(new Account("7"));
            expectedAccounts.add(new Account("8"));
            expectedAccounts.add(new Account("9"));

            ArrayList<Account> accounts = AccountNumberParser.parseFile("test/entries/numbers");
            assertEquals(expectedAccounts.size(), accounts.size());

            for (int i = 0; i < expectedAccounts.size(); i++) {
                assertEquals(expectedAccounts.get(i).getNumber(), accounts.get(i).getNumber());
            }
        } catch (IOException exception) {}
    }

    @Test
    public void parseNonExistingFile() {
        boolean exceptionThrew = false;

        try {
            ArrayList<Account> accounts = AccountNumberParser.parseFile("test/entries/not_existing");
        } catch (IOException e) {
            exceptionThrew = true;
        }

        assertTrue(exceptionThrew);
    }

    @Test
    public void createAccountFromInvalidEntry() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String[] invalidEntry = {" _ | ||_|", "|||||||||"};
        Object[] args = {invalidEntry};

        Method m = AccountNumberParser.class.getDeclaredMethod("createAccountFromEntry", String[].class);
        m.setAccessible(true);
        Object o = m.invoke(null, args);

        assertEquals("0", ((Account) o).getNumber());
    }

    @Test
    public void createAccountFromValidEntry() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String[] invalidEntry = {" _ | ||_|", "     |  |"}; // 01
        Object[] args = {invalidEntry};

        Method m = AccountNumberParser.class.getDeclaredMethod("createAccountFromEntry", String[].class);
        m.setAccessible(true);
        Object o = m.invoke(null, args);

        assertEquals("01", ((Account) o).getNumber());
    }

    @Test
    public void initEntry() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method m = AccountNumberParser.class.getDeclaredMethod("initEntry");
        m.setAccessible(true);
        Object o = m.invoke(null);

        String[] entries = (String[]) o;

        assertEquals(9, entries.length);

        for (int i = 0; i < entries.length; i++) {
            assertEquals("", entries[i]);
        }
    }
}