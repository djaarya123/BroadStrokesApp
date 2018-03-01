package dhiraj.projectapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import dhiraj.projectapp.Models.SingleRowForUserList;
import dhiraj.projectapp.R;

/**
 * Created by Dhiraj on 28-02-2018.
 */

public class UserListRecyclerAdapter extends RecyclerView.Adapter<UserListRecyclerAdapter.MyViewHolder> {

    ArrayList<SingleRowForUserList> singleRowForUserLists;

    public UserListRecyclerAdapter(ArrayList<SingleRowForUserList> userList){
        this.singleRowForUserLists=userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_layout,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SingleRowForUserList row=singleRowForUserLists.get(position);
        holder.txtName.setText(row.tv_name);
        holder.txtCity.setText(row.tv_city);
    }

    @Override
    public int getItemCount() {
        if(singleRowForUserLists.size()>0){
            return singleRowForUserLists.size();
        }else {
            return 0;
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtCity;

        private MyViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.tvUserName);
            txtCity = (TextView) itemView.findViewById(R.id.tv_userCity);

        }
    }



}
