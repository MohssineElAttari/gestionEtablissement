/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import classes.Employe;
import dao.IDao;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author dell
 */
public class EmployeService implements IDao<Employe> {

    @Override
    public boolean create(Employe o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return false;
        }
    }

    @Override
    public boolean update(Employe o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return false;
        }
    }

    @Override
    public boolean delete(Employe o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return false;
        }
    }

    @Override
    public Employe findById(int id) {
        Employe m = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            m = (Employe) session.get(Employe.class, id);
            tx.commit();
            session.close();
            return m;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return m;
        }
    }

    @Override
    public List<Employe> findAll() {
        List<Employe> employes = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employes = session.createQuery("from Employe").list();
        session.getTransaction().commit();
        session.close();
        return employes;
    }

//    public Employe findByEmail(String email) {
//        Employe e = null;
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        e = (Employe) session.createQuery("Select e from Employe e where email=? and password =?").setParameter(0, email).uniqueResult();
//        session.getTransaction().commit();
//        session.close();
//
//        return e;
//    }

        public List<Employe> FindBetweenDates(int id, Date d1, Date d2) {
        List<Employe> ee = null;
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        ee = s.createQuery("SELECT e FROM Employe e WHERE e.profil.id = ? AND  dateEmbauche BETWEEN ? AND ? ").setParameter(0, id).setParameter(1, d1).setParameter(2, d2).list();

//        ee = query.list();
        s.getTransaction().commit();
        s.close();

        return ee;
    }
       
    public List<Employe> findEmployeByProfilId(int id) {
        List<Employe> empList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM Employe e WHERE e.profil.id =" + id);
        empList = query.list();
        session.getTransaction().commit();
        session.close();
        return empList;
    }

//    public List<Employe> FindByNameAndLastName(String nom, String prenom) {
//        List<Employe> ee = null;
//        Session s = HibernateUtil.getSessionFactory().openSession();
//        s.beginTransaction();
//        ee = s.createQuery("SELECT e FROM Employe e WHERE e.nom = ? AND  e.prenom ? ").setParameter(0, nom).setParameter(1, prenom).list();
//
////        ee = query.list();
//        s.getTransaction().commit();
//        s.close();
//
//        return ee;
//    }

    public int CountEmployesByProfilName(String nom) {
        int nb = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        nb = Integer.parseInt(session.createQuery("SELECT COUNT(e) FROM Employe e WHERE e.profil.libelle =" + nom).uniqueResult().toString());
        session.getTransaction().commit();
        session.close();
        return nb;
    }

    public Employe findByEmail(String email) {
        Employe e = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        e = (Employe) session.createQuery("Select e from Employe e where e.email=?").setParameter(0, email).uniqueResult();
        session.getTransaction().commit();
        session.close();

        return e;
    }

}
