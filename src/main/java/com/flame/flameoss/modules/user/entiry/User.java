package com.flame.flameoss.modules.user.entiry;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_user", schema = "flameoss", catalog = "")
public class User implements Serializable {
    private static final long serialVersionUID = 6748768797545261371L;
    private long userId;
    private String userAccount;
    private String userPass;
    private String userNickname;
    private String userEmail;
    private String userUrl;
    private int userStatus;
    private String displayName;
    private Timestamp createTime;

    @Id
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_account", nullable = false, length = 60)
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Basic
    @JsonIgnore
    @Column(name = "user_pass", nullable = false, length = 255)
    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Basic
    @Column(name = "user_nickname", nullable = false, length = 50)
    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    @Basic
    @Column(name = "user_email", nullable = false, length = 100)
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "user_url", nullable = false, length = 100)
    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    @Basic
    @Column(name = "user_status", nullable = false)
    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    @Basic
    @Column(name = "display_name", nullable = false, length = 250)
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                userStatus == user.userStatus &&
                Objects.equals(userAccount, user.userAccount) &&
                Objects.equals(userPass, user.userPass) &&
                Objects.equals(userNickname, user.userNickname) &&
                Objects.equals(userEmail, user.userEmail) &&
                Objects.equals(userUrl, user.userUrl) &&
                Objects.equals(displayName, user.displayName) &&
                Objects.equals(createTime, user.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, userAccount, userPass, userNickname, userEmail, userUrl, userStatus, displayName, createTime);
    }
}
