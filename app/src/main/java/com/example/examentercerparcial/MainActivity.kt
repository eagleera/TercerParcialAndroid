package com.example.examentercerparcial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var personList: MutableList<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        personList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("persons")

        btnSave.setOnClickListener {
            savePerson()
        }

        ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    personList.clear()
                    for (person in p0.children){
                        val psn = person.getValue(Person::class.java)
                        personList.add(psn!!)
                    }
                    val adapter = PersonAdapter(applicationContext, R.layout.persons, personList)
                    listPerson.adapter = adapter
                }
            }
        })
    }
    private fun savePerson(){
        val name = editName.text.toString().trim()
        if(name.isEmpty()){
            editName.error = "Ingresa un nombre raza :("
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("persons")
        val personId = ref.push().key
        if (personId != null) {
            val person = personId?.let { Person(it, name, ratingBar.numStars) }
            ref.child(personId).setValue(person).addOnCompleteListener{
                Toast.makeText(this, "Se ha agregado una persona", Toast.LENGTH_LONG).show()
            }
        }

    }
}
