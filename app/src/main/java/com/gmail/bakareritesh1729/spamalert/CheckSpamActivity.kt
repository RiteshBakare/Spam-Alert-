package com.gmail.bakareritesh1729.spamalert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.gmail.bakareritesh1729.spamalert.Constants.progressBarDialog
import com.gmail.bakareritesh1729.spamalert.Constants.setRegularSMSList
import com.gmail.bakareritesh1729.spamalert.Constants.setSpamSMSList
import com.gmail.bakareritesh1729.spamalert.databinding.ActivityCheckSpamBinding
import com.gmail.bakareritesh1729.spamalert.helpers.TextClassificationClient
import kotlinx.coroutines.*
import org.tensorflow.lite.support.label.Category


class CheckSpamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckSpamBinding

    private var smsList: ArrayList<String>? = ArrayList()

    private var spamSMSList: ArrayList<String> = ArrayList()
    private var regularSMSList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckSpamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = TextClassificationClient(applicationContext)
        client.load()

        val progressBar = progressBarDialog(this)

        progressBar.show()

        try {
            smsList = Constants.getUserSMS()
        } catch (e: java.lang.NullPointerException) {
            Log.e("message ", "error is ${e.message} ")
        }

        if (smsList != null) {

            var counter = 0

            while (counter < smsList!!.size) {

                val toSend: String = smsList!![counter]
                val result: List<Category> = client.classify(toSend)
                val score = result[1].score

                if (score > 0.8) {
                    spamSMSList.add(toSend)
                } else {
                    regularSMSList.add(toSend)
                }
                counter++
            }


            val myScope = CoroutineScope(Dispatchers.IO)

            myScope.launch {

                setSpamSMSList(spamSMSList)
                setRegularSMSList(regularSMSList)

                withContext(Dispatchers.Main) {
                    progressBar.dismiss()
                    startActivity(Intent(this@CheckSpamActivity, SpamSMSResultActivity::class.java))
                }
            }


        }


    }


}
