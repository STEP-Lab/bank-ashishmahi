import com.thoughtworks.CreditTransaction;
import com.thoughtworks.DebitTransaction;
import com.thoughtworks.Transactions;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.core.Is.is;
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
        transactions.debit(1000,"Aditi",1000);
        assertThat(transactions.list,hasItem(new DebitTransaction(1000, "Aditi", 1000,new Date())));
    }

    @Test
    public void mustRecordCreditTransaction() {
        transactions.credit(1000,"Aditi",1000);
        assertThat(transactions.list,hasItem(new CreditTransaction(1000, "Aditi", 1000,new Date())));
    }

    @Test
    public void filterTransactionByAmount() {
        transactions.credit(1000,"Aditi",1000);
        transactions.credit(400,"Aditi",1000);
        transactions.credit(1400,"Aditi",1000);
        Transactions expected = this.transactions.filterByAmountGreaterThan(1000);
        assertThat(expected.list,hasItems(new CreditTransaction(1400,"Aditi",1000)));
    }

    @Test
    public void getAllTransactionAfterDate() {
        int date = new Date().getDate();
        transactions.credit(1000,"Aditi",1000);
        transactions.list.get(0).getDate().setDate(date+1);
        transactions.credit(400,"Aditi",1000);
        Date expectedDate = new Date();
        expectedDate.setDate(date+1);
        CreditTransaction transaction = new CreditTransaction(1000, "Aditi", 1000, expectedDate);
        Transactions expected = this.transactions.getAllTransactionAfter(new Date());
        assertThat(expected.list,hasItem(transaction));
        assertThat(expected.list.size(),is(1));
    }

    @Test
    public void getAllTransactionBeforeDate() {
        int date = new Date().getDate();
        transactions.credit(1000,"Aditi",1000);
        transactions.list.get(0).getDate().setDate(date-1);
        transactions.credit(400,"Aditi",1000);
        Date expectedDate = new Date();
        expectedDate.setDate(date-1);
        CreditTransaction transaction = new CreditTransaction(1000, "Aditi", 1000, expectedDate);
        Transactions expected = this.transactions.getAllTransactionBefore(new Date());
        assertThat(expected.list,hasItem(transaction));
        assertThat(expected.list.size(),is(1));
    }

    @Test
    public void getAllCreditTransaction() {
        transactions.debit(1000,"Aditi",1000);
        transactions.credit(400,"Aditi",1000);
        transactions.debit(1400,"Aditi",1000);
        Transactions expected = this.transactions.getAllCredit();
        assertThat(expected.list,hasItem(new CreditTransaction(400,"Aditi",1000)));
    }

    @Test
    public void getAllDebitTransaction() {
        transactions.credit(1000,"Aditi",1000);
        transactions.credit(400,"Aditi",1000);
        transactions.debit(1400,"Aditi",1000);
        Transactions expected = this.transactions.getAllDebit();
        assertThat(expected.list,hasItem(new DebitTransaction(1400,"Aditi",1000)));
    }

    @Test
    public void shouldPrintInFile() throws FileNotFoundException, UnsupportedEncodingException {
        transactions.credit(1000,"Aditi",1000);
        ArrayList<String> expected = new ArrayList<>();
        PrintWriter writer = new PrintWriter("file-name.txt", "utf-8"){
            @Override
            public void println(String x) {
                expected.add(x);
            }
        };
        transactions.print(writer);
        writer.close();
        assertThat(expected,hasItems(new CreditTransaction(1000,"Aditi",1000).toString()));
    }

    @Test
    public void shouldWriteToCsvFile() throws FileNotFoundException, UnsupportedEncodingException {
        transactions.credit(1000,"Ashish",1000);
        transactions.credit(1000,"Ashish",1000);
        ArrayList<String> expected = new ArrayList<>();
        PrintWriter writer = new PrintWriter("test.csv", "utf-8"){
            @Override
            public void println(String x) {
                expected.add(x);
            }
        };
        transactions.printCsv(writer);
        writer.close();
        assertThat(expected,hasItems(new CreditTransaction(1000,"Ashish",1000).toCsv(),new CreditTransaction(1000,"Ashish",1000).toCsv()));
    }

}
