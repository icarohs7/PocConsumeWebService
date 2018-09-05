package com.github.icaro.pocconsumewebservice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.icaro.pocconsumewebservice.model.ApiUrl
import com.github.icaro.pocconsumewebservice.model.db.ApiUrlDatabase
import com.github.icaro.pocconsumewebservice.viewmodel.MainActivityViewModel
import com.github.kittinunf.fuel.httpGet
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import lk.android.observable.bindString
import org.jetbrains.anko.*
import org.jetbrains.anko.coroutines.experimental.bg

class MainActivity : AppCompatActivity() {
    private val viewModel = MainActivityViewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeDatabase()
        // Código da view
        verticalLayout {
            val textRequestResponse = textView {
                textSize = 15f
            }
            
            val editApiUrl = editText {
                hint = "URL da API aqui"
                text.append("worked?")
                bindString(viewModel.urlDaApi)
            }
            
            button("Fazer Requisição") {
                setOnClickListener {
                    //async para permitir execução de códigos no background
                    async(UI) {
                        //UI para representar o contexto da thread principal(UI)
                        //await faz o código parar naquele ponto até a chamada retornar
                        val textoRecebido = consumeWebService(viewModel.urlDaApi.value).await()
                        //log para verificação
                        Log.i("teste", "$textoRecebido")
                        //alterar texto para resposta
                        textRequestResponse.text = textoRecebido
                    }
                }
            }
        }
    }
    
    //Função fazendo acesso ao backend e retornando a resposta
    private fun consumeWebService(url: String): Deferred<String> {
        //bloco bg para representar código em segundo plano (background)
        return bg { String(url.httpGet().responseString().second.data) }
    }
    
    //Se o banco não contiver a url, adicionar
    private fun initializeDatabase() {
        ApiUrlDatabase.dbTransactionAsync(this) { dao ->
            val url = dao.queryUrl().value
            if (url == null) {
                dao.insert(ApiUrl("https://jsonplaceholder.typicode.com/todos/1"))
            }
            viewModel.urlDaApi.value = dao.queryUrl().value?.url ?: ""
        }
    }
}
