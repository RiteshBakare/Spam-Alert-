package com.gmail.bakareritesh1729.spamalert

import android.app.Dialog
import android.content.Context

object Constants {

    private var smsList: ArrayList<String> = ArrayList()

    private var spamSMSList : ArrayList<String> = ArrayList()

    private var regularSMSList : ArrayList<String> = ArrayList()

    fun setUserSMS(smsList: ArrayList<String>) {
        this.smsList = smsList
    }
    fun getUserSMS(): ArrayList<String>? {
        return smsList.ifEmpty {
            null
        }
    }

    fun setSpamSMSList(spamSMSList: ArrayList<String>) {
        this.spamSMSList = spamSMSList
    }
    fun getSpamSMSList(): ArrayList<String>? {
        return spamSMSList.ifEmpty {
            null
        }
    }

    fun setRegularSMSList(regularSMSList: ArrayList<String>) {
        this.regularSMSList = regularSMSList
    }
    fun getRegularSMSList(): ArrayList<String>? {
        return regularSMSList.ifEmpty {
            return null
        }
    }

    fun progressBarDialog(context: Context): ProgressIndicator {

        val progressBar = Dialog(context)
        progressBar.setContentView(R.layout.progress_bar)

        fun showProgressBar() {
            progressBar.show()
        }

        fun dismissProgressBar() {
            progressBar.dismiss()
        }

        return object : ProgressIndicator {
            override fun show() {
                showProgressBar()
            }

            override fun dismiss() {
                dismissProgressBar()
            }
        }
    }

    interface ProgressIndicator {
        fun show()
        fun dismiss()
    }


}