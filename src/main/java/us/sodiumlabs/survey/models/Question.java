package us.sodiumlabs.survey.models;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Copyright (C) 2014 us.sodiumlabs.us
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
@Entity
@Table(name = "question")
public class Question {

    public enum EQuestionType {
        TEXT
    }

    private Integer _id;

    private String _label;

    private EQuestionType _type;

    private Survey _survey;

    /**
     * @return the id.
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return _id;
    }

    /**
     * @return the question label.
     */
    @Column(name = "label")
    public String getLabel() {
        return _label;
    }

    /**
     * @return the Survey this question belongs to.
     */
    @JoinColumn(name = "survey_id")
    @ManyToOne
    public Survey getSurvey() {
        return _survey;
    }

    /**
     * @return the question type.
     */
    @Column(name = "type")
    public EQuestionType getType() {
        return _type;
    }

    /**
     * @param id the id of the question.
     */
    public void setId(final Integer id) {
        _id = id;
    }

    /**
     * @param label the label for the question.
     */
    public void setLabel(final String label) {
        _label = label;
    }

    public void setSurvey(final Survey survey) {
        _survey = survey;
    }

    /**
     * @param type the type of question.
     */
    public void setType(final EQuestionType type) {
        _type = type;
    }
}
