package com.example.moviles_vinils_app_grupo_32.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.moviles_vinils_app_grupo_32.R
import com.example.moviles_vinils_app_grupo_32.models.Album
import com.example.moviles_vinils_app_grupo_32.viewmodels.CreateAlbumViewModel

class CreateAlbumFragment: Fragment(), View.OnClickListener {

    var _navHostFragment: NavHostFragment? = null
    var _navController: NavController? = null
    private lateinit var name: EditText
    private lateinit var cover: EditText
    private lateinit var releaseDate: EditText
    private lateinit var description: EditText
    private lateinit var genre: EditText
    private lateinit var recordLabel: EditText
    private lateinit var viewModel: CreateAlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        _navController = _navHostFragment!!.navController
        val view = inflater?.inflate(
            R.layout.create_album_fragment,
            container, false)
        val createAlbumButton: Button = view.findViewById(R.id.createAlbumBtn)

        name = view.findViewById(R.id.textAlbumName)
        cover = view.findViewById(R.id.cover)
        releaseDate = view.findViewById(R.id.releaseDate)
        description = view.findViewById(R.id.description)
        genre = view.findViewById(R.id.genre)
        recordLabel = view.findViewById(R.id.recordLabel)

        createAlbumButton.setOnClickListener(this);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.create_album)
        viewModel = ViewModelProvider(this, CreateAlbumViewModel.Factory(activity.application)).get(
            CreateAlbumViewModel::class.java)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.createAlbumBtn -> {
                viewModel.createAlbum(getAlbumObject())
                val action = viewModel.newAlbum.value?.albumId?.let {
                    CreateAlbumFragmentDirections.actionCreateAlbumFragmentToAddTrackFragment(
                        it
                    )
                }
                if (action != null) {
                    _navController?.navigate(action)
                }
            }
        }
    }

    private fun getAlbumObject(): Album {
        return Album(
            null,
            name.text.toString(),
            cover.text.toString(),
            releaseDate.text.toString(),
            description.text.toString(),
            genre.text.toString(),
            recordLabel.text.toString(), null, null
        )
    }
}