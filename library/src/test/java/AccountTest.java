import com.thoughtworks.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws MinimumBalanceError, InvalidAccountNumber {
            account = Account.createAccount(AccountNumber.createAccountNumber("1234-1234"),10000);
    }

    @Test
    public void checkBalance(){
        assertThat(account.getBalance(),is(10000.0));
    }
    @Test(expected = MinimumBalanceError.class)
    public void checkMinimumBalance() throws MinimumBalanceError, InvalidAccountNumber {
        Account.createAccount(AccountNumber.createAccountNumber("1345-1345"),500);
    }


    @Test
    public void shouldNotAlterBalanceWhenBalanceGoesBelowMinimumBalanceLimit() throws InvalidAmountException {
        try {
            account.withDraw(9400);
        } catch (MinimumBalanceError minimumBalanceError) {
            assertThat(account.getBalance(),is(10000.0));
        }
    }

    @Test
    public void withdrawAmount() throws MinimumBalanceError, InvalidAmountException {
        account.withDraw(1000);
        assertThat(account.getBalance(),is(9000.0));
    }

    @Test
    public void shouldNotWithDrawNegativeAmount() throws MinimumBalanceError {
        try{
        account.withDraw(-1000);
        } catch (InvalidAmountException e){
            assertThat(account.getBalance(),is(10000.0));
        }
    }

    @Test(expected = MinimumBalanceError.class)
    public void minimumBalanceRequired() throws MinimumBalanceError, InvalidAmountException {
        account.withDraw(9500);
    }
}
