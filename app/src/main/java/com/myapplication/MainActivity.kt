package com.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class MainActivity : AppCompatActivity() {
    //lateinit var progress: ProgressBar
    lateinit var listView_details: ListView
    var arrayList_details:ArrayList<Model> = ArrayList();
    val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //progress = findViewById(R.id.progressBar)
        listView_details = findViewById<ListView>(R.id.listview) as ListView
        run("https://agf.ikomobi.fr/android-hiring-test/products.json")
    }
 /*
  * récuperer le flux JSON de l'url placer en parametre et utilise la class CustomAdapter pour placer
  * ce flux dans le ListView
  * @param {String} url
  */
    fun run(url: String){
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object:Callback{
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                var str = response.body()!!.string()
                val json = JSONArray(str)

                var i:Int = 0
                var size:Int = json.length()
                arrayList_details = ArrayList()

                for (i in 0 .. size-1){
                    var jsonObj = json.getJSONObject(i)
                    var model = Model(jsonObj.getString("name"), jsonObj.getDouble("price"), jsonObj.getString("image"));
                    arrayList_details.add(model)
                }

                runOnUiThread{
                    val obj: CustomAdapter = CustomAdapter(applicationContext, arrayList_details)
                    listView_details.adapter = obj
                }
            }
        })
    }
}