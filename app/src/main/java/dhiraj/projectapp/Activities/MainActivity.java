package dhiraj.projectapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import dhiraj.projectapp.Models.SingleRowForUserList;
import dhiraj.projectapp.Models.User;
import dhiraj.projectapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName,etAge,etCity,etState,etCountry;
    Toolbar toolbar;
    Button btnSubmit;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.userList:
                startActivity(new Intent(MainActivity.this,UserListActivity.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                break;
        }
        return false;
    }

    private void initialize() {
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add User");
        toolbar.setTitleTextColor(Color.WHITE);

        etName=(EditText)findViewById(R.id.etName);
        etAge=(EditText)findViewById(R.id.etAge);
        etCity=(EditText)findViewById(R.id.etCity);
        etState=(EditText)findViewById(R.id.etState);
        etCountry=(EditText)findViewById(R.id.etCountry);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);

        firebaseDatabase=FirebaseDatabase.getInstance();
        userDatabase=firebaseDatabase.getReference("user");

        btnSubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmit:
                if(isValidEntry()){

                    addUserToDataBase(etName.getText().toString().trim(),etAge.getText().toString().trim(),etCity.getText().toString().trim(),
                            etState.getText().toString().trim(),etCountry.getText().toString().trim());
                }
                break;
        }
    }

    private void addUserToDataBase(String name, String age, String city, String state, String country) {
        String completeAddress=city+","+state+","+country;
        User user=new User(name,age,completeAddress);
        userDatabase.child(String.valueOf(System.currentTimeMillis())).setValue(user);
        startActivity(new Intent(MainActivity.this,UserListActivity.class));
        etName.setText("");etAge.setText("");etCity.setText("");etState.setText("");etCountry.setText("");
    }

    public boolean isValidEntry() {
        if(isValidName() && isValidAge() && isValidCity() && isValidState() && isValidCountry()){
            return true;
        }else{
            return false;
        }
    }

    public boolean isValidName(){
        String name=etName.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(MainActivity.this,"Please provide your name !",Toast.LENGTH_SHORT).show();
            return false;
        }else
            return true;
    }

    public boolean isValidAge(){
        String age=etAge.getText().toString().trim();
        if(TextUtils.isEmpty(age)){
            Toast.makeText(MainActivity.this,"Please provide your age !",Toast.LENGTH_SHORT).show();
            return false;
        }else
            return true;
    }

    public boolean isValidCity(){
        String city=etCity.getText().toString().trim();
        if(TextUtils.isEmpty(city)){
            Toast.makeText(MainActivity.this,"Please provide your city !",Toast.LENGTH_SHORT).show();
            return false;
        }else
            return true;
    }

    public boolean isValidState(){
        String state=etState.getText().toString().trim();
        if(TextUtils.isEmpty(state)   ){
            Toast.makeText(MainActivity.this,"Please provide your state !",Toast.LENGTH_SHORT).show();
            return false;
        }else
            return true;
    }

    public boolean isValidCountry(){
        String country=etCountry.getText().toString().trim();
        if(TextUtils.isEmpty(country)   ){
            Toast.makeText(MainActivity.this,"Please provide your country !",Toast.LENGTH_SHORT).show();
            return false;
        }else
            return true;
    }

}
