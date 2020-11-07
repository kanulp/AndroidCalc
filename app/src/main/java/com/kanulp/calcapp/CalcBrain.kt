package com.kanulp.calcapp

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException

class CalcBrain() {

    private val INIT = ""
    private var currentOperation = INIT
    private var isFutureOperationButtonClicked: Boolean = false
    private var isInstantOperationButtonClicked: Boolean = false
    private var isEqualButtonClicked: Boolean = false

    private var currentNumber: Double = 0.0 // Value can be changed.
    private var currentResult: Double = 0.0
    private var memory: Double = 0.0

    private var historyText = "" // Recognize type of variable without declaring it.
    private var historyInstantOperationText = ""
    private var historyActionList: ArrayList<String> = ArrayList()
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
    private val NEGATE = "negate"

    private val ADDITION = " + "
    private val SUBTRACTION = " − "
    private val MULTIPLICATION = " × "
    private val DIVISION = " ÷ "
    private val EX = " a^b "

    private val PERCENTAGE = ""
    private val ROOT = "√"
    private val pi = "π"
    private val SQUARE = "sqr"
    private val FRACTION = "1/"

    private val COMMA = ","
    private val EQUAL = " = "

    var myTVDATA = ""

    public fun getCurrentNo(): Double? {
        return currentNumber
    }
    public fun setCurrentNo(no:Double) {
        currentNumber = no
    }

    public fun getHistoryText(): String? {
        return historyText
    }
    public fun setHistoryText() {
        historyText = ""
    }
    public fun setHistoryText(str: String) {
        historyText=str
    }
    public fun getMemory(): Double? {
        return memory
    }
    public fun setMemory(no:Double){
        memory = no
    }

    public fun isInstantOperationButtonClicked(): Boolean {
        return isInstantOperationButtonClicked
    }

    public fun getCurrentOperation():String{
        return currentOperation
    }

    public fun getCurrentResult():Double{
        return currentResult
    }
    public fun setCurrentOperation() {
        currentOperation = ""
    }




    public fun btn_clear(){
        currentNumber = 0.0
        currentResult = 0.0
        currentOperation = INIT

        historyText = ""
        historyInstantOperationText = ""



        isFutureOperationButtonClicked = false
        isEqualButtonClicked = false
        isInstantOperationButtonClicked = false
    }

    @Throws(IllegalArgumentException::class)
    public fun onNumberButtonClick(str: String?,number: String, isHistory: Boolean = false):String {
        var tv_ans=""
        var currentValue: String = str.toString()
        // In Kotlin there is no more conditional operator ? : like it is in Java, which is used as a shortcut for setting a single variable to one of two states based on a single condition. Here everything can be conveniently done using if..else statement.
        // In Kotlin, using the equality operator == will call the equals method behind the scenes, so it's totaly acceptable to use it for string comparision.
        currentValue = if (currentValue == ZERO || isFutureOperationButtonClicked || isInstantOperationButtonClicked || isEqualButtonClicked || isHistory) number else StringBuilder().append(currentValue).append(number).toString()

        try {
            currentNumber = formatStringToDouble(currentValue)
        } catch (e: ParseException) {
            throw IllegalArgumentException("String must be number.")
        }

        tv_ans = currentValue

        if (isEqualButtonClicked) {
            currentOperation = INIT
            historyText = ""
        }

        if (isInstantOperationButtonClicked) {
            historyInstantOperationText = ""
            myTVDATA = StringBuilder().append(historyText).append(currentOperation).toString()
            isInstantOperationButtonClicked = false
        }

        isFutureOperationButtonClicked = false
        isEqualButtonClicked = false
        return tv_ans
    }
    public fun onFutureOperationButtonClick(operation: String,str:String) {

        if (!isFutureOperationButtonClicked && !isEqualButtonClicked) {
            calculateResult()
        }

        currentOperation = operation

        if (isInstantOperationButtonClicked) {
            isInstantOperationButtonClicked = false
            //historyText = tv_data?.text.toString()
            historyText = str
        }
        //tv_data?.text = StringBuilder().append(historyText).append(operation).toString()
        myTVDATA = StringBuilder().append(historyText).append(operation).toString()

        isFutureOperationButtonClicked = true
        isEqualButtonClicked = false
    }

    public fun getMYTVDATA() : String {
        return myTVDATA
    }
    public fun setMYTVDATA() {
         myTVDATA = ""
    }

    public fun clickPlusMinus(str:String): String? {

        var returnText = ""
        val currentValue: String = str

        currentNumber = formatStringToDouble(currentValue)
        if (currentNumber == 0.0)
            return "0.0"

        currentNumber *= -1
        //tv_ans?.text = formatDoubleToString(currentNumber)
        returnText = formatDoubleToString(currentNumber)

        if (isInstantOperationButtonClicked) {
            historyInstantOperationText = "($historyInstantOperationText)"
            historyInstantOperationText = StringBuilder().append(NEGATE).append(historyInstantOperationText).toString()
            //tv_data?.text = StringBuilder().append(historyText).append(currentOperation).append(historyInstantOperationText).toString()
                //returnText = returnText+" "+ StringBuilder().append(historyText).append(currentOperation).append(historyInstantOperationText).toString()
            myTVDATA = StringBuilder().append(historyText).append(currentOperation).append(historyInstantOperationText).toString()

        }

        if (isEqualButtonClicked) {
            currentOperation = INIT
        }

        isFutureOperationButtonClicked = false
        isEqualButtonClicked = false

        return returnText
    }

