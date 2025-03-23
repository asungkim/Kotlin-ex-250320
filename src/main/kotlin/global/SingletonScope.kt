package global

import domain.system.SystemController
import domain.wiseSaying.controller.WiseSayingController
import domain.wiseSaying.repository.WiseSayingFileRepository
import domain.wiseSaying.repository.WiseSayingMemRepository
import domain.wiseSaying.service.WiseSayingService

object SingletonScope {
    val wiseSayingMemRepository by lazy { WiseSayingMemRepository() }
    val wiseSayingFileRepository by lazy { WiseSayingFileRepository() }
    val wiseSayingService by lazy { WiseSayingService() }
    val wiseSayingController by lazy { WiseSayingController() }
    val systemController by lazy { SystemController() }
}