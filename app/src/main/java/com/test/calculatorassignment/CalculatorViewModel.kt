package com.test.calculatorassignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception

class CalculatorViewModel : ViewModel() {

    private val resultLiveData = MutableLiveData<String>()
    private val errorLiveData = MutableLiveData<String>()
    var historyList = mutableListOf<String>()

    fun calculateExpression(exp: String) {
        val arithmetic = Arithmetic()
        try {
            var result = arithmetic.evaluate(exp)
            if (result != (-1)) {
                resultLiveData.value = result.toString()
                if (historyList.size >= 10) {
                    historyList.removeAt(historyList.lastIndex)
                }
                if (exp.trim() != result.toString().trim())
                    historyList.add(0, "$exp\n=$result")
            } else
                errorLiveData.value = "invalid"
        } catch (e: Exception) {
            errorLiveData.value = e.message ?: "invalid operation"
        }
    }

    fun getResultLivaData(): LiveData<String> = resultLiveData
    fun getErrorLivaData(): LiveData<String> = errorLiveData
    fun getHistoryfList(): List<String> = historyList
}