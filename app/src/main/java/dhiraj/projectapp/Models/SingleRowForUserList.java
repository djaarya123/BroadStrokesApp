package dhiraj.projectapp.Models;

import android.widget.TextView;

/**
 * Created by Dhiraj on 28-02-2018.
 */

public class SingleRowForUserList {

    public String tv_name,tv_city;


    public SingleRowForUserList(String name,String city){
        this.tv_name=name;
        this.tv_city=city;
    }


    public String getTv_name() {
        return tv_name;
    }

    public String getTv_city() {
        return tv_city;
    }
}
