import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountValidatorTest {

    @Test
    public void validateValidAccountNumber() {
        String validNumber = "345882865";
        AccountValidator account = new AccountValidator(validNumber);
        assertTrue(account.getIsValid());
    }

    @Test
    public void validateInvalidAccountNumber() {
        String invalidNumber = "223456789";
        AccountValidator account = new AccountValidator(invalidNumber);
        assertFalse(account.getIsValid());
        //assertEquals(false, account.getIsValid());
    }
}
