package br.com.otaviolms.tabnews.implementations.bases

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import br.com.otaviolms.tabnews.implementations.annotations.Binding
import kotlin.reflect.full.findAnnotation

abstract class BaseActivity<BINDING : ViewBinding> : AppCompatActivity() {

    protected lateinit var bnd: BINDING
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this::class.findAnnotation<Binding>()?.let {
            val method = it.binding.java.getMethod(
                "inflate",
                LayoutInflater::class.java
            )
            bnd = method.invoke(null, layoutInflater) as BINDING
            setContentView(bnd.root)
        }
    }
}