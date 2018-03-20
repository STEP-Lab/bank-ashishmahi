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
            account = new Account("1234-1234", 1000);
    }

    @Test
    public void checkBalance(){
        assertThat(account.getBalance(),is(1000));
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
    public void validateAccountNumber() throws InvalidAccountNumber, MinimumBalanceError {
        new Account("1234",2000);
    }
}