    private fun countEx(a : Double,b:Double) : Double {
        return Math.pow(a,b)
    }

    private fun useNumberFormat(): DecimalFormat {

        val symbols = DecimalFormatSymbols()
        symbols.decimalSeparator = ','

        val format = DecimalFormat("#.##############")
        format.decimalFormatSymbols = symbols

        return format
    }

     fun formatDoubleToString(number: Double): String {
        return useNumberFormat().format(number)
    }

     fun formatStringToDouble(number: String): Double {
        return useNumberFormat().parse(number).toDouble()
    }

    fun memory_minus(str: String): String  {
        val currentValue: String = str?.toString()
        val thisOperationNumber: Double = formatStringToDouble(currentValue)

        val newMemory = memory - thisOperationNumber

        var displayText = "Memory subtracted : ${formatDoubleToString(memory)} - ${formatDoubleToString(thisOperationNumber)} = ${formatDoubleToString(newMemory)}"

        memory = newMemory
        return displayText
    }
    fun memory_plus(str: String): String  {

        val currentValue: String = str.toString()
        val thisOperationNumber: Double = formatStringToDouble(currentValue)

        val newMemory = memory + thisOperationNumber

        // Strings in Kotlin can include references to variables that are interpolated.
        // In addition to simple variable references, they can also include any expression enclosed in curly braces.
        // Also you can still do the string concatenation if you like using plus sign.
        var displayText = "Memory Added : ${formatDoubleToString(memory)} + ${formatDoubleToString(thisOperationNumber)} = ${formatDoubleToString(newMemory)}"

        memory = newMemory

        return displayText
    }

    public fun clearEntry(newNumber: Double = 0.0) {
        historyInstantOperationText = ""

        if (isEqualButtonClicked) {
            currentOperation = INIT
            historyText = ""
        }

//        if (isInstantOperationButtonClicked)
//            tv_data?.text = StringBuilder().append(historyText).append(currentOperation).toString()

        isInstantOperationButtonClicked = false
        isFutureOperationButtonClicked = false
        isEqualButtonClicked = false

        currentNumber = newNumber

    }
    fun clickEqual() : String{
        //var ans = ""
        if (isFutureOperationButtonClicked) {
            currentNumber = currentResult
        }

        val historyAllText = calculateResult()

        historyActionList.add(historyAllText)

        historyText = StringBuilder().append(formatDoubleToString(currentResult)).toString()

        isFutureOperationButtonClicked = false
        isEqualButtonClicked = true
        return historyText
    }
    private val Double.sqrt: Double get() = Math.sqrt(this)

    fun onInstantOperationButtonClick(operation: String,str: String):String {
        var tv_ans= ""

        var currentValue: String = str
        var thisOperationNumber: Double = formatStringToDouble(currentValue)

        currentValue = "(${formatDoubleToString(thisOperationNumber)})"

        when (operation) {
            PERCENTAGE -> {
                thisOperationNumber = (currentResult * thisOperationNumber) / 100
                currentValue = formatDoubleToString(thisOperationNumber)
            }
            // Later we use this property to find square root of the provided number.
            ROOT -> thisOperationNumber = thisOperationNumber.sqrt
            SQUARE -> thisOperationNumber = thisOperationNumber * thisOperationNumber
            FRACTION -> thisOperationNumber = 1 / thisOperationNumber

            //pi ->

        }

        if (isInstantOperationButtonClicked) {
            historyInstantOperationText = "($historyInstantOperationText)"
            historyInstantOperationText = StringBuilder().append(operation).append(historyInstantOperationText).toString()
            myTVDATA = if (isEqualButtonClicked) historyInstantOperationText else StringBuilder().append(historyText).append(currentOperation).append(historyInstantOperationText).toString()
        } else if (isEqualButtonClicked) {
            historyInstantOperationText = StringBuilder().append(operation).append(currentValue).toString()
            myTVDATA = historyInstantOperationText
        } else {
            historyInstantOperationText = StringBuilder().append(operation).append(currentValue).toString()
            myTVDATA = StringBuilder().append(historyText).append(currentOperation).append(historyInstantOperationText).toString()
        }

        tv_ans = formatDoubleToString(thisOperationNumber)

        if (isEqualButtonClicked) currentResult = thisOperationNumber else currentNumber = thisOperationNumber

        isInstantOperationButtonClicked = true
        isFutureOperationButtonClicked = false

        return tv_ans
    }

    fun calculateResult(): String {

        when (currentOperation) {
            INIT -> {
                currentResult = currentNumber
                //historyText = StringBuilder().append(tv_data?.text.toString()).toString()
                historyText = StringBuilder().append(myTVDATA).toString()
            }
            ADDITION -> currentResult += currentNumber
            SUBTRACTION -> currentResult -= currentNumber
            MULTIPLICATION -> currentResult *= currentNumber
            DIVISION -> currentResult /= currentNumber
            EX -> currentResult = countEx(currentResult,currentNumber)
        }

        //tv_ans?.text = formatDoubleToString(currentResult)

        if (isInstantOperationButtonClicked) {
            isInstantOperationButtonClicked = false
            //historyText = tv_data?.text.toString()
            historyText = myTVDATA?.toString()
            if (isEqualButtonClicked) historyText = StringBuilder().append(historyText).append(currentOperation).append(formatDoubleToString(currentNumber)).toString()
        } else {
            historyText = StringBuilder().append(historyText).append(currentOperation).append(formatDoubleToString(currentNumber)).toString()
        }

        return StringBuilder().append(historyText).append(EQUAL).append(formatDoubleToString(currentResult)).toString()
    }




}