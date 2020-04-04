/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import dao.ImageDAO;
import entity.Image;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hnpav
 * @version 2.0
 * @since 2019-09-30
 */
public class ImageLogic extends GenericLogic<Image, ImageDAO> {

    // **************************************************
    // Constants
    // **************************************************
    public static final String ID = "id";
    public static final String PATH = "path";
    public static final String NAME = "name";
    public static final String DATE = "date";
    public static final String FEED_ID = "feedId";

    // Constructor
    public ImageLogic() {
        super(new ImageDAO());
    }

    @Override
    public List<Image> getAll() {
        return get(() -> dao().findAll());
    }

    @Override
    public Image getWithId(int id) {
        return get(() -> dao().findById(id));
    }

    /**
     * This method is getting for the FeedID
     *
     * @param feedId
     * @return returning the feedId
     */
    public List<Image> getImagesWithFeedId(int feedId) {
        return get(() -> dao().findByFeedId(feedId));
    }

    /**
     * this method is for getting image with the name
     *
     * @param name
     * @return retuning the name
     */
    public List<Image> getImagesWithName(String name) {
        return get(() -> dao().findbyName(name));
    }

    /**
     * this method is for getting an image by path
     *
     * @param path
     * @return returning the image by path
     */
    public Image getImageWithPath(String path) {
        return get(() -> dao().findByPath(path));
    }

    /**
     * This method is for the Images with the Date
     *
     * @param date
     * @return returning the date
     */
    public List<Image> getImagesWithDate(Date date) {
        return get(() -> dao().findbyDate(date));
    }

    @Override
    public List<Image> search(String search) {
        return get(() -> dao().findContaining(search));
    }

    @Override
    public Image createEntity(Map<String, String[]> parameterMap) {
        Image img = new Image();

        img.setPath(parameterMap.get(PATH)[0]);
        img.setName(parameterMap.get(NAME)[0]);
        img.setDate(new Date(Long.parseLong(parameterMap.get(DATE)[0])));

        return img;
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList("id", "path", "name", "feedId");
    }

    @Override
    public List<String> getColumnCodes() {
        return Arrays.asList(ID, PATH, NAME, FEED_ID);
    }

    @Override
    public List<?> extractDataAsList(Image e) {
        return Arrays.asList(e.getId(), e.getName(), e.getPath(), e.getFeedid());
    }

}
