package com.cabify.demo.dependencyinjection

import com.cabify.demo.domain.usecase.GetProductUseCase
import org.koin.dsl.module

val usesCasesModules = module {
    single { GetProductUseCase(get()) }
}