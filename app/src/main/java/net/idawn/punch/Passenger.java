package net.idawn.punch;

/**
 * Created by xixi on 2014/7/28.
 */
public class Passenger {

    private int id;
    private String name;
    private String tel;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }
}
