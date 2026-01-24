package com.example.electronicazytron.viewModel

import androidx.lifecycle.ViewModel
import com.example.electronicazytron.model.entities.Usuario
import com.example.electronicazytron.model.repository.UsuarioRepository

class UsuarioViewModel : ViewModel() {

    private val repository = UsuarioRepository()

    fun insert(usuario: Usuario){
        repository.agregar(usuario)
    }
    fun validar(usuario: Usuario): Boolean{
        return repository.validar(usuario)
    }

}