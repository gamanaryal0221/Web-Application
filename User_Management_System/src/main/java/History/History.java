/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package History;

/**
 *
 * @author pguragain3
 */
public class History {
    private String username;
    private String time;
    private String action;

    public History(String username, String time, String action) {
        this.username = username;
        this.time = time;
        this.action= action;
    }
      public History( String time, String action) {
        this.time = time;
        this.action= action;
    }

    public History() {
    }
      

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username= username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    
}
