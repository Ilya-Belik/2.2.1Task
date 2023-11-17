package hiber.service;
import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.TypedQuery;
import java.util.List;

import org.hibernate.SessionFactory;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Autowired
   private SessionFactory sessionFactory;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional
   @Override
   public void deleteAllUsers(){
      userDao.listUsers();
   }

   @Transactional
   @Override
   public User findOwner(String car_name, String car_series) {
      return userDao.findOwner(car_name, car_series);
   }

   @Transactional(readOnly = true)
   @Override
   public User findUserByCarModelAndSeries(String carModel, int carSeries) {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series", User.class);
      query.setParameter("model", carModel);
      query.setParameter("series", String.valueOf(carSeries));
      List<User> resultList = query.getResultList();
      if (!resultList.isEmpty()) {
         return resultList.get(0);
      }
      return null;
   }
}