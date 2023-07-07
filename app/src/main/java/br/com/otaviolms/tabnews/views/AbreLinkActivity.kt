package br.com.otaviolms.tabnews.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AbreLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intentData: Uri? = intent.data
        val intentMain = Intent(this, MainActivity::class.java)
        if (intentData != null) intentMain.putExtra(
            "path",
            (intentData.path ?: "").removePrefix("/")
        )
        startActivity(intentMain)
    }

}