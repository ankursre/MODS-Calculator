package com.test.calculatorassignment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var hisAdapter: HistoryAdapter
    var exp: String = ""
    lateinit var vm: CalculatorViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)

        observeLiveData()
        handleClick()

    }

    private fun observeLiveData() {
        vm.getResultLivaData().observe(this, Observer {
            etInput.setText(it)
        })

        vm.getErrorLivaData().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun handleClick() {
        button0.setOnClickListener { etInput.setText(etInput.text.toString() + "0") }
        button1.setOnClickListener { etInput.setText(etInput.text.toString() + "1") }
        button2.setOnClickListener { etInput.setText(etInput.text.toString() + "2") }
        button3.setOnClickListener { etInput.setText(etInput.text.toString() + "3") }
        button4.setOnClickListener { etInput.setText(etInput.text.toString() + "4") }
        button5.setOnClickListener { etInput.setText(etInput.text.toString() + "5") }
        button6.setOnClickListener { etInput.setText(etInput.text.toString() + "6") }
        button7.setOnClickListener { etInput.setText(etInput.text.toString() + "7") }
        button8.setOnClickListener { etInput.setText(etInput.text.toString() + "8") }
        button9.setOnClickListener { etInput.setText(etInput.text.toString() + "9") }

        buttonAdd.setOnClickListener {
            if (etInput == null) etInput.setText("") else etInput.setText(
                etInput.text.toString() + " + "
            )
        }
        buttonSub.setOnClickListener {
            if (etInput == null) etInput.setText("") else etInput.setText(
                etInput.text.toString() + " - "
            )
        }
        buttonMultiplication.setOnClickListener {
            if (etInput == null) etInput.setText("") else etInput.setText(
                etInput.text.toString() + " * "
            )
        }
        buttonDivision.setOnClickListener {
            if (etInput == null) etInput.setText("") else etInput.setText(
                etInput.text.toString() + " / "
            )
        }

        buttonEquals.setOnClickListener {
            exp = etInput.text.toString()
            vm.calculateExpression(exp)
        }
        buttonClear.setOnClickListener {
            val s1: String = etInput.text.toString()
            if (s1.isNotEmpty()) etInput.setText(s1.substring(0, s1.length - 1))
            Toast.makeText(
                applicationContext, getString(R.string.clear_string_msg),
                Toast.LENGTH_SHORT
            ).show()
        }
        buttonClear.setOnLongClickListener {
            etInput.setText("")
            true
        }
        buttonExit.setOnClickListener { System.exit(0) }

        historyBtn.setOnClickListener {
            if(vm.getHistoryfList().isNotEmpty()) {
                showHistoryDialod()
            }else
                Toast.makeText(this, getString(R.string.no_history_msg),Toast.LENGTH_SHORT).show()
        }
    }

    private fun showHistoryDialod() {
        var dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.dialog_history)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        var listView = dialog.findViewById<RecyclerView>(R.id.rvHistory)
        listView?.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            hisAdapter = HistoryAdapter(vm.getHistoryfList())
            adapter = hisAdapter

        }
        dialog.show()
    }
}