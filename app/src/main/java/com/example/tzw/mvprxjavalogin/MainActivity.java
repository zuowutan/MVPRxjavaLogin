package com.example.tzw.mvprxjavalogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tzw.mvprxjavalogin.mvp.model.MVPLoginBean;
import com.example.tzw.mvprxjavalogin.mvp.presenterImp.LoginPresenterImp;
import com.example.tzw.mvprxjavalogin.mvp.view.LoginView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements LoginView{
    private EditText mAccount;
    private EditText mPassWord;
    private Button mLoginButton;
    private final String  TAG= "MainActivity";
    private LoginPresenterImp mLoginPresenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoginPresenterImp = new LoginPresenterImp();
        mLoginPresenterImp.attachView(this);

        mAccount = (EditText) findViewById(R.id.account);
        mPassWord = (EditText) findViewById(R.id.password);
        mLoginButton = (Button) findViewById(R.id.login);



        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//        这里就做简单的逻辑操作
                if ( TextUtils.isEmpty(mAccount.getText())  ){
                    showToast("账号不能为空");
                    return;
                }else if (TextUtils.isEmpty(mPassWord.getText())){
                    showToast("密码不能为空");
                    return;

                }
                else {
                    String userAccount = mAccount.getText().toString().trim();
                    String userPassWord = mPassWord.getText().toString().trim();
                    MVPLoginBean bean = new MVPLoginBean();
                    bean.setUserName(userAccount);
                    bean.setPassWord(userPassWord);
                    mLoginPresenterImp.mvpLoginMethod(bean);
                }


            }
        });


    }



//  P层的接口回调
    @Override
    public void loginSuccess(int tag, String msg) {
        showSuccess(msg);
    }

    @Override
    public void loginFailed(int tag, String msg) {
        showFailure(msg);
    }

    @Override
    public void loginException(String msg) {
        showException(msg);
   }

    @Override
    public void showToast(String msg) {
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    private  void showSuccess(String msg){
        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(msg)
                .show();
    }
    private  void showFailure(String msg){
        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(msg)
                .show();
    }

    private  void showException(String msg){
        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(msg)
                .show();
    }


}
