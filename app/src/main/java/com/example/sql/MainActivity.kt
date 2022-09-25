package com.example.sql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val MyDb = MyDBHealper(this)
//        MyDb.addStudent("Manoj Kumar","B-Tech","A")
//        MyDb.addStudent("Deepak T R","BSc-Arg","E")
        Update_btn.setOnClickListener {
//            if (update_name.text.toString()!=""&&update_Class.text.toString()!=""){
                var u_id = idForUpdate.text.toString().toInt()
                var u_name = update_name.text.toString()
                var u_class =update_Class.text.toString()
                var u_sec =    Update_section.text.toString()

                MyDb.updates(u_id,u_name,u_class,u_sec)
//                Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(this, "Something Wrong", Toast.LENGTH_SHORT).show()
//            }
        }


val student = MyDb.getStudents()

        for(i in 0 until student.size){
            Log.d("Student${i+1}","Sno. ${student[i].Sno},${student[i].Name},${student[i].Class},${student[i].Sec}")
        }

 btn.setOnClickListener {
     if (name.text.toString()!="" && Class.text.toString()!=""){
         var name = name.text.toString()
         var Class = Class.text.toString()
         var section = section.text.toString()
         MyDb.addStudent(name,Class,section)
         Toast.makeText(this, "Save is Successful", Toast.LENGTH_SHORT).show()
     }
 }
        delete_btn.setOnClickListener {
            if (delete_id.text.toString().toInt()!=0){
                MyDb.Delete(delete_id.text.toString().toInt())
                Toast.makeText(this, "Delete Successful", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Enter the Id", Toast.LENGTH_SHORT).show()
            }

        }
    }
}