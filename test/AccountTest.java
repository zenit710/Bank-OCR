import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void getNumber() {
        String accountNumber = "123456789";
        Account account = new Account(accountNumber);

        assertEquals(accountNumber, account.getNumber());
    }

    @Test
    public void isValidValidAccountNumber() {
        String validNumber = "345882865";
        Account account = new Account(validNumber);

        assertTrue(account.isValidNumber());
    }

    @Test
    public void isValidInvalidAccountNumber() {
        String invalidNumber = "223456789";
        Account account = new Account(invalidNumber);

        assertFalse(account.isValidNumber());
    }

    @Test
    public void isIllegalLegalAccountNumber() {
        String validNumber = "345882865";
        Account account = new Account(validNumber);

        assertFalse(account.isIllegalNumber());
    }

    @Test
    public void isIllegalIllegalAccountNumber() {
        String invalidNumber = "2234?6789";
        Account account = new Account(invalidNumber);

        assertTrue(account.isIllegalNumber());
    }
}