package chillout.hackaton.tablefootballclient.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by matlo on 25.11.2017.
 */

public class BasicUser {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("userName")
    @Expose
    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}