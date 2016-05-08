package edu.moli9479csumb.otterairways;

/**
 * Created by joemoe on 5/7/16.
 */
public class Users {

    private int id;
    private String username;
    private String password;


    public Users(){

    }
    //overloaded constructor.
    public Users(String username, String password){
        this.username = username;
        this.password = password;
    }
/****************** Getters  ********************/
    public int getId() {return id;}
    public String getUserName(){return username;}
    public String getPassword(){return password;}

/******************* Setters ********************/
    public void setId(int id){
        this.id = id;
    }

    public void setUserName(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString(){
        return "User[id=" + id + ",username=" + username + ",password=" + password + "]";
    }
}
