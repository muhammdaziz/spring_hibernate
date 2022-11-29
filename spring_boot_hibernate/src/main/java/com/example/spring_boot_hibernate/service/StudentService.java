package com.example.spring_boot_hibernate.service;

import com.example.spring_boot_hibernate.model.Student;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final SessionFactory sessionFactory;

    public void saveStudent(Student student){
        getSession().save(student);
    }

    public List<Student> getStudents(){
        return getSession().createQuery("select s from Student s", Student.class).list();
    }

    public boolean delete(Student student){
        getSession().delete(student);

        return true;
    }


    private Session getSession(){
        Session session = sessionFactory.getCurrentSession();

        if (session == null)
            session = sessionFactory.openSession();

        return session;
    }
}
