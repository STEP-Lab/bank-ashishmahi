import com.thoughtworks.Account;
import com.thoughtworks.AccountNumber;
import com.thoughtworks.InvalidAccountNumber;
import com.thoughtworks.MinimumBalanceError;
import org.junit.Test;

public class AccountNumberTest {
    @Test(expected = InvalidAccountNumber.class)
    public void throwsErrorWhenAccountNumContainsOnlyNumbers() throws InvalidAccountNumber {
        new AccountNumber("1234");
    }

    @Test(expected = InvalidAccountNumber.class)
    public void throwsErrorIfAccountNumberContainsAlphabetsOnly() throws InvalidAccountNumber {
        new AccountNumber("abcd");
    }

    @Test(expected = InvalidAccountNumber.class)
    public void throwsErrorWhenAccNumIsShort() throws InvalidAccountNumber {
        new AccountNumber("1234-123");
    }
}
