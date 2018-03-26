import com.thoughtworks.AccountNumber;
import com.thoughtworks.InvalidAccountNumber;
import org.junit.Test;

public class AccountNumberTest {
    @Test(expected = InvalidAccountNumber.class)
    public void throwsErrorWhenAccountNumContainsOnlyNumbers() throws InvalidAccountNumber {
        AccountNumber.createAccountNumber("1234");
    }

    @Test(expected = InvalidAccountNumber.class)
    public void throwsErrorIfAccountNumberContainsAlphabetsOnly() throws InvalidAccountNumber {
        AccountNumber.createAccountNumber("abcd");
    }

    @Test(expected = InvalidAccountNumber.class)
    public void throwsErrorWhenAccNumIsShort() throws InvalidAccountNumber {
        AccountNumber.createAccountNumber("1234-123");
    }
}
