package fr.mbds.dtla.movieapp.ui.tvdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.mbds.dtla.movieapp.R
import fr.mbds.dtla.movieapp.databinding.FragmentMovieDetailBinding
import fr.mbds.dtla.movieapp.databinding.FragmentTvshowDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVShowDetailFragment : Fragment() {

    private val viewModel: TVShowDetailViewModel by viewModel()
    private lateinit var binding: FragmentTvshowDetailBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvshowDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            tvshow.observe(viewLifecycleOwner, { tvshow ->
                binding.tvshowTitle.text = tvshow.name
                binding.contentDate.text = tvshow.date
                binding.contentVote.text = tvshow.vote
                binding.contentDescription.text = tvshow.description
                Picasso.get().load(tvshow.backdrop).into(binding.tvshowImg)
            })

            error.observe(viewLifecycleOwner, {
                binding.tvshowTitle.text = getString(R.string.error)
                binding.titleDate.text = ""
                binding.titleDescription.text = ""
                binding.titleVote.text = ""
            })

            getTVShow(requireArguments().getString("tvshowId")!!.toInt(), getString(R.string.language))
        }
    }
}
