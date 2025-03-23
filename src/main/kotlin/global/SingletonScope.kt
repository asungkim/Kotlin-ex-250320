package global

import domain.system.SystemController
import domain.wiseSaying.WiseSayingController
import domain.wiseSaying.WiseSayingRepository
import domain.wiseSaying.WiseSayingService

object SingletonScope {
    val wiseSayingRepository = WiseSayingRepository()
    val wiseSayingService = WiseSayingService()
    val wiseSayingController = WiseSayingController()
    val systemController = SystemController()
}