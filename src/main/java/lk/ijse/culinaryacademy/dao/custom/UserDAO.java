package lk.ijse.culinaryacademy.dao.custom;

import lk.ijse.culinaryacademy.dao.SuperDAO;
import lk.ijse.culinaryacademy.entity.User;

public interface UserDAO extends SuperDAO {
    void save(User user);
}
