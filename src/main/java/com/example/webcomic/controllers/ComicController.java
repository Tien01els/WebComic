package com.example.webcomic.controllers;

import com.example.webcomic.dtos.ComicDTO;
import com.example.webcomic.response.ResponseObject;
import com.example.webcomic.services.comic.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/app/1.0/comic")
public class ComicController {
    @Autowired
    private ComicService comicService;

    @PostMapping("/addComic")
    public ResponseEntity<ResponseObject> addComic(@RequestBody ComicDTO comicDTO) {
        return ResponseEntity.ok(comicService.addComic(comicDTO));
    }

    @PutMapping("/editComic")
    public ResponseEntity<ResponseObject> editComic(@RequestBody ComicDTO comicDTO) {
        return ResponseEntity.ok(comicService.editComic(comicDTO));
    }

    @GetMapping("/getAllComic")
    public ResponseEntity<ResponseObject> getAllComic() {
        return ResponseEntity.ok(comicService.getAllComic());
    }

    @GetMapping("/getComicInfo/{idComic}")
    public ResponseEntity<ResponseObject> getComicInfo(@PathVariable String idComic) {
        return ResponseEntity.ok(comicService.getComicInfo(idComic));
    }

    @GetMapping("/searchComics")
    public ResponseEntity<ResponseObject> searchComics(@RequestParam String keyword) {
        return ResponseEntity.ok(comicService.searchComics(keyword));
    }

    @GetMapping("/getFavComic")
    public ResponseEntity<ResponseObject> getFavComic(@RequestBody List<String> ListIdFavComic) {
        return ResponseEntity.ok(comicService.getFavComic(ListIdFavComic));
    }

    @GetMapping("/browseComic/{idComic}")
    public ResponseEntity<ResponseObject> browseComic(@PathVariable String idComic, @RequestBody Map<String, String> comicMode) {
        return ResponseEntity.ok(comicService.browseComic(idComic, comicMode.get("Mode")));
    }


    @GetMapping("/hideComic/{idComic}")
    public ResponseEntity<ResponseObject> hideComic(@PathVariable String idComic) {
        return ResponseEntity.ok(comicService.hideComic(idComic));
    }

}
