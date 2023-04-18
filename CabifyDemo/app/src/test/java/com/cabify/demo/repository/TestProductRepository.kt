package com.cabify.demo.repository

import com.cabify.demo.data.model.Product
import com.cabify.demo.data.model.ResponseApi
import com.cabify.demo.data.domain.repository.ProductRepositoryImpl
import com.cabify.demo.data.model.Tshirt
import com.cabify.demo.data.model.Voucher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

class TestProductRepository {

    @InjectMocks
    private lateinit var productRepository: ProductRepositoryImpl

    @Before
    fun setUp() {
        // initializes vars with @Mock
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `getProducts should return expected products`() = runBlocking {
        val expected = sampleProducts
        val list = productRepository.getProducts().first().products

        Assert.assertEquals(
            expected,
            list,
        )
    }

    private val sampleProducts = listOf(
        Product(
            code = "VOUCHER", name = "Cabify Voucher", price = BigDecimal.valueOf(5)
        ),
        Product(
            code = "TSHIRT", name = "Cabify T-Shirt", price = BigDecimal.valueOf(20)
        ),
        Product(
            code = "MUG", name = "Cabify Coffee Mug", price = BigDecimal.valueOf(7.5)
        ),
    )
}

