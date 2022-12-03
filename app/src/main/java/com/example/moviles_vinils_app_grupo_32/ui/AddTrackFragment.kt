package com.example.moviles_vinils_app_grupo_32.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviles_vinils_app_grupo_32.R
import com.example.moviles_vinils_app_grupo_32.databinding.AddTrackFragmentBinding
import com.example.moviles_vinils_app_grupo_32.models.Track
import com.example.moviles_vinils_app_grupo_32.ui.adapters.TracksAdapter
import com.example.moviles_vinils_app_grupo_32.viewmodels.AddTrackViewModel

class AddTrackFragment : Fragment(), View.OnClickListener {

    var _navHostFragment: NavHostFragment? = null
    var _navController: NavController? = null

    private var _binding:AddTrackFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AddTrackViewModel
    private var viewModelAdapter: TracksAdapter? = null

    private lateinit var name: EditText
    private lateinit var minutes: EditText
    private lateinit var seconds: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddTrackFragmentBinding.inflate(inflater, container, false)
        _navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        _navController = _navHostFragment!!.navController
//        val view =  inflater?.inflate(R.layout.add_track_fragment, container, false)
        val view = binding.root

        val addTrackButton: Button = view.findViewById(R.id.addTrackBtn)
        val saveTracksButton: Button = view.findViewById(R.id.saveTracksBtn)
        viewModelAdapter = TracksAdapter()

        name = view.findViewById(R.id.trackName)
        minutes = view.findViewById(R.id.trackMinutes)
        seconds = view.findViewById(R.id.trackSeconds)

        addTrackButton.setOnClickListener(this)
        saveTracksButton.setOnClickListener(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.tracksRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.add_track_to_album_title)
        val args: AddTrackFragmentArgs by navArgs()
        viewModel = ViewModelProvider(this, AddTrackViewModel.Factory(activity.application, args.albumId)).get(
            AddTrackViewModel::class.java)
        viewModel.tracksForNewAlbumLiveData.observe(viewLifecycleOwner, Observer<List<Track>?>{
            it.apply { viewModelAdapter!!.tracks = this}
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean>{ isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.addTrackBtn -> {
                viewModel.addTrackToList(getTrackObject())
            }
            R.id.saveTracksBtn -> {
                viewModel.saveAllTracks()
                val action = AddTrackFragmentDirections.actionAddTrackFragmentToCreateAlbumFragment()
                if (action != null) {
                    _navController?.navigate(action)
                }
            }
        }
    }

    private fun getTrackObject(): Track {
        var minutesValue = minutes.text.toString()
        var secondsValue = seconds.text.toString()
        var duration = minutesValue.plus(":").plus(secondsValue)
        val track = Track(
            null,
            name.text.toString(),
            duration
        )
        minutes.text.clear()
        seconds.text.clear()
        name.text.clear()
        return track
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}