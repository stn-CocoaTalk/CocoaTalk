package com.stn.cocoatalk.di

import com.stn.cocoatalk.data.remote.LoginService
import com.stn.cocoatalk.data.remote.LoginServiceImpl
import com.stn.cocoatalk.data.remote.ChatSocketService
import com.stn.cocoatalk.data.remote.ChatSocketServiceImpl
import com.stn.cocoatalk.data.remote.MessageService
import com.stn.cocoatalk.data.remote.MessageServiceImpl
import com.stn.cocoatalk.data.repository.CocoaRepositoryImpl
import com.stn.cocoatalk.domain.repository.CocoaRepository
import com.stn.cocoatalk.domain.usecase.AddUser
import com.stn.cocoatalk.domain.usecase.CocoaUseCases
import com.stn.cocoatalk.domain.usecase.GetUserByEmail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    @Provides
    @Singleton
    fun provideMessageService(client: HttpClient): MessageService {
        return MessageServiceImpl(client)
    }

    @Provides
    @Singleton
    fun provideChatSocketService(client: HttpClient): ChatSocketService {
        return ChatSocketServiceImpl(client)
    }

    @Provides
    @Singleton
    fun provideLoginService(client: HttpClient): LoginService {
        return LoginServiceImpl(client)
    }

    @Provides
    @Singleton
    fun provideRepository(loginService: LoginService): CocoaRepository {
        return CocoaRepositoryImpl(loginService)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: CocoaRepository): CocoaUseCases {
        return CocoaUseCases(
            GetUserByEmail = GetUserByEmail(repository),
            AddUser = AddUser(repository)
        )
    }
}