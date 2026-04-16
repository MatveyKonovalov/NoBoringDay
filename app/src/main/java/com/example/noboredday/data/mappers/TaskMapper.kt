package com.example.noboredday.data.mappers

import com.example.noboredday.data.dtomodels.BoredTranslations
import com.example.noboredday.data.dtomodels.IdeasDto
import com.example.noboredday.domain.models.Ideas
import javax.inject.Inject

class TaskMapper @Inject constructor() {
    fun toDomain(ideasDto: IdeasDto) = Ideas(
        title = BoredTranslations.translate(ideasDto.activityENG),
        description = ideasDto.type,
        key = ideasDto.key
    )
}