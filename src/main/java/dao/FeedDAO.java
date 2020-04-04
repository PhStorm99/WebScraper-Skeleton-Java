/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Feed;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hnpav
 * @version 2.0
 * @since 2019-09-30
 */
public class FeedDAO extends GenericDAO<Feed> {

    /**
     * Constructor FeedDAO calling super class
     */
    public FeedDAO() {
        super(Feed.class);
    }

    /**
     * this is findAll method
     *
     * @return findAll
     */
    public List<Feed> findAll() {
        return findResults("Feed.findAll", null);
    }

    /**
     * this is method which find feed through id
     *
     * @param id
     * @return findbyIId
     */
    public Feed findById(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return findResult("Feed.findById", map);
    }

    /**
     *
     * To find hostID, this method is used
     *
     * @param hostid
     * @return HostById
     */
    public List<Feed> findByHostId(int hostid) {
        Map<String, Object> map = new HashMap<>();
        map.put("hostid", hostid);
        return findResults("Feed.findByHostId", map);
    }

    /**
     * To find through path
     *
     * @param path
     * @return returning path
     */
    public Feed findByPath(String path) {
        Map<String, Object> map = new HashMap<>();
        map.put("path", path);
        return findResult("Feed.findByPath", map);
    }

    /**
     * To find through type
     *
     * @param type
     * @return returning type
     */
    public List<Feed> findbyType(String type) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        return findResults("Feed.findByType", map);
    }

    /**
     * to find through name
     *
     * @param name
     * @return returning name
     */
    public List<Feed> findbyName(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        return findResults("Feed.findByName", map);

    }

    /**
     * to find through search
     *
     * @param search
     * @return search
     */
    public List<Feed> findContaining(String search) {
        Map<String, Object> map = new HashMap<>();
        map.put("search", search);
        return findResults("Feed.findContaining", map);

    }

}
