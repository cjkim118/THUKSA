package com.community.tsinghua;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.provider.Settings.Secure;
import static android.util.Log.i;


/**
 * Created by LG on 2015-11-28.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {

    // 이메일 중복확인 //
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> accountList;

    // url to get all products list
    private static String url_all_account = "http://1.doksuri3.sinaapp.com/DH/login.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ACCOUNT = "account";
    private static final String TAG_USER_EMAIL = "user_email";
    private static final String TAG_USER_STUID = "user_stuid";

    // products JSONArray
    JSONArray account = null;

    // 이메일 중복확인 //




    int check_register = 0; // 초기값 0
    private Button btnRegister;
    private TextView btnToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnToLogin = (TextView) findViewById(R.id.link_login);

        btnRegister.setOnClickListener(this);
        btnToLogin.setOnClickListener(this);
    }
    private void BackToLogin(){  //이미있어? 돌아가랏 클릭했을때 발생하는 함수
        Intent intent = new Intent(RegisterActivity.this,
                LoginActivity.class); // 액티비티넘어가는거 그냥바로 로그인화면으로돌아감
        startActivity(intent);
        finish();
    }
    private void Register(){      // 다입력하고 가입하기 눌렀을때 여기다가
        // 다른 처리들 추가시켜야함 isEmpty 나 뭐그런것들 중복처리
        check_register = 0;
        new LoadAllaccount().execute();
        new SendPost().execute();


    }



    //================SendPost()==================//

    private class SendPost extends AsyncTask<Void, Void, String> {


        protected String doInBackground(Void... unused) {
            String content = executeClient();
            return content;
        }



        // 실제 전송하는 부분
        public String executeClient() {

            //setContentView(R.layout.custom_dialog);
            /*get_device_id*/
            String user_device_id = Secure.getString(getContentResolver(), Secure.ANDROID_ID);


            /*timestamp*/
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String user_timestamp = formatter.format(date);

            ArrayList<NameValuePair> post = new ArrayList<NameValuePair>();
            EditText user_email = (EditText) findViewById(R.id.thu_email);
            EditText user_stuid = (EditText) findViewById(R.id.thu_stunum);
            EditText user_name = (EditText) findViewById(R.id.thu_name);
            EditText user_pw = (EditText) findViewById(R.id.thu_pw);
            //       EditText user_birth = (EditText) findViewById(R.id.user_birth);
            //       EditText user_major = (EditText) findViewById(R.id.user_major);
            //       EditText user_stuid = (EditText) findViewById(R.id.user_stuid);
            String user_input_email = user_email.getText().toString();
            String user_input_stuid = user_stuid.getText().toString();
            String user_input_name = user_name.getText().toString();
            String user_input_pw = user_pw.getText().toString();

            Log.d("처음 검증단계",Integer.toString(check_register));

            if (!isValidEmail(user_input_email))
            {
                check_register = 1; // 이메일 빈칸 혹은 이메일형식이 아닐 경우
            }
            else if (user_input_pw.equals(""))
            {
                check_register = 2; // 비밀번호 빈칸
            }
            else if (user_input_name.equals(""))
            {
                check_register = 3; // 이름 빈칸
            }
            else if (user_input_stuid.equals("") || (user_input_stuid.length()!=10))
            {
                check_register = 4; // 학번 빈칸 혹은 10자리가 아닐 경우
            }
            else if(check_register == 5 || check_register == 6) // 이메일 혹은 학번이 이미 존재할 경우
            {
                ;
            }
            else if(user_input_pw.length()<=7)
            {
                check_register = 7; // 비밀번호는 8자리 이상
            }
            else // 모든게 정상적으로 입력했을 경우, 즉 check_register은 여전히 초기값 0
            {

                post.add(new BasicNameValuePair("user_email", user_email.getText().toString()));
                post.add(new BasicNameValuePair("user_stuid", user_stuid.getText().toString()));
                post.add(new BasicNameValuePair("user_name", user_name.getText().toString()));
                post.add(new BasicNameValuePair("user_pw", user_pw.getText().toString()));
                //       post.add(new BasicNameValuePair("user_device_id", user_device_id));
                //       post.add(new BasicNameValuePair("user_birth", user_birth.getText().toString()));
                //       post.add(new BasicNameValuePair("user_major", user_major.getText().toString()));
                //       post.add(new BasicNameValuePair("user_timestamp", user_timestamp));
                //       post.add(new BasicNameValuePair("user_grade", "1"));

                // 연결 HttpClient 객체 생성
                HttpClient client = new DefaultHttpClient();

                // 객체 연결 설정 부분, 연결 최대시간 등등
                HttpParams params = client.getParams();
                HttpConnectionParams.setConnectionTimeout(params, 5000);
                HttpConnectionParams.setSoTimeout(params, 5000);

                // Post객체 생성
                HttpPost httpPost = new HttpPost("http://1.doksuri3.sinaapp.com/DH/register.php");

                try {
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(post, "UTF-8");
                    httpPost.setEntity(entity);
                    client.execute(httpPost);
                    return EntityUtils.getContentCharSet(entity);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        protected void onPostExecute(String result) {
            // 모두 작업을 마치고 실행할 일 (메소드 등등)
            Log.d("모든 작업을 마침",Integer.toString(check_register));
            if(check_register == 1) {
                Toast.makeText(getApplicationContext(), "올바른 이메일을 입력해주세요!", Toast.LENGTH_LONG).show();
            }
            else if(check_register == 2) {
                Toast.makeText(getApplicationContext(), "올바른 비밀번호를 입력해주세요!", Toast.LENGTH_LONG).show();
            }
            else if(check_register == 3) {
                Toast.makeText(getApplicationContext(), "올바른 이름을 입력해주세요!", Toast.LENGTH_LONG).show();
            }
            else if(check_register == 4) {
                Toast.makeText(getApplicationContext(), "올바른 학번을 입력해주세요!", Toast.LENGTH_LONG).show();
            }
            else if(check_register == 5) {
                Toast.makeText(getApplicationContext(), "이미 존재하는 이메일입니다!", Toast.LENGTH_LONG).show();
            }
            else if(check_register == 6){
                Toast.makeText(getApplicationContext(), "이미 존재하는 학번입니다!", Toast.LENGTH_LONG).show();
            }
            else if(check_register == 7){
                Toast.makeText(getApplicationContext(), "8자리이상의 비밀번호를 입력해주세요!", Toast.LENGTH_LONG).show();
            }
            else { //올바르게 입력했을 때, 즉 register -> login 페이지로 이동
                Toast.makeText(getApplicationContext(), "회원가입 성공!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this,
                        LoginActivity.class); // 액티비티넘어가는거
                startActivity(intent);
                finish();
            }
        }
    }

    //=================SendPost().End===================//



    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.link_login:
                BackToLogin();
                break;
            case R.id.btnRegister:
                Register();
            default:
        }
    }



    // 이메일 중복확인 //
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
            //Log.d("All account : ",json.toString());

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
                        String user_stuid = c.getString(TAG_USER_STUID);
                        EditText user_input_email = (EditText) findViewById(R.id.thu_email);
                        EditText user_input_stuid = (EditText) findViewById(R.id.thu_stunum);
                        String user_input_email2 = user_input_email.getText().toString();
                        String user_input_stuid2 = user_input_stuid.getText().toString();

                        Log.d("이메일,학번 중복단계",Integer.toString(check_register));
                        //Log.d("쓴 메일",user_input_email);
                        //Log.d("쓴 학번",user_input_stuid);
                        if(user_email.equals(user_input_email2)) // compare str1 , str2
                        {
                            check_register = 5; // 이미 존재하는 이메일
                            break;
                        }

                        else if(user_stuid.equals(user_input_stuid2))
                        {
                            check_register = 6; // 이미 존재하는 학번
                            break;
                        }
                        else
                        {
                            check_register = 0; // 이메일 및 학번 중복확인 성공
                        }

                        /*
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_USER_EMAIL, user_email);
                        map.put(TAG_USER_STUID, user_stuid);

                        // adding HashList to ArrayList
                        accountList.add(map);
                        */
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

            Log.d("마지막 뭐로 빠지나", Integer.toString(check_register));

            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                }
            });

        }

    }

    // 이메일 중복확인 //

}


