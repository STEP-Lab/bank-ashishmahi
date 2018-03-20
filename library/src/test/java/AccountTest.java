import com.thoughtworks.Account;
import com.thoughtworks.InvalidAccountNumber;
import com.thoughtworks.MinimumBalanceError;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws Exception, MinimumBalanceError, InvalidAccountNumber {
            account = new Account("1234-1234",10000);
    }

    @Test
    public void checkBalance(){
        assertThat(account.getBalance(),is(10000.0));
    }
    @Test
    public void checkAccountNumber(){
        assertThat(account.getAccountNumber(),is("1234-1234"));
    }

    @Test(expected = MinimumBalanceError.class)
    public void checkMinimumBalance() throws MinimumBalanceError, InvalidAccountNumber {
        new Account("1345-1345",500);
    }

    @Test(expected = InvalidAccountNumber.class)
    public void throwsErrorWhenAccountNumContainsOnlyNumbers() throws InvalidAccountNumber, MinimumBalanceError {
        new Account("1234",2000);
    }

    @Test(expected = InvalidAccountNumber.class)
    public void throwsErrorIfAccountNumberContainsAlphabetsOnly() throws InvalidAccountNumber, MinimumBalanceError {
        new Account("abcd",10000);
    }

    @Test(expected = InvalidAccountNumber.class)
    public void throwsErrorWhenAccNumIsShort() throws InvalidAccountNumber, MinimumBalanceError {
        new Account("1234-123",4000);
    }

    @Test
    public void shouldNotAlterBalanceWhenBalanceGoesBelowMinimumBalanceLimit() {
        try {
            account.withDraw(9400);
        } catch (MinimumBalanceError minimumBalanceError) {
            assertThat(account.getBalance(),is(10000.0));
        }
    }

    @Test
    public void withdrawAmount() throws MinimumBalanceError {
        account.withDraw(1000);
        assertThat(account.getBalance(),is(9000.0));
    }

    @Test(expected = MinimumBalanceError.class)
    public void minimumBalanceRequired() throws MinimumBalanceError {
        account.withDraw(9500);
    }
}
