package br.com.otaviolms.tabnews.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import br.com.otaviolms.tabnews.databinding.ActivityMainBinding
import br.com.otaviolms.tabnews.implementations.annotations.Binding
import br.com.otaviolms.tabnews.implementations.bases.BaseActivity
import br.com.otaviolms.tabnews.implementations.bases.BaseFragment
import br.com.otaviolms.tabnews.singletons.Sessao
import br.com.otaviolms.tabnews.views.login.LoginUiState
import br.com.otaviolms.tabnews.views.login.LoginViewModel
import org.koin.android.ext.android.inject

class AbreLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intentData: Uri? = intent.data
        val intentMain = Intent(this, MainActivity::class.java)
        if (intentData != null) {
            Log.i("LogTabNews", "Path: ${intentData.path}")
            Toast.makeText(this, intentData.path, Toast.LENGTH_SHORT).show()
            intentMain.putExtra("path", intentData.path)
        }
        startActivity(intentMain)
    }

}