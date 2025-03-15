package com.giandiport80.topediaapp.core.data.source.remote.network

import com.giandiport80.topediaapp.core.data.source.model.AlamatToko
import com.giandiport80.topediaapp.core.data.source.model.Category
import com.giandiport80.topediaapp.core.data.source.model.Home
import com.giandiport80.topediaapp.core.data.source.model.Product
import com.giandiport80.topediaapp.core.data.source.remote.request.CreateTokoRequest
import com.giandiport80.topediaapp.core.data.source.remote.request.LoginRequest
import com.giandiport80.topediaapp.core.data.source.remote.request.RegisterRequest
import com.giandiport80.topediaapp.core.data.source.remote.request.UpdateProfileRequest
import com.giandiport80.topediaapp.core.data.source.remote.response.BaseListResponse
import com.giandiport80.topediaapp.core.data.source.remote.response.BaseResponse
import com.giandiport80.topediaapp.core.data.source.remote.response.LoginResponse
import com.giandiport80.topediaapp.core.data.source.remote.response.TokoResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @GET("home")
    suspend fun getHome(): Response<BaseResponse<Home>>

    @POST("login")
    suspend fun login(
        @Body login: LoginRequest
        // @Field("email") email: String,
        // @Field("email") password: String,
    ): Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Body data: RegisterRequest
    ): Response<LoginResponse>

    @PUT("update-user/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body data: UpdateProfileRequest
    ): Response<LoginResponse>

    @Multipart
    @POST("upload-user/{id}")
    suspend fun uploadImageUser(
        @Path("id") id: Int,
        @Part data: MultipartBody.Part
    ): Response<LoginResponse>

    // TOKO
    @POST("toko")
    suspend fun createToko(
        @Body data: CreateTokoRequest
    ): Response<BaseResponse<TokoResponse>>

    @GET("toko-user/{id}")
    suspend fun getUser(
        @Path("id") id: Int,
    ): Response<LoginResponse>

    @GET("alamat/toko/{id}")
    suspend fun getAlamatToko(
        @Path("id") tokoId: Int? = null,
    ): Response<BaseListResponse<AlamatToko>>

    @POST("alamat")
    suspend fun createAlamatToko(
        @Body data: AlamatToko,
    ): Response<BaseResponse<AlamatToko>>

    @PUT("alamat/{id}")
    suspend fun updateAlamatToko(
        @Path("id") id: Int? = null,
        @Body data: AlamatToko,
    ): Response<BaseResponse<AlamatToko>>

    @DELETE("alamat/{id}")
    suspend fun deleteAlamatToko(
        @Path("id") tokoId: Int? = null,
    ): Response<BaseResponse<AlamatToko>>


    @GET("products/toko/{id}")
    suspend fun getProduct(
        @Path("id") tokoId: Int? = null,
    ): Response<BaseListResponse<Product>>

    @POST("products")
    suspend fun createProduct(
        @Body data: Product,
    ): Response<BaseResponse<Product>>

    @PUT("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int? = null,
        @Body data: Product,
    ): Response<BaseResponse<Product>>

    @DELETE("products/{id}")
    suspend fun deleteProduct(
        @Path("id") tokoId: Int? = null,
    ): Response<BaseResponse<Product>>

    @Multipart
    @POST("upload/product")
    suspend fun uploadProduct(
        @Part data: MultipartBody.Part? = null
    ): Response<BaseResponse<String>>

    @GET("category")
    suspend fun getCategory(): Response<BaseListResponse<Category>>

    @POST("category")
    suspend fun createCategory(
        @Body data: Category,
    ): Response<BaseResponse<Category>>

    @PUT("category/{id}")
    suspend fun updateCategory(
        @Path("id") id: Int? = null,
        @Body data: Category,
    ): Response<BaseResponse<Category>>

    @DELETE("category/{id}")
    suspend fun deleteCategory(
        @Path("id") tokoId: Int? = null,
    ): Response<BaseResponse<Category>>

    @Multipart
    @POST("upload/{id}")
    suspend fun uploadImage(
        @Path("id") id: String? = null,
        @Part data: MultipartBody.Part? = null
    ): Response<BaseResponse<String>>
}