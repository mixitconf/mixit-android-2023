package org.mixitconf.service.synchronization

import androidx.room.Transaction
import org.mixitconf.api.TalkApiRepository
import org.mixitconf.database.LinkRepository
import org.mixitconf.database.SpeakerRepository
import org.mixitconf.api.dto.SpeakerApiDto
import retrofit2.Response
import timber.log.Timber

class SpeakerSynchronizationService(
    private val talkApiRepository: TalkApiRepository,
    private val speakerRepository: SpeakerRepository,
    private val linkRepository: LinkRepository
) : SynchronizationService<SpeakerApiDto>() {

    override suspend fun read(): Response<List<SpeakerApiDto>> =
        talkApiRepository.speakers()

    @Transaction
    override suspend fun save(result: List<SpeakerApiDto>, mode: Companion.SyncMode) {
        if(result.isEmpty()) {
            Timber.w("API returned no speaker")
            return
        }
        // We remove all speakers and links
        speakerRepository.deleteAll()
        linkRepository.deleteAll()

        val persistedLogin = mutableListOf<String>()
        result.map { it }.forEach { speaker ->
            if (!persistedLogin.contains(speaker.login)) {
                speakerRepository.create(speaker.toEntity())
                speaker.links
                    .map { it.toEntity(speaker.login) }
                    .forEach { linkRepository.create(it) }
                persistedLogin.add(speaker.login)
            }
        }
    }
}