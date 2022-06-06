package com.example.webcomic.services.comic;

import com.example.webcomic.dtos.ComicDTO;
import com.example.webcomic.enums.Mode;
import com.example.webcomic.repositories.AccountRepository;
import com.example.webcomic.response.ResponseObject;
import com.example.webcomic.entities.Comic;
import com.example.webcomic.repositories.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ComicServiceImpl implements ComicService {

    @Autowired
    ComicRepository comicRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public ResponseObject addComic(ComicDTO comicDTO) {
        return new ResponseObject("Success", "Add comic successfully",
                new ComicDTO(comicRepository.save(new Comic(comicDTO))));
    }

    @Override
    public ResponseObject editComic(ComicDTO comicDTO) {
        Optional<Comic> comicEdited = comicRepository.findById(comicDTO.getId());
        if (comicEdited.get().equals(comicDTO)) {
            return new ResponseObject("Fail", "The new information comic is the same as previous information comic", "");
        }
        return new ResponseObject("Success", "Updated successfully", new ComicDTO(comicRepository.save(new Comic(comicDTO))));
    }

    @Override
    public ResponseObject getComicInfo(String id) {
        //String pdfPath = "https://firebasestorage.googleapis.com/v0/b/testjdf-debe9.appspot.com/o/LC-purple.pdf?alt=media&token=26071fbd-5fa7-489e-9414-a45485c81313";
//        String pdfPath = "D:/Image/LC-purple.pdf";
//        File file = new File(pdfPath);
//        System.out.println(file.toString());
//        try (PDDocument doc = PDDocument.load(file)) {
//            PDFRenderer pdfRenderer = new PDFRenderer(doc);
//            List<byte[]> bytesList = new ArrayList<>();
//            for (int page = 0; page < doc.getNumberOfPages(); ++page) {
//                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
//                byte[] bytes = toByteArray(bim, "png");
//                bytesList.add(bytes);
//            }
//            return new ResponseObject("Success", "Get comic successfully", bytesList);
//        } catch (InvalidPasswordException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Optional<Comic> comic = comicRepository.findComicById(id);
        List<String> authorList = new ArrayList<>();
        comic.get().getAuthor().forEach(idAuthor -> {
            authorList.add(accountRepository.findAccountById(idAuthor).get().getUser().getFullname());
        });
        comic.get().setAuthor(authorList);
        return new ResponseObject("Success", "Get comic successfully", new ComicDTO(comic.get()));
    }
    public static byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }
    @Override
    public ResponseObject searchComics(String keyword) {
        List<Comic> comicList = comicRepository.findByName(keyword);
        List<ComicDTO> comicDTOList = new ArrayList<>();
        comicList.forEach(comic -> {
            comicDTOList.add(new ComicDTO(comic));
        });
        return new ResponseObject("Success", "Found comics successfully", comicDTOList);
    }

    @Override
    public ResponseObject getFavComic(List<String> listIdFavComic) {
        List<ComicDTO> comicDTOList = new ArrayList<>();
        listIdFavComic.forEach(id -> {
            Optional<Comic> comic = comicRepository.findComicById(id);
            comicDTOList.add(new ComicDTO(comic.get()));
        });
        return new ResponseObject("Success", "Found comics successfully", comicDTOList);
    }

    @Override
    public ResponseObject browseComic(String idComic, String mode) {
        Optional<Comic> comic = comicRepository.findComicById(idComic);
        Mode comicMode = Mode.valueOf(mode);
        comic.get().setShareMode(comicMode);
        return new ResponseObject("Success", "Browse successfully", new ComicDTO(comicRepository.save(comic.get())));
    }

    @Override
    public ResponseObject hideComic(String idComic) {
        Optional<Comic> comic = comicRepository.findComicById(idComic);
        Mode comicMode = Mode.PRIVATE;
        comic.get().setShareMode(comicMode);
        return new ResponseObject("Success", "Hide successfully", new ComicDTO(comicRepository.save(comic.get())));
    }

    @Override
    public ResponseObject getAllComic() {
        List<Comic> comicList = comicRepository.findAll();
        List<ComicDTO> comicDTOList = new ArrayList<>();
        comicList.forEach(comic -> {
            comicDTOList.add(new ComicDTO(comic));
        });
        return new ResponseObject("Success", "Hide comic successfully", comicDTOList);
    }
}
