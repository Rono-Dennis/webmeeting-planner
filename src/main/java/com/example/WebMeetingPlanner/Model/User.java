package com.example.WebMeetingPlanner.Model;



import javax.persistence.*;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;
import java.util.*;

import lombok.Data;
import lombok.ToString;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @Email(message = "{fooCommand.email.email.message}")
    @Email
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @NotBlank(message = "Password is required")
    @Column(nullable = false, length = 64)
    private String password;
    @NotBlank(message = "firstname is required")
    @Column(nullable = false, length = 20)
    private String firstName;
    @NotBlank(message = "lastname is required")
    @Column(nullable = false, length = 20)
    private String lastName;

    @NotBlank(message = "PhoneNumber is required")
    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "reset_password_token", length = 255)
    private String resetPasswordToken;

    @Column(name = "set_password_token", length = 255)
    private String setPasswordToken;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "failed_attempt")
    private int failedAttempt;

    @Column(name = "lock_time")
    private Date lockTime;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organization organization;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )



    private Set<Role> roles = new HashSet<>();

//     = new HashSet<>()
//    fetch = FetchType.LAZY, cascade = CascadeType.ALL,
//    (mappedBy = "users", fetch=FetchType.EAGER, cascade=CascadeType.PERSIST/)
//    , fetch=FetchType.EAGER, cascade=CascadeType.PERSIST
    @ManyToMany(mappedBy = "users")
    private List<Scheduling> schedulings;

    public User() {
    }

    public User(long id, String email, String password, String firstName, String lastName, String phoneNumber, String resetPasswordToken, String setPasswordToken, boolean accountNonLocked, int failedAttempt, Date lockTime, boolean enabled, Organization organization, Set<Role> roles, List<Scheduling> schedulings) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.resetPasswordToken = resetPasswordToken;
        this.setPasswordToken = setPasswordToken;
        this.accountNonLocked = accountNonLocked;
        this.failedAttempt = failedAttempt;
        this.lockTime = lockTime;
        this.enabled = enabled;
        this.organization = organization;
        this.roles = roles;
        this.schedulings = schedulings;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", resetPasswordToken='" + resetPasswordToken + '\'' +
                ", setPasswordToken='" + setPasswordToken + '\'' +
                ", accountNonLocked=" + accountNonLocked +
                ", failedAttempt=" + failedAttempt +
                ", lockTime=" + lockTime +
                ", enabled=" + enabled +
                ", organization=" + organization +
                ", roles=" + roles +
                ", schedulings=" + schedulings +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getSetPasswordToken() {
        return setPasswordToken;
    }

    public void setSetPasswordToken(String setPasswordToken) {
        this.setPasswordToken = setPasswordToken;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Scheduling> getSchedulings() {
        return schedulings;
    }

    public void setSchedulings(List<Scheduling> schedulings) {
        this.schedulings = schedulings;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public int getFailedAttempt() {
        return failedAttempt;
    }

    public void setFailedAttempt(int failedAttempt) {
        this.failedAttempt = failedAttempt;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}