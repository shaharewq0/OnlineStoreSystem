package DAL;

import Domain.info.Question;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Question_DATest {

    Question_DA da;
    Question question;

    @Before
    public void setUp() throws Exception {
        da=new Question_DA();
        question=new Question("how are you");
        question.addAnswers("hello");
        }

    @Test
    public void getAll() {
        int size=da.getAll().size();
        da.add(question);
        assertEquals(da.getAll().size()-1,size);
    }

    @Test
    public void update() {
       da.add(question);
       question.setQuestion("how high are you?");
       da.update(question);
       assertTrue(da.getAll().get(0).getQuestion().equals("how high are you?"));
       question.addAnswers("its hi, how are you.....");
       da.update(question);
       List<String> A=da.getAll().get(0).getAnswers();
       assertTrue(A.contains("its hi, how are you....."));
       assertTrue(A.contains("hello"));
    }

    @Test
    public void delete() {
       da.add(question);
       int size=da.getAll().size();
       da.delete(question);
       int Nsize=da.getAll().size();
       assertEquals(size-1,Nsize);
    }

    @Test
    public void add() {
        da.add(question);
        Question que=da.getAll().get(0);
        assertEquals(que.getId(),question.getId());
    }
}