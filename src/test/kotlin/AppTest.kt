import global.SingletonScope
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class AppTest {

    @BeforeEach
    fun setUp() {
        SingletonScope.wiseSayingMemRepository.clear()
        SingletonScope.wiseSayingFileRepository.clear()
        SingletonScope.wiseSayingFileRepository.initTable()
    }

    @Test
    fun `명언 등록`() {
        val out = TestBot.run(
            """
                등록
                인생은 짧고, 예술은 길다.
                헨리 장
            """.trimIndent()
        )

        println(out)

        assertThat(out).contains("명언 :")
        assertThat(out).contains("작가 :")
        assertThat(out).contains("1번 명언이 등록되었습니다.")
    }

    @Test
    fun `명언 목록`() {
        val result = TestBot.run(
            """
             등록
             나의 죽음을 적들에게 알리지 말라.
             충무공 이순신
             등록
             천재는 99%의 노력과 1%의 영감이다.
             에디슨
             목록
         """
        )

        println(result)

        assertThat(result).contains("1 / 충무공 이순신 / 나의 죽음을 적들에게 알리지 말라.")
        assertThat(result).contains("2 / 에디슨 / 천재는 99%의 노력과 1%의 영감이다.")
    }

    @Test
    fun `명언 목록(keyword)`() {
        val result = TestBot.run(
            """
             등록
             나의 죽음을 적들에게 알리지 말라.
             충무공 이순신
             등록
             천재는 99%의 노력과 1%의 영감이다.
             에디슨
             등록
             나는 천재다.
             김아성
             목록?keywordType=content&keyword=천재
         """
        )

        println(result)

        assertThat(result).contains("3 / 김아성 / 나는 천재다.")
        assertThat(result).contains("2 / 에디슨 / 천재는 99%의 노력과 1%의 영감이다.")
        assertThat(result).contains("검색타입 : content")
        assertThat(result).contains("검색어 : 천재")
    }

    @Test
    fun `빌드`() {
        val result = TestBot.run(
            """
              등록
              나의 죽음을 적들에게 알리지 말라.
              충무공 이순신
              등록
              천재는 99%의 노력과 1%의 영감이다.
              에디슨
              빌드
          """
        )

        assertThat(result)
            .contains("data.json 파일의 내용이 갱신되었습니다.");
    }

    @Test
    fun `makeSampleData`() {
        TestBot.makeSampleData(10)

        val result = TestBot.run(
            """
                목록
            """.trimIndent()
        )

        assertThat(result).contains("1 / ")
        assertThat(result).contains("10 / ")
    }
}