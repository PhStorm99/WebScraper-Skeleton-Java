/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import dao.FeedDAO;
import entity.Feed;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hnpav
 * @version 2.0
 * @since 2019-09-30
 */
public class FeedLogic extends GenericLogic<Feed, FeedDAO> {

    // **************************************************
    // Constants
    // **************************************************
    public static final String ID = "id";
    public static final String PATH = "path";
    public static final String TYPE = "type";
    public static final String NAME = "name";
    public static final String HOST_ID = "hostId";

    // Constructor
    public FeedLogic() {
        super(new FeedDAO());
    }

    @Override
    public List<Feed> getAll() {
        return get(() -> dao().findAll());
    }

    @Override
    public Feed getWithId(int id) {
        return get(() -> dao().findById(id));
    }

    /**
     * Parametrized method returning the path
     *
     * @param path
     * @return returning get method for a path
     */
    public Feed getFeedWithPath(String path) {
        return get(() -> dao().findByPath(path));
    }

    /**
     * Parametrized method returning the type
     *
     * @param type
     * @return returning the get method for type String
     */
    public List<Feed> getFeedsWithType(String type) {
        return get(() -> dao().findbyType(type));
    }

    /**
     * parametrized method returning the name
     *
     * @param name
     * @return returning the get method with name
     */
    public List<Feed> getFeedsWithName(String name) {
        return get(() -> dao().findbyName(name));
    }

    /**
     * host id parameterized variable
     *
     * @param hostId
     * @return returning the hostID
     */
    public List<Feed> getFeedsWithHostID(int hostId) {
        return get(() -> dao().findByHostId(hostId));
    }

    @Override
    public List<Feed> search(String search) {
        return get(() -> dao().findContaining(search));
    }

    @Override
    public Feed createEntity(Map<String, String[]> parameterMap) {
        Feed feed = new Feed();

        if (parameterMap.containsKey(ID)) {
            feed.setId(Integer.valueOf(parameterMap.get(ID)[0]));
        }
        if (parameterMap.containsKey(NAME) && parameterMap.get(NAME) != null) {
            String name = parameterMap.get(NAME)[0];

            if (name.isEmpty() || name.length() > 45) {
                throw new RuntimeException("INVALID");
            }
        }
        //Setting the Path, Type, Name to feed and getting it.
        feed.setPath(parameterMap.get(PATH)[0]);
        feed.setType(parameterMap.get(TYPE)[0]);
        feed.setName(parameterMap.get(NAME)[0]);

        return feed;
    }

    @Override
    public List<String> getColumnNames() {
        return Arrays.asList("id", "path", "String", "name", "hostId");
    }

    @Override
    public List<String> getColumnCodes() {
        return Arrays.asList(ID, PATH, TYPE, NAME, HOST_ID);
    }

    @Override
    public List<?> extractDataAsList(Feed e) {
        return Arrays.asList(e.getId(), e.getPath(), e.getType(), e.getName(), e.getHostid());
    }

}
