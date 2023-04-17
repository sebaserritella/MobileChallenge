package com.cabify.demo.data.domain.service

import com.cabify.demo.data.model.ResponseApi

interface ProductLocalDataSource {

    fun getProducts(): ResponseApi
}