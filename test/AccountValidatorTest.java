import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountValidatorTest {

    @Test
    public void validateValidAccountNumber() {
        String validNumber = "345882865";
        AccountValidator account = new AccountValidator(new Account(validNumber));
        assertTrue(account.Validate());
    }

    @Test
    public void validateInvalidAccountNumber() {
        String invalidNumber = "223456789";
        AccountValidator account = new AccountValidator(new Account(invalidNumber));
        assertFalse(account.Validate());
    }
}
