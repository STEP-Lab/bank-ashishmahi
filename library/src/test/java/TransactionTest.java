import com.thoughtworks.DebitTransaction;
import com.thoughtworks.Transaction;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TransactionTest {
    @Test
    public void mustCreateDebitTransaction() {
        Date date = new Date();
        Transaction transaction = new DebitTransaction(date,1000, "AnotherAccount");
        assertThat(transaction.getDate(),is(date));
    }
}
