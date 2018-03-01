package dhiraj.projectapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import dhiraj.projectapp.Adapters.UserListRecyclerAdapter;
import dhiraj.projectapp.Constants;
import dhiraj.projectapp.Listeners.RecyclerItemTouchListener;
import dhiraj.projectapp.Models.SingleRowForUserList;
import dhiraj.projectapp.R;

public class UserListActivity extends AppCompatActivity {

    RelativeLayout layoutForUserList;
    Toolbar toolbarAtUserList;
    RecyclerView recyclerList;
    UserListRecyclerAdapter userListRecyclerAdapter;
    ProgressBar progressBarInList;
    FirebaseDatabase firebaseDatabase;
    SwipeRefreshLayout refreshList;
    DatabaseReference userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        initialize();

        refreshList.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                            getData();

                    }
                }
        );
    }

    private void initialize() {

        layoutForUserList=(RelativeLayout)findViewById(R.id.layoutForUserList);
        toolbarAtUserList=(Toolbar)findViewById(R.id.toolbarAtUserList);
        recyclerList=(RecyclerView)findViewById(R.id.recyclerList);
        progressBarInList=(ProgressBar)findViewById(R.id.progressBarInList);
        setSupportActionBar(toolbarAtUserList);
        getSupportActionBar().setTitle("User List");
        toolbarAtUserList.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        refreshList=(SwipeRefreshLayout)findViewById(R.id.refreshList);

        firebaseDatabase=FirebaseDatabase.getInstance();
        userDatabase=firebaseDatabase.getReference("user");

        getData(); // network call to get remote data


    }


    private void collectAllUsers(Map<String, Object> value) {
        ArrayList<SingleRowForUserList> userList = new ArrayList<>();
        ArrayList<String> keys=new ArrayList<>();
        for (Map.Entry<String, Object> entry : value.entrySet()){
            Map singleUser = (Map) entry.getValue();
            keys.add(entry.getKey());
            userList.add(new SingleRowForUserList(singleUser.get("name").toString(),singleUser.get("city").toString()));
        }
        FillRecyclerList(userList,keys);
    }

    private void FillRecyclerList(final ArrayList<SingleRowForUserList> userList, final ArrayList<String> keys) {
        userListRecyclerAdapter=new UserListRecyclerAdapter(userList);
        recyclerList.setAdapter(userListRecyclerAdapter);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));
        recyclerList.addOnItemTouchListener(new RecyclerItemTouchListener(this, recyclerList, new RecyclerItemTouchListener.RecyclerTouchListener() {
            @Override
            public void onClickItem(View v, int position) {
                Intent intent=new Intent(UserListActivity.this,UpdateUserActivity.class);
                intent.putExtra(Constants.USER_ID,keys.get(position));
                startActivity(intent);
            }
        }));

        userListRecyclerAdapter.notifyDataSetChanged();
        if(refreshList.isRefreshing()){
            refreshList.setRefreshing(false);
            Constants.showShortToastMsg(UserListActivity.this,"List Refreshed !");
        }
        layoutForUserList.setVisibility(View.VISIBLE);
        progressBarInList.setVisibility(View.GONE);
    }


    public void getData() {

        userDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                collectAllUsers((Map<String,Object>) dataSnapshot.getValue());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(R.anim.slide_back_in,R.anim.slide_back_out);
        return true;
    }




}
