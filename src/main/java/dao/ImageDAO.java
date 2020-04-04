/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Image;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hnpav
 * @version 2.0
 * @since 2019-09-30
 */
public class ImageDAO extends GenericDAO<Image> {

    /**
     * Constructor ImageDAO calling super class
     */
    public ImageDAO() {

        super(Image.class);
    }

    /**
     * this is finding all the image list
     *
     * @return findAll
     */
    public List<Image> findAll() {

        return findResults("Image.findAll", null);
    }

    /**
     * this is find through id
     *
     * @param id
     * @return find through id
     */
    public Image findById(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return findResult("Image.findById", map);

    }

    /**
     * There is feed ID method
     *
     * @param feedid
     * @return feedid.
     */
    public List<Image> findByFeedId(int feedid) {
        Map<String, Object> map = new HashMap<>();
        map.put("feedid", feedid);
        return findResults("Image.findByFeedId", map);

    }

    /**
     * Finding by name method
     *
     * @param name
     * @return returning name
     */
    public List<Image> findbyName(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        return findResults("Image.findbyName", map);
    }

    /**
     * FindByPath method
     *
     * @param path
     * @return returning path
     */
    public Image findByPath(String path) {
        Map<String, Object> map = new HashMap<>();
        map.put("path", path);
        return findResult("Image.findByPath", map);
    }

    /**
     * Method through find byDate
     *
     * @param date
     * @return returning date
     */
    public List<Image> findbyDate(Date date) {
        Map<String, Object> map = new HashMap<>();
        map.put("date", date);
        return findResults("Image.findByDate", map);
    }

    /**
     * There's this method called findContaining through searching
     *
     * @param search
     * @return returning search map
     */
    public List<Image> findContaining(String search) {
        Map<String, Object> map = new HashMap<>();
        map.put("search", search);
        return findResults("Image.findContaining", map);

    }

}
