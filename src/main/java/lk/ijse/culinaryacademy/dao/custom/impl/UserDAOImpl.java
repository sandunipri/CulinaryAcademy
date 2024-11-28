package lk.ijse.culinaryacademy.dao.custom.impl;

import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.dao.custom.UserDAO;
import lk.ijse.culinaryacademy.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAOImpl implements UserDAO {
    @Override
    public void save(User user) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }
}
