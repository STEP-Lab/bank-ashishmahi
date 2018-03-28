import com.thoughtworks.*;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws MinimumBalanceError, InvalidAccountNumber {
            account = Account.createAccount(AccountNumber.createAccountNumber("1234-1234"),10000);
    }

    @Test
    public void checkBalance(){
        assertThat(account.getBalance(),is(Money.of(CurrencyUnit.of("INR"), 10000.0)));
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
            assertThat(account.getBalance(),is(Money.of(CurrencyUnit.of("INR"), 10000.0)));
        }
    }

    @Test
    public void withdrawAmount() throws MinimumBalanceError, InvalidAmountException {
        account.withDraw(1000);
        assertThat(account.getBalance(),is(Money.of(CurrencyUnit.of("INR"), 9000.0)));
    }

    @Test
    public void shouldNotWithDrawNegativeAmount() throws MinimumBalanceError {
        try{
        account.withDraw(-1000);
        } catch (InvalidAmountException e){
            assertThat(account.getBalance(),is(Money.of(CurrencyUnit.of("INR"), 10000.0)));
        }
    }

    @Test
    public void creditAmount() throws InvalidAmountException {
        account.credit(1000);
        assertThat(account.getBalance(),is(Money.of(CurrencyUnit.of("INR"), 11000.0)));
    }

    @Test
    public void shouldNotCreditNegativeAmount() throws InvalidAmountException{
        try{
            account.credit(-1000);
        } catch (InvalidAmountException e){
            assertThat(account.getBalance(),is(Money.of(CurrencyUnit.of("INR"), 10000.0)));
        }
    }

    @Test(expected = MinimumBalanceError.class)

    public void minimumBalanceRequired() throws MinimumBalanceError, InvalidAmountException {
        account.withDraw(9500);
    }

    @Test
    public void shouldRecordCreditTransactionAsSoonAsTransactionHappens() throws InvalidAmountException {
        account.credit(1000);
        Transactions expected =  account.getTransactions();
        assertThat(expected.list,hasItem(new CreditTransaction(1000,account.getAccountNumber(),11000)));
    }

    @Test
    public void shouldRecordDebitTransaction() throws InvalidAmountException, MinimumBalanceError {
        account.withDraw(1000);
        Transactions expected =  account.getTransactions();
        assertThat(expected.list,hasItem(new DebitTransaction(1000,account.getAccountNumber(),9000)));
    }

    @Test
    public void shouldAddBalanceAfterTransactionInTransactions() throws InvalidAmountException, MinimumBalanceError {
        account.withDraw(1000);
        ArrayList list =  account.getTransactions().list;
        Transaction expected = (Transaction) list.get(list.size()-1);
        assertThat(expected.getBalance(),is(9000.0));
    }
}
