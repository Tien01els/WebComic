package com.example.webcomic.services.comic;

import com.example.webcomic.dtos.ComicDTO;
import com.example.webcomic.response.ResponseObject;
import org.springframework.stereotype.Service;

@Service
public interface ComicService {
    ResponseObject addComic(ComicDTO comicDTO);
    ResponseObject editComic(ComicDTO comicDTO);;
    ResponseObject getComicInfo(String id);
    ResponseObject searchComics(String name);
}
