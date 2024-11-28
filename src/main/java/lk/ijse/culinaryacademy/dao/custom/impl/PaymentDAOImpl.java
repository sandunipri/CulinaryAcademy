package lk.ijse.culinaryacademy.dao.custom.impl;

import lk.ijse.culinaryacademy.dao.custom.PaymentDAO;
import lk.ijse.culinaryacademy.entity.Payment;
import org.hibernate.Session;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public void save(Session session, Payment payment) {
        session.save(payment);
    }
}
