package dao;

import entity.Host;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shariar Emami
 * @since Jan 14, 2019
 */
public class HostDAO extends GenericDAO<Host> {

    public HostDAO() {
        super(Host.class);
    }
    
    public List<Host> findAll(){
        //first argument is a name given to a named query defined in appropriate entity
        //second argument is map used for parameter substitution.
        //parameters are names starting with : in named queries, :[name]
        return findResults( "Host.findAll", null);
    }
    
    public Host findById( int id){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        //first argument is a name given to a named query defined in appropriate entity
        //second argument is map used for parameter substitution.
        //parameters are names starting with : in named queries, :[name]
        //in this case the parameter is named "id and value for it is saved in map
        return findResult( "Host.findById", map);
    }
    
    public Host findByName( String name){
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        return findResult( "Host.findByName", map);
    }
    
    public List<Host> findContaining (String search){
        Map<String, Object> map = new HashMap<>();
        map.put("search", search);
        return findResults( "Host.findContaining", map);
    }
}
