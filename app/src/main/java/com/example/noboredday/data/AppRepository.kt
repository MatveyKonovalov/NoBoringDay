package com.example.noboredday.data

import com.example.noboredday.data.database.IdeaDao
import com.example.noboredday.data.dtomodels.IdeasDto
import com.example.noboredday.data.mappers.TaskMapper
import com.example.noboredday.data.network.NetworkWork
import com.example.noboredday.domain.models.Ideas
import com.example.noboredday.domain.repository.AppRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class AppRepository @Inject constructor(
    private val taskMapper: TaskMapper,
    private val networkWork: NetworkWork,
    private val ideaDao: IdeaDao
) : AppRepositoryInterface {
    override suspend fun getRandomIdea(): Ideas {
        return withContext(Dispatchers.IO) {
            val ideasDto = networkWork.getIdea()

            savedIdea(ideasDto!!.first())
            taskMapper.toDomainFromDto(ideasDto!!.first())
        }

    }

    private suspend fun savedIdea(ideas: IdeasDto) {
        withContext(Dispatchers.IO) {
            ideaDao.insertIdea(taskMapper.toEntityFromDto(ideas))
        }
    }

    override suspend fun getIdeas(): List<Ideas> {
        return withContext(Dispatchers.IO) {
            val ideasEntity = ideaDao.getAllIdeas()
            ideasEntity.map { ideaEntity -> taskMapper.toDomainFromEntity(ideaEntity) }.reversed()
        }
    }

    override suspend fun getIdeaFromLStore(): Ideas {
        return withContext(Dispatchers.IO){
            val randomIndex = Random.nextInt(0, activities.size)
            ideaDao.insertIdea(taskMapper.toEntityFromDomain(activities[randomIndex]))
            activities[randomIndex]
        }

    }
    suspend fun deleteIdea(key: String){
        withContext(Dispatchers.IO){
            ideaDao.deleteIdea(key)
        }
    }

}