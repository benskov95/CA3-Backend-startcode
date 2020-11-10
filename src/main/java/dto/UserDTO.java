package dto;

import entities.Role;
import entities.User;

import java.util.List;

public class UserDTO {

    private String userName;
    private List<String> roles;

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.roles = user.getRolesAsStrings();
    }
}
