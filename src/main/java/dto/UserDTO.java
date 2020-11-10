package dto;

import entities.Role;
import entities.User;

import java.util.List;

public class UserDTO {

    private String userName;
    private List<String> roles;
    private String password;


    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.roles = user.getRolesAsStrings();
    }

    public UserDTO(){}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
