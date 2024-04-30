package com.rubenmackin.realtimebasico.data

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.rubenmackin.realtimebasico.Todo
import kotlin.random.Random

class FirebaseInstance(context: Context) {

    private val database = Firebase.database
    private val myRef = database.reference

    init {
        FirebaseApp.initializeApp(context)
    }

    fun writeOnFirebase(title:String, description:String) {
        val randonValue: String = Random.nextInt(1, 200).toString()
        //myRef.setValue("Mi primera escritura: $randonValue")
        val newItem = myRef.push()
        newItem.setValue(Todo(title, description))
    }

    fun setupDatabaseListener(postListener: ValueEventListener) {
        database.reference.addValueEventListener(postListener)
    }

    /*private fun getGenericTodoTaskItem(randomValue: String): Todo {
        return Todo(title = "tarea $randomValue", description = "Esto es una descripción")
    }*/

    fun removeFromDatabase(reference: String) {
        myRef.child(reference).removeValue()
    }

    fun updateFromDatabase(reference: String) {
        myRef.child(reference).child("done").setValue(true)
    }
}