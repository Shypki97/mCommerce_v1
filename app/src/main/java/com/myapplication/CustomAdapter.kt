package com.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso

class CustomAdapter (context: Context, arrayListDetails:ArrayList<Model>) : BaseAdapter() {
    private val layoutInflater: LayoutInflater
    private val arrayListDetails: ArrayList<Model>
    private val context: Context?

    init {
        this.layoutInflater = LayoutInflater.from(context)
        this.arrayListDetails = arrayListDetails
        this.context = context
    }

/*
 * fonction qui va permettre de remplir le contenue de ListView
 * @param {Int} position
 * @param {View} p1
 * @param {ViewGroup} parent
 * @return {View}
 */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val listRowHolder: ListRowHolder
        if (convertView == null) {
            view = this.layoutInflater.inflate(R.layout.listview, parent, false)
            listRowHolder = ListRowHolder(view)
            view.tag = listRowHolder
        } else {
            view = convertView
            listRowHolder = view.tag as ListRowHolder
        }

        listRowHolder.name.text = arrayListDetails.get(position).name
        listRowHolder.price.setText(arrayListDetails.get(position).price.toString())
        Picasso.get().load(arrayListDetails.get(position).image).into(listRowHolder.image)
        return view
    }
    /*
     * Renvoie la position d'un item placer en paramétre
     * @param {Int} position
     * @return {Any}
     */
    override fun getItem(position: Int): Any {
        return arrayListDetails.get(position)
    }
    /*
     * Renvoie l'id de l'item placer en parametre
     * @param {Int} position
     * @return {Long}
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /*
     * Renvoie la taille de l'ArrayList<Modele>
     * @return{Int}
     */
    override fun getCount(): Int {
        return arrayListDetails.size
    }
}

class ListRowHolder(row: View?) {
    public val name: TextView
    public val price: TextView
    public val image: ImageView
    public val linearLayout: LinearLayout

    /*
     * permet d'initialiser les TextView, ImageView et LinearLayout
     */
    init {
        this.name = row?.findViewById<TextView>(R.id.name) as TextView
        this.price = row?.findViewById<TextView>(R.id.price) as TextView
        this.image = row?.findViewById(R.id.image)
        this.linearLayout = row?.findViewById<LinearLayout>(R.id.linearLayout) as LinearLayout
    }
}
