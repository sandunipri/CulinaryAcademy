package lk.ijse.culinaryacademy.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.ijse.culinaryacademy.config.SessionFactoryConfig;
import lk.ijse.culinaryacademy.entity.Payment;
import lk.ijse.culinaryacademy.view.tdm.PaymentCourseTm;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainFormContoller {

    public Text lblUserName;
    public Text lblGreeting;
    @FXML
    private TableView<PaymentCourseTm> PaymentCourseTablrView;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colCoursePrice;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPaymentID;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private Label labelCourseCount;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelOrderCount;

    @FXML
    private Label labelStudentCount;

    @FXML
    private Label labelTime;

    @FXML
    private AnchorPane rootNodeMain;

    public void initialize() {
        getCourseCount();
        getStudentCount();
        allPayment();
        setDateTime();
        loadpaymentorders();
        setCellValueFactory();
        loadGreeting();
    }

    private void loadGreeting() {
        lblUserName.setText(LoginFormController.user.getUserName());
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        String status;

        if (hour >= 5 && hour < 12) {
            status = "Good Morning!";
        } else if (hour >= 12 && hour < 18) {
            status = "Good Afternoon!";
        } else {
            status = "Good Evening!";
        }
        lblGreeting.setText(status);
    }

    private void setCellValueFactory() {
        colPaymentID.setCellValueFactory(new PropertyValueFactory<>("PaymentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        colCoursePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("PaymentDate"));
    }

    private void loadpaymentorders() {
            try (Session session = SessionFactoryConfig.getInstance().getSession()) {
                // Query to fetch the most recent payments (limit to, say, 10 records)
                String hql = "FROM Payment p ORDER BY p.paymentDate DESC";
                List<Payment> payments = session.createQuery(hql, Payment.class)
                        .setMaxResults(10)
                        .list();

                ObservableList<PaymentCourseTm> recentPayments = FXCollections.observableArrayList();

                for (Payment payment : payments) {
                    PaymentCourseTm tm = new PaymentCourseTm(
                            payment.getPaymentId(),
                            payment.getStudentCourseDetails().getStudent().getName(),
                            payment.getStudentCourseDetails().getCourses().getName(),
                            payment.getStudentCourseDetails().getCourses().getPrice(),
                            payment.getStudentCourseDetails().getRegDate()

                    );
                    recentPayments.add(tm);
                }

                PaymentCourseTablrView.setItems(recentPayments);
            } catch (HibernateException e) {
                e.printStackTrace();
}
    }

    private void setDateTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            labelTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        labelDate.setText(dtf.format(now));
}

    private void allPayment() {
        long count = 0;
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT COUNT(*) FROM Payment ";
            count = (long) session.createQuery(hql).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        labelOrderCount.setText(String.valueOf(count));
    }

    private void getCourseCount() {
        long count = 0;
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT COUNT(*) FROM Course ";
            count = (long) session.createQuery(hql).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        labelCourseCount.setText(String.valueOf(count));
    }

    public void getStudentCount() {
        long count = 0;
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT COUNT(*) FROM Student ";
            count = (long) session.createQuery(hql).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        labelStudentCount.setText(String.valueOf(count));
}

}
