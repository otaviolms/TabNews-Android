package br.com.otaviolms.tabnews.modules

import br.com.otaviolms.tabnews.views.login.LoginViewModel
import br.com.otaviolms.tabnews.views.post.PostViewModel
import br.com.otaviolms.tabnews.views.posts.PostsViewModel
import br.com.otaviolms.tabnews.views.user.UserViewModel
import org.koin.dsl.module

fun obterModulesKoin() = module {
    factory { PostViewModel() }
    factory { PostsViewModel() }
    factory { UserViewModel() }
    factory { LoginViewModel() }
}