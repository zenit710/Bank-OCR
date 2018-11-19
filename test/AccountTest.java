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
        String legalNumber = "345882865";
        Account account = new Account(legalNumber);

        assertFalse(account.isIllegalNumber());
    }

    @Test
    public void isIllegalIllegalAccountNumber() {
        String illegalNumber = "2234?6789";
        Account account = new Account(illegalNumber);

        assertTrue(account.isIllegalNumber());
    }

    @Test
    public void isValidIllegalAccountNumber() {
        String illegalNumber = "2234?6789";
        Account account = new Account(illegalNumber);

        assertTrue(account.isIllegalNumber());
        assertFalse(account.isValidNumber());
    }
}