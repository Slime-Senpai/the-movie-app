package fr.mbds.dtla.movieapp.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.mbds.dtla.movieapp.R

class AboutFragment : Fragment() {

    private lateinit var aboutViewModel: AboutViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        aboutViewModel = ViewModelProvider(this)[AboutViewModel::class.java]
        val root = inflater.inflate(R.layout.fragment_about, container, false)
        val textView: TextView = root.findViewById(R.id.text_about)
        aboutViewModel.text.observe(viewLifecycleOwner, {
            textView.text = getString(it)
        })
        return root
    }
}
