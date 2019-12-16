package cn.xyy.ejb2;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="userlist")

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userid;

    @Column(name="username", length=50)
    private String username;

    @Column(name="userpwd", length=50)
    private String userpwd;

    @Column(name="age")
    private Integer age;

    @JoinColumn(name="departmentid")
    private Department department;

    public Integer getUserid() {
        return userid;
    }
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /*此类型与Department为“多对一关联”，通过@ManyToOne注解指名。通过departmentid字段中（外键）与Department相关联。 */

    @ManyToOne(cascade=CascadeType.ALL,optional=false)
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
}