package com.example.catchat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class InboxFragment : Fragment() {
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_inbox, container, false)
    }
}