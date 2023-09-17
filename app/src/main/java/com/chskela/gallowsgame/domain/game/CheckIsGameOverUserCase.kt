package com.chskela.gallowsgame.domain.game

import com.chskela.gallowsgame.domain.settings.GetMaxNumberOfErrorsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CheckIsGameOverUserCase @Inject constructor(private val getMaxNumberOfErrorsUseCase: GetMaxNumberOfErrorsUseCase) {

    operator fun invoke(attempts: Int): Flow<Boolean> {
        return getMaxNumberOfErrorsUseCase().map { it <= attempts }
    }
}