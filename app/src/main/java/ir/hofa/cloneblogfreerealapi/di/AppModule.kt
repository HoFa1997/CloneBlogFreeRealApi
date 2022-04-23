package ir.hofa.cloneblogfreerealapi.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hofa.cloneblogfreerealapi.common.Constants
import ir.hofa.cloneblogfreerealapi.data.remote.FreeRealAPI
import ir.hofa.cloneblogfreerealapi.data.repository.FreeRealApiRepositoryImpl
import ir.hofa.cloneblogfreerealapi.domain.repository.FreeRealApiRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideApi(): FreeRealAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(FreeRealAPI::class.java)
    }


    @Singleton
    @Provides
    fun provideRepository(api: FreeRealAPI): FreeRealApiRepository {
        return FreeRealApiRepositoryImpl(api)
    }

//    @Provides
//    @Singleton
//    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
//        return Room.databaseBuilder(
//            appContext,
//            AppDatabase::class.java,
//            "FreeRealAPI"
//        ).build()
//    }

//    @Singleton
//    @Provides
//    fun provideDao(appDatabase: AppDatabase): AppDao {
//        return appDatabase.productsDao()
//    }
}