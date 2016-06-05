package com.dogenote.aboutlogin;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dogenote.R;
import com.dogenote.aboutlogin.UserService;
import com.dogenote.aboutlogin.DbHelper;

public class LoginActivity extends Activity implements OnClickListener {
	// 分别为用户名和密码
	private EditText mUsername;
	private EditText mPasswd;
	// 分别为登陆和注册按钮
	private Button mLogin;
	private Button mRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(DbHelper.db == null){
			Toast.makeText(LoginActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
		}
		
		setContentView(R.layout.login);

		mUsername = (EditText) findViewById(R.id.editname);
		mPasswd = (EditText) findViewById(R.id.editpasswd);

		mLogin = (Button) findViewById(R.id.btlogin);
		mRegister = (Button) findViewById(R.id.btregister);

		mLogin.setOnClickListener(this);
		mRegister.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btlogin:
			String userName = mUsername.getText().toString().trim();
			String passWd = mPasswd.getText().toString().trim();

			UserService userService = new UserService(LoginActivity.this);
  
			boolean flag = userService.Login(userName, passWd);
			if (flag) {
				Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
			}
		case R.id.btregister:
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, RegisterActivity.class);
			LoginActivity.this.startActivity(intent);
			LoginActivity.this.finish();
		}

	}
}
