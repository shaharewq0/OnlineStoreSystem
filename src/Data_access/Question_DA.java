package Data_access;

import Domain.info.Question;

import java.util.Optional;

public class Question_DA extends DOA<Question> {

    public Question_DA(){
        super();
        TABLE_NAME="QUESTION_TABLE";
    }

    @Override
    public Optional<Question> get(int id) {
        return Optional.ofNullable(entityManager.find(Question.class,id));
    }

    @Override
    public void save(Question question) {
        executeInsideTransaction(entityManager -> entityManager.persist(question));
    }

    @Override
    public void update(Question question, String[] params) {
        //TODO: add set to question
        executeInsideTransaction(entityManager -> entityManager.merge(question));
    }

    @Override
    public void delete(Question question) {
        executeInsideTransaction(entityManager -> entityManager.remove(question));
    }
}
