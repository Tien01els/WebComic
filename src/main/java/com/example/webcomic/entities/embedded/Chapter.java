package com.example.webcomic.entities.embedded;

import com.example.webcomic.dtos.ChapterDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Chapter {
    private Integer chapterNumber;
    private String chapterName;
    private List<String> listChapterURL;

    public Chapter(ChapterDTO chapter) {
        this.chapterNumber = chapter.getChapterNumber();
        this.chapterName = chapter.getChapterName();
        chapter.getListChapterURL().forEach(url -> {
            if (this.listChapterURL == null) {
                this.listChapterURL = new ArrayList<>();
            }
            this.listChapterURL.add(url);
        });
    }

}
