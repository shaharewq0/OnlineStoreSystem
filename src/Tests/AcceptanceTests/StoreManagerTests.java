package Tests.AcceptanceTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import Tests.AcceptanceTests.auxiliary.Question;
import Tests.AcceptanceTests.auxiliary.StoreDetails;

public class StoreManagerTests extends AccTest {
    private static String username = "user1";
    private static String password = "pass1";
    private static String validStoreName = "store";
    private static String validProductName = "product";
    private static String ownerUsername = "user2";
    private static String regUsername = "user3";
    private static Question question = new Question("q1");
    private static Question question2 = new Question("q2");

    @BeforeClass
    public static void setUpInit() {
        system.init();
        system.register(username, password);
        system.register(ownerUsername, password);
        system.register(regUsername, password);

        system.login(ownerUsername, password);
        system.openStore(new StoreDetails(validStoreName));
        system.appointStoreManager(username);
        system.addProductToStore(validStoreName, validProductName);
        system.logout();

        system.login(username, password);
    }


    @Test
    public void viewStoreSellingHistory() {
        assertTrue(system.getStoreSellingHistory(validStoreName).isEmpty());
        system.logout();

        system.addToBasket(validStoreName, validProductName);
        //TODO: buy from basket

        system.login(username, password);
        assertFalse(system.getStoreSellingHistory(validStoreName).isEmpty());
        //TODO: check selling history
    }

    @Test
    public void lookupUserQuestions() {
        assertTrue(system.getStoreQuestions(validStoreName).isEmpty());
        system.logout();

        system.login(regUsername, password);
        system.askQuestion(validStoreName, question);
        system.logout();

        system.login(username, password);
        List<Question> questions = system.getStoreQuestions(validStoreName);
        assertFalse(questions.isEmpty());
        assertEquals(questions.get(0).getQuestion(), question.getQuestion());
    }

    @Test
    public void answerQuestions() {
        system.logout();

        system.login(regUsername, password);
        system.askQuestion(validStoreName, question2);
        system.logout();

        system.login(username, password);

        List<Question> questions = system.getStoreQuestions(validStoreName);
        boolean flag = false;
        for (Question q : questions) {
            if (q.getQuestion().equals(question2.getQuestion())) {
                assertNull(q.getAnswer());
                flag = true;
            }
        }
        assertTrue(flag);
        system.answerQuestion(question2);

        questions = system.getStoreQuestions(validStoreName);
        flag = false;
        for (Question q : questions) {
            if (q.getQuestion().equals(question2.getQuestion())) {
                assertEquals(q.getAnswer(), question2.getAnswer());
                flag = true;
            }
        }
        assertTrue(flag);
    }
}
