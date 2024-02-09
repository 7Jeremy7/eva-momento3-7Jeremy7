package com.example.evam3.service

import com.example.evam3.entity.Scene
import com.example.evam3.repository.FilmRepository
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SceneService {
    @Autowired
    lateinit var sceneRepository: SceneRepository

    @Autowired
    lateinit var filmRepository: FilmRepository

    fun list ():List<Scene>{
        return sceneRepository.findAll()
    }

    fun save (scene: Scene):Scene{
        try{

            filmRepository.findById(scene.filmId)

            scene.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Film no debe ser vacio")


            return sceneRepository.save(scene)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }

    }
    fun update(model: Scene): Scene {
        try {
            sceneRepository.findById(model.id)
                ?: throw Exception("ID no existe")

            return sceneRepository.save(model)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun updateDescription(modelo: Scene): Scene {
        try{
            val response = sceneRepository.findById(modelo.id)
                ?: throw Exception("ID no existe")
            response.apply {
                description=modelo.description //un atributo del modelo
            }
            return sceneRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }


    fun listById (id:Long?): Scene?{
        return sceneRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = sceneRepository.findById(id)
                ?: throw Exception("ID no existe")
            sceneRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}