package us.sodiumlabs.survey.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
@Table(name = "survey")
public class Survey implements Serializable {

    private Integer _id;

    private String _name;

    private List<Question> _questions;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "survey_id", unique = true, nullable = false)
    public Integer getId() {
        return _id;
    }

    /**
     * @return the survey name.
     */
    @Column(name = "name")
    public String getName() {
        return _name;
    }


    @OneToMany(mappedBy="survey")
    @OrderBy("id")
    public List<Question> getQuestions() {
        return _questions;
    }

    public void setId(Integer id) {
        _id = id;
    }

    /**
     * @param name the name for the question.
     */
    public void setName(final String name) {
        _name = name;
    }

    public void setQuestions(List<Question> questions) {
        _questions = questions;
    }
}
