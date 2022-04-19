package com.itis.androiditis_hw_2.di.modules

import com.itis.androiditis_hw_2.data.impl.CharactersRepositoryImpl
import com.itis.androiditis_hw_2.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {
    @Binds
    abstract fun characterRepository(
        charactersRepositoryImpl: CharactersRepositoryImpl
    ): CharacterRepository
}
