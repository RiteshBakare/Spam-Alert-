package com.gmail.bakareritesh1729.spamalert.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gmail.bakareritesh1729.spamalert.SMSResult.RegularSMSFragment
import com.gmail.bakareritesh1729.spamalert.SMSResult.SpamSMSFragment
import com.gmail.bakareritesh1729.spamalert.TabLayout.MessageFragment
import com.gmail.bakareritesh1729.spamalert.TabLayout.PhoneFragment

class FragmentSMSResultAdapter(
    fragmentManager : FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            SpamSMSFragment()
        }
        else {
            RegularSMSFragment()
        }
    }


}