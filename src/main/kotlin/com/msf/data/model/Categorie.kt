package com.msf.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Categorie(val category_id: Int, val category_name: String, val post_id: Int)

