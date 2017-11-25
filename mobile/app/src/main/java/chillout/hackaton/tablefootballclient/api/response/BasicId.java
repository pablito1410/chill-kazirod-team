package chillout.hackaton.tablefootballclient.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by matlo on 25.11.2017.
 */

public class BasicId {

    @SerializedName("id")
    @Expose
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
