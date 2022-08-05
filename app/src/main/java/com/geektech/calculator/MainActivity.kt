package com.geektech.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.geektech.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnZero.setOnClickListener {setTextField("0")}
        binding.btn1.setOnClickListener {setTextField("1")}
        binding.btn2.setOnClickListener {setTextField("2")}
        binding.btn3.setOnClickListener {setTextField("3")}
        binding.btn4.setOnClickListener {setTextField("4")}
        binding.btn5.setOnClickListener {setTextField("5")}
        binding.btn6.setOnClickListener {setTextField("6")}
        binding.btn7.setOnClickListener {setTextField("7")}
        binding.btn8.setOnClickListener {setTextField("8")}
        binding.btn9.setOnClickListener {setTextField("9")}

        binding.btnMinus.setOnClickListener {setTextField("-")}
        binding.btnPlus.setOnClickListener {setTextField("+")}
        binding.btnMultiplication.setOnClickListener {setTextField("*")}
        binding.btnDivision.setOnClickListener { setTextField("/") }
        binding.btnLeftBracket.setOnClickListener {setTextField("(")}
        binding.btnRightBracket.setOnClickListener {setTextField(")")}
        binding.btnDot.setOnClickListener { setTextField(".") }

        binding.btnClear.setOnClickListener {
            binding.tvMathOperation.text = ""
            binding.tvResultText.text = ""
        }

        binding.btnBack.setOnClickListener {

            val str = binding.tvMathOperation.text.toString()
            if (str.isNotEmpty()){
                binding.tvMathOperation.text = str.substring(0,str.length-1)
            }
            binding.tvResultText.text = ""
        }
        binding.btnEqual.setOnClickListener {
            try {
                val ex = ExpressionBuilder(binding.tvMathOperation.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if (result == longRes.toDouble()){
                    binding.tvResultText.text = longRes.toString()
                }else{
                    binding.tvResultText.text = result.toString()
                }
//                if (result == ceil(result)) -> Integer

            }catch (e: Exception){
                Log.e("ololo",e.message.toString())
            }
        }
    }

    fun setTextField (str: String)  {
        if(binding.tvResultText.text != "") {
            binding.tvMathOperation.text = binding.tvResultText.text
            binding.tvResultText.text = ""

        }
        binding.tvMathOperation.append(str)

    }
}