package fr.mbds.dtla.movieapp.ui.hometv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.mbds.dtla.movieapp.R
import fr.mbds.dtla.movieapp.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeTVFragment : Fragment() {

    private val homeViewModel: HomeTVViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(homeViewModel) {
            token.observe(viewLifecycleOwner, {
                //récupérer les catégories
                getCategories(getString(R.string.language))
            })

            categories.observe(viewLifecycleOwner, {
                binding.categoryList.adapter = CategoryTVAdapter(it) { category ->
                    val direction = HomeTVFragmentDirections.actionHomeTVFragmentToTVShows(category.id.toString(), category.name)
                    findNavController().navigate(direction)
                }
            })

            error.observe(viewLifecycleOwner, {
                //afficher l'erreur
            })
        }
    }
}
