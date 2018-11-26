/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import classes.Etudiant;
import dao.IDao;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author dell
 */
public class EtudiantService implements IDao<Etudiant> {

    @Override
    public boolean create(Etudiant o) {
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
    public boolean update(Etudiant o) {
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
    public boolean delete(Etudiant o) {
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
    public Etudiant findById(int id) {
        Etudiant m = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            m = (Etudiant) session.get(Etudiant.class, id);
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
    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        etudiants = session.createQuery("from Etudiant").list();
        session.getTransaction().commit();
        session.close();
        return etudiants;
    }

    public List<Etudiant> FindByNameAndLastName(String nom, String prenom) {
        List<Etudiant> ee = null;
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        ee = s.createQuery("SELECT e FROM Etudiant e WHERE e.nom = ? AND  e.prenom ? ").setParameter(0, nom).setParameter(1, prenom).list();

//        ee = query.list();
        s.getTransaction().commit();
        s.close();

        return ee;
    }

    public List<String> DistinctDecision() {
        List<String> nb = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        nb = session.createQuery("SELECT DISTINCT decision FROM Etudiant ").list();
        session.getTransaction().commit();
        session.close();
        return nb;
    }

    public int countDecision(String nom) {
        int nb = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        nb = Integer.parseInt(session.createQuery("SELECT Count(e) FROM Etudiant e where decision = ? ").setParameter(0, nom).uniqueResult().toString());
        //nb = Integer.parseInt(session.createSQLQuery("SELECT Count(*) FROM Etudiant where decision = " +nom).toString());
        session.getTransaction().commit();
        session.close();
        return nb;
    }

    public List<String> DistinctDernierNiveau() {
        List<String> nb = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        nb = session.createQuery("SELECT DISTINCT dernierNiveau FROM Etudiant ").list();
        session.getTransaction().commit();
        session.close();
        return nb;
    }

    public int countDernierNiveau(String nom) {
        int nb = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        nb = Integer.parseInt(session.createQuery("SELECT Count(e) FROM Etudiant e where dernierNiveau = ? ").setParameter(0, nom).uniqueResult().toString());
        session.getTransaction().commit();
        session.close();
        return nb;
    }

}
