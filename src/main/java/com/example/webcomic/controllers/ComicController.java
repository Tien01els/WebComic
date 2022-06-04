package com.example.webcomic.controllers;

import com.example.webcomic.dtos.ComicDTO;
import com.example.webcomic.response.ResponseObject;
import com.example.webcomic.services.comic.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/app/1.0/comic")
public class ComicController {
    @Autowired
    private ComicService comicService;

    @PostMapping("/addComic")
    public ResponseEntity<ResponseObject> addComic(@RequestBody ComicDTO comic) {
        return ResponseEntity.ok(comicService.addComic(comic));
    }

    @PutMapping("/editComic")
    public ResponseEntity<ResponseObject> editComic(@RequestBody ComicDTO comic) {
        return ResponseEntity.ok(comicService.editComic(comic));
    }

    @GetMapping("/getComicInfo/{id}")
    public ResponseEntity<ResponseObject> getComicInfo(@PathVariable String id) {
        return ResponseEntity.ok(comicService.getComicInfo(id));
    }

    @GetMapping("/searchComics")
    public ResponseEntity<ResponseObject> searchComics(@RequestParam String keyword) {
        return ResponseEntity.ok(comicService.searchComics(keyword));
    }

    @GetMapping("/getFavComic")
    public ResponseEntity<ResponseObject> getFavComic(@RequestBody List<String> ListIdFavComic) {
        return ResponseEntity.ok(comicService.getFavComic(ListIdFavComic));
    }
}
