package com.community.tsinghua;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.community.tsinghua.helper.PrefManager;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LG on 2015-11-24.
 */
public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText inputEmail;
    private Button btnLogin;
    private PrefManager pref;

    int check_login = 0; // 초기값 0;

    /////////////// 오늘추가


    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> accountList;

    // url to get all products list
    private static String url_all_account = "http://1.doksuri3.sinaapp.com/DH/login.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ACCOUNT = "account";
    private static final String TAG_USER_EMAIL = "user_email";
    private static final String TAG_USER_NAME = "user_name";
    private static final String TAG_USER_PW = "user_pw";
    private static final String TAG_USER_STUID = "user_stuid";

    // products JSONArray
    JSONArray account = null;

    /////////////////////////까지////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        TextView Register = (TextView) findViewById(R.id.link_signup);
        Register.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent_register = new Intent(LoginActivity.this,RegisterActivity.class); // 액티비티넘어가는거
                startActivity(intent_register);
                finish();

            }
        });




        pref = new PrefManager(getApplicationContext());
        if (pref.isLoggedIn()) { // 자동로그인 부분 //

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
        }


        Button login_button = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        login_button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v)
            {
                inputEmail = (EditText) findViewById(R.id.thu_id);
                login();
            }

        });


    }
    private void login() {
        String email = inputEmail.getText().toString();

        if (isValidEmail(email))
        {
            // Hashmap for ListView
            Log.d("TAG","login()->isValidEmail()");
            accountList = new ArrayList<HashMap<String, String>>();

            // Loading login in Background Thread
            new LoadAllaccount().execute();

        }

        else
        {
            Toast.makeText(getApplicationContext(), "올바른 이메일을 입력해주세요!", Toast.LENGTH_LONG).show();
        }

    }






    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllaccount extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("로그인 중 입니다...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_account, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All account : ",json.toString());

            try {

                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {

                    // products found
                    // Getting Array of Products
                    account = json.getJSONArray(TAG_ACCOUNT);

                    // looping through All Products
                    for (int i = 0; i < account.length(); i++) {
                        JSONObject c = account.getJSONObject(i);

                        // Storing each json item in variable
                        String user_email = c.getString(TAG_USER_EMAIL);
                        String user_name = c.getString(TAG_USER_NAME);
                        String user_pw = c.getString(TAG_USER_PW);
                        String user_stuid = c.getString(TAG_USER_STUID);

                        EditText user_input_email = (EditText) findViewById(R.id.thu_id);
                        EditText user_input_password = (EditText) findViewById(R.id.thu_pw);

                        String user_input_id = user_input_email.getText().toString();
                        String user_input_pw = user_input_password.getText().toString();

                        pwdMD5 MD5 = new pwdMD5();

                        user_input_pw = MD5.pwdMD5(user_input_pw);
                        //Log.d("TAG",user_input_pw);

                        Log.d("TAG",user_input_id);
                        if(user_email.equals(user_input_id)) // compare str1 , str2
                        {
                            if(user_pw.equals(user_input_pw)) {
                                Log.d("진입", "성공");
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                pref.createLoginSession(user_input_id); // 저 주소는 edittext에서 입력한 값임
                                check_login = 1; // 로그인 성공
                                // finish();
                            }

                            else
                            {

                                if(user_input_pw.equals(MD5.pwdMD5("")))
                                {
                                    check_login = 2; // 비밀번호 칸이 빈 상태, 로그인 실패
                                }

                                else
                                {
                                    check_login = 3; // 비밀번호가 다름, 로그인 실패
                                }
                            }

                            break;
                        }

                        else
                        {
                            if(user_input_pw.equals(MD5.pwdMD5("")))
                            {
                                check_login = 2; // 비밀번호 칸이 빈 상태 로그인 실패
                                break;
                            }
                            else
                            {
                                check_login = 4; // 잘못된 이메일 로그인 실패
                            }
                        }

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_USER_EMAIL, user_email);
                        map.put(TAG_USER_NAME, user_name);
                        map.put(TAG_USER_PW, user_pw);
                        map.put(TAG_USER_STUID, user_stuid);

                        // adding HashList to ArrayList
                        accountList.add(map);
                    }


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            Log.d("CHECK", Integer.toString(check_login));
            if(check_login == 1) // 로그인 정보 통과
            {
                finish();
            }
            else {
                if (check_login == 2)
                {
                    Toast.makeText(getApplicationContext(), "올바른 비밀번호를 입력해주세요!", Toast.LENGTH_LONG).show();
                }
                else if (check_login == 3)// 이메일 혹은 비밀번호 틀림, 따라서 통과 못함
                {
                    Toast.makeText(getApplicationContext(), "잘못된 비밀번호입니다!", Toast.LENGTH_LONG).show();
                }
                else if (check_login == 4 || check_login == 0)
                {
                    Toast.makeText(getApplicationContext(), "잘못된 이메일입니다!", Toast.LENGTH_LONG).show();
                }

            }
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                }
            });

        }

    }



    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLinkToRegisterScreen:
                login();
                break;
            default:
        }
    }
}
