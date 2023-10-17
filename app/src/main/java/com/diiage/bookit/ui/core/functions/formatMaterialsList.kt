package com.diiage.bookit.ui.core.functions

import androidx.compose.runtime.Composable
import com.diiage.bookit.domain.models.Material

@Composable
fun formatMaterialsList(materials: List<Material>?): String {
    return when {
        materials == null || materials.isEmpty() -> "Aucune information"
        materials.size == 1 -> materials[0].libelle
        materials.size == 2 -> "${materials[0].libelle}, ${materials[1].libelle}"
        else -> "${materials[0].libelle}, ${materials[1].libelle}..."
    }
}