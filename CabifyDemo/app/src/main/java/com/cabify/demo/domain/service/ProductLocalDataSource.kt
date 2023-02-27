package com.cabify.demo.domain.service

import com.cabify.demo.data.model.ResponseApi

interface ProductLocalDataSource {

    fun getProducts(): ResponseApi
}