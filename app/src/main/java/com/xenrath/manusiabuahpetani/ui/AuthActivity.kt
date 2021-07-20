package com.xenrath.manusiabuahpetani.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xenrath.manusiabuahpetani.R
import com.xenrath.manusiabuahpetani.ui.login.LoginActivity
import com.xenrath.manusiabuahpetani.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        mainButton()

    }

    private fun mainButton() {
        button_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        button_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}