package lk.ijse.culinaryacademy.dao.custom;

import lk.ijse.culinaryacademy.dao.SuperDAO;
import lk.ijse.culinaryacademy.entity.Payment;
import org.hibernate.Session;

public interface PaymentDAO extends SuperDAO {
    void save(Session session, Payment payment);
}
