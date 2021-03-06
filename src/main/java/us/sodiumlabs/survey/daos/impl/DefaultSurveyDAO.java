package us.sodiumlabs.survey.daos.impl;

import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import us.sodiumlabs.survey.daos.ISurveyDAO;
import us.sodiumlabs.survey.models.Question;
import us.sodiumlabs.survey.models.Survey;

import java.io.Serializable;
import java.util.List;

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
 * Created by Alex on 8/18/2014.
 */
@Transactional
public class DefaultSurveyDAO extends HibernateDaoSupport implements ISurveyDAO {

    @Override
    public Survey getSurveyById(final Serializable id) {
        return getHibernateTemplate().get(Survey.class, id);
    }

    @Override
    public List<Survey> getAllSurveys() {
        return (List<Survey>)(getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Survey.class)));
    }

    @Override
    public void save(Survey survey) {
        getHibernateTemplate().saveOrUpdate(survey);

        for(Question question : survey.getQuestions()){
            getHibernateTemplate().saveOrUpdate(question);
        }
    }
}
