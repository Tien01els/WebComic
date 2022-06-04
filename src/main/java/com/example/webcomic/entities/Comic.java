package com.example.webcomic.entities;

import com.example.webcomic.dtos.ComicDTO;
import com.example.webcomic.entities.embedded.Chapter;
import com.example.webcomic.enums.Mode;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "Comic")
public class Comic {
    @Id
    private String id;
    private String comicName;
    private List<String> author;
    private String genres;
    private String status;
    private Long view;
    private String content;
    private Date lastUpdate;
    private Mode shareMode;
    private List<Chapter> listChap;

    public Comic(ComicDTO comic) {
        this.id = comic.getId();
        this.comicName = comic.getComicName();
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
        comic.getListChap().forEach(chapter -> {
            if (this.listChap == null) {
                this.listChap = new ArrayList<>();
            }
            this.listChap.add(new Chapter(chapter));
        });
    }
}
