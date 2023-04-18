package com.cabify.demo.data.domain.repository

import com.cabify.demo.data.model.ResponseApi
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts(): Flow<ResponseApi>
    fun getLocalProducts(): ResponseApi
}