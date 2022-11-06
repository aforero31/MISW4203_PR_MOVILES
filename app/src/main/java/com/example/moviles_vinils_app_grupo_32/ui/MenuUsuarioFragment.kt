package com.example.moviles_vinils_app_grupo_32.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.moviles_vinils_app_grupo_32.R

class MenuUsuarioFragment : Fragment(), OnClickListener {
    var _navHostFragment: NavHostFragment? = null
    var _navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        _navController = _navHostFragment!!.navController
        val view = inflater?.inflate(R.layout.menu_usuario_fragment, container, false)
        val userMenuButton: Button = view.findViewById(R.id.button3)
        userMenuButton.setOnClickListener(this)
        val collectorMenuButton: Button = view.findViewById(R.id.button4)
        userMenuButton.setOnClickListener(this)
        collectorMenuButton.setOnClickListener(this)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.actionBar?.title= getString(R.string.title_menu)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button3 -> _navController?.navigate(R.id.action_menuUsuarioFragment_to_albumFragment)
            R.id.button4 -> _navController?.navigate(R.id.action_menuUsuarioFragment_to_musicianFragment2)
        }
    }
}