package com.example.webcomic.dtos;

import com.example.webcomic.entities.embedded.Chapter;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ChapterDTO {
    private Integer chapterNumber;
    private String chapterName;
    private List<String> listChapterURL;

    public ChapterDTO(Chapter chapter) {
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
