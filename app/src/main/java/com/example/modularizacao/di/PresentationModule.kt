package com.example.modularizacao.di

import com.example.modularizacao.MainListAdapter
import com.example.modularizacao.MainViewModel
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    single {
        Picasso.get()
    }

    factory {
        MainListAdapter(picasso = get())
    }

    viewModel {
        MainViewModel(
            useCase = get(),
            uiScheduler = AndroidSchedulers.mainThread()
        )
    }
}
