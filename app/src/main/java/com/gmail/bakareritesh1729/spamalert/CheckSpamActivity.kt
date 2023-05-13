package com.gmail.bakareritesh1729.spamalert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gmail.bakareritesh1729.spamalert.Model.Constants
import com.gmail.bakareritesh1729.spamalert.Model.Constants.progressBarDialog
import com.gmail.bakareritesh1729.spamalert.Model.Constants.setRegularSMSList
import com.gmail.bakareritesh1729.spamalert.Model.Constants.setSpamSMSList
import com.gmail.bakareritesh1729.spamalert.Model.UserData
import com.gmail.bakareritesh1729.spamalert.databinding.ActivityCheckSpamBinding
import com.gmail.bakareritesh1729.spamalert.helpers.TextClassificationClient
import kotlinx.coroutines.*
import org.tensorflow.lite.support.label.Category


class CheckSpamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckSpamBinding

    private var smsList: ArrayList<UserData>? = ArrayList()

    private var spamSMSList: ArrayList<UserData> = ArrayList()
    private var regularSMSList: ArrayList<UserData> = ArrayList()
    private var bankSMSList: ArrayList<UserData> = ArrayList()

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

                if (checkForBank(smsList!![counter].address.toString()) == true) {
                    bankSMSList.add(smsList!![counter])
                    Log.e("bankMessage","the bank SMS is ${smsList!![counter].address.toString()}")
                    counter++
                    continue
                }

                val toSend: String = smsList!![counter].body!!
                val result: List<Category> = client.classify(toSend)
                val score = result[1].score

                if (score > 0.8999) {
                    spamSMSList.add(smsList!![counter])
                } else {
                    regularSMSList.add(smsList!![counter])
                }
                counter++
            }


            val myScope = CoroutineScope(Dispatchers.IO)

            myScope.launch {

                setSpamSMSList(spamSMSList)
                setRegularSMSList(regularSMSList)
                if (bankSMSList.isNotEmpty()) {
                    Constants.setBankSMSList(bankSMSList)
//                    Log.e("bankMessage", "bank SMS list is Not Empty ")
                }

                withContext(Dispatchers.Main) {
                    progressBar.dismiss()
//                    Log.e("bankMessage", "list is $bankSMSList ")
                    startActivity(Intent(this@CheckSpamActivity, SpamSMSResultActivity::class.java))
                    finish()
                }
            }


        }


    }

    private fun checkForBank(text: String): Boolean {
        if (text.contains("BT-INDBNK") or text.contains("BW-SBIUPI")) {
            return true
        }
        if (text.contains("JD-BOIIND") or text.contains("VM-BOIIND")
            or text.contains("AX-BOIIND") or text.contains("JD-IOBCHN") or text.contains("AX-IPBMSG")
            or text.contains("VM-UNIONB") or text.contains("JD-AxisBk") or text.contains("JM-FCHDFC")
        ) {
            return true
        }
        return false
    }

}
