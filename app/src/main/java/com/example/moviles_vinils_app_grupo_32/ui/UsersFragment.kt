package com.example.moviles_vinils_app_grupo_32.ui

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.moviles_vinils_app_grupo_32.R
import com.example.moviles_vinils_app_grupo_32.databinding.UsersFragmentBinding

class UsersFragment : Fragment(), OnClickListener {
    var _navHostFragment: NavHostFragment? = null
    var _navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        _navController = _navHostFragment!!.navController
        val view = inflater?.inflate(R.layout.users_fragment,
            container, false)
        val userMenuButton: Button = view.findViewById(R.id.button)
        userMenuButton.setOnClickListener(this);
        val collectorMenuButton: Button = view.findViewById(R.id.button2)
        userMenuButton.setOnClickListener(this);
        collectorMenuButton.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.button -> _navController?.navigate(R.id.action_usersFragment_to_menuUsuarioFragment)
            R.id.button2 -> _navController?.navigate(R.id.action_usersFragment_to_collectorDetailFragment)
        }

    }

}