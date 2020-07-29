package tool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuizCheckerToolTest {
    QuizCheckerTool checker;

    @BeforeEach
    public void setUp() {
        checker = new QuizCheckerTool(3);
    }

    @Test
    public void isCorrectTest() {
        assertTrue(checker.isCorrect(3));
        assertFalse(checker.isCorrect(1));
    }

    @Test
    public void serializeAnswerTest() {
        assertEquals(2, checker.serializeAnswer("2"));
        assertEquals(0, checker.serializeAnswer("5"));
        assertEquals(0, checker.serializeAnswer("0"));
        assertEquals(0, checker.serializeAnswer("2.0"));
        assertEquals(0, checker.serializeAnswer("two"));
    }
}
