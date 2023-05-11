package com.gmail.bakareritesh1729.spamalert

import android.app.Dialog
import android.content.Context
import com.gmail.bakareritesh1729.spamalert.Model.UserData

object Constants {

    private var smsList: ArrayList<UserData> = ArrayList()

    private var spamSMSList : ArrayList<UserData> = ArrayList()

    private var regularSMSList : ArrayList<UserData> = ArrayList()

    fun setUserSMS(smsList: ArrayList<UserData>) {
        this.smsList = smsList
    }
    fun getUserSMS(): ArrayList<UserData>? {
        return smsList.ifEmpty {
            null
        }
    }

    fun setSpamSMSList(spamSMSList: ArrayList<UserData>) {
        this.spamSMSList = spamSMSList
    }
    fun getSpamSMSList(): ArrayList<UserData>? {
        return spamSMSList.ifEmpty {
            null
        }
    }

    fun setRegularSMSList(regularSMSList: ArrayList<UserData>) {
        this.regularSMSList = regularSMSList
    }
    fun getRegularSMSList(): ArrayList<UserData>? {
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