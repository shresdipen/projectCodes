package edu.mum.waa545.project.dbiimpl;

import edu.mum.waa545.project.db.UserRepository;
import edu.mum.waa545.project.model.Address;
import edu.mum.waa545.project.model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tsegaab
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private List<User> ourusers = new ArrayList();

    //initialize our users in the constructor we no use db
    public UserRepositoryImpl() {
        System.out.println("Constructor called......................");
        ourusers.add(new User("username1", "password1", "firstname1", "lastname1", 0, new Date(), "gender1",
                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
        ourusers.add(new User("username2", "password2", "firstname2", "lastname2", 0, new Date(), "gender2",
                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
        ourusers.add(new User("vic", "password3", "firstname3", "lastname3", 0, new Date(), "gender3",
                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username4", "password4", "firstname4", "lastname4", 0, new Date(), "gender4",
//                new Address("US", "IA", "MUM Highschool", "Trump college", "US")));
//        ourusers.add(new User("username5", "password5", "firstname5", "lastname5", 0, new Date(), "gender5",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username6", "password6", "helen", "lastname6", 0, new Date(), "gender6",
//                new Address("US", "IA", "Chicago Highschool", "Trump college", "US")));
//        ourusers.add(new User("username7", "password7", "firstname7", "lastname7", 0, new Date(), "gender7",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username8", "password8", "firstname8", "lastname8", 0, new Date(), "gender8",
//                new Address("US", "IA", "Highschool", "Trump college", "US")));
//        ourusers.add(new User("username9", "password9", "helen", "lastname9", 0, new Date(), "gender9",
//                new Address("US", "IA", "Highschool", "Trump college", "US")));
//        ourusers.add(new User("username10", "password10", "firstname10", "lastname10", 0, new Date(), "gender10",
//                new Address("US", "IA", "Linn Highschool", "Trump college", "US")));
//
//        ourusers.add(new User("username5", "password5", "firstname5", "lastname5", 0, new Date(), "gender5",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username6", "password6", "firstname6", "lastname6", 0, new Date(), "gender6",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username7", "password7", "firstname7", "lastname7", 0, new Date(), "gender7",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username8", "password8", "firstname8", "lastname8", 0, new Date(), "gender8",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username9", "password9", "firstname9", "lastname9", 0, new Date(), "gender9",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username10", "password10", "firstname10", "lastname10", 0, new Date(), "gender10",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        
//        ourusers.add(new User("username2", "password2", "firstname2", "lastname2", 0, new Date(), "gender2",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username3", "password3", "firstname3", "lastname3", 0, new Date(), "gender3",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username4", "password4", "firstname4", "lastname4", 0, new Date(), "gender4",
//                new Address("US", "IA", "MUM Highschool", "Trump college", "US")));
//        ourusers.add(new User("username5", "password5", "firstname5", "lastname5", 0, new Date(), "gender5",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username6", "password6", "helen", "lastname6", 0, new Date(), "gender6",
//                new Address("US", "IA", "Chicago Highschool", "Trump college", "US")));
//        ourusers.add(new User("username7", "password7", "firstname7", "lastname7", 0, new Date(), "gender7",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username8", "password8", "firstname8", "lastname8", 0, new Date(), "gender8",
//                new Address("US", "IA", "Highschool", "Trump college", "US")));
//        ourusers.add(new User("username9", "password9", "helen", "lastname9", 0, new Date(), "gender9",
//                new Address("US", "IA", "Highschool", "Trump college", "US")));
//        ourusers.add(new User("username10", "password10", "firstname10", "lastname10", 0, new Date(), "gender10",
//                new Address("US", "IA", "Linn Highschool", "Trump college", "US")));
//
//        ourusers.add(new User("username5", "password5", "firstname5", "lastname5", 0, new Date(), "gender5",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username6", "password6", "firstname6", "lastname6", 0, new Date(), "gender6",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username7", "password7", "firstname7", "lastname7", 0, new Date(), "gender7",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username8", "password8", "firstname8", "lastname8", 0, new Date(), "gender8",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username9", "password9", "firstname9", "lastname9", 0, new Date(), "gender9",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        ourusers.add(new User("username10", "password10", "firstname10", "lastname10", 0, new Date(), "gender10",
//                new Address("US", "IA", "Lincon Highschool", "Trump college", "US")));
//        

    }

    @Override
    public List<User> getAllusers() {
        return ourusers;
    }


    @Override
    public void save(User user) {
        ourusers.add(user);
        System.out.println("User: "+user);
        System.out.println("Users: "+ourusers);
    }

    @Override
    public User findUserByName(String username) {
        System.out.println("Users from find: "+ourusers);
        for (User user : ourusers) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
             
        }
        return null;
    }

}
