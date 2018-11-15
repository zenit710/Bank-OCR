import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void getNumber() {
        String accountNumber = "123456789";
        Account account = new Account(accountNumber);

        assertEquals(accountNumber, account.getNumber());
    }
}