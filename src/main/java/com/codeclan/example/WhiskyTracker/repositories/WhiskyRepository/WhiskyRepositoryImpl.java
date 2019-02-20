package com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;


import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class WhiskyRepositoryImpl implements WhiskyRepositoryCustom {


    @Autowired
    EntityManager entityManager;

    @Override
    public List<Whisky> findWhiskyByAge(int age, Long id) {
        List<Whisky> results = null;
        Criteria cr = null;
        try {
            Session session = entityManager.unwrap(Session.class);
            cr = session.createCriteria(Whisky.class);
            cr.add(Restrictions.eq("distillery.id", id));
            cr.add(Restrictions.eq("age",age));
        }catch (HibernateError e){
            e.printStackTrace();
        }
        results = cr.list();
        return results;
    }
}
