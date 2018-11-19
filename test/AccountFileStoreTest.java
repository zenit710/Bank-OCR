import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountFileStoreTest {

    @Before
    public void setUp() {
        AccountFileStore store = new AccountFileStore("test/results/numbers");
    }

    @Test
    public void save() {
    }
}