package com.github.icaro.pocconsumewebservice.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.icaro.pocconsumewebservice.model.db.ApiUrlDatabase
import com.github.icaro.pocconsumewebservice.view.MainActivityView
import com.github.icaro.pocconsumewebservice.viewmodel.MainActivityViewModel
import org.jetbrains.anko.setContentView
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainActivityViewModel(WeakReference(this))
        initUrl(viewModel)
        MainActivityView(viewModel).setContentView(this)
    }
    
    private fun initUrl(viewModel: MainActivityViewModel) {
        ApiUrlDatabase(this) { dao ->
            viewModel.api.value =
                dao.queryUrl()?.url
                ?: "https://jsonplaceholder.typicode.com/todos/1"
        }
    }
}
