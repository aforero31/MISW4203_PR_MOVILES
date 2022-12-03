package com.example.moviles_vinils_app_grupo_32.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.moviles_vinils_app_grupo_32.R


class MenuCollectorFragment : Fragment(), View.OnClickListener {

    var _navHostFragment: NavHostFragment? = null
    var _navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        _navController = _navHostFragment!!.navController
        val view = inflater?.inflate(R.layout.fragment_menu_collector,
            container, false)
        val userMenuButton: Button = view.findViewById(R.id.button6)
        userMenuButton.setOnClickListener(this);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.actionBar?.title= getString(R.string.title_menu)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button6 -> _navController?.navigate(R.id.action_menuCollectorFragment_to_createAlbumFragment)
        }
    }

}