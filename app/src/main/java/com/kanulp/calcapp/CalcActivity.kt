package com.kanulp.calcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class CalcActivity : AppCompatActivity(),View.OnClickListener {

    var tv_data : TextView? = null
    var tv_ans : TextView? = null
    var button_ex : Button? = null
    var button_pi : Button? = null
    var button_m_minus : Button? = null
    var button_m_plus : Button? = null
    var button_mr : Button? = null
    var button_mc : Button? = null
    var button_plus_minus : Button? = null
    var button_percentage : Button? = null
    var button_divide : Button? = null
    var button_7 : Button? = null
    var button_8 : Button? = null
    var button_9 : Button? = null
    var button_multi : Button? = null
    var button_4 : Button? = null
    var button_5 : Button? = null
    var button_6 : Button? = null
    var button_minus : Button? = null
    var button_3 : Button? = null
    var button_2 : Button? = null
    var button_1 : Button? = null
    var button_plus : Button? = null
    var button_0 : Button? = null
    var button_dot : Button? = null
    var button_equal : Button? = null
    var button_c : Button? = null
    var button_root : Button? = null
    var button_sqr : Button? = null
    var pivalue = Math.PI

   // private var isFutureOperationButtonClicked: Boolean = false
    private var isInstantOperationButtonClicked: Boolean = false
   // private var isEqualButtonClicked: Boolean = false

   // private var currentNumber: Double = 0.0 // Value can be changed.
  //  private var currentResult: Double = 0.0
  //  private var memory: Double = 0.0

 //   private var historyText = "" // Recognize type of variable without declaring it.
 //   private var historyInstantOperationText = ""
 //   private var historyActionList: ArrayList<String> = ArrayList()

    private val ZERO: String = "0"
    private val ONE: String = "1"
    private val TWO: String = "2"
    private val THREE: String = "3"
    private val FOUR: String = "4"
    private val FIVE: String = "5"
    private val SIX: String = "6"
    private val SEVEN: String = "7"
    private val EIGHT: String = "8"
    private val NINE: String = "9"


    private val INIT = ""

    private val ADDITION = " + "
    private val SUBTRACTION = " − "
    private val MULTIPLICATION = " × "
    private val DIVISION = " ÷ "
    private val EX = " a^b "

    private val PERCENTAGE = ""
    private val ROOT = "√"
    private val pi = "π"
    private val SQUARE = "sqr"


//    private var currentOperation = INIT
    var calcBrain : CalcBrain? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        bindViews()
        calcBrain = CalcBrain()
    }

    private fun bindViews() {

        tv_data = findViewById(R.id.tv_data)
        tv_ans = findViewById(R.id.tv_ans)
        button_c = findViewById(R.id.button_c)
        button_ex = findViewById(R.id.button_ex)
        button_pi = findViewById(R.id.button_pi)
        button_m_minus = findViewById(R.id.button_m_minus)
        button_m_plus = findViewById(R.id.button_m_plus)
        button_mr = findViewById(R.id.button_mr)
        button_mc = findViewById(R.id.button_mc)
        button_plus_minus = findViewById(R.id.button_plus_minus)
        button_percentage = findViewById(R.id.button_percentage)
        button_divide = findViewById(R.id.button_divide)
        button_7 = findViewById(R.id.button_7)
        button_8 = findViewById(R.id.button_8)
        button_9 = findViewById(R.id.button_9)
        button_multi = findViewById(R.id.button_multi)
        button_4 = findViewById(R.id.button_4)
        button_5 = findViewById(R.id.button_5)
        button_6 = findViewById(R.id.button_6)
        button_minus = findViewById(R.id.button_minus)
        button_3 = findViewById(R.id.button_3)
        button_2 = findViewById(R.id.button_2)
        button_1 = findViewById(R.id.button_1)
        button_plus = findViewById(R.id.button_plus)
        button_0 = findViewById(R.id.button_0)
        button_dot = findViewById(R.id.button_dot)
        button_equal = findViewById(R.id.button_equal)

        button_root = findViewById(R.id.button_root)
        button_sqr = findViewById(R.id.button_sqr)

        button_c?.setOnClickListener(this)
        button_ex?.setOnClickListener(this)
        button_pi?.setOnClickListener(this)
        button_m_minus?.setOnClickListener(this)
        button_m_plus?.setOnClickListener(this)
        button_mr?.setOnClickListener(this)
        button_mc?.setOnClickListener(this)
        button_plus_minus?.setOnClickListener(this)
        button_percentage?.setOnClickListener(this)
        button_divide?.setOnClickListener(this)
        button_7?.setOnClickListener(this)
        button_8?.setOnClickListener(this)
        button_9?.setOnClickListener(this)
        button_multi?.setOnClickListener(this)
        button_4?.setOnClickListener(this)
        button_5?.setOnClickListener(this)
        button_6?.setOnClickListener(this)
        button_minus?.setOnClickListener(this)
        button_3?.setOnClickListener(this)
        button_2?.setOnClickListener(this)
        button_1?.setOnClickListener(this)
        button_plus?.setOnClickListener(this)
        button_0?.setOnClickListener(this)
        button_dot?.setOnClickListener(this)
        button_equal?.setOnClickListener(this)
        button_root?.setOnClickListener(this)
        button_sqr?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {

        when(p0?.id){


            R.id.button_ex->{
                calcBrain?.onFutureOperationButtonClick(EX,tv_data?.text.toString())
                tv_data?.text = calcBrain?.getMYTVDATA()
                tv_ans?.text = calcBrain?.formatDoubleToString(calcBrain?.getCurrentResult()!!)

            }
            R.id.button_pi->{
                tv_ans?.text = calcBrain?.onNumberButtonClick(str = tv_ans?.text?.toString(),number = pivalue.toString())
                tv_data?.text = calcBrain?.getMYTVDATA()
            }

            R.id.button_root->{
                calcBrain?.onInstantOperationButtonClick(ROOT,tv_ans?.text.toString())
                tv_data?.text = calcBrain?.getMYTVDATA()
            }

            R.id.button_sqr->{
                //onInstantOperationButtonClick(SQUARE)
                calcBrain?.onInstantOperationButtonClick(SQUARE,tv_ans?.text.toString())
                tv_data?.text = calcBrain?.getMYTVDATA()
            }

            R.id.button_c->{

                calcBrain?.btn_clear()
                tv_ans?.text = calcBrain?.formatDoubleToString(calcBrain?.getCurrentNo()!!)
                tv_data?.text = ""
                calcBrain?.setHistoryText()
                calcBrain?.setCurrentOperation()
                calcBrain?.setMYTVDATA()
            }

            R.id.button_m_minus ->{
                //buttonMemoryClear.isEnabled = true
                //buttonMemoryRecall.isEnabled = true

                var displayText = calcBrain?.memory_minus(tv_ans?.text.toString())
                Toast.makeText(applicationContext, displayText, Toast.LENGTH_LONG).show()
            }
            R.id.button_m_plus ->{
                //buttonMemoryClear.isEnabled = true
                //buttonMemoryRecall.isEnabled = true

                var displayText = calcBrain?.memory_plus(tv_ans?.text.toString())
                Toast.makeText(applicationContext, displayText, Toast.LENGTH_LONG).show()

            }
            R.id.button_mr -> {
                //clearEntry(memory)
                calcBrain?.clearEntry(calcBrain?.getMemory()!!)
                if (calcBrain?.isInstantOperationButtonClicked()!!)
                    tv_data?.text = StringBuilder().append(calcBrain?.getHistoryText()).append(calcBrain?.getCurrentOperation()).toString()
                tv_ans?.text =  calcBrain?.formatDoubleToString(calcBrain?.getMemory()!!)
                Toast.makeText(applicationContext, getString(R.string.memory_recalled_toast), Toast.LENGTH_SHORT).show()
            }
            R.id.button_mc -> {

                //buttonMemoryClear.isEnabled = false
                //buttonMemoryRecall.isEnabled = false

                //memory = 0.0
                calcBrain?.setMemory(0.0)
                Toast.makeText(applicationContext, getString(R.string.memory_cleared_toast), Toast.LENGTH_SHORT).show()

            }
            R.id.button_plus_minus ->{
                var ans = calcBrain?.clickPlusMinus(tv_ans?.text.toString())
                tv_ans?.text = ans
                tv_data?.text = calcBrain?.getHistoryText()

            }
            R.id.button_percentage -> {
                //onFutureOperationButtonClick(PERCENTAGE)
                calcBrain?.onFutureOperationButtonClick(PERCENTAGE,tv_data?.text.toString())
                tv_data?.text = calcBrain?.getMYTVDATA()
                tv_ans?.text = calcBrain?.formatDoubleToString(calcBrain?.getCurrentResult()!!)

            }
            R.id.button_divide ->{
                //onFutureOperationButtonClick(DIVISION)
                calcBrain?.onFutureOperationButtonClick(DIVISION,tv_data?.text.toString())
                tv_data?.text = calcBrain?.getMYTVDATA()
                tv_ans?.text = calcBrain?.formatDoubleToString(calcBrain?.getCurrentResult()!!)

            }
            R.id.button_7 -> {
                //onNumberButtonClick(SEVEN)
                tv_ans?.text = calcBrain?.onNumberButtonClick(str = tv_ans?.text?.toString(),number = SEVEN)
                tv_data?.text = calcBrain?.getMYTVDATA()
            }
            R.id.button_8->{
                //onNumberButtonClick(EIGHT)
                tv_ans?.text = calcBrain?.onNumberButtonClick(str = tv_ans?.text?.toString(),number = EIGHT)
                tv_data?.text = calcBrain?.getMYTVDATA()
            }
            R.id.button_9->{
                //onNumberButtonClick(NINE)
                tv_ans?.text = calcBrain?.onNumberButtonClick(str = tv_ans?.text?.toString(),number = NINE)
                tv_data?.text = calcBrain?.getMYTVDATA()
            }
            R.id.button_multi->{
                //onFutureOperationButtonClick(MULTIPLICATION)
                calcBrain?.onFutureOperationButtonClick(MULTIPLICATION,tv_data?.text.toString())
                tv_data?.text = calcBrain?.getMYTVDATA()
                tv_ans?.text = calcBrain?.formatDoubleToString(calcBrain?.getCurrentResult()!!)

            }
            R.id.button_4 ->{
                //onNumberButtonClick(FOUR)
                tv_ans?.text = calcBrain?.onNumberButtonClick(str = tv_ans?.text?.toString(),number = FOUR)
                tv_data?.text = calcBrain?.getMYTVDATA()
            }
            R.id.button_5 ->{
                //onNumberButtonClick(FIVE)
                tv_ans?.text = calcBrain?.onNumberButtonClick(str = tv_ans?.text?.toString(),number = FIVE)
                tv_data?.text = calcBrain?.getMYTVDATA()
            }
            R.id.button_6 ->{
                //onNumberButtonClick(SIX)
                tv_ans?.text = calcBrain?.onNumberButtonClick(str = tv_ans?.text?.toString(),number = SIX)
                tv_data?.text = calcBrain?.getMYTVDATA()
            }
            R.id.button_minus ->{
                //onFutureOperationButtonClick(SUBTRACTION)
                calcBrain?.onFutureOperationButtonClick(SUBTRACTION,tv_data?.text.toString())
                tv_data?.text = calcBrain?.getMYTVDATA()
                tv_ans?.text = calcBrain?.formatDoubleToString(calcBrain?.getCurrentResult()!!)

            }
            R.id.button_3 ->{
                //onNumberButtonClick(THREE)
                tv_ans?.text = calcBrain?.onNumberButtonClick(str = tv_ans?.text?.toString(),number = THREE)
                tv_data?.text = calcBrain?.getMYTVDATA()
                //tv_ans?.text = calcBrain?.formatDoubleToString(calcBrain?.getCurrentResult()!!)

            }
            R.id.button_2 ->{
                //onNumberButtonClick(TWO)
                tv_ans?.text = calcBrain?.onNumberButtonClick(str = tv_ans?.text?.toString(),number = TWO)
                tv_data?.text = calcBrain?.getMYTVDATA()
                //tv_ans?.text = calcBrain?.formatDoubleToString(calcBrain?.getCurrentResult()!!)

            }
            R.id.button_1 ->{
                //onNumberButtonClick(ONE)
                tv_ans?.text = calcBrain?.onNumberButtonClick(str = tv_ans?.text?.toString(),number = ONE)
                tv_data?.text = calcBrain?.getMYTVDATA()
            }
            R.id.button_plus ->{
                //onFutureOperationButtonClick(ADDITION)
                calcBrain?.onFutureOperationButtonClick(ADDITION,tv_data?.text.toString())
                tv_data?.text = calcBrain?.getMYTVDATA()
                tv_ans?.text = calcBrain?.formatDoubleToString(calcBrain?.getCurrentResult()!!)

            }
            R.id.button_0 ->{
                tv_ans?.text = calcBrain?.onNumberButtonClick(str = tv_ans?.text?.toString(),number = ZERO)
                tv_data?.text = calcBrain?.getMYTVDATA()

            }
            R.id.button_dot ->{

            }
            R.id.button_equal ->{
                //clickEqual()
                var ans = calcBrain?.clickEqual()
                Toast.makeText(applicationContext, ans.toString(), Toast.LENGTH_LONG).show()
                tv_data?.text = ""
                tv_ans?.text = calcBrain?.formatDoubleToString(calcBrain?.getCurrentResult()!!)

            }

        }

    }




}