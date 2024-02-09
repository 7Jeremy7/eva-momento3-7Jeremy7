package com.example.evam3.controller


import com.example.evam3.entity.Film
import com.example.evam3.service.FilmService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
@RestController
@RequestMapping("/film")
class FilmController {
    @Autowired
    lateinit var filmService: FilmService

    @GetMapping
    fun list(): ResponseEntity<*> {
        return ResponseEntity(filmService.list(), HttpStatus.OK)
    }

    @PostMapping
    fun save(@RequestBody film: Film): ResponseEntity<Film> {
        return ResponseEntity<Film>(filmService.save(film), HttpStatus.CREATED)
    }

    @PutMapping
    fun update (@RequestBody modelo:Film):ResponseEntity<Film>{
        return ResponseEntity(filmService.update(modelo), HttpStatus.OK)
    }
    @PatchMapping
    fun updateDirector (@RequestBody modelo: Film):ResponseEntity<Film>{
        return ResponseEntity(filmService.updateDirector(modelo), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*>{
        return ResponseEntity(filmService.listById (id), HttpStatus.OK)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return filmService.delete(id)
    }

}