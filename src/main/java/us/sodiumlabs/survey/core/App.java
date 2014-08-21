package us.sodiumlabs.survey.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import us.sodiumlabs.survey.daos.ISurveyDAO;
import us.sodiumlabs.survey.models.Question;
import us.sodiumlabs.survey.models.Survey;
import us.sodiumlabs.survey.services.ISurveyService;

import java.util.Arrays;

/**
 * Copyright (C) 2014 sodiumlabs.us
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * <p>
 * Created by Alex on 8/20/2014.
 */
public class App {
    public static void main(final String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        loadSurveys(context);

        printContext(context);
    }

    private static void loadSurveys(ApplicationContext context) {
        ISurveyDAO dao = (ISurveyDAO)context.getBean("defaultSurveyDAO");

        final Survey iceCream = new Survey();
        iceCream.setName("Ice Cream Survey");

        final Question flavor = new Question();
        flavor.setLabel("What is your favorite ice cream flavor?");
        flavor.setType(Question.EQuestionType.TEXT);
        flavor.setSurvey(iceCream);

        final Question timesPerWeek = new Question();
        timesPerWeek.setLabel("How many times a week do you eat ice cream?");
        timesPerWeek.setType(Question.EQuestionType.TEXT);
        timesPerWeek.setSurvey(iceCream);

        iceCream.setQuestions(Arrays.asList(flavor,timesPerWeek));

        dao.save(iceCream);
    }

    public static void printContext(final ApplicationContext context){
        final ISurveyService service = (ISurveyService)context.getBean("defaultSurveyService");

        System.out.println("Begin printing context:\n\n");

        for(final Survey survey : service.getAllSurveys()){
            printSurvey(survey);
        }

        System.out.println("End context print.");
    }

    public static void printSurvey(final Survey survey) {
        System.out.println(
            "Printing survey with id: " + survey.getId() +
                "\n\t and name: " + survey.getName() + "\n\n"
        );

        for(final Question question : survey.getQuestions()) {
            printQuestion(question);
        }

        System.out.println("End survey print.");
    }

    public static void printQuestion(final Question question) {
        System.out.println(
                "\tPrinting Question with id: " + question.getId() +
                        "\n\t\t and name: " + question.getLabel() +
                        "\n\t\t and type: " + question.getType() + "\n\n"
        );
    }
}
