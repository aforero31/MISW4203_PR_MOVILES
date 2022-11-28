package com.example.moviles_vinils_app_grupo_32.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.moviles_vinils_app_grupo_32.R
import com.example.moviles_vinils_app_grupo_32.models.Album
import com.example.moviles_vinils_app_grupo_32.network.NetworkServiceAdapter

class CreateAlbumFragment: Fragment(), View.OnClickListener {

    var _navHostFragment: NavHostFragment? = null
    var _navController: NavController? = null
    lateinit var name: EditText
    lateinit var cover: EditText
    lateinit var releaseDate: EditText
    lateinit var description: EditText
    lateinit var genre: EditText
    lateinit var recordLabel: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        _navController = _navHostFragment!!.navController
        val view = inflater?.inflate(
            R.layout.create_album_fragment,
            container, false)
        val userMenuButton: Button = view.findViewById(R.id.button5)
        name = view.findViewById(R.id.textAlbumName)
        cover = view.findViewById(R.id.cover)
        releaseDate = view.findViewById(R.id.releaseDate)
        description = view.findViewById(R.id.description)
        genre = view.findViewById(R.id.genre)
        recordLabel = view.findViewById(R.id.recordLabel)

        userMenuButton.setOnClickListener(this);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.create_album)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button5 -> {
                val album = Album(null,
                    name.text.toString(),
                    cover.text.toString(),
                    releaseDate.text.toString(),
                    description.text.toString(),
                    genre.text.toString(),
                    recordLabel.text.toString(), null, null)
                Log.d("Album", album.toString())
                NetworkServiceAdapter.getInstance(requireActivity().application).postAlbum(album, {
                    Log.d("Album response", it.toString())
                    Toast.makeText(activity, "Album created ...$it", Toast.LENGTH_LONG).show()
                },{
                    Log.e("tag", "error is " + it!!.message)
                    Toast.makeText(activity, "Error network ...${it!!.message}", Toast.LENGTH_LONG).show()
                })

            }
        }
    }
}