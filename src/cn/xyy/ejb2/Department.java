package cn.xyy.ejb2;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "department")

public class Department implements Serializable {
    private Integer departmentid;
    private String departmentname;
    private Set<User> users=new HashSet<User>();

    public Department() { }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getDepartmentid() {
        return departmentid;
    }
    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    @Column(name="departmentname")
    public String getDepartmentname() {
        return departmentname;
    }
    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    /*Department和User为一对多关系，而且当前类型对应的是User的Department数据成员	且实现所有的级联操作，并且加载集合中的数据（子项)为延迟加载*/
    @OneToMany(mappedBy="department",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @OrderBy(value="userid ASC")//加入到集合中的User对象按照userid排序
    public Set<User> getUsers() {
        return users;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    //增加两个方法，作用为向users集合中添加和删除User对象
    public void addNewUser(User user){
        if(!this.users.contains(user)){
            this.users.add(user);
            user.setDepartment(this);
        }
    }
    public void removeUser(User user){
        user.setDepartment(null);
        this.users.remove(user);
    }
}
