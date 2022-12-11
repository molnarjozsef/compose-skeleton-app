package com.jozsefmolnar.newskeletonapp.di

import com.jozsefmolnar.newskeletonapp.decoder.StringDecoder
import com.jozsefmolnar.newskeletonapp.decoder.UriDecoder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface StringDecoderModule {
    @Binds
    fun bindStringDecoder(uriDecoder: UriDecoder): StringDecoder
}
