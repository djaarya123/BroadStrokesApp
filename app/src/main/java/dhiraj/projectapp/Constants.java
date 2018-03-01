package dhiraj.projectapp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.Toast;

/**
 * Created by Dhiraj on 01-03-2018.
 */

public class Constants {

    public static final String USER_ID=null;
    public static boolean IS_NAME_CHANGED=false;
    public static boolean IS_AGE_CHANGED=false;
    public static boolean IS_CITY_CHANGED=false;
    public static boolean IS_STATE_CHANGED=false;
    public static boolean IS_COUNTRY_CHANGED=false;


    public static void showShortToastMsg(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    public static void showLongToastMsg(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }


    public static TextWatcher NameWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            IS_NAME_CHANGED=true;
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public static TextWatcher AgeWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            IS_AGE_CHANGED=true;
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public static TextWatcher CityWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            IS_CITY_CHANGED=true;
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public static TextWatcher StateWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            IS_STATE_CHANGED=true;
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public static TextWatcher CountryWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            IS_COUNTRY_CHANGED=true;
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
