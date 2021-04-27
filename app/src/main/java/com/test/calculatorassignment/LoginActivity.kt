package com.test.calculatorassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn.setOnClickListener {
            if (et_user_name.text.isEmpty()) {
                Toast.makeText(this, getString(R.string.user_name_err_msg), Toast.LENGTH_SHORT).show()
            } else if (et_password.text.isEmpty()) {
                Toast.makeText(this, getString(R.string.passwrd_err_msg), Toast.LENGTH_SHORT).show()
            } else if ((et_user_name.text.toString() != "admin") || (et_password.text.toString() != "admin")) {
                Toast.makeText(this, getString(R.string.cred_err_msg), Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}