package model;

import java.util.Objects;

/**
 * A model class that represents a user in CarMechanic Web App
 * @author Alexander
 */
public class CMUser {
    
    /* * * * Attributes * * * */
    private int user_id;
    private String username;
    private String uname;
    private String password;
    private String email;
    private int role;
    private String roleDescription;
    private String accountType;
    private boolean notificationsOn;
    private String misc;
    private String birthDate;

    public CMUser(int user_id, String username, String uname, String password, String email, int role, String accountType, String misc, String birthDate) {
        this.user_id = user_id;
        this.username = username;
        this.uname = uname;
        this.password = password;
        this.email = email;
        this.role = role;
        this.accountType = accountType;
        this.misc = misc;
        this.birthDate = birthDate;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getUname() {
        return uname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public String getAccountType() {
        return accountType;
    }

    public boolean isNotificationsOn() {
        return notificationsOn;
    }

    public String getMisc() {
        return misc;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setNotificationsOn(boolean notificationsOn) {
        this.notificationsOn = notificationsOn;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "CMUser{" + "user_id=" + user_id + ", username=" + username + ", uname=" + uname + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.uname);
        hash = 53 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CMUser other = (CMUser) obj;
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }
    
    
    
}
