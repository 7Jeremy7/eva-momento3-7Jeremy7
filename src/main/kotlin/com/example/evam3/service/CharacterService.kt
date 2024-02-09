package com.example.evam3.service

import com.example.evam3.entity.Character
import com.example.evam3.repository.CharacterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CharacterService {
    @Autowired
    lateinit var characterRepository: CharacterRepository

    @Autowired
    lateinit var sceneRepository: CharacterRepository

    fun list ():List<Character>{
        return characterRepository.findAll()
    }

    fun save (character: Character):Character{
        character.description?.takeIf { it.trim().isNotEmpty() }
            ?: throw Exception("la desccripicón no debe ser vacio")

        try{
            sceneRepository.findById(character.sceneId)
                ?:throw Exception(" ${character.sceneId} no encontrada")
            return characterRepository.save(character)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }

    }


    fun update(model: Character): Character{
        try {
            characterRepository.findById(model.id)
                ?: throw Exception("el  ${model.id} no existe")

            return characterRepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }


    fun updateCell(model: Character): Character{
        try {
            val response = characterRepository.findById((model.id))
                ?: throw  Exception("Id no existe")

            response.apply {
                description = model.description
            }
            return characterRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun listById (id:Long?):Character?{
        return characterRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = characterRepository.findById(id)
                ?: throw Exception("ID no existe")
            characterRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}