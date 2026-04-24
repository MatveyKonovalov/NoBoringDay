package com.example.noboredday.data.mappers

import com.example.noboredday.data.database.IdeaEntity
import com.example.noboredday.data.dtomodels.BoredTranslations
import com.example.noboredday.data.dtomodels.IdeasDto
import com.example.noboredday.domain.models.Ideas
import javax.inject.Inject

class TaskMapper @Inject constructor() {
    fun toDomainFromDto(ideasDto: IdeasDto) = Ideas(
        title = BoredTranslations.translate(ideasDto.activityENG),
        description = ideasDto.type,
        key = ideasDto.key
    )
    fun toEntityFromDto(idea: IdeasDto) = IdeaEntity(
        key = idea.key,
        title = idea.activityENG,
        description = idea.duration
    )
    fun toDomainFromEntity(ideaEntity: IdeaEntity) = Ideas(
        title = ideaEntity.title,
        description = ideaEntity.description,
        key = ideaEntity.key
    )
}