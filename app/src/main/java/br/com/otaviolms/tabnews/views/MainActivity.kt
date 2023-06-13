package br.com.otaviolms.tabnews.views

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import br.com.otaviolms.tabnews.R
import br.com.otaviolms.tabnews.databinding.ActivityMainBinding
import br.com.otaviolms.tabnews.databinding.FragmentPostsBinding
import br.com.otaviolms.tabnews.extensions.colocarUnderline
import br.com.otaviolms.tabnews.extensions.removerUnderline
import br.com.otaviolms.tabnews.views.posts.PostsFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var bnd: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bnd = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bnd.root)
    }

//    override fun onBackPressed() {}

}