package com.ayhanunal.dialogdesignsharedpref

import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Display
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    //method-1
    var askAgainIsChecked = false

    //method-2
    val PREFS_NAME = "checkBoxDialogSP"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*
        //method-1 ----------------------------------------------------------------------------------------------------------------------------------------------------------
        val dialogClickListener: DialogInterface.OnClickListener = DialogInterface.OnClickListener { dialog, which ->
            if (askAgainIsChecked){
                Toast.makeText(applicationContext, "TRUE", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext, "FALSE", Toast.LENGTH_LONG).show()
            }
        }

        val builder = AlertDialog.Builder(this)
        val dialog = builder.setTitle("AAAAAAAAAAAA")
            .setMultiChoiceItems(R.array.do_not_show_again_array, null, DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                if (isChecked){
                    askAgainIsChecked = true
                }
            })
            .setPositiveButton("Ja", dialogClickListener)
            .setNegativeButton("Nein", dialogClickListener).show();

        //----------------------------------------------------------------------------------------------------------------------------------------------------------------

         */

        //method-2
        val adb = AlertDialog.Builder(this)
        val adbInflater = LayoutInflater.from(this)
        val eulaLayout = adbInflater.inflate(R.layout.checkbox, null)
        val settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val skipMessage = settings.getString("skipMsg", "not_checked")


        val dontShowAgain = eulaLayout.findViewById<CheckBox>(R.id.skip)
        adb.setView(eulaLayout)
        adb.setCancelable(false)
        adb.setTitle("Your Title")
        adb.setMessage("Your Dialog Messgae")
        adb.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            var checkBoxResult = "not_checked"
            if(dontShowAgain.isChecked){
                checkBoxResult = "checked"
            }

            val settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            val editor = settings.edit()

            editor.putString("skipMsg", checkBoxResult)
            editor.commit()


        })
        if (!skipMessage.equals("checked")){
            adb.show()
        }



    }
}