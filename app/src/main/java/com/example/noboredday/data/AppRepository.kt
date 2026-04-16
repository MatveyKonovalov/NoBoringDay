package com.example.noboredday.data

import com.example.noboredday.data.mappers.TaskMapper
import com.example.noboredday.data.network.NetworkWork
import com.example.noboredday.domain.models.Ideas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val taskMapper: TaskMapper,
    private val networkWork: NetworkWork,
) {
    suspend fun getRandomIdea(): Ideas{
        return withContext(Dispatchers.IO){
            val ideasDto = networkWork.getIdea()
            taskMapper.toDomain(ideasDto!!.first())
        }

    }

}