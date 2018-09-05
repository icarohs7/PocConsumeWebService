package com.github.icaro.pocconsumewebservice.viewmodel

import android.support.v7.app.AppCompatActivity
import com.github.icaro.pocconsumewebservice.model.db.*
import com.github.icaro.pocconsumewebservice.model.entities.ApiUrl
import com.github.kittinunf.fuel.httpGet
import lk.kotlin.observable.property.StandardObservableProperty
import org.jetbrains.anko.coroutines.experimental.bg
import java.lang.ref.WeakReference

class MainActivityViewModel(private val context: WeakReference<AppCompatActivity>) {
    val api = StandardObservableProperty("")
    val responseText = StandardObservableProperty("")
    
    fun consumeWebService() {
        bg {
            //Update url at database
            safeDatabaseTransaction { dao -> dao.insert(ApiUrl(api.value)) }
            val responseBytes = api.value.httpGet().responseString().second.data
            responseText.value = String(responseBytes)
        }
    }
    
    private fun safeDatabaseTransaction(fn: (dao: ApiUrlDao) -> Unit) {
        val ctx = context.get() ?: return
        ApiUrlDatabase(ctx, fn)
    }
}