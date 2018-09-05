package com.github.icaro.pocconsumewebservice.view

import android.view.View
import com.github.icaro.pocconsumewebservice.activity.MainActivity
import com.github.icaro.pocconsumewebservice.viewmodel.MainActivityViewModel
import lk.android.observable.bindString
import org.jetbrains.anko.*

class MainActivityView(private val viewModel: MainActivityViewModel) : AnkoComponent<MainActivity> {
    
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        verticalLayout {
            editText {
                bindString(viewModel.api)
                hint = "API Url Here"
            }.lparams {
                width = matchParent
                height = wrapContent
                weight = 0f
            }
            
            button("Fazer Requisição") {
                setOnClickListener { viewModel.consumeWebService() }
            }.lparams {
                width = matchParent
                height = wrapContent
                weight = 0f
            }
            
            textView {
                bindString(viewModel.responseText)
                textSize = 15f
            }.lparams {
                width = matchParent
                height = 0
                weight = 1f
            }
        }
    }
}