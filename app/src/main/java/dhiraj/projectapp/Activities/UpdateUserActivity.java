package dhiraj.projectapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Map;
import dhiraj.projectapp.Constants;
import dhiraj.projectapp.Models.User;
import dhiraj.projectapp.R;

public class UpdateUserActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout linearLayoutAtUpdateUser;
    ProgressBar progressBarInUserUpdate;
    String user_id=null;
    Toolbar toolbar;
    User userData;

    boolean isUpdateRequired=false;
    FloatingActionButton btnUpdate,btnDone;
    EditText etName,etAge,etCity,etState,etCountry;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        initialize();

    }

    private void initialize() {
        toolbar=(Toolbar)findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("User Data");
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        linearLayoutAtUpdateUser=(LinearLayout) findViewById(R.id.linearLayoutAtUpdateUser);
        progressBarInUserUpdate=(ProgressBar)findViewById(R.id.progressBarInUserUpdate);
        btnUpdate=(FloatingActionButton)findViewById(R.id.btnUpdate);
        btnDone=(FloatingActionButton)findViewById(R.id.btnDone);


        etName=(EditText) findViewById(R.id.etName);
        etAge=(EditText) findViewById(R.id.etAge);
        etCity=(EditText) findViewById(R.id.etCity);
        etState=(EditText) findViewById(R.id.etState);
        etCountry=(EditText) findViewById(R.id.etCountry);

        etName.addTextChangedListener(Constants.NameWatcher);
        etAge.addTextChangedListener(Constants.AgeWatcher);
        etCity.addTextChangedListener(Constants.CityWatcher);
        etState.addTextChangedListener(Constants.StateWatcher);
        etCountry.addTextChangedListener(Constants.CountryWatcher);

        btnUpdate.setOnClickListener(this);
        btnDone.setOnClickListener(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        userDatabase=firebaseDatabase.getReference("user");

        Intent intent=getIntent();
        user_id=intent.getStringExtra(Constants.USER_ID);
        if(user_id!=null){
            // call api to get data related to this user..
            getUserDataById(user_id);
        }else{
            startActivity(new Intent(UpdateUserActivity.this,UserListActivity.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            finish();
        }

    }

    private void getUserDataById(String user_id) {
        userDatabase.child(user_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map singleUser = (Map) dataSnapshot.getValue();
                String address=singleUser.get("city").toString()+","+singleUser.get("state").toString()+","+singleUser.get("country").toString();
                User user=new User(singleUser.get("name").toString(),singleUser.get("age").toString(),address);
                userData =user;
                filldata(userData);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void filldata(User userData) {
        if(userData!=null){
            etName.setText(userData.getName());
            etAge.setText(userData.getAge());
            etCity.setText(userData.getCity());
            etState.setText(userData.getState());
            etCountry.setText(userData.getCountry());
        }

        etName.setEnabled(false);
        etAge.setEnabled(false);
        etCity.setEnabled(false);
        etState.setEnabled(false);
        etCountry.setEnabled(false);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUpdate:
                isUpdateRequired=true;
                makeEditTextEnable(isUpdateRequired);
                makeUpdate();
                break;
            case R.id.btnDone:
                isUpdateRequired=false;
                makeEditTextEnable(isUpdateRequired);
                makeUpdate();
                if(Constants.IS_NAME_CHANGED || Constants.IS_AGE_CHANGED || Constants.IS_CITY_CHANGED || Constants.IS_STATE_CHANGED
                        || Constants.IS_COUNTRY_CHANGED){
                    updateUserData();
                }else{
                    makeEditTextEnable(false);
                }
                break;
        }
    }

    private void makeUpdate() {
        if(isUpdateRequired){
            btnUpdate.setVisibility(View.GONE);
            btnDone.setVisibility(View.VISIBLE);
        }else{
            btnUpdate.setVisibility(View.VISIBLE);
            btnDone.setVisibility(View.GONE);
        }
    }

    private void updateUserData() {
        Map<String,Object> updateUserMap = new HashMap<String,Object>();
        updateUserMap.put("name", etName.getText().toString().trim());
        updateUserMap.put("age", etAge.getText().toString().trim());
        updateUserMap.put("city", etCity.getText().toString().trim());
        updateUserMap.put("state", etState.getText().toString().trim());
        updateUserMap.put("country", etCountry.getText().toString().trim());
        userDatabase.child(user_id).updateChildren(updateUserMap);
        Constants.showShortToastMsg(this,"Updated");
        makeEditTextEnable(false);
    }

    public void makeEditTextEnable(boolean value){
        etName.setEnabled(value);
        etAge.setEnabled(value);
        etCity.setEnabled(value);
        etState.setEnabled(value);
        etCountry.setEnabled(value);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(R.anim.slide_back_in,R.anim.slide_back_out);
        return true;
    }

}
