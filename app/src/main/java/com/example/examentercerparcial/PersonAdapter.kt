package com.example.examentercerparcial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PersonAdapter(val mCtx: Context, val layoutResId: Int, val personList: List<Person>)
    :ArrayAdapter<Person>(mCtx, layoutResId, personList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val person = personList[position]
        textViewName.text = person.name
        return view;
    }

}