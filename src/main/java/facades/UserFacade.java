package facades;

import dto.UserDTO;
import entities.User;

import javax.enterprise.inject.Typed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import security.errorhandling.AuthenticationException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public List<UserDTO> getAllUsers (){

        EntityManager em = emf.createEntityManager();

        try{
           TypedQuery query = em.createQuery("SELECT u from User u", User.class);
            List<User> userList = query.getResultList();
            List<UserDTO> userDTOlist = new ArrayList<>();

            for (User user: userList){
                userDTOlist.add(new UserDTO(user));
            }

            return userDTOlist;

        }finally {
            em.close();
        }

    }

}
