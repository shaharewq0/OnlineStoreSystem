package DAL;

import Domain.info.Question;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Question_DATest {

    Question_DA da;
    Question question;

    @Before
    public void setUp() throws Exception {
        da=new Question_DA();
        question=new Question("how are you");
    }

    @Test
    public void getAll() {
        da.getAll();
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void add() {
    }
}