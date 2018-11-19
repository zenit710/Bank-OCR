import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountValidatorTest {

    @Test
    public void validateValidAccountNumber() {
        String validNumber = "345882865";
        AccountValidator validator = new AccountValidator();
        boolean isValid = validator.validate(new Account(validNumber));
        assertTrue(isValid);
    }

    @Test
    public void validateInvalidAccountNumber() {
        String invalidNumber = "223456789";
        AccountValidator validator = new AccountValidator();
        boolean isValid = validator.validate(new Account(invalidNumber));
        assertFalse(isValid);
    }
}
