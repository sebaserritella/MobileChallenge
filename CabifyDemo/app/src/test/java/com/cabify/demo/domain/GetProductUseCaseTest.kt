package com.cabify.demo.domain

import com.cabify.demo.data.domain.repository.ProductRepositoryImpl
import com.cabify.demo.data.domain.usecase.GetProductUseCase
import com.cabify.demo.data.model.Product
import com.cabify.demo.data.model.Tshirt
import com.cabify.demo.data.model.Voucher
import com.cabify.demo.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

class GetProductUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    var repository = ProductRepositoryImpl()

    @InjectMocks
    lateinit var useCase: GetProductUseCase

    @Before
    fun setUp() {
        // initializes vars with @Mock
        MockitoAnnotations.initMocks(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun whenNoFilters_allNewsResourcesAreReturned() = runBlocking {
        val expected = sampleProducts
        val list = useCase.invoke().first()

        Assert.assertEquals(
            expected,
            list,
        )
    }

    @Test
    fun `fetch products should be 3`() = runBlocking {
        val expected = sampleProducts.count()
        val list = launch {
            useCase.invoke()
        }

        Assert.assertEquals(
            expected,
            list,
        )
    }
}

private val sampleProducts = listOf(
    Voucher(
        code = "VOUCHER", name = "Cabify Voucher", price = BigDecimal.valueOf(5)
    ),
    Tshirt(
        code = "TSHIRT", name = "Cabify T-Shirt", price = BigDecimal.valueOf(20)
    ),
    Product(
        code = "MUG", name = "Cabify Coffee Mug", price = BigDecimal.valueOf(7.5)
    ),
)

