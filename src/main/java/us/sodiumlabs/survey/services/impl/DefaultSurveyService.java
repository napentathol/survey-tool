package us.sodiumlabs.survey.services.impl;

import org.springframework.transaction.annotation.Transactional;
import us.sodiumlabs.survey.daos.ISurveyDAO;
import us.sodiumlabs.survey.models.Question;
import us.sodiumlabs.survey.models.Survey;
import us.sodiumlabs.survey.services.ISurveyService;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C) 2014 sodiumlabs.us
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * <p/>
 * Created by Alex on 8/18/2014.
 */
@Transactional
public class DefaultSurveyService implements ISurveyService{

    private final ISurveyDAO _surveyDAO;

    public DefaultSurveyService(final ISurveyDAO dao){
        _surveyDAO = dao;
    }

    @Override
    public List<Survey> getAllSurveys() {
        List<Survey> allSurveys = _surveyDAO.getAllSurveys();

        for(Survey survey : allSurveys){
            survey.getQuestions().size();
        }

        return allSurveys;
    }

    @Override
    public Survey getSurveyById(int id) {
        return _surveyDAO.getSurveyById(id);
    }

    @Override
    public boolean receiveAnswerToQuestions(Map<Question, String> questionAnswerMap) {
        for (Map.Entry<Question,String> questionAnswer : questionAnswerMap.entrySet()) {
            printQuestionAnswers(questionAnswer.getKey(), questionAnswer.getValue());
        }

        return true;
    }

    private void printQuestionAnswers(Question q, String s) {
        System.out.println(
            "Question was answered" +
                "\n\tid: " + q.getId() +
                "\n\tlabel: " + q.getLabel() +
                "\n\tanswer: " + s + "\n"
        );
    }
}
