package com.applicaton.internshipvk.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.applicaton.internshipvk.R
import com.applicaton.internshipvk.app.App
import com.applicaton.internshipvk.databinding.FragmentProductsBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private var isRotateLoadState = false

    private val bundle = Bundle()

    private val viewModel: ProductsViewModel by activityViewModels {
        (requireContext().applicationContext as App).getAppComponent().productsViewModelFactory()
    }

    private val pagedAdapter = ProductsAdapter { productModel ->
        onItemClick(productModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            isRotateLoadState = it.getBoolean(ARG_PARAM1)
        }
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        enterTransition = inflater.inflateTransition(R.transition.slide_left)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = pagedAdapter.withLoadStateFooter(CustomLoadStateAdapter())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefresh.setOnRefreshListener {
            if (isRotateLoadState) isRotateLoadState = false
            if (!isNetworkAvailable(requireContext())) {
                Toast.makeText(
                    context,
                    "Возникли проблемы с интернетом",
                    Toast.LENGTH_SHORT
                ).show()
                binding.swipeRefresh.isRefreshing = false
            } else pagedAdapter.refresh()
        }

        pagedAdapter.loadStateFlow.onEach {
            binding.swipeRefresh.isRefreshing = it.refresh == LoadState.Loading
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.state.onEach {
            binding.recyclerView.layoutManager?.onRestoreInstanceState(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.products.collect {
                    pagedAdapter.submitData(it)
                }

            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(ARG_PARAM1, true)
    }

     fun onItemClick(item: ProductModel) {
        bundle.putParcelable(ARG_PARAM2, item)
        binding.recyclerView.layoutManager?.onSaveInstanceState()
            ?.let { viewModel.setStateRecycler(it) }
        findNavController().navigate(R.id.action_ProductsFragment_to_InfoFragment, bundle)
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}