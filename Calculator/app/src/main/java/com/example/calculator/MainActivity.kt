package com.example.calculator

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression
import java.math.BigDecimal

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val dataModel: DataModel by viewModels()
    var dotCondition:Boolean = true
    var signCondition:Boolean = false
    var isPortrait = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.numbers_holder, NumbersFragment.newInstance())
            .commit()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.scientific_holder, ScientificFragment.newInstance())
            .commit()

        binding.mainEditText.showSoftInputOnFocus = false
        binding.mainEditText.requestFocus()
        binding.secondEditText.showSoftInputOnFocus = false
        binding.mainEditText.customSelectionActionModeCallback = object : ActionMode.Callback {
            override fun onCreateActionMode(p0: ActionMode?, p1: Menu?): Boolean {
                return false
            }

            override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
                //p1?.clear()
                return true
            }

            override fun onActionItemClicked(p0: ActionMode?, p1: MenuItem?): Boolean {
                return false
            }

            override fun onDestroyActionMode(p0: ActionMode?) {

            }
        }
        binding.buttonSetLandscape?.setOnClickListener {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

            Toast.makeText(baseContext, "Landscape Orientation", Toast.LENGTH_SHORT).show()
        }
        binding.buttonSetLayout?.setOnClickListener {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            Toast.makeText(baseContext, "Portrait Orientation", Toast.LENGTH_SHORT).show()
        }
        dataModel.message.observe(this) {
            when(it){
                "0" -> {
                    checkWheretoInsert(binding.mainEditText,"0",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"0")
                    calculate(binding.mainEditText)
                }
                "1" ->{
                    checkWheretoInsert(binding.mainEditText,"1",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"1")
                    calculate(binding.mainEditText)
                }
                "2" ->{
                    checkWheretoInsert(binding.mainEditText,"2",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"2")
                    calculate(binding.mainEditText)
                }
                "3" ->{
                    checkWheretoInsert(binding.mainEditText,"3",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"3")
                    calculate(binding.mainEditText)
                }
                "4" ->{
                    checkWheretoInsert(binding.mainEditText,"4",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"4")
                    calculate(binding.mainEditText)
                }
                "5" ->{
                    checkWheretoInsert(binding.mainEditText,"5",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"5")
                    calculate(binding.mainEditText)
                }
                "6" ->{
                    checkWheretoInsert(binding.mainEditText,"6",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"6")
                    calculate(binding.mainEditText)
                }
                "7" ->{
                    checkWheretoInsert(binding.mainEditText,"7",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"7")
                    calculate(binding.mainEditText)
                }
                "8" ->{
                    checkWheretoInsert(binding.mainEditText,"8",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"8")
                    calculate(binding.mainEditText)
                }
                "9" ->{
                    checkWheretoInsert(binding.mainEditText,"9",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"9")
                    calculate(binding.mainEditText)
                }
                "." ->{
                    checkWheretoInsert(binding.mainEditText,".",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,".")
                    calculate(binding.mainEditText)
                }
                "+" ->{
                    checkWheretoInsert(binding.mainEditText,"+",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"+")
                    if(binding.mainEditText.text.isNotEmpty() && !binding.mainEditText.text.endsWith("(")
                        && binding.mainEditText.text.toString() !="-"){
                        calculate(binding.mainEditText)
                    }

                }
                "−" ->{
                    checkWheretoInsert(binding.mainEditText,"-",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"-")
                    if(binding.mainEditText.text.toString() != "-"){
                        calculate(binding.mainEditText)
                    }
                }
                "×" ->{
                    checkWheretoInsert(binding.mainEditText,"×",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"×")
                    if(binding.mainEditText.text.isNotEmpty() && !binding.mainEditText.text.endsWith("(")
                        && binding.mainEditText.text.toString() !="-"){
                        calculate(binding.mainEditText)
                    }
                }
                "÷" ->{
                    checkWheretoInsert(binding.mainEditText,"÷",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"÷")
                    if(binding.mainEditText.text.isNotEmpty() && !binding.mainEditText.text.endsWith("(")
                        &&  binding.mainEditText.text.toString() !="-"){
                        calculate(binding.mainEditText)
                    }
                }
                "(" ->{
                    //binding.mainEditText.append("%")
                    checkWheretoInsert(binding.mainEditText,"(",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"(")
                }
                ")" ->{
                    checkWheretoInsert(binding.mainEditText,")",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,")")
                    calculate(binding.mainEditText)
                }
                "clear" ->{
                    binding.mainEditText.setText("")
                    binding.secondEditText.setText("")

                }
                "delete" ->{
                    customDeleteInEdit(binding.mainEditText)
                    if(binding.mainEditText.text.isNotEmpty() &&
                        binding.mainEditText.text.toString() != "." &&
                        binding.mainEditText.text.toString() != "√" &&
                        binding.mainEditText.text.toString() != "-" &&
                        binding.mainEditText.text.toString() != "+" &&
                        binding.mainEditText.text.toString() != "×" &&
                        binding.mainEditText.text.toString() != "÷") {
                        calculate(binding.mainEditText)
                    }
                    else{
                        binding.secondEditText.setText("")
                    }

                }
                "equals" ->{
                    val reg = "[.][+×÷-]$".toRegex()
                    val reg2 = "[.+×÷-]$".toRegex()
                    if(binding.secondEditText.text.toString()!="Error" && binding.secondEditText.text.isNotEmpty()){
                        binding.mainEditText.setText(binding.secondEditText.text.toString())
                        binding.mainEditText.setSelection(binding.mainEditText.text.length)
                        binding.secondEditText.setText("")
                    }
                    else if(binding.secondEditText.text.isEmpty() && binding.mainEditText.text.isNotEmpty()){
                        if(binding.mainEditText.text.toString().contains(reg)){
                            binding.mainEditText.setText(binding.mainEditText.text.toString().dropLast(2))
                            binding.mainEditText.setSelection(binding.mainEditText.text.length)
                        }
                        else if(binding.mainEditText.text.toString().contains(reg2)){
                            binding.mainEditText.setText(binding.mainEditText.text.toString().dropLast(1))
                            binding.mainEditText.setSelection(binding.mainEditText.text.length)
                        }
                    }
                }
                "!" ->{
                    var cur = binding.mainEditText.selectionEnd
                    if(cur != 0) {
                        checkWheretoInsert(binding.mainEditText, "!", ::addCustomCharInEdit)
                        //addCustomCharInEdit(binding.mainEditText,"!")
                        calculate(binding.mainEditText)
                    }
                    else{
                        Toast.makeText(applicationContext,"invalid format",Toast.LENGTH_SHORT).show()
                    }
                }
                "pi" ->{
                    checkWheretoInsert(binding.mainEditText,"π",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"π")
                    calculate(binding.mainEditText)
                }
                "e" ->{
                    checkWheretoInsert(binding.mainEditText,"e",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"e")
                    calculate(binding.mainEditText)
                }
                "e^x" ->{
                    checkWheretoInsert(binding.mainEditText,"e^(",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"e^(")
                    calculate(binding.mainEditText)
                }
                "x^2" ->{
                    checkWheretoInsert(binding.mainEditText,"^(2)",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"^(2)")
                    if(binding.mainEditText.text.isNotEmpty()) {
                        calculate(binding.mainEditText)
                    }
                }
                "x^y" ->{
                    checkWheretoInsert(binding.mainEditText,"^(",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"^(")
                    //calculate(binding.mainEditText)
                }
                "ln" ->{
                    checkWheretoInsert(binding.mainEditText,"ln(",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"ln(")
                    calculate(binding.mainEditText)
                }
                "log" ->{
                    checkWheretoInsert(binding.mainEditText,"log(",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"log(")
                    calculate(binding.mainEditText)
                }
                "sin" ->{
                    checkWheretoInsert(binding.mainEditText,"sin(",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"sin(")
                    calculate(binding.mainEditText)
                }
                "cos" ->{
                    checkWheretoInsert(binding.mainEditText,"cos(",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"cos(")
                    calculate(binding.mainEditText)
                }
                "tan" ->{
                    checkWheretoInsert(binding.mainEditText,"tan(",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"tan(")
                    calculate(binding.mainEditText)
                }
                "2^x" ->{
                    checkWheretoInsert(binding.mainEditText,"2^(",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"2^(")
                    calculate(binding.mainEditText)
                }
                "√" ->{
                    checkWheretoInsert(binding.mainEditText,"√(",::addCustomCharInEdit)
                    //addCustomCharInEdit(binding.mainEditText,"√(")
                }

            }

        }
    }

    private fun checkWheretoInsert(edit: EditText, symbol: String, addChar:(EditText,String)->Unit){
        val Sign = "[-+×÷()]".toRegex()
        val Sign2 = "[-+×÷.!^(]".toRegex()
        val numbers = "[0-9]".toRegex()
        val startSelection: Int = edit.selectionStart

        var selectedWord = ""
        var length = 0
        val str = edit.text.toString().split(Sign).toTypedArray()
        for (currentWord in str) {
            println(currentWord)
            length += currentWord.length + 1
            if (length > startSelection) {
                selectedWord = currentWord

                break
            }
        }
            if(selectedWord == "cos" || selectedWord  == "sin" || selectedWord == "tan" ||
                selectedWord =="ln" || selectedWord =="log"){
                var cursorPos: Int = edit.selectionStart
                if((edit.text.toString()[cursorPos]=='s'&&edit.text.toString()[cursorPos+1]=='i')
                    || edit.text.toString()[cursorPos]=='l' || edit.text.toString()[cursorPos]=='t'
                    || edit.text.toString()[cursorPos]=='c'){
                    if(symbol.contains(numbers))
                    {
                        edit.text = edit.text.insert(cursorPos, symbol)
                        edit.text = edit.text.insert(cursorPos+1, "×")
                        edit.setSelection(cursorPos+2)
                    }
                    else if(!symbol.contains(Sign2)){
                        edit.text = edit.text.insert(cursorPos, symbol)
                        edit.setSelection(cursorPos+symbol.length)
                    }

                }

            }
            else{
                addCustomCharInEdit(edit, symbol)
            }
    }



    private fun addCustomCharInEdit(edit: EditText,symbol: String){
        var cursorPos: Int = edit.selectionEnd
        var str : String = edit.text.toString()
        val Sign = "[-+×÷^]".toRegex()
        val Sign2 = "[-+×÷()]".toRegex()
        val regex = "[-+×÷()][.]".toRegex()
        val endOnNumbersOrEorPi = "[.0-9eπ]$".toRegex()
        if(symbol.contains(Sign)&& (!symbol.contains("2^(") && !symbol.contains("e^("))){
            if(cursorPos==0){
                if(symbol=="-"){
                    if(edit.text.isEmpty()){
                        edit.text.insert(cursorPos, symbol)
                        edit.setSelection(cursorPos + symbol.length)
                    }
                    else{
                        if(!str[cursorPos].toString().contains(Sign)){
                            edit.text.insert(cursorPos, symbol)
                            edit.setSelection(cursorPos + symbol.length)
                        }
                    }
                }
            }
            else if(str[cursorPos-1]=='('){
                if(symbol=="-"){
                    edit.text.insert(cursorPos, symbol)
                    edit.setSelection(cursorPos + symbol.length)
                }

            }
            else if(cursorPos==str.length){
                if(!str[cursorPos-1].toString().contains(Sign)){
                    edit.text.insert(cursorPos, symbol)
                    edit.setSelection(cursorPos + symbol.length)
                }
            }
            else if(!str[cursorPos-1].toString().contains(Sign) && !str[cursorPos].toString().contains(Sign)){
                edit.text.insert(cursorPos, symbol)
                edit.setSelection(cursorPos + symbol.length)
            }
        }
        else if(symbol=="."){
            val strs = str.split(Sign).toTypedArray()
            var index:Int=0
            val str2 = str.substring(0,cursorPos)
            for(c in str2){
                if(c.toString().contains(Sign)){
                    index++
                }
            }
            if(!strs[index].contains(".")){
                edit.text.insert(cursorPos, symbol)
                edit.setSelection(cursorPos + symbol.length)
            }
        }
        else{
            edit.text.insert(cursorPos, symbol)
            edit.setSelection(cursorPos + symbol.length)
        }
        if(edit.text.toString().startsWith(".")){
            edit.setText(edit.text.insert(0, "0"))
            edit.setSelection(cursorPos+symbol.length+1)
        }
        if(edit.text.contains(regex)){
            edit.setText(edit.text.insert(cursorPos, "0"))
            edit.setSelection(cursorPos+2)
        }
        cursorPos = edit.selectionEnd
        str=edit.text.toString()
        if(cursorPos>symbol.length && str[cursorPos-1-symbol.length].toString().contains(endOnNumbersOrEorPi)
            && (symbol=="(" || symbol=="e" || symbol=="π"|| symbol=="√(" || symbol=="cos(" || symbol=="sin("
                    || symbol=="log(" || symbol=="ln(" || symbol=="tan(")){
//Toast.makeText(applicationContext, "first", Toast.LENGTH_SHORT).show()
            edit.text.insert(cursorPos-symbol.length, "×")
            edit.setSelection(cursorPos + 1)
        }
        if(cursorPos>symbol.length && str[cursorPos-1-symbol.length].toString().contains("!")
            && (symbol=="(" || symbol=="e" || symbol=="π"|| symbol=="√(" || symbol=="cos(" || symbol=="sin("
                    || symbol=="log(" || symbol=="ln(" || symbol=="tan(" || symbol.contains(endOnNumbersOrEorPi))){
//Toast.makeText(applicationContext, "first", Toast.LENGTH_SHORT).show()
            edit.text.insert(cursorPos-symbol.length, "×")
            edit.setSelection(cursorPos + 1)
        }
    }


    private fun calculate(edit: EditText) {
        try {
            val Sign = "[-+×÷()]".toRegex()
            val Sign2 = "[-+×÷]".toRegex()
            val mas = edit.text.toString().split(Sign).toTypedArray()
            val mas2 = edit.text.toString().split(Sign2).toTypedArray()
            for(i in mas){
                if((i.length>7 || i.contains(".")) && i.contains("!")){
                    binding.secondEditText.setText("Error")
                    return
                }
            }
            for(i in mas2){
                if(i.length>8 && i.contains("^")){
                    if(i.endsWith("^(1")){
                        break
                    }
                    binding.secondEditText.setText("Error")
                    return
                }
            }
            val startSelection: Int = edit.selectionStart
            val endsWithSign = "[-+×÷√.]$".toRegex()
            val notOnlyDigit = "[^.0123456789]".toRegex()
            val regex2 = "[.][-+×÷^]".toRegex()
            var str:String = edit.text.toString()
            if(str.contains(regex2)){
                str=str.replace(".-","-")
                str=str.replace(".+","+")
                str=str.replace(".×","×")
                str=str.replace(".÷","÷")
                str=str.replace(".^","^")
//str=str.replace("!!","!)!")
                var i = binding.mainEditText.selectionEnd
//str=str.replaceRange(i-3,i-3,"(")
            }
            if(str.contains("e!")){
                binding.secondEditText.setText("Error")
                return
            }
            if(str.contains("π!")){
                binding.secondEditText.setText("Error")
                return
            }
            if(str.contains("!!")){
                var selectedWord = ""
                var length = 0
                val str2 = edit.text.toString().split(Sign).toTypedArray()
                for (currentWord in str2) {
//println(currentWord)
                    length += currentWord.length + 1
                    if (length > startSelection) {
                        selectedWord = currentWord
                        break
                    }
                }
                val k=selectedWord.length
                if(selectedWord.startsWith('1') && selectedWord[1]=='!'){
                    str=str.replace(selectedWord,"1")
                }
                if(selectedWord.startsWith('2') && selectedWord[1]=='!'){
                    str=str.replace(selectedWord,"2")
                }
                if(selectedWord.startsWith('0') && selectedWord[1]=='!'){
                    str=str.replace(selectedWord,"1")
                }
//str=str.replace("2!","2")
                str=str.replace("!!","!)!")
                var i = binding.mainEditText.selectionEnd

                str=str.replaceRange(i-k,i-k,"(")
//if(selectedWord.contains())
                //var cursorPos:Int = binding.mainEditText.selectionEnd
                //edit.setSelection(cursorPos-(selectedWord.length+1))

                //val strs = str.split(Sign).toTypedArray()
                //var index:Int=0
                //val str3 = str.substring(0,i)
                //for(c in str2){
                    //if(c.toString().contains(Sign)){
                        //index++
                    //}
                //}
                //for(a in 0..index){

                //}


                /*if(!selectedWord.endsWith("!!") && selectedWord.contains(notOnlyDigit)){
                    var cursorPos:Int = binding.mainEditText.selectionEnd
                    binding.mainEditText.setSelection(cursorPos-(selectedWord.length+2))
                    var text: String = binding.mainEditText.text.toString()
                    var text1: String = text.substring(0, cursorPos)
                    text1 = text1.dropLast(1)
                    binding.mainEditText.setSelection(cursorPos - 1)

                    binding.mainEditText.text.insert(cursorPos, "!")
                    binding.mainEditText.setSelection(cursorPos + 1)
                    var text2: String = text.substring(cursorPos, text.length)
                    text = text1 + text2
                    binding.mainEditText.setText(text)


                    binding.mainEditText.setSelection(text.length)


                }

                 */



            }
            if(str.contains(endsWithSign)){
                str=str.dropLast(1)
            }
            if(str.count{it=='('} > str.count{it==')'}){
                for(i in 1..str.count{it=='('}-str.count{it==')'}){
                    str= "$str)"
                }
            }
            if(str.substring(1,str.length).contains(notOnlyDigit) || str.contains("√") || str.contains("π") ||str.contains("e") ) {
                str = str.replace('×', '*')
                str = str.replace('÷', '/')
                str = str.replace("log","log10")
                val e = Expression(str)
                val result:Double = e.calculate()
                if(!result.isNaN()) {
                    val bigD: BigDecimal = BigDecimal.valueOf(result)
                    binding.secondEditText.setText(bigD.stripTrailingZeros().toPlainString())
//binding.secondEditText.setText(result.toString())
                }
                else {
                    binding.secondEditText.setText("Error")
                }
            }
            else{
                binding.secondEditText.setText("")
            }
        }
        catch (e:Exception){
//binding.secondEditText.setText(e.message)
            binding.secondEditText.setText("Error")
        }
    }

    private fun customDeleteInEdit(editText: EditText) {
        var cursorPosition: Int = editText.selectionEnd;
        if (editText.text.isNotEmpty() && cursorPosition != 0) {
            if (editText.selectionStart == editText.selectionEnd) {

                var text: String = editText.text.toString()
                if(cursorPosition>1 && editText.text.toString()[cursorPosition-1]=='(' && (editText.text.toString()[cursorPosition-2]=='s'
                            || editText.text.toString()[cursorPosition-2]=='n' || editText.text.toString()[cursorPosition-2]=='g')){
                    if(editText.text.toString()[cursorPosition-3]=='l'){
                        text = text.removeRange(cursorPosition-3, cursorPosition)
                        editText.setText(text)
                        editText.setSelection(cursorPosition-3)
                    }
                    else {
                        text = text.removeRange(cursorPosition - 4, cursorPosition)
                        editText.setText(text)
                        editText.setSelection(cursorPosition - 4)
                    }
                }
                else if((editText.text.toString()[cursorPosition-1]=='s' || editText.text.toString()[cursorPosition-1]=='n'
                            || editText.text.toString()[cursorPosition-1]=='g') && editText.text.toString()[cursorPosition]=='('){
                    if(editText.text.toString()[cursorPosition-2]=='l'){
                        text = text.removeRange(cursorPosition-2, cursorPosition+1)
                        editText.setText(text)
                        editText.setSelection(cursorPosition-2)
                    }
                    else {
                        text = text.removeRange(cursorPosition - 3, cursorPosition + 1)
                        editText.setText(text)
                        editText.setSelection(cursorPosition - 3)
                    }
                }
                else if(editText.text.toString()[cursorPosition-1]=='l' && editText.text.toString()[cursorPosition]=='n'){
                    text = text.removeRange(cursorPosition - 1, cursorPosition + 2)
                    editText.setText(text)
                    editText.setSelection(cursorPosition - 1)
                }
                else if((editText.text.toString()[cursorPosition-1]=='o' && editText.text.toString()[cursorPosition]=='s')||
                    (editText.text.toString()[cursorPosition-1]=='i' && editText.text.toString()[cursorPosition]=='n')||
                    (editText.text.toString()[cursorPosition-1]=='a' && editText.text.toString()[cursorPosition]=='n')||
                    (editText.text.toString()[cursorPosition-1]=='o' && editText.text.toString()[cursorPosition]=='g')){
                    text = text.removeRange(cursorPosition-2, cursorPosition+2)
                    editText.setText(text)
                    editText.setSelection(cursorPosition-2)
                }
                else if((editText.text.toString()[cursorPosition-1]=='c' && editText.text.toString()[cursorPosition]=='o')||
                    (editText.text.toString()[cursorPosition-1]=='s' && editText.text.toString()[cursorPosition]=='i')||
                    (editText.text.toString()[cursorPosition-1]=='t' && editText.text.toString()[cursorPosition]=='a')||
                    (editText.text.toString()[cursorPosition-1]=='l' && editText.text.toString()[cursorPosition]=='o')){
                    text = text.removeRange(cursorPosition-1, cursorPosition+3)
                    editText.setText(text)
                    editText.setSelection(cursorPosition-1)
                }
                else {
                    var text1: String = text.substring(0, cursorPosition)
                    text1 = text1.dropLast(1)
                    var text2: String = text.substring(cursorPosition, text.length)
                    text = text1 + text2
                    editText.setText(text)
                    editText.setSelection(cursorPosition - 1)
                }
            } else {
                var text: String = editText.text.toString()
                var cursorStart: Int = editText.selectionStart
                var cursorEnd: Int = editText.selectionEnd
                text = text.removeRange(cursorStart, cursorEnd)
                editText.setText(text)
                editText.setSelection(cursorStart)
            }

        }
    }


}