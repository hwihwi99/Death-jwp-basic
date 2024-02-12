package next.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import next.model.User;

import java.util.List;

public class UserDaoTest {

    @Test
    public void crud() throws Exception {
        User expected = new User("userId", "password", "name", "javajigi@email.com");
        UserDao userDao = new UserDao();
        userDao.insert(expected);

//        User own = userDao.findByUserId("userId");
//        System.out.println(own.getUserId());
//        System.out.println(own.getPassword());
//        System.out.println(own.getName());
//        System.out.println(own.getEmail());

        List<User> user = userDao.findAll();
        for (User u : user) {
            System.out.println(u.getUserId());
        }

        User actual = userDao.findByUserId(expected.getUserId());
        assertEquals(expected, actual);
    }

}
