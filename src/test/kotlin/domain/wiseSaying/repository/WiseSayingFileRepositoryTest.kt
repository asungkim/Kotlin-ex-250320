package domain.wiseSaying.repository

import domain.wiseSaying.entity.WiseSaying
import global.SingletonScope.wiseSayingFileRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WiseSayingFileRepositoryTest {

    @BeforeEach
    fun setUp() {
        wiseSayingFileRepository.clear()
        wiseSayingFileRepository.initTable()
    }

    @Test
    fun `save`() {
        val wiseSaying = wiseSayingFileRepository
            .save(WiseSaying(content = "인생은 짧고, 예술은 길다.", author = "헨리 장"))

        val filePath = wiseSayingFileRepository
            .tableDirPath
            .toFile()
            .listFiles()
            ?.find { it.name == "${wiseSaying.id}.json" }

        assertThat(filePath).isNotNull()
    }

    @Test
    fun `saveLastId, loadLastId`() {
        wiseSayingFileRepository.saveLastId(10)
        assertThat(wiseSayingFileRepository.loadLastId()).isEqualTo(10)
    }
}