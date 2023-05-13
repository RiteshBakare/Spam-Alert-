package com.gmail.bakareritesh1729.spamalert.Model

import android.app.Dialog
import android.content.Context
import com.gmail.bakareritesh1729.spamalert.R

object Constants {

    private var smsList: ArrayList<UserData> = ArrayList()

    private var spamSMSList : ArrayList<UserData> = ArrayList()

    private var regularSMSList : ArrayList<UserData> = ArrayList()

    private var bankSMSList : ArrayList<UserData> = ArrayList()

    fun setUserSMS(smsList: ArrayList<UserData>) {
        Constants.smsList = smsList
    }
    fun getUserSMS(): ArrayList<UserData>? {
        return smsList.ifEmpty {
            null
        }
    }

    fun setSpamSMSList(spamSMSList: ArrayList<UserData>) {
        Constants.spamSMSList = spamSMSList
    }
    fun getSpamSMSList(): ArrayList<UserData>? {
        return spamSMSList.ifEmpty {
            null
        }
    }

    fun setRegularSMSList(regularSMSList: ArrayList<UserData>) {
        Constants.regularSMSList = regularSMSList
    }
    fun getRegularSMSList(): ArrayList<UserData>? {
        return regularSMSList.ifEmpty {
            return null
        }
    }

    fun setBankSMSList(bankSMSList: ArrayList<UserData>) {
        Constants.bankSMSList = bankSMSList
    }
    fun getBankSMSList(): ArrayList<UserData>? {
        return bankSMSList.ifEmpty {
            null
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