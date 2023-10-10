package com.diiage.bookit.ui.screens.profil

import android.app.Application
import com.diiage.bookit.ui.core.ViewModel


class ProfilViewModel(application: Application) : ViewModel<ProfilState>(ProfilState(), application)

data class ProfilState(
    val role: String? = null
)