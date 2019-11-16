package com.devAon.soptgitstar.feature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.devAon.soptgitstar.R

class SignInActivity : AppCompatActivity() {

    private var edtSignInId: EditText? = null
    private var edtSignInPw: EditText? = null
    private var btnSignInSignUp: TextView? = null
    private var btnSignInSignIn: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        makeController()
    }


    private fun makeController() {
        edtSignInId = findViewById(R.id.edtSignInId)
        edtSignInPw = findViewById(R.id.edtSignInPw)
        btnSignInSignUp = findViewById(R.id.btnSignInSignUp)
        btnSignInSignIn = findViewById(R.id.btnSignInSignIn)


        //익명
        btnSignInSignUp?.setOnClickListener( //회원가입 버튼 click
            object : View.OnClickListener {
                override fun onClick(p0: View?) {

                    //회원가입 페이지로
                    val intent = Intent(this@SignInActivity, SignUpActivity::class.java)

                    startActivity(intent)
                }
            }
        )

        // kotlin의 람다식
        btnSignInSignIn?.setOnClickListener {
            val id = edtSignInId?.text.toString()
            val pw = edtSignInPw?.text.toString()

            if (id.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "아이디나 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val response = requestLogin(id, pw)
            if (response) {
                //val intent = Intent(this, FollowerListActivity::class.java)
                // 로그인에 성공한 아이디를 넘겨주자.
                intent.putExtra("login", id)

                startActivity(intent)
            }
            else {
                // 로그인이 실패했으면 Toast를 사용해 로그인이 실패했다고 알려주고 아이디 혹은 비밀번호를 다시 입력하게 포커스를 이동시켜주자.
                Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                edtSignInId?.requestFocus()
            }
        }
    }

    private fun requestLogin(id: String, pw: String): Boolean {
        return true
    }
}

