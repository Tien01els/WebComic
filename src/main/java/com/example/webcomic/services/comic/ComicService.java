package com.example.webcomic.services.comic;

import com.example.webcomic.dtos.ComicDTO;
import com.example.webcomic.response.ResponseObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ComicService {
    ResponseObject addComic(ComicDTO comicDTO);
    ResponseObject editComic(ComicDTO comicDTO);;
    ResponseObject getComicInfo(String id);
    ResponseObject searchComics(String name);
    ResponseObject getFavComic(List<String> listIdFavComic);
}
