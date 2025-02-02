package com.giandiport80.topediaapp.core.data.repository

import com.giandiport80.topediaapp.core.data.source.local.LocalDataSource
import com.giandiport80.topediaapp.core.data.source.remote.RemoteDataSource

class AppRepository(val local: LocalDataSource, val remoteDataSource: RemoteDataSource) {
}