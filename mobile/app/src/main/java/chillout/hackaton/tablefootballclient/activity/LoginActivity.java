package chillout.hackaton.tablefootballclient.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;


import android.os.Bundle;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.IOException;
import java.util.List;

import chillout.hackaton.tablefootballclient.R;
import chillout.hackaton.tablefootballclient.TFCApplication;
import chillout.hackaton.tablefootballclient.api.ApiClient;
import chillout.hackaton.tablefootballclient.api.TableFootballService;
import chillout.hackaton.tablefootballclient.api.request.LoginUserRequest;
import chillout.hackaton.tablefootballclient.api.request.RegisterUserRequest;
import chillout.hackaton.tablefootballclient.api.response.BasicId;
import chillout.hackaton.tablefootballclient.api.response.BasicUser;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity{

    private EditText mNicknameView;
    private EditText mPasswordView;
    private View mProgressView;

    private LoginOrRegisterUserAsyncTask mAuthTask = null;

    private View mLoginFormView;

    private Boolean isRegister = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mNicknameView = (EditText) findViewById(R.id.nickname);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isRegister = false;
                attemptLogin();
            }
        });

        Button mSignUpButton = (Button) findViewById(R.id.sign_up_button);
        mSignUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isRegister = true;
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }
        mNicknameView.setError(null);
        mPasswordView.setError(null);

        String nickname = mNicknameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)){
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid nickname.
        if (TextUtils.isEmpty(nickname)) {
            mNicknameView.setError(getString(R.string.error_field_required));
            focusView = mNicknameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            mAuthTask = new LoginOrRegisterUserAsyncTask(nickname, password);
            mAuthTask.execute((Void) null);
        }
    }

    private void showProgress(final boolean show) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public class LoginOrRegisterUserAsyncTask extends AsyncTask<Void, Void, Boolean> {

       private final String mNickname;
       private final String mPassword;

        LoginOrRegisterUserAsyncTask(String nickname, String password) {
            this.mNickname = nickname;
            this.mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            TableFootballService apiService = ApiClient.instance();
                Call<BasicId> call;
                if (isRegister) {
                    RegisterUserRequest request = new RegisterUserRequest(mNickname,mPassword);
                    call = apiService.registerUser(request);

                } else {
                    LoginUserRequest request = new LoginUserRequest(mNickname,mPassword);
                    call = apiService.loginUser(request);
                }

            Response<BasicId> response = null;
            try {
                response = call.execute();
                if(response.isSuccessful()){
                    TFCApplication.setTOKEN(response.headers().get("Authorization"));
                    TFCApplication.setUserId(response.body().getId());
                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                return false;
//            }
//            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                startMainActivity();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
//
