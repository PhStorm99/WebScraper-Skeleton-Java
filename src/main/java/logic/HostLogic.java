package logic;

import dao.HostDAO;
import entity.Host;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hnpav
 * @version 2.0
 * @since 2019-09-30
 */
public class HostLogic extends GenericLogic<Host, HostDAO> {

    // **************************************************
    // Constants
    // **************************************************
    /**
     * create static final variables with proper name of each column. this way
     * you will never manually type it again, instead always refer to these
     * variables.
     *
     * by using the same name as column id and html element names we can make
     * our code simpler. this is not recommended for proper production project.
     */
    public static final String ID = "id";
    public static final String NAME = "name";

    /**
     * Constructor
     */
    public HostLogic() {
        super(new HostDAO());
    }

    @Override
    public List<Host> getAll() {
        return get(() -> dao().findAll());
    }

    @Override
    public Host getWithId(int id) {
        return get(() -> dao().findById(id));
    }

    /**
     * Method getting the host name
     *
     * @param name
     * @return retuning the get method
     */
    public Host getHostWithName(String name) {
        return get(() -> dao().findByName(name));
    }

    @Override
    public List<Host> search(String search) {
        return get(() -> dao().findContaining(search));
    }

    @Override
    public Host createEntity(Map<String, String[]> parameterMap) {
        //never create another logic within another logic.
        //create a new Entity object
        Host host = new Host();

        if (parameterMap.containsKey(ID)) {
            //everything in the map is string so it must first be converted to 
            //appropriate type. have in mind also that values are stored in an
            //array of String, almost always the value is at index zero unless
            //you have used duplicated key/name somewhere.
            host.setId(Integer.parseInt(parameterMap.get(ID)[0]));
        }
        //have in mind also that values are stored in an
        //array of String, almost always the value is at index zero unless
        //you have used duplicated key/name somewhere.
        host.setName(parameterMap.get(NAME)[0]);

        return host;
    }

    /**
     * this method is used to send a list of all names to be used form table
     * column headers. by having all names in one location there is less chance
     * of mistakes.
     *
     * this list must be in the same order as getColumnCodes and
     * extractDataAsList
     *
     * @return list of all column display names.
     */
    @Override
    public List<String> getColumnNames() {
        return Arrays.asList("ID", "Name");
    }

    /**
     * this method returns a list of column names that match the official column
     * names in the db. by having all names in one location there is less chance
     * of mistakes.
     *
     * this list must be in the same order as getColumnNames and
     * extractDataAsList
     *
     * @return list of all column names in DB.
     */
    @Override
    public List<String> getColumnCodes() {
        return Arrays.asList(ID, NAME);
    }

    /**
     * return the list of values of all columns (variables) in given entity.
     *
     * this list must be in the same order as getColumnNames and getColumnCodes
     *
     * @param e - given Entity to extract data from.
     * @return list of extracted values
     */
    @Override
    public List<?> extractDataAsList(Host e) {
        return Arrays.asList(e.getId(), e.getName());
    }
}
