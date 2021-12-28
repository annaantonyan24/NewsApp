package com.example.domain.interactors

import com.example.core.ActionResult
import com.example.domain.model.Data

interface NewsInteractor {

    suspend operator fun invoke(): ActionResult<List<Data>>

}