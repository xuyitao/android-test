package com.kunion.autotest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/4.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edtName)
    EditText edtName;

    @BindView(R.id.edtPwd)
    EditText edtPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnLogin})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("name",edtName.getText().toString().trim());
                intent.putExtra("pwd",edtPwd.getText().toString().trim());
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
