package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User findOwner(String carName, String carSeries) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
              "SELECT u FROM User u JOIN u.car c WHERE c.model = :carName AND c.series = :carSeries", User.class);
      query.setParameter("carName", carName);
      query.setParameter("carSeries", carSeries);
      return query.getSingleResult();
   }
}