import com.thoughtworks.CreditTransaction;
import com.thoughtworks.DebitTransaction;
import com.thoughtworks.Transactions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

    private Transactions transactions;

    @Before
    public void setUp() {
        transactions = new Transactions();
    }

    @Test
    public void mustRecordDebitTransaction() {
        transactions.debit(1000,"Aditi");
        assertThat(transactions.list,hasItem(new DebitTransaction(1000, "Aditi", new Date())));
    }

    @Test
    public void mustRecordCreditTransaction() {
        transactions.credit(1000,"Aditi");
        assertThat(transactions.list,hasItem(new CreditTransaction(1000, "Aditi", new Date())));
    }

    @Test
    public void printTransaction() throws FileNotFoundException, UnsupportedEncodingException {
        transactions.credit(1000,"Aditi");
        ArrayList<String> expected = new ArrayList<>();
        PrintWriter writer = new PrintWriter("file-name.txt", "utf-8"){
            @Override
            public void println(String x) {
                expected.add(x);
            }
        };
        transactions.print(writer);
        writer.close();
        assertThat(expected,hasItems(new CreditTransaction(1000,"Aditi").toString()));
    }

    @Test
    public void filterTransactionByAmount() {
        transactions.credit(1000,"Aditi");
        transactions.credit(400,"Aditi");
        transactions.credit(1400,"Aditi");
        Transactions expected = this.transactions.filterByAmountGreaterThan(1000);
        assertThat(expected.list,hasItems(new CreditTransaction(1400,"Aditi")));
    }

    @Test
    @Ignore
    public void filterTransactionByDate() {
        transactions.credit(1000,"Aditi");
        transactions.credit(400,"Aditi");
//        Transactions expected = this.transactions.filterByDateAfter(new Date());
    }

    @Test
    public void filterTransactionByType() {
        transactions.debit(1000,"Aditi");
        transactions.credit(400,"Aditi");
        transactions.debit(1400,"Aditi");
        Transactions expected = this.transactions.filterByType("credit");
        assertThat(expected.list,hasItem(new CreditTransaction(400,"Aditi")));
    }
}
