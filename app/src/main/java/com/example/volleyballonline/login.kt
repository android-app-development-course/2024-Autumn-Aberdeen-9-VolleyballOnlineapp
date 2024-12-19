package com.example.volleyballonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class login : AppCompatActivity() {
    private val mySQLHelper = MySQLHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)
        val registerButton: Button = findViewById(R.id.registerButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            CoroutineScope(Dispatchers.Main).launch {
                val isLoginSuccessful = withContext(Dispatchers.IO) {
                    mySQLHelper.checkLogin(username, password)
                }

                if (isLoginSuccessful) {
                    // Successful login, navigate to another activity
                    Toast.makeText(this@login, "登陆成功，请稍侯...", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@login, MainActivity::class.java)
                    intent.putExtra("username",username)
                    startActivity(intent)
                } else {
                    // Failed login, show error message
                    Toast.makeText(this@login, "账号密码错误！请重新输入!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 确保在销毁时关闭数据库连接，如果有必要的话
    }
}