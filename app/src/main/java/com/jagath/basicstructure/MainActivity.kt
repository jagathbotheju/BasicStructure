package com.jagath.basicstructure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListeners()
    }

    private fun setupListeners(){
        btnRegister.setOnClickListener { registerUser() }

        txtUserName.validate{validateUserName()}
        txtEmail.validate { validateEmail() }
        txtPhone.validate { validatePhoneNo() }
        txtPassword.validate { validatePassword() }
    }

    private fun validateUserName():Boolean  {
        val username=txtUserName.text.toString()

        return when {
            username.isEmpty() -> {
                textInputLayoutUserName.error="User name cannot be empty..."
                false
            }
            username.length>=15 -> {
                textInputLayoutUserName.error="User name too long..."
                false
            }
            else -> {
                textInputLayoutUserName.error=null
                true
            }
        }
    }

    private fun validateEmail():Boolean{
        val email=txtEmail.text.toString().trim()

        return if(email.isEmpty()){
            textInputLayoutEmail.error="Email cannot be empty..."
            false
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            textInputLayoutEmail.error="Invalid email address..."
            return false
        } else{
            textInputLayoutEmail.error=null
            true
        }

    }

    private fun validatePhoneNo():Boolean{
        val phone=txtPhone.text.toString()

        return if(phone.isEmpty()){
            textInputLayoutPhone.error="Phone number cannot be empty..."
            false
        }else{
            textInputLayoutPhone.error=null
            true
        }
    }

    private fun validatePassword():Boolean{
        val password=txtPassword.text.toString()
        val passwordPattern=Regex("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@?#$%]).{8,20})")

        /*
            (			# Start of group
            (?=.*\d)		#   must contains one digit from 0-9
            (?=.*[a-z])		#   must contains one lowercase characters
            (?=.*[A-Z])		#   must contains one uppercase characters
            (?=.*[@?#$%])	#   must contains one special symbols in the list @?#$%
            .		        #   match anything with previous condition checking
            {8,20}	        #   length at least 6 characters and maximum of 20
            )
        */


        return if(password.isEmpty()){
            textInputLayoutPassword.error="Password cannot be empty..."
            false
        }else if(!passwordPattern.matches(password)){
            textInputLayoutPassword.error="Password is not valid..."
            return false
        }else {
            textInputLayoutPassword.error=null
            true
        }
    }

    private fun registerUser(){
        if(validateUserName() &&
            validateEmail() && validatePhoneNo() &&
            validatePassword()){

            //now we have validated fields
            //normally we send this values to backend server
            //but for this app will display fullname in a Snackbar
            val username=txtUserName.text.toString()
            val email=txtEmail.text.toString()
            val phone=txtPhone.text.toString()
            val password=txtPassword.text.toString()

            Snackbar.make(
                findViewById(R.id.coordinatorLayout),
                "${username} registered",
                Snackbar.LENGTH_LONG).show()
        }

    }


}
