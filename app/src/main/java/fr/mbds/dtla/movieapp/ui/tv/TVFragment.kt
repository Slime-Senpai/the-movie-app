package fr.mbds.dtla.movieapp.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.mbds.dtla.movieapp.R
import fr.mbds.dtla.movieapp.databinding.FragmentMoviesBinding
import fr.mbds.dtla.movieapp.ui.movies.MovieFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVFragment : Fragment() {

    private val viewModel: TVViewModel by viewModel()
    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            tvshows.observe(viewLifecycleOwner, {
                binding.categoryList.adapter = TVAdapter(it) { tvshow ->
                    val direction = TVFragmentDirections.actionTVShowsToTVShowDetail(tvshow.id.toString())
                    findNavController().navigate(direction)
                }
            })

            error.observe(viewLifecycleOwner, {
                //afficher l'erreur
            })

            (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_tvshows) + " - " + requireArguments().getString("categoryName")

            getTVShows(requireArguments().getString("categoryId")!!.toInt(), getString(R.string.language))
        }
    }
}
