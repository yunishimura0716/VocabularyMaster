package tool;

/*
Class provide the tool to evaluate the user's answer on vocabulary quiz
 */
public class QuizCheckerTool {
    int answer;

    // EFFECTS: set user's answer
    public QuizCheckerTool(int answer) {
        this.answer = answer;
    }

    public boolean isCorrect(int userAnswer) {
        return answer == userAnswer;
    }

    public int serializeAnswer(String userAnswer) {
        int serializedAnswer;
        try {
            serializedAnswer = Integer.parseInt(userAnswer);
            if (serializedAnswer < 1 || 4 < serializedAnswer) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            return 0;
        }
        return serializedAnswer;
    }
}
