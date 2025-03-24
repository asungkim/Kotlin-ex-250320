package domain.wiseSaying.repository

import domain.wiseSaying.entity.WiseSaying
import global.AppConfig
import java.nio.file.Path

class WiseSayingFileRepository : WiseSayingRepository {
    private var lastId: Int = 0

    init {
        initTable()
    }

    val tableDirPath: Path
        get() = AppConfig.tableDirPath.resolve("wiseSaying")

    override fun save(wiseSaying: WiseSaying): WiseSaying {
        val target = if (wiseSaying.isNew()) wiseSaying.copy(id = getNextId()) else wiseSaying
        return target.also { saveFile(it) }
    }

    private fun saveFile(wiseSaying: WiseSaying) {
        tableDirPath.resolve("${wiseSaying.id}.json").toFile().writeText(wiseSaying.jsonStr)
    }

    override fun findAll(): List<WiseSaying> {
        return tableDirPath.toFile()
            .listFiles()
            ?.filter { it.extension == "json" }
            ?.map { WiseSaying.fromJson(it.readText()) }
            .orEmpty()
    }

    override fun findById(id: Int): WiseSaying? {
        TODO("Not yet implemented")
    }

    override fun delete(wiseSaying: WiseSaying) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        tableDirPath.toFile().deleteRecursively()
    }

    fun initTable() {
        tableDirPath.toFile().run {
            if (!exists()) {
                mkdirs()
            }
        }
    }

    fun saveLastId(id: Int) {
        tableDirPath.resolve("lastId.txt").toFile().writeText(id.toString())
    }

    fun loadLastId(): Int {
        tableDirPath.resolve("lastId.txt").toFile().run {
            if (!exists()) {
                return 1
            }
            return readText().toInt()
        }
    }

    private fun getNextId(): Int {
        return loadLastId().also {
            saveLastId(it + 1)
        }
    }


}