package com.example.webcomic.dtos;

import com.example.webcomic.enums.Mode;
import com.example.webcomic.entities.Comic;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ComicDTO {
    private String id;
    private String comicName;
    private String thumbnail;
    private List<String> author;
    private String genres;
    private String status;
    private Long view;
    private String content;
    private Date lastUpdate;
    private Mode shareMode;
    private List<ChapterDTO> listChap;

    public ComicDTO(Comic comic) {
        this.id = comic.getId();
        this.comicName = comic.getComicName();
        this.thumbnail = comic.getThumbnail();
        comic.getAuthor().forEach(author -> {
            if (this.author == null) {
                this.author = new ArrayList<>();
            }
            this.author.add(author);
        });
        this.genres = comic.getGenres();
        this.status = comic.getStatus();
        this.view = comic.getView();
        this.content = comic.getContent();
        this.lastUpdate = comic.getLastUpdate();
        this.shareMode = comic.getShareMode();
        if (comic.getListChap() != null) {
            comic.getListChap().forEach(chapter -> {
                if (this.listChap == null) {
                    this.listChap = new ArrayList<>();
                }
                this.listChap.add(new ChapterDTO(chapter));
            });
        }
    }
}
